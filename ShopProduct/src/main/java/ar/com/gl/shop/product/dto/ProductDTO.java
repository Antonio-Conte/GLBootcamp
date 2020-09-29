package ar.com.gl.shop.product.dto;

import java.io.Serializable;

import ar.com.gl.shop.product.entity.Category;
import ar.com.gl.shop.product.entity.Stock;

public class ProductDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4285858736854323939L;

	private int id;
	private String name;
	private String description;
	private String status;
	private Stock stock;
	private Category category;

	public ProductDTO() {

	}

	public ProductDTO(int id, String name, String description, String status, Stock stock, Category category) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.status = status;
		this.stock = stock;
		this.category = category;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescrition(String description) {
		this.description = description;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Stock getStock() {
		return this.stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString()
	{
		return "id: " + this.id + " name: " + this.name + " description: " + this.description + " status: " + this.status + "\nStock: " + this.stock.toString() + "\nCategory: " + this.category.toString();
	}

}
