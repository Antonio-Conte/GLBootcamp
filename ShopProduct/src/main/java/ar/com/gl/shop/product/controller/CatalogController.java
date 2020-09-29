
package ar.com.gl.shop.product.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.gl.shop.product.dto.CatalogDTO;
import ar.com.gl.shop.product.entity.Category;
import ar.com.gl.shop.product.error.exceptions.ApiException;
import ar.com.gl.shop.product.error.exceptions.CategoryNotFoundException;
import ar.com.gl.shop.product.services.CategoryService;

@RestController
@RequestMapping("/catalog")

public class CatalogController {

	@Autowired
	private CategoryService categoryService;
	
	public CatalogController()
	{
		
	}
	
	public CatalogController(CategoryService categoryService)
	{
		this.categoryService = categoryService;
	}
	
	@GetMapping
	public List<CatalogDTO> retrieveCategories() throws ApiException
	{
		List<CatalogDTO> response = categoryListToCatalogDTOList(categoryService.select());
		
		if(response.isEmpty())
			throw new CategoryNotFoundException();
		
		return response;
	}
	
	@GetMapping("/{id}")
	public CatalogDTO retrieveCategoryById(@PathVariable(name = "id")final int id) throws ApiException
	{		
		Category request = categoryService.select(id);
		
		if (request != null)
			return categoryToCatalogDTO(request);
		else
			throw new CategoryNotFoundException();
	}
	
	@GetMapping("/search")
	public CatalogDTO retrieveCategoryByName(@RequestParam(name = "name")final String name) throws ApiException
	{
		CatalogDTO response = null;
		response=   searchByName(name);
		return response;
	}
	
	
	private CatalogDTO searchByName(String name) throws ApiException{
		CatalogDTO response = null;
		try 
		{
			
			Optional<Category> request = categoryService.select().stream()
					.filter(x -> x.getName().equals(name))
					.findFirst();
				response = request.isPresent() ? categoryToCatalogDTO(request.get()) : null;
				return response;

		}

		catch(NoSuchElementException ex)
		{
			throw new CategoryNotFoundException();
		}
	
		
	}
	
	@PostMapping
	public CatalogDTO createCategory(@RequestBody final CatalogDTO catalog) throws ApiException
	{
		
		if(categoryService.select(catalog.getId()) != null)
			throw new ApiException("El ID ya esta registrado");
		
		Category request = catalogDTOToCategory(catalog);
		categoryService.insert(request);
		
		return categoryToCatalogDTO(request);
	}
	
	@PutMapping("/{id}")
	public CatalogDTO updateCategory(@PathVariable(name = "id") final int id, @RequestBody CatalogDTO catalog) throws ApiException
	{
		if (categoryService.select(id) == null)
			throw new CategoryNotFoundException();
		
		Category request = catalogDTOToCategory(catalog);
		categoryService.update(id, request);
		
		return categoryToCatalogDTO(request);
	}

	@DeleteMapping("/{id}")
	public CatalogDTO disableCategory(@PathVariable(name ="id") final int id) throws ApiException
	{
		if (categoryService.select(id) == null)
			throw new CategoryNotFoundException();
		Category request = categoryService.select(id);
		categoryService.disableCategory(id);
		
		CatalogDTO response = categoryToCatalogDTO(request);
		
		return response;
	}


	private List<CatalogDTO> categoryListToCatalogDTOList(List<Category> categoryList)
	{
		List<CatalogDTO> response = new ArrayList<>();
		
		for(Category category : categoryList)
		{
			response.add(categoryToCatalogDTO(category));
		}
		
		return response;
	}
	
	private CatalogDTO categoryToCatalogDTO(Category category)
	{		
		return new CatalogDTO(category.getId(), category.getName(), category.getDescription(), category.getStatus());
	
	}
	
	private Category catalogDTOToCategory(CatalogDTO catalog)
	{
		Category response = new Category();
		
		response.setId(catalog.getId());
		response.setName(catalog.getName());
		response.setDescription(catalog.getDescription());
		response.setStatus(catalog.getStatus());
		
		return response;
	}
	
	
}
