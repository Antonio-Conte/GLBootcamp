package ar.com.gl.shop.product.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="STOCK")


public class Stock {

	@Id
	@Column(name="ST_ID")
	private int id;
	
	@Column(name="ST_QUANTITY")
	private int quantity;
	
	@Column(name="ST_LOCATION_CODE")
	private String locationCode;

	@Column(name="ST_ESTADO")
	private String status;
	
	public Stock() {
		
	}
	
	public Stock(int id ,int quantity,String locationCode, String status) {
		this.id=id;
		this.quantity=quantity;
		this.locationCode=locationCode;
		this.status= status;
		
	}
	
	public int getId(){
		return id;
		
	}
	public void setId(int id) {
		this.id=id;
		
		
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		
		this.quantity=quantity;
	}
	
	public  String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String  locationCode) {
		this.locationCode=locationCode;
		
	}
	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status=status;
	}



	
	
}
