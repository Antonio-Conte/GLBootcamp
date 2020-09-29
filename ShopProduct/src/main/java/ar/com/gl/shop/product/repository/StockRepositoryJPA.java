package ar.com.gl.shop.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.gl.shop.product.entity.Stock;

@Repository
public interface StockRepositoryJPA extends JpaRepository<Stock, Integer>{

}
