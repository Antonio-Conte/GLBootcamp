package services;

import in.CustomerInResponseDTO;
import model.CustomerDTO;

public interface CustomerService{
	
	public CustomerInResponseDTO getAllCustomer();
	public CustomerDTO getCustomerById(Integer id);
	public CustomerDTO addCustomer(CustomerDTO customer);
	public CustomerDTO updateCustomer(CustomerDTO customer);
	public CustomerDTO updateStatus(String status, Integer id);
	public Boolean deleteCustomer(Integer id);
	
}
