package com.globallogic.bootcampgl.feign.model;

public class Category {

	private int id;
	private String name;
	private String description;
	private String estado;

	public Category(int id, String description, String name, String estado) {
		this.id = id;
		this.description = description;
		this.name = name;
		this.estado = estado;
	}

	public Category() {

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

}
