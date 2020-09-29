package ar.com.gl.customer.in;

import java.util.ArrayList;
import java.util.List;

import ar.com.gl.customer.model.CustomerDTO;


public class CustomerInResponseDTO {
	
	private List<CustomerDTO> customers = new ArrayList<>();

	public CustomerInResponseDTO() {
		
	}
	
	public CustomerInResponseDTO(List<CustomerDTO> customers) {
		this.setCustomers(customers);
	}
	
	public List<CustomerDTO> getCustomers()
	{
		return this.customers;
	}
	
	public void setCustomers(List<CustomerDTO> customers)
	{
		this.customers.clear();
		this.customers.addAll(customers);
	}

	
}
