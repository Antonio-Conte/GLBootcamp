package com.globallogic.bootcampgl.feign.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.bootcampgl.feign.clients.ShopProductProxy;
import com.globallogic.bootcampgl.feign.dto.ProductDTO;

@RestController
public class FeignShopProductController {

	@Autowired
	private ShopProductProxy shopProductProxy;
	
	@GetMapping("/products")
	public List<ProductDTO> retrieveAllProducts()
	{
		return shopProductProxy.retrieveAllProducts();
	}
	
	@GetMapping("/products/{id}")
	public ProductDTO retrieveProductById(@PathVariable(name = "id") final int id) {
		return shopProductProxy.retrieveProductById(id);
	}

	@GetMapping("/products/search")
	public ProductDTO retrieveProductByIdOrCategory(@RequestParam(name = "name",required = false) final String name,
			@RequestParam(name = "id", required = false) final int id) {
		return shopProductProxy.retrieveProductByNameOrCategory(name, id);
	}

	@PostMapping("/products")
	public ProductDTO createProduct(@RequestBody final ProductDTO product) {
		return shopProductProxy.createProduct(product);
	}

	@PutMapping("/products")
	public ProductDTO updateProduct(@RequestBody final ProductDTO product) {
		return shopProductProxy.updateProduct(product);
	}

	@PatchMapping("/products/{id}")
	public ProductDTO updateProductAtributes(@PathVariable(name = "id") final int id,
			@RequestBody final ProductDTO product) {
		return shopProductProxy.updateProductAtributes(id, product);
	}

	@DeleteMapping("/products/{id}")
	public ProductDTO deleteProduct(@PathVariable(name = "id") final int id) {
		return shopProductProxy.deleteProduct(id);
	}
}
