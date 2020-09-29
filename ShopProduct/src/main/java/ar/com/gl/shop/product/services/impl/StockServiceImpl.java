package ar.com.gl.shop.product.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.gl.shop.product.entity.Stock;
import ar.com.gl.shop.product.repository.StockRepositoryJPA;
import ar.com.gl.shop.product.services.StockService;

@Service
public class StockServiceImpl implements StockService {


	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private StockRepositoryJPA repository;

	public StockServiceImpl(StockRepositoryJPA stockRepository) {
		this.repository = stockRepository;
	}

	@Override
	public List<Stock> select() {
		List<Stock> response = new ArrayList<>();
		repository.findAll().stream().filter(x -> x.getStatus().equals("1")).forEach(response::add);

		return response;
	}

	@Override
	public Stock select(Integer element) {
		try {
			return repository.getOne(element);
		} catch (EntityNotFoundException ex) {
			return null;
		}

	}

	// RETORNA true SI EL ELEMENTO YA EXISTE
	@Override
	public void insert(Stock element) {
		try {
			repository.save(element);
		} catch (IllegalArgumentException ex) {
				logger.info("Error, contacte con un administrador", ex);
		}
	}

	// RETORNA true SI EL ELEMENTO NO EXISTE
	@Override
	public Stock update(Integer id, Stock element) {
		try {
			repository.save(element);
		} catch (IllegalArgumentException ex) {
			logger.info("Error, contacte con un administrador", ex);
		}
		return element;
	}

	// RETORNA true SI EL ID NO EXISTE EN LA LISTA
	@Override
	public void delete(Integer element) {
		try {
			repository.deleteById(element);
		} catch (IllegalArgumentException ex) {
			logger.info("El elemento no existe", ex);
		}
	}
	
	
}
