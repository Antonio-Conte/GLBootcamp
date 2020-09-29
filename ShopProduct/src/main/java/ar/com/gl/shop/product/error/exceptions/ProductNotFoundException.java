package ar.com.gl.shop.product.error.exceptions;

public class ProductNotFoundException extends ApiException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2003070441889163895L;

	public ProductNotFoundException()
	{
		super("Producto no encontrado!");
	}
}
