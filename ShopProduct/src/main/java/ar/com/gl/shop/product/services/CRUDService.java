package ar.com.gl.shop.product.services;

import java.util.List;

import org.springframework.stereotype.Service;
@Service
public interface CRUDService <T, Q> {

	public List<T> select() ;
	public T select (Q element);
	public void insert (T element);
	public T update (Q id, T element);
	public void delete (Q element);
	
	
}

