package ar.com.gl.shop.product.error.exceptions;

public class StockNotFoundException extends ApiException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3563961571552311344L;

	public StockNotFoundException()
	{
		super("Stock no encontrado");
	}
}
