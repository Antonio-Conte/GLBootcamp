package ar.com.gl.shop.product.dto;

import java.io.Serializable;

public class CatalogDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6137173826360946253L;

	private int id;
	private String name;
	private String description;
	private String status;
	
	public CatalogDTO()
	{
		
	}
	
	public CatalogDTO(int id, String name, String description, String status)
	{
		this.id = id;
		this.name = name;
		this.description = description;
		this.status = status;
	}
	
	public int getId() {return this.id;}
	public void setId(int id) {this.id = id;}
	
	public String getName() {return this.name;}
	public void setName(String name) {this.name = name;}
	
	public String getDescription() {return this.description;}
	public void setDescription(String description) {this.description = description;}
	
	public String getStatus() {return this.status;}
	public void setStatus(String status) {this.status = status;}
	
	@Override
	public String toString()
	{
		return "id: " + this.id + " name: " + this.name + " description: " + this.description + " status: " + this.status;
	}
	
}
