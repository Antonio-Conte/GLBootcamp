package ar.com.gl.shop.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.gl.shop.product.entity.Category;

@Repository
public interface CategoryRepositoryJPA extends JpaRepository<Category, Integer>{

}
