package ar.com.gl.shop.product.model;

public class Product {
	private int id;
	private String name;
	private String description;
	private double price;
	private Stock stock;
	private Category category;
	private String status;
	
	
	public Product() {
		
	}
	
	public Product(int id,String name,String description,double price,Stock stock,Category category, String status) {
		
		this.id=id;
		this.name=name;
		this.description=description;
		this.price=price;
		this.setStock(stock);
		this.setCategory(category);
		this.status = status;
		
	}
	
	public int getId() {
		return id;
				
	}
	
	public void setId(int id) {
		this.id=id;
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name=name;
		
		
	}
	
	public String getDescription() {
		return description;
	}
	public void  setDescription(String description) {
		this.description=description;
		
	}
	
	public double getPrice() {
		return price;
		
	}
	public void setPrice (double price) {
		this.price=price;
		
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus (String status) {
		this.status = status;
	}
	
	

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
