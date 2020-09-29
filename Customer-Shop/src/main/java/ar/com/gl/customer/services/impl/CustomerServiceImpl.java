package ar.com.gl.customer.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import ar.com.gl.customer.in.CustomerInResponseDTO;
import ar.com.gl.customer.model.CustomerDTO;
import ar.com.gl.customer.repository.CustomerRepository;
import ar.com.gl.customer.services.CustomerService;



@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public CustomerInResponseDTO getAllCustomer() {
	
		CustomerInResponseDTO response = new CustomerInResponseDTO();
		List<CustomerDTO> request = new ArrayList<>();
		
		for(CustomerDTO customer : customerRepository.findAll())
		{
			if(customer!=null)
			request.add(customer);
		}
		
		response.setCustomers(request);

		return response;
	}

	public CustomerDTO getCustomerById(Integer id) {
		Optional<CustomerDTO> response = null ;
		 response = customerRepository.findById(id);
				 
			
		 
		return response.isPresent() ? response.get() : null;
			
		
	}

	public CustomerDTO addCustomer(CustomerDTO customer) {
		boolean request =true;
		try 
		{
		 request = customerRepository.existsById(customer.getId());
		}
		catch(Exception e) {
			
		}
		
		
		try
		{
			
			
		return (!request) ? customerRepository.save(customer) : null;
		}
		catch(Exception e) {
			
		}
		return customer;
	
	}

	public CustomerDTO updateCustomer(CustomerDTO customer) {
		boolean request=true;
		
		try {
			 request = customerRepository.existsById(customer.getId());
		}
		catch(Exception e) {
			
		}
		try {
			
		
		
		
		return (request) ? customerRepository.save(customer) : null;
		}
		catch(Exception e) {
		
		}
		
		return customer;
	}

	public CustomerDTO updateStatus(String status, Integer id) {
		Optional<CustomerDTO> customer = customerRepository.findById(id);
		CustomerDTO request = customer.isPresent() ? customer.get() : null;
		if (request != null)
		{
			request.setStatus(status);
			try {
			return customerRepository.save(request);
			}
			catch(Exception e) {
			
			}
		}
		return null;
	}

	public Boolean deleteCustomer(Integer id) {
		boolean request = customerRepository.existsById(id);
		if (request)
		{
			try {
				customerRepository.deleteById(id);
				return true;
			}
			catch(Exception e) {
				
			}
			
		}
		return false;
	}

}
