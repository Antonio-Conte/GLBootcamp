package com.globallogic.bootcampgl.feign.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.globallogic.bootcampgl.feign.dto.ProductDTO;

@FeignClient(name = "bootcampgl", url = "http://localhost:9099")
public interface ShopProductProxy {

	@GetMapping("/products/v1/product")
	public List<ProductDTO> retrieveAllProducts();
	
	@GetMapping("/products/v1/product/{id}")
	public ProductDTO retrieveProductById(@PathVariable(name = "id") final int id);

	@GetMapping("/products/v1/product/search")
	public ProductDTO retrieveProductByNameOrCategory(@RequestParam(name = "name") final String name,
			@RequestParam(name = "categoryId") final int id);

	@PostMapping("/products/v1/product")
	public ProductDTO createProduct(@RequestBody final ProductDTO product);

	@PutMapping("/products/v1/product")
	public ProductDTO updateProduct(@RequestBody final ProductDTO product);

	@PatchMapping("/products/v1/product/{id}")
	public ProductDTO updateProductAtributes(@PathVariable(name = "id") final int id,
			@RequestBody final ProductDTO product);

	@DeleteMapping("/products/v1/product/{id}")
	public ProductDTO deleteProduct(@PathVariable(name = "id") final int id);
}
