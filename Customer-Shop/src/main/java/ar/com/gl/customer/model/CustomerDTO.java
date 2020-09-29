package ar.com.gl.customer.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CUSTOMER")
public class CustomerDTO  {
	
	/**
	 * 
	 */
	

	@Id
	@Column(name = "CU_ID")
	private int id;
	
	@Column(name = "CU_LASTNAME", length = 45)
	private String lastName;
	
	@Column(name = "CU_FIRSTNAME", length = 45)
	private String name;
	
	@Column(name = "CU_STATUS", length = 45)
	private String status;
	
	@Column(name = "CU_EMAIL", length = 45)
	private String email;

	public CustomerDTO(int id, String lastName, String name, String status, String email) {
		this.id = id;
		this.lastName = lastName;
		this.name = name;
		this.status = status;
		this.email = email;
	}
	public CustomerDTO()
	{
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
