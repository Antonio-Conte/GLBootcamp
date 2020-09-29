package ar.com.gl.shop.product.services;

import org.springframework.stereotype.Service;

import ar.com.gl.shop.product.entity.Category;
@Service
public interface CategoryService extends CRUDService<Category, Integer>{

	Category disableCategory(int id);

}
