package ar.com.gl.shop.product.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.gl.shop.product.dto.ProductDTO;
import ar.com.gl.shop.product.entity.Product;
import ar.com.gl.shop.product.error.exceptions.ApiException;
import ar.com.gl.shop.product.error.exceptions.CategoryNotFoundException;
import ar.com.gl.shop.product.error.exceptions.ProductNotFoundException;
import ar.com.gl.shop.product.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public List<ProductDTO> retrieveAllProducts()
	{
		List<ProductDTO> response = new ArrayList<>();
		
		for(Product product : productService.select())
		{
			response.add(productToProductDTO(product));
		}
		
		return response;
	}
	
	@GetMapping("/{id}")
	public ProductDTO retrieveProductById(@PathVariable(name = "id") final int id) throws ApiException {
		Product request = productService.select(id);
		
		if(request != null)
		{
			return productToProductDTO(request);
		}
		else
		{
			throw new ProductNotFoundException();
		}
	}

	@GetMapping("/search")
	public ProductDTO retrieveProductByNameOrCategory(@RequestParam(name = "name") final String name,
			@RequestParam(name = "categoryId")final int categoryId) throws ApiException {
		ProductDTO response = null;
		try {
			Optional<Product> request = productService.select().stream()
					.filter(x -> (x.getName().equals(name) || x.getCategory().getId() == categoryId ))
					.findAny();
				
			response = request.isPresent() ? productToProductDTO(request.get()) : null;

			return response;
			
		} catch (NoSuchElementException ex) {
			throw new CategoryNotFoundException();
		}
	}

	@PostMapping
	public ProductDTO createProduct(@RequestBody final ProductDTO product){
		
		productService.insert(productDTOToProduct(product));

		return product;
	}

	@PutMapping
	public ProductDTO updateProduct(@RequestBody final ProductDTO product){
		Product request = productDTOToProduct(product);
		productService.update(request.getId(), request);

		return product;
	}

	@PatchMapping("/{id}")
	public ProductDTO updateProductAttributes(@PathVariable final int id, @RequestBody final ProductDTO product) {

		Product request = productDTOToProduct(product);
		productService.update(id, request);

		return product;
	}

	@DeleteMapping("/{id}")
	public ProductDTO deleteProduct(@PathVariable final int id) {
		Product request = productService.select(id);
		productService.delete(id);

		return productToProductDTO(request);
	}

	private ProductDTO productToProductDTO(Product product) {
		ProductDTO response = new ProductDTO();

		response.setId(product.getId());
		response.setDescrition(product.getDescription());
		response.setName(product.getName());
		response.setStatus(product.getStatus());
		response.setCategory(product.getCategory());
		response.setStock(product.getStock());

		return response;
	}

	private Product productDTOToProduct(ProductDTO product) {
		Product response = new Product();

		response.setId(product.getId());
		response.setDescription(product.getDescription());
		response.setName(product.getName());
		response.setStatus(product.getStatus());
		response.setCategory(product.getCategory());
		response.setStock(product.getStock());

		return response;
	}
}