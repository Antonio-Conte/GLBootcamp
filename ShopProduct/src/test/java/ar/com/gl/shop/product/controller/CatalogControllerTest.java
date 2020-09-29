package ar.com.gl.shop.product.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ar.com.gl.shop.product.dto.CatalogDTO;
import ar.com.gl.shop.product.entity.Category;
import ar.com.gl.shop.product.error.exceptions.ApiException;
import ar.com.gl.shop.product.services.CategoryService;
import ar.com.gl.shop.product.services.impl.CategoryServiceImpl;


public class CatalogControllerTest {

	@Mock
	private CategoryService service;

	@InjectMocks
	private CatalogController controller;

	@BeforeEach
	void setup() {
		service = Mockito.mock(CategoryServiceImpl.class);
		controller = new CatalogController(service);
	}

	@Test
	@DisplayName("Get all categories")
	void catalogControllerRetrieveCategoriesTest() {
		List<Category> listTest = new ArrayList<Category>();
		Category category = new Category(1, "Description 1", "Name 1", "1");
		listTest.add(category);
		when(service.select()).thenReturn(listTest);

		try {
			List<CatalogDTO> response = controller.retrieveCategories();
			assertNotNull(response);
			assertEquals(response.size(), listTest.size());
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	@DisplayName("Get all categories, Empty")
	void catalogControllerRetrieveCategories_WithEmptyList_Test()
	{
		when(service.select()).thenReturn(new ArrayList<Category>());
		
		assertThatThrownBy(() ->{
			controller.retrieveCategories();
		}).isInstanceOf(ApiException.class);
	}

	@ParameterizedTest(name = "Get category by id")
	@ValueSource(ints = { 1, 1234 })
	void catalogControllerRetrieveCategoryByIdTest(int id) {
		Category category = new Category(1, "Description 1", "Name 1", "1");

		when(service.select(id)).thenReturn(category);

		try {
			CatalogDTO response = controller.retrieveCategoryById(id);

			assertNotNull(response);
			assertTrue(catalogDTOEqualsToCategory(response, category));
		} catch (Exception e) {
			assertThat(e).isInstanceOf(ApiException.class);
		}
	}

	@ParameterizedTest(name = "Get category by name")
	@ValueSource(strings = { "Name", "NotFound" })
	void catalogControllerRetrieveCategoryByName(String testName) {
		String name = "Name";
		Category category = new Category(1, "Description", name, "1");
		List<Category> listTest = new ArrayList<Category>();

		listTest.add(category);

		when(service.select()).thenReturn(listTest);

		try {
			CatalogDTO response = controller.retrieveCategoryByName(name);
			assertNotNull(response);
			assertTrue(catalogDTOEqualsToCategory(response, category));
		} catch (Exception e) {
			assertThat(e).isInstanceOf(ApiException.class);
		}
	}

	@Test
	@DisplayName("Create category")
	void catalogControllerCreateCategoryTest() {
		Category category = new Category(1, "Description", "Name", "1");
		CatalogDTO request = new CatalogDTO(category.getId(), category.getName(), category.getDescription(), category.getStatus());
		List<Category> listTest = new ArrayList<Category>();
		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock a) {
				listTest.add(category);
				return null;
			}
		}).when(service).insert(Mockito.any(Category.class));
		
		try {
			controller.createCategory(request);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
		
		Category response = listTest.get(0);
		
		assertNotNull(response);
		assertTrue(catalogDTOEqualsToCategory(request, response));
	}
	
	@Test
	@DisplayName("Create category, ID repeated")
	void catalogControllerCreateTategory_WithRepeatedId_Test()
	{
		CatalogDTO request = new CatalogDTO(1, "Name", "Description", "1");
		when(service.select(Mockito.anyInt())).thenReturn(new Category());
		
		assertThatThrownBy(() -> {
			controller.createCategory(request);
		}).isInstanceOf(ApiException.class);
	}
	
	@Test
	@DisplayName("Update category")
	void catalogControllerUpdateCategoryTest()
	{
		Category category = new Category(1, "Description", "Name", "1");
		CatalogDTO request = new CatalogDTO(category.getId(), category.getName(), category.getDescription(), category.getStatus());
		when(service.update(1, catalogToCategory(request))).thenReturn(category);
	
		try {
			CatalogDTO response = controller.updateCategory(1, request);
			assertNotNull(response);
			assertTrue(catalogDTOEqualsToCategory(response, category));
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Update category, ID not found")
	void catalogControllerUpdateCategory_WhenIdNotFound_Test()
	{
		when(service.select(Mockito.anyInt())).thenReturn(null);
		assertThatThrownBy(() -> {
			controller.updateCategory(1234, new CatalogDTO());
		}).isInstanceOf(ApiException.class);
	}
	
	@Test
	@DisplayName("Disable category")
	void catalogControllerDisableCategoryTest()
	{
		Category category = new Category(1, "Description", "Name", "1");
		when(service.disableCategory(1)).thenReturn(category);
			
		try {
			CatalogDTO response = controller.disableCategory(1);
			category.setStatus("0");
			assertNotNull(response);
			assertTrue(response.getStatus().equals("0"));
			assertTrue(catalogDTOEqualsToCategory(response, category));
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	@DisplayName("Disable category, ID not found")
	void catalogControllerDisableCategory_IdNotFound_Test()
	{
		when(service.select(Mockito.anyInt())).thenReturn(null);
		
		assertThatThrownBy(() -> {
			controller.disableCategory(1234);
		}).isInstanceOf(ApiException.class);
	}

	private boolean catalogDTOEqualsToCategory(CatalogDTO catalog, Category category) {
		return catalog.getId() == category.getId() && catalog.getDescription() == category.getDescription()
				&& catalog.getName() == category.getName() && catalog.getStatus() == category.getStatus();
	}

	private Category catalogToCategory(CatalogDTO catalog)
	{
		return new Category(catalog.getId(), catalog.getDescription(), catalog.getName(), catalog.getStatus());
	}
}
