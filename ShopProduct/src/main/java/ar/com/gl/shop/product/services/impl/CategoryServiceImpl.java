package ar.com.gl.shop.product.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.gl.shop.product.entity.Category;
import ar.com.gl.shop.product.repository.CategoryRepositoryJPA;
import ar.com.gl.shop.product.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CategoryRepositoryJPA repository;

	public CategoryServiceImpl(CategoryRepositoryJPA repository) {
		this.repository = repository;
	}

	@Override
	public List<Category> select() {
		List<Category> response = new ArrayList<>();
		repository.findAll().stream().filter(x -> x.getStatus() != null && x.getStatus().equals("1"))
				.forEach(response::add);

		return response;
	}

	@Override
	public Category select(Integer element) {
		try {
			if (!repository.existsById(element))
				return null;
			return repository.getOne(element);

		}
		catch (EntityNotFoundException ex) {
			return null;
		}
	}

	@Override
	public void insert(Category element) {
		try {

			repository.save(element);
		} catch (IllegalArgumentException ex) {
			logger.info("Error, contacte con un administrador", ex);
		}
	}

	@Override
	public Category update(Integer id, Category element) {
		try {
			element.setId(id);
			repository.save(element);
		} catch (IllegalArgumentException ex) {
			logger.info("Error, contacte con un administrador", ex);
		}
		return repository.getOne(id);
	}

	@Override
	public void delete(Integer element) {
		try {
			repository.deleteById(element);
		} catch (IllegalArgumentException ex) {
			logger.info("El elemento no existe", ex);
		}
	}

	@Override
	public Category disableCategory(int id) {
		Category category = repository.getOne(id);
		try {
			category.setStatus("0");
			repository.save(category);
		} catch (IllegalArgumentException ex) {
			logger.info("Error, contacte con un administrador", ex);
		}
		return category;

	}

}
