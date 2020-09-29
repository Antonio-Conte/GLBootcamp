package ar.com.gl.shop.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ar.com.gl.shop.product.entity.Category;
import ar.com.gl.shop.product.repository.CategoryRepositoryJPA;
import ar.com.gl.shop.product.services.impl.CategoryServiceImpl;


public class CategoryServiceImplTest {

	@Mock
	private CategoryRepositoryJPA repository;
	@InjectMocks
	private CategoryServiceImpl categoryService;

	@BeforeEach
	public void setup() {
		repository = Mockito.mock(CategoryRepositoryJPA.class);
		categoryService = new CategoryServiceImpl(repository);
	}

	@Test
	@DisplayName("Retornar la lista completa")
	public void testSelectRetornaLaLista() {
		List<Category> listaNueva = new ArrayList<Category>();
		Category nueva = new Category(1, "Description 1", "Name 1", "1");
		Category nueva1 = new Category(2, "Description 2", "Name 2", "0");
		listaNueva.add(nueva);
		listaNueva.add(nueva1);

		when(repository.findAll()).thenReturn(listaNueva);

		List<Category> resultado = categoryService.select();
		assertNotNull(resultado);
		assertEquals(listaNueva.get(0), resultado.get(0));
		assertEquals(1, resultado.size());
	}

	@Test
	@DisplayName("Retornar la lista, Exception")
	void categoryServiceSelect_EntityNotFound_Test() {
		when(repository.getOne(Mockito.anyInt())).thenThrow(new EntityNotFoundException());
		
		Category response = categoryService.select(1234);
		assertNull(response);
	}

	@Test
	@DisplayName("Retornar un elemento de la lista")
	public void testSelectCategory() {
		Category category = new Category();
		List<Category> listaNueva = new ArrayList<Category>();
		Category nueva = new Category(1, "Description 1", "Name 1", "1");
		Category nueva1 = new Category(2, "Description 2", "Name 2", "1");
		listaNueva.add(nueva);
		listaNueva.add(nueva1);

		when(repository.findAll()).thenReturn(listaNueva);
		when(repository.getOne(listaNueva.get(0).getId())).thenReturn(listaNueva.get(0));

		category = listaNueva.get(0);

		Category categoryResponse = categoryService.select(listaNueva.get(0).getId());
		assertEquals(category.getDescription(), categoryResponse.getDescription());
		assertEquals(category.getId(), categoryResponse.getId());
		assertEquals(category.getName(), categoryResponse.getName());
	}

	@Test
	@DisplayName("Insertar un elemento en la lista")
	public void testInsertCategory() {
		int idCategoria = 50;
		Category categoryNueva = new Category(idCategoria, "Description nueva", "Category nueva", "1");
		List<Category> lista = new ArrayList<Category>();
		when(repository.findAll()).thenReturn(lista);
		when(repository.save(categoryNueva)).thenReturn(agregarALista(lista, categoryNueva));
		when(repository.getOne(idCategoria)).thenReturn(lista.get(0));

		categoryService.insert(categoryNueva);

		Category categoryResponse = categoryService.select(idCategoria);

		assertEquals(categoryNueva.getDescription(), categoryResponse.getDescription());
		assertEquals(categoryNueva.getName(), categoryResponse.getName());
		assertEquals(categoryNueva.getId(), categoryResponse.getId());

	}

	private Category agregarALista(List<Category> lista, Category nueva) {
		lista.add(nueva);
		return nueva;
	}

	@Test
	@DisplayName("Actualizar un elemento en la lista")
	public void testUpdateCategory() {
		Category categoryAct = new Category(50, "Description nueva", "Category nueva", "1");
		Category category = new Category(50, "Description", "NAME", "1");
		List<Category> lista = new ArrayList<Category>();

		lista.add(category);

		when(repository.findAll()).thenReturn(lista);
		when(repository.save(categoryAct)).thenAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock a) {
				lista.remove(category);
				lista.add(categoryAct);
				return null;
			}
		});
		when(repository.getOne(categoryAct.getId())).thenReturn(lista.get(0));

		Category categoryResponse = categoryService.update(category.getId(), categoryAct);
		assertEquals(categoryAct.getDescription(), categoryResponse.getDescription());
		assertEquals(categoryAct.getName(), categoryResponse.getName());
		assertEquals(categoryAct.getId(), categoryResponse.getId());
	}

	@Test
	@DisplayName("Borrar un elemento de la lista")
	public void testDeleteCategory() {
		Category category = new Category(50, "Description nueva", "Category nueva", "1");
		List<Category> lista = new ArrayList<Category>();

		lista.add(category);

		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock a) throws Throwable {
				lista.remove(category);
				return null;

			}
		}).when(repository).deleteById(category.getId());

		categoryService.delete(category.getId());

		List<Category> categoryResponse = categoryService.select();

		assertTrue(categoryResponse.isEmpty());

	}
	
	@Test
	@DisplayName("Insertar elemento, IllegalArgumentException")
	void categoryServiceInsert_IllegalArgumentException_Test()
	{
		Category category = new Category(1, "", "", "1");
		when(repository.save(Mockito.any(Category.class))).thenThrow(new IllegalArgumentException());
		doThrow(new IllegalArgumentException()).when(repository).deleteById(Mockito.anyInt());
		
		categoryService.insert(category);
		categoryService.update(category.getId(), category);
		categoryService.delete(category.getId());
		
		assertTrue(Boolean.TRUE);
	}

}
