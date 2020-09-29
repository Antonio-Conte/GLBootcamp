package services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.CustomerInResponseDTO;
import model.CustomerDTO;
import repository.CustomerRepository;
import services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public CustomerInResponseDTO getAllCustomer() {
		CustomerInResponseDTO response = new CustomerInResponseDTO();
		List<CustomerDTO> request = new ArrayList<>();
		
		for(CustomerDTO customer : customerRepository.findAll())
		{
			request.add(customer);
		}
		
		response.setCustomers(request);

		return response;
	}

	public CustomerDTO getCustomerById(Integer id) {
		Optional<CustomerDTO> response = customerRepository.findById(id);
		
		return response.isPresent() ? response.get() : null;
	}

	public CustomerDTO addCustomer(CustomerDTO customer) {
		
		boolean request = customerRepository.existsById(customer.getId());
		
		return (!request) ? customerRepository.save(customer) : null;
	}

	public CustomerDTO updateCustomer(CustomerDTO customer) {
		
		boolean request = customerRepository.existsById(customer.getId());
		
		return (request) ? customerRepository.save(customer) : null;
	}

	public CustomerDTO updateStatus(String status, Integer id) {
		Optional<CustomerDTO> customer = customerRepository.findById(id);
		CustomerDTO request = customer.isPresent() ? customer.get() : null;
		if (request != null)
		{
			request.setStatus(status);
			return customerRepository.save(request);
		}
		return null;
	}

	public Boolean deleteCustomer(Integer id) {
		boolean request = customerRepository.existsById(id);
		if (request)
		{
			customerRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
