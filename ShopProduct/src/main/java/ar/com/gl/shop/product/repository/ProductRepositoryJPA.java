package ar.com.gl.shop.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.gl.shop.product.entity.Product;

@Repository
public interface ProductRepositoryJPA extends JpaRepository<Product, Integer>{

}
