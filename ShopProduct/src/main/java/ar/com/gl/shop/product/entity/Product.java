package ar.com.gl.shop.product.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class Product {

	@Id
	@Column(name = "PR_ID")
	private int id;

	@Column(name = "PR_NAME")
	private String name;

	@Column(name = "PR_DESCRIPTION")
	private String description;

	@Column(name = "PR_PRECIO")
	private Double price;

	@Column(name = "PR_ESTADO")
	private String estado;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "ST_ID")
	private Stock stock;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "CA_ID")
	private Category category;

	public Product(int id, String name, String description, double price, String estado, Stock stock,
			Category category) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.estado = estado;
		this.stock = stock;
		this.category = category;

	}

	public Product() {

	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setStatus(String estado) {
		this.estado = estado;
	}

	public String getStatus() {
		return estado;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public Stock getStock() {
		return stock;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Category getCategory() {
		return category;
	}

}
