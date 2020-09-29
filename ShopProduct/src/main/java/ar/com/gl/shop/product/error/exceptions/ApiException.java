package ar.com.gl.shop.product.error.exceptions;

import java.io.Serializable;

public class ApiException extends Exception implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1788609154157332395L;

	private final String message;
	
	public ApiException(String message)
	{
		super();
		this.message = message;
	}
	
	@Override 
	public String getMessage()
	{
		return this.message;
	}
	
}
