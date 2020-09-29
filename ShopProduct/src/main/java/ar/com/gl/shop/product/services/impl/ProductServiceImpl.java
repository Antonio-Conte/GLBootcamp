package ar.com.gl.shop.product.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.gl.shop.product.entity.Product;
import ar.com.gl.shop.product.repository.CategoryRepositoryJPA;
import ar.com.gl.shop.product.repository.ProductRepositoryJPA;
import ar.com.gl.shop.product.repository.StockRepositoryJPA;
import ar.com.gl.shop.product.services.ProductService;
import ar.com.gl.shop.product.services.exceptions.InsertProductException;

@Service
public class ProductServiceImpl implements ProductService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProductRepositoryJPA repositoryProdJPA;
	@Autowired
	private StockRepositoryJPA repositoryStockJPA;
	@Autowired
	private CategoryRepositoryJPA repositoryCatJPA;

	public ProductServiceImpl(ProductRepositoryJPA repositoryProdJPA, StockRepositoryJPA repositoryStockJPA,
			CategoryRepositoryJPA repositoryCatJPA) {
		this.repositoryProdJPA = repositoryProdJPA;
		this.repositoryStockJPA = repositoryStockJPA;
		this.repositoryCatJPA = repositoryCatJPA;
	}

	private boolean checkCategory(Product element) {
		return repositoryCatJPA.existsById(element.getCategory().getId());
	}

	private boolean checkStock(Product element) {
		return repositoryStockJPA.existsById(element.getStock().getId());
	}

	public List<Product> select() {

		List<Product> repo = repositoryProdJPA.findAll();
		for (Product product : repo) {
			if ("0".equals(product.getStatus())) {
				repo.remove(product);
			}
		}
		return repo;
	}

	public Product select(Integer element) {
		Product p = repositoryProdJPA.getOne(element);
		if ("0".equals(p.getStatus())) {
			return null;
		}
		return p;
	}

	public void insert(Product element) {

		try {
			if (!checkCategory(element))
				throw new InsertProductException("Error: Categoria no existe");
			if (!checkStock(element))
				throw new InsertProductException("Error: Stock no existe");
			if (repositoryProdJPA.existsById(element.getId()))
				throw new InsertProductException("Error: Producto ya insertado!");

			repositoryProdJPA.save(element);
		} catch (InsertProductException e) {
			logger.info(e.getMessage(), e);
		}

	}

	public Product update(Integer id, Product element) {

		try {
			if (!checkCategory(element))
				throw new InsertProductException("Error!: Categoria no existe");
			if (!checkStock(element))
				throw new InsertProductException("Error: Stock no existe");
			if (!repositoryProdJPA.existsById(element.getId()))
				throw new InsertProductException("Error: Producto no encontrado!");

			repositoryProdJPA.save(element);
		} catch (InsertProductException e) {
			logger.info(e.getMessage(), e);
		}

		return repositoryProdJPA.getOne(id);
	}

	public void delete(Integer element) {
		Product p = repositoryProdJPA.getOne(element);

		try {
			repositoryProdJPA.delete(p);
		} catch (IllegalArgumentException e) {
			logger.info("Error: No se ha encontrado el elemento-.", e);
		}

	}

	public void deleteLogic(int element) {
		Product p = repositoryProdJPA.getOne(element);
		p.setStatus("0");

		try {
			repositoryProdJPA.save(p);
		} catch (IllegalArgumentException e) {
			 logger.info("Error: No se ha encontrado el elemento-.", e);
		}
	}
}
