package ar.com.gl.shop.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="CATEGORY")
public class Category {

	@Id
	@Column(name="CA_ID")
	private int id;
	
	@Column(name="CA_NAME")
	private String name;
	
	@Column(name="CA_DESCRIPTION")
	private String description;
	
	
	
	@Column(name="CA_ESTADO")
	private String estado;
	
	
	public Category(int id, String description, String name, String estado) 
	{
		this.id=id;
		this.description=description;
		this.name=name;
		this.estado=estado;
	}
	public Category() {
		
	}
	
	
	public void setId(int id) {
		this.id=id;
	}
	
	public int getId() {
		return id;
	}
	
	
	public void setName(String name) {
		this.name=name;
	}
	public String getName() {
		return name;
	}
	
	
	public void setDescription(String description) {
		this.description=description;
	}
	public String getDescription() {
		return description;
	}
	
	public void setStatus(String estado) {
		this.estado=estado;
	}
	public String getStatus() {
		return estado;
	}
	
}
