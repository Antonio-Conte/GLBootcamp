package ar.com.gl.shop.product.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ar.com.gl.shop.product.dto.ProductDTO;
import ar.com.gl.shop.product.entity.Category;
import ar.com.gl.shop.product.entity.Product;
import ar.com.gl.shop.product.entity.Stock;
import ar.com.gl.shop.product.error.exceptions.ApiException;
import ar.com.gl.shop.product.services.ProductService;
import ar.com.gl.shop.product.services.impl.ProductServiceImpl;

public class ProductControllerTest {

	@Mock
	private ProductService productService;

	@InjectMocks
	private ProductController controller;

	@BeforeEach
	void setup() {
		productService = Mockito.mock(ProductServiceImpl.class);
		controller = new ProductController(productService);
	}

	@Test
	@DisplayName("Get all products")
	void productControllerGetAllProducts()
	{
		when(productService.select()).thenReturn(new ArrayList<>());
		
		List<ProductDTO> response = controller.retrieveAllProducts();
		
		assertNotNull(response);
		assertTrue(response.isEmpty());
	}
	
	@Test
	@DisplayName("Get product by id")
	void productControllerGetProductById() {
		Product product = new Product();
		when(productService.select(Mockito.anyInt())).thenReturn(product);

		try {
			ProductDTO response = controller.retrieveProductById(1);
			assertNotNull(response);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("Get product by name")
	void productControllerGetProductByName() {
		String nombre = "Nombre a buscar";
		Product product = new Product(1, nombre, "", 200, "1", new Stock(1, 200, "", "1"),
				new Category(1, "", "", "1"));
		List<Product> listTest = new ArrayList<Product>();
		listTest.add(product);

		when(productService.select()).thenReturn(listTest);

		ProductDTO response;
		try {
			response = controller.retrieveProductByNameOrCategory(nombre, 1234);
			assertNotNull(response);
			assertTrue(productDTOEqualsToProduct(response, product));
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	@DisplayName("Get product by category id")
	void productControllerGetProductByCategoryId() {
		int id = 1;
		Product product = new Product(1, "nombre", "", 200, "1", new Stock(1, 200, "", "1"),
				new Category(id, "", "", "1"));
		List<Product> listTest = new ArrayList<Product>();
		listTest.add(product);

		when(productService.select()).thenReturn(listTest);

		ProductDTO response;
		try {
			response = controller.retrieveProductByNameOrCategory("", id);
			assertNotNull(response);
			assertTrue(productDTOEqualsToProduct(response, product));
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	@DisplayName("Create product")
	void productControllerGetProductByIdTest() {
		Product product = new Product(1, "Name", "Description", 200, "1", new Stock(1, 200, "", "1"),
				new Category(1, "", "", "1"));
		ProductDTO request = productToDTO(product);
		ProductDTO response = new ProductDTO();
		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock a) {
				ProductDTO temp = productToDTO(product);
				response.setId(temp.getId());
				response.setName(temp.getName());
				response.setDescrition(temp.getDescription());
				response.setCategory(temp.getCategory());
				response.setStock(temp.getStock());
				response.setStatus(temp.getStatus());
				return null;
			}
		}).when(productService).insert(Mockito.any(Product.class));

		controller.createProduct(request);
		assertNotNull(response);
		assertTrue(productDTOEqualsToProduct(response, product));

	}

	@Test
	@DisplayName("Update product")
	void productControllerUpdateProductTest() {
		Product product = new Product(1, "name", "Description", 200, "1", new Stock(1, 200, "", "1"),
				new Category(1, "", "", "1"));
		when(productService.update(Mockito.anyInt(), Mockito.any(Product.class))).thenReturn(product);

		ProductDTO response = controller.updateProduct(productToDTO(product));
		assertNotNull(response);
		assertTrue(productDTOEqualsToProduct(response, product));

	}

	@Test
	@DisplayName("Delete product")
	void productControllerDeleteProductTest() {
		Product product = new Product(1, "", "", 200, "1", new Stock(1, 200, "", "1"), new Category(1, "", "", "1"));
		List<Product> listTest = new ArrayList<Product>();
		listTest.add(product);
		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock a) {
				listTest.remove(product);
				return null;
			}
		}).when(productService).delete(1);
		when(productService.select(1)).thenReturn(product);

		controller.deleteProduct(1);

		assertTrue(listTest.isEmpty());
	}

	private ProductDTO productToDTO(Product product) {
		ProductDTO response = new ProductDTO();
		response.setId(product.getId());
		response.setName(product.getName());
		response.setDescrition(product.getDescription());
		response.setStock(product.getStock());
		response.setCategory(product.getCategory());
		response.setStatus(product.getStatus());
		return response;
	}

	private boolean productDTOEqualsToProduct(ProductDTO dto, Product product) {
		return dto.getId() == product.getId() && dto.getName().equals(product.getName())
				&& dto.getCategory().getId() == product.getCategory().getId()
				&& dto.getStock().getId() == product.getStock().getId() && dto.getStatus().equals(product.getStatus())
				&& dto.getDescription().equals(product.getDescription());
	}
}
