package ar.com.gl.shop.product.services.exceptions;

public class InsertProductException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4490938458874807133L;
	private final String errorString;
	public InsertProductException(String errorString) {
		 this.errorString=errorString;
		
	}
	
	public String getString() {
		return errorString;
	}
}
