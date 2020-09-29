package services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ar.com.gl.customer.in.CustomerInResponseDTO;
import ar.com.gl.customer.model.CustomerDTO;
import ar.com.gl.customer.repository.CustomerRepository;
import ar.com.gl.customer.services.impl.CustomerServiceImpl;



@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

	@Mock
	private CustomerRepository customerRepository;

	@InjectMocks
	private CustomerServiceImpl customerService;

	private List<CustomerDTO> listTest;
	private CustomerDTO customer;
	private Optional<CustomerDTO> optional;

	@BeforeEach
	void setUp() {
		listTest = new ArrayList<>();
		customer = new CustomerDTO(1, "Apellido", "Nombre", "1", null);
	}

	@Test
	@DisplayName("Consultar todos los clientes")
	void testGetAllCustomer() {
		listTest.add(customer);

		when(customerRepository.findAll()).thenReturn(listTest);

		CustomerInResponseDTO response = customerService.getAllCustomer();

		assertNotNull(response);
		assertEquals(response.getCustomers().size(), listTest.size());
		assertTrue(response.getCustomers().equals(listTest));
	}

	@Test
	@DisplayName("Consultar cliente por Id")
	void testGetCustomerById() {
		optional = Optional.of(customer);
		when(customerRepository.findById(Mockito.anyInt())).thenReturn(optional);

		CustomerDTO response = customerService.getCustomerById(1234);

		assertNotNull(response);
		assertTrue(compararCustomerDTO(customer, response));
	}

	@Test
	void testAddCustomer() {
		when(customerRepository.existsById(Mockito.anyInt())).thenReturn(Boolean.FALSE);
		when(customerRepository.save(Mockito.any(CustomerDTO.class))).thenReturn(customer);

		CustomerDTO response = customerService.addCustomer(customer);

		assertNotNull(response);
		assertTrue(compararCustomerDTO(customer, response));
	}

	@Test
	void testUpdateCustomer() {
		when(customerRepository.existsById(Mockito.anyInt())).thenReturn(Boolean.TRUE);
		when(customerRepository.save(Mockito.any(CustomerDTO.class))).thenReturn(customer);
		
		customer.setLastName("Nuevo");
		customer.setName("Nuevo");
		
		CustomerDTO response = customerService.updateCustomer(customer);
		
		assertNotNull(response);
		assertTrue(compararCustomerDTO(customer, response));
	}

	@Test
	void testUpdateStatus() {
		optional = Optional.of(customer);
		when(customerRepository.findById(Mockito.anyInt())).thenReturn(optional);
		when(customerRepository.save(Mockito.any(CustomerDTO.class))).thenReturn(customer);
		
		customer.setStatus("1");
		
		CustomerDTO response = customerService.updateStatus("0", customer.getId());
		
		assertNotNull(response);
		assertTrue(compararCustomerDTO(customer, response));
	}

	@Test
	void testDelateCustomer() {
		listTest.add(customer);
		when(customerRepository.existsById(Mockito.anyInt())).thenReturn(Boolean.TRUE);
		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock a)
			{
				listTest.remove(customer);
				return null;
			}
		}).when(customerRepository).deleteById(Mockito.anyInt());
		
		boolean response = customerService.deleteCustomer(customer.getId());
		
		assertTrue(response);
	}

	private boolean compararCustomerDTO(CustomerDTO request, CustomerDTO response) {
		return request.getId() == response.getId() && request.getLastName().equals(response.getLastName())
				&& request.getName() == response.getName() && request.getStatus() == response.getStatus();
	}
}
