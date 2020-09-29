package ar.com.gl.shop.product.services.exceptions;

public class InsertStockException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5652742315678613600L;
	private final String errorString;

	public InsertStockException(String errorString) {
		this.errorString = errorString;

	}

	public String getString() {
		return errorString;
	}

}
