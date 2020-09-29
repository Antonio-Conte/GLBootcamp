package ar.com.gl.shop.product.error.exceptions;

public class CategoryNotFoundException extends ApiException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5498008504125456581L;

	public CategoryNotFoundException()
	{
		super("Categoria no encontrada");

	}
}
