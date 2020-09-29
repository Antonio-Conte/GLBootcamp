package ar.com.gl.shop.product.model;

public class Category {
	private String description;
	private String name;
	private int id;
	private String status;
	
	public Category(String description,String name,int id, String status) {
		this.description=description;
		this.name=name;
		this.id=id;
		this.status=status;
		
		
	}
	
	public Category() {
		
	}
	
	public String  getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description=description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
		
	}
	
	public int getId() {
		return id;
		
	}
	
	public void setId(int id ) {
		this.id=id;
	}

	public String getStatus(){
			return status;
	}
	
	public void setStatus(String Status ) {
		this.status=Status;
	}


}
