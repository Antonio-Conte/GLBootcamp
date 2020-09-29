package ar.com.gl.shop.product;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import ar.com.gl.shop.product.entity.Category;
import ar.com.gl.shop.product.entity.Product;
import ar.com.gl.shop.product.entity.Stock;
import ar.com.gl.shop.product.repository.CategoryRepositoryJPA;
import ar.com.gl.shop.product.repository.ProductRepositoryJPA;
import ar.com.gl.shop.product.repository.StockRepositoryJPA;
import ar.com.gl.shop.product.services.impl.ProductServiceImpl;

public class ProductServiceImplTest {

	@Mock
	private ProductRepositoryJPA repositoryProdJPA;
	private CategoryRepositoryJPA repositoryCatJPA;
	private StockRepositoryJPA repositoryStoJPA;

	private ProductServiceImpl productService;

	@BeforeEach
	public void test() {

		repositoryProdJPA = Mockito.mock(ProductRepositoryJPA.class);
		repositoryCatJPA = Mockito.mock(CategoryRepositoryJPA.class);
		repositoryStoJPA = Mockito.mock(StockRepositoryJPA.class);

		productService = new ProductServiceImpl(repositoryProdJPA, repositoryStoJPA, repositoryCatJPA);

	}

	@Test
	@DisplayName("Consulta a toda la lista de registros")
	public void testSelect() {
		List<Product> listaProductos = new ArrayList<Product>();
		Product nuevo1 = new Product(1, "Nombre 1", "Descripcion 1", 1, "1", new Stock(1, 200, "ASD123", "1"),
				new Category(1, "Desc", "Name", "1"));
		Product nuevo2 = new Product(2, "Nombre 2", "Descripcion 2", 2, "1", new Stock(1, 200, "ASD123", "1"),
				new Category(1, "Desc", "Name", "1"));
		listaProductos.add(nuevo1);
		listaProductos.add(nuevo2);

		when(repositoryProdJPA.findAll()).thenReturn(listaProductos);

		List<Product> productResponse = productService.select();

		assertFalse(productResponse.isEmpty());
		assertEquals(listaProductos.size(), productResponse.size());
		assertEquals(listaProductos.get(0), productResponse.get(0));
		assertEquals(listaProductos.get(1), productResponse.get(1));
	}
	

	@Test
	@DisplayName("Consulta a un registro de la lista por ID")
	public void testSelectProduct() {
		List<Product> listaProductos = new ArrayList<Product>();
		Product nuevo1 = new Product(1, "Nombre 1", "Descripcion 1", 1, "1", new Stock(1, 200, "ASD123", "1"),
				new Category(1, "Desc", "Name", "1"));

		listaProductos.add(nuevo1);

		when(repositoryProdJPA.findAll()).thenReturn(listaProductos);
		when(repositoryProdJPA.getOne(nuevo1.getId())).thenReturn(listaProductos.get(0));

		Product productResponse = productService.select(nuevo1.getId());

		assertEquals(nuevo1.getCategory(), productResponse.getCategory());
		assertEquals(nuevo1.getDescription(), productResponse.getDescription());
		assertEquals(nuevo1.getId(), productResponse.getId());
		assertEquals(nuevo1.getName(), productResponse.getName());
		assertEquals(nuevo1.getPrice(), productResponse.getPrice());
		assertEquals(nuevo1.getStatus(), productResponse.getStatus());
		assertEquals(nuevo1.getStock(), productResponse.getStock());
	}

	@Test

	@DisplayName("Insertar un registro en la lista")
	public void testInsert() {

		List<Product> listaProductos = new ArrayList<Product>();
		Product nuevo1 = new Product(1, "Nombre 1", "Descripcion 1", 1, "1", new Stock(1, 200, "ASD123", "1"),
				new Category(1, "Desc", "Name", "1"));
		listaProductos.add(nuevo1);
		when(repositoryProdJPA.findAll()).thenReturn(listaProductos);
		when(repositoryProdJPA.save(nuevo1)).thenReturn(nuevo1);
		when(repositoryProdJPA.getOne(nuevo1.getId())).thenReturn(listaProductos.get(0));
		when(repositoryCatJPA.existsById(Mockito.anyInt())).thenReturn(Boolean.TRUE);

		productService.insert(nuevo1);

		Product productResponse = productService.select(nuevo1.getId());

		assertEquals(nuevo1.getCategory(), productResponse.getCategory());
		assertEquals(nuevo1.getDescription(), productResponse.getDescription());
		assertEquals(nuevo1.getId(), productResponse.getId());
		assertEquals(nuevo1.getName(), productResponse.getName());
		assertEquals(nuevo1.getPrice(), productResponse.getPrice());
		assertEquals(nuevo1.getStatus(), productResponse.getStatus());
		assertEquals(nuevo1.getStock(), productResponse.getStock());
	}

	@Test
	@DisplayName("Actualizar un registro de la lista")
	public void testUpdate() {
		List<Product> listaProductos = new ArrayList<Product>();
		Product nuevo1 = new Product(1, "Nombre 1", "Descripcion 1", 1, "1", new Stock(1, 200, "ASD123", "1"),
				new Category(1, "Desc", "Name", "1"));
		Product modificado = nuevo1;
		modificado.setName("Nuevo nombre");
		modificado.setPrice(20);
		listaProductos.add(modificado);

		when(repositoryProdJPA.findAll()).thenReturn(listaProductos);
		when(repositoryProdJPA.save(modificado)).thenReturn(modificado);
		when(repositoryProdJPA.getOne(nuevo1.getId())).thenReturn(listaProductos.get(0));

		Product productResponse = productService.update(modificado.getId(), modificado);

		assertEquals(modificado.getId(), productResponse.getId());

	}

	@Test
	@DisplayName("Borrar un registro de manera fisica de la lista")
	public void testDelete() {
		ArrayList<Product> listaProductos = new ArrayList<Product>();
		Product nuevo1 = new Product(1, "Nombre 1", "Descripcion 1", 1, "1", new Stock(1, 200, "ASD123", "1"),
				new Category(1, "Desc", "Name", "1"));

		listaProductos.add(nuevo1);

		when(repositoryProdJPA.findAll()).thenReturn(listaProductos);

		productService.delete(nuevo1.getId());

		ArrayList<Product> productResponse = new ArrayList<Product>();

		assertTrue(productResponse.isEmpty());
	}

	@Test
	@DisplayName("Borrar un registro de manera logica de la lista")
	public void testDeleteLogico() {

		ArrayList<Product> listaProductos = new ArrayList<Product>();
		Product nuevo1 = new Product(1, "Nombre 1", "Descripcion 1", 1, "1", new Stock(1, 200, "ASD123", "1"),
				new Category(1, "Desc", "Name", "1"));

		listaProductos.add(nuevo1);
		listaProductos.get(0).setStatus("0");

		when(repositoryProdJPA.getOne(nuevo1.getId())).thenReturn(listaProductos.get(0));

		productService.deleteLogic(nuevo1.getId());

		assertEquals("0",listaProductos.get(0).getStatus());

	}

	@Test
	@DisplayName("Insertar producto, cuando la category no existe")
	void productServiceInsertProduct_CategoryNotFound_Test()
	{
		Product product = new Product(1, "Nombre 1", "Descripcion 1", 1, "1", new Stock(1, 200, "ASD123", "1"),new Category(1, "Desc", "Name", "1"));
		when(repositoryCatJPA.existsById(Mockito.anyInt())).thenReturn(Boolean.FALSE);
		
		productService.insert(product);
		
		assertTrue(Boolean.TRUE);
		
	}
	
	@Test
	@DisplayName("InsertarProducto, cuando el stock no existe")
	void productServiceInserProduct_StockNotFound_Test()
	{
		Product product = new Product(1, "Nombre 1", "Descripcion 1", 1, "1", new Stock(1, 200, "ASD123", "1"),new Category(1, "Desc", "Name", "1"));
		when(repositoryCatJPA.existsById(Mockito.anyInt())).thenReturn(Boolean.TRUE);
		when(repositoryStoJPA.existsById(Mockito.anyInt())).thenReturn(Boolean.FALSE);
		
		productService.insert(product);
		
		assertTrue(Boolean.TRUE);
	}
	@Test
	@DisplayName("Insertar producto, cuando el id producto ya existe")
	void productServiceInsertProduct_ProductExiste_Test()
	{
		Product product = new Product(1, "Nombre 1", "Descripcion 1", 1, "1", new Stock(1, 200, "ASD123", "1"),new Category(1, "Desc", "Name", "1"));
		when(repositoryCatJPA.existsById(Mockito.anyInt())).thenReturn(Boolean.TRUE);
		when(repositoryStoJPA.existsById(Mockito.anyInt())).thenReturn(Boolean.TRUE);
		when(repositoryProdJPA.existsById(Mockito.anyInt())).thenReturn(Boolean.TRUE);
		
		productService.insert(product);
		
		assertTrue(Boolean.TRUE);
		
	}
	
	
	@Test
	@DisplayName("Actualizar producto, cuando la category no existe")
	void productServiceUpdateProduct_CategoryNotFound_Test()
	{
		Product product = new Product(1, "Nombre 1", "Descripcion 1", 1, "1", new Stock(1, 200, "ASD123", "1"),new Category(1, "Desc", "Name", "1"));
		when(repositoryCatJPA.existsById(Mockito.anyInt())).thenReturn(Boolean.FALSE);
		
		productService.update(product.getId(), product);
		
		assertTrue(Boolean.TRUE);
		
	}
	
	@Test
	@DisplayName("Actualizar, cuando el stock no existe")
	void productServiceUpdate_StockNotFound_Test()
	{
		Product product = new Product(1, "Nombre 1", "Descripcion 1", 1, "1", new Stock(1, 200, "ASD123", "1"),new Category(1, "Desc", "Name", "1"));
		when(repositoryCatJPA.existsById(Mockito.anyInt())).thenReturn(Boolean.TRUE);
		when(repositoryStoJPA.existsById(Mockito.anyInt())).thenReturn(Boolean.FALSE);
		
		productService.update(product.getId(), product);
		
		assertTrue(Boolean.TRUE);
	}
	@Test
	@DisplayName("Actualizar producto, general exception")
	void productServiceUpdateProduct_GeneralException_Test()
	{
		Product product = new Product(1, "Nombre 1", "Descripcion 1", 1, "1", new Stock(1, 200, "ASD123", "1"),new Category(1, "Desc", "Name", "1"));
		
		when(repositoryCatJPA.existsById(Mockito.anyInt())).thenReturn(Boolean.TRUE);
		when(repositoryStoJPA.existsById(Mockito.anyInt())).thenReturn(Boolean.TRUE);
		when(repositoryProdJPA.existsById(Mockito.anyInt())).thenReturn(Boolean.FALSE);
		
		productService.update(product.getId(), product);
		
		assertTrue(Boolean.TRUE);
		
	}

	
}
