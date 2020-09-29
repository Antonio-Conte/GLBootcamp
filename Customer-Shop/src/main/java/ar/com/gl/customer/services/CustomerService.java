package ar.com.gl.customer.services;

import org.springframework.stereotype.Service;

import ar.com.gl.customer.in.CustomerInResponseDTO;
import ar.com.gl.customer.model.CustomerDTO;



@Service
public interface CustomerService{
	
	public CustomerInResponseDTO getAllCustomer();
	public CustomerDTO getCustomerById(Integer id);
	public CustomerDTO addCustomer(CustomerDTO customer);
	public CustomerDTO updateCustomer(CustomerDTO customer);
	public CustomerDTO updateStatus(String status, Integer id);
	public Boolean deleteCustomer(Integer id);
	
}
