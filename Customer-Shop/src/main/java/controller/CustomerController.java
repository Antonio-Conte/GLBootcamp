package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.CustomerInResponseDTO;
import model.CustomerDTO;
import services.CustomerService;

@RestController
@RequestMapping("/customer")
@ComponentScan(basePackageClasses = CustomerService.class)

public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	public CustomerController() {

	}

	public CustomerController(CustomerService customerService) 
	{
		this.customerService=customerService;
	}
	
	@GetMapping
	public ResponseEntity<CustomerInResponseDTO> getAllCustomer() 
	{
		return new ResponseEntity<>(customerService.getAllCustomer(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable(name = "id")final Integer id)
	{
		return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<CustomerDTO> addCustomer(@RequestBody CustomerDTO customer)
	{
		return new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.OK);
				
	}
	
	@PutMapping
	public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerDTO customer)
	{
		
		return new ResponseEntity<>(customerService.updateCustomer(customer), HttpStatus.OK);

	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable(name = "id ")Integer id)
	{
	if(customerService.deleteCustomer(id))
		{
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);		
	}
	
	@PatchMapping(path = "/{id}")
	public ResponseEntity<CustomerDTO> updateStatus(@RequestBody String status, @PathVariable(name = "id") Integer id)
	{
		return new ResponseEntity<>(customerService.updateStatus(status,id), HttpStatus.OK);
	}

}
