package ar.com.gl.shop.product.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ar.com.gl.shop.product.error.exceptions.ApiException;
import ar.com.gl.shop.product.error.exceptions.CategoryNotFoundException;
import ar.com.gl.shop.product.error.exceptions.ProductNotFoundException;
import ar.com.gl.shop.product.error.exceptions.StockNotFoundException;
		
@ControllerAdvice
public class ErrorHandler {

	private ApiException response;

	@ExceptionHandler(value = { ApiException.class, ProductNotFoundException.class, CategoryNotFoundException.class,
			StockNotFoundException.class })
	public ResponseEntity<Object> handleElementNotFoundException(Exception e) {
		
	
		
		 response = new ApiException(e.getMessage());

		return new ResponseEntity<>(response.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleGeneralException(Exception e) {
		

		 response = new ApiException("Error, contacte a un administrador");

		return new ResponseEntity<>(response.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}