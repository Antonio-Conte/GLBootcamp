package ar.com.gl.shop.product.repository.impl;

import java.util.ArrayList;
import java.util.Objects;

import ar.com.gl.shop.product.model.Category;
import ar.com.gl.shop.product.model.Product;
import ar.com.gl.shop.product.model.Stock;
import ar.com.gl.shop.product.repository.Repository;

public class RepositoryImpl implements Repository{

	private ArrayList<Product> listaProductos = new ArrayList<Product>();
	private ArrayList<Stock> listaStock = new ArrayList<Stock>();
	private ArrayList<Category> listaCategorias = new ArrayList<Category>();
	
	private static RepositoryImpl repository;
	
	
	private RepositoryImpl()
	{
		
	}
	
	public static RepositoryImpl getInstance()
	{
		if (Objects.isNull(repository))
		{
			repository = new RepositoryImpl();
		}
		return repository;
	}
	
	public ArrayList<Product> getListaProductos() {return this.listaProductos;}
	public void setListaProductos(ArrayList<Product> listaNueva) {this.listaProductos = listaNueva;}
	
	public ArrayList<Stock> getListaStock() {return this.listaStock;}
	public void setListaStock(ArrayList<Stock> listaNueva) {this.listaStock = listaNueva;}
	
	public ArrayList<Category> getListaCategorias() {return this.listaCategorias;}
	public void setListaCategorias(ArrayList<Category> listaNueva) {this.listaCategorias = listaNueva;}
}
