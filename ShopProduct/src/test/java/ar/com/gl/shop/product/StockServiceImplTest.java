package ar.com.gl.shop.product;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ar.com.gl.shop.product.entity.Stock;
import ar.com.gl.shop.product.repository.StockRepositoryJPA;
import ar.com.gl.shop.product.services.impl.StockServiceImpl;

public class StockServiceImplTest {

	
	@Mock
	private  StockRepositoryJPA repository;
	@InjectMocks
	private  StockServiceImpl stockService;
	
	@BeforeEach
	public void setup()
	{
		repository = Mockito.mock(StockRepositoryJPA.class);
		stockService = new StockServiceImpl(repository);
	}
	@Test
	@DisplayName("Retornar la lista completa")
	public void testSelectRetornaLaLista(){
		
		when(repository.findAll()).thenReturn(new ArrayList<Stock>());
		
		List<Stock> resultado = stockService.select();
		assertNotNull(resultado);
		assertTrue(resultado.isEmpty());
	}
	
	@Test
	@DisplayName("Retornar un elemento de la lista")
	public void testSelectStock() {
		
		Stock stock = new Stock(1, 5,"ASD123","0");

		when(repository.getOne(stock.getId())).thenReturn(stock);
		
		Stock stockResponse = stockService.select(stock.getId());
		
		
		assertNotNull(stockResponse);
		assertEquals(stock.getId(), stockResponse.getId());
		assertEquals(stock.getLocationCode(), stockResponse.getLocationCode());
		assertEquals(stock.getQuantity(), stockResponse.getQuantity());
	}
	
	
	@Test
	@DisplayName("Insertar un elemento en la lista")
	public void testInsertStock()
	{
		int idStock = 1;
		Stock stockNuevo = new Stock(1, 5,"ASD123","0");
		List<Stock> lista = new ArrayList<Stock>();
		when(repository.findAll()).thenReturn(lista);
		when(repository.save(stockNuevo)).thenReturn(agregarALista(lista, stockNuevo));
		when(repository.getOne(idStock)).thenReturn(lista.get(0));
		
		stockService.insert(stockNuevo);
		
		Stock stockResponse = stockService.select(stockNuevo.getId());
		
		assertEquals(stockNuevo.getId(), stockResponse.getId());
		assertEquals(stockNuevo.getLocationCode(), stockResponse.getLocationCode());
		assertEquals(stockNuevo.getQuantity(), stockResponse.getQuantity());
	} 
		
	private Stock agregarALista(List<Stock> lista, Stock nueva)
	{
		lista.add(nueva);
		return nueva;
	}
	
	@Test
	@DisplayName("Actualizar un elemento en la lista")
	public void testUpdateStock()
	{
		Stock stockAct = new Stock(1, 5,"ASD123","1");
		Stock stock = new Stock(1, 10, "ZXC789","1");
		List<Stock> lista = new ArrayList<Stock>();
		
		lista.add(stock);
		
		when(repository.save(stockAct)).thenAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock a)
			{
				lista.remove(stock);
				lista.add(stockAct);
				return null;
			}
		});
		
		Stock stockResponse = stockService.update(stock.getId(), stockAct);
		assertEquals(stockAct.getId(), stockResponse.getId());
		assertEquals(stockAct.getLocationCode(), stockResponse.getLocationCode());
		assertEquals(stockAct.getQuantity(), stockResponse.getQuantity());
	}
	
	@Test
	@DisplayName("Borrar un elemento de la lista")
	public void testDeleteStock()
	{
		Stock stock = new Stock(1, 10, "ZXC789","0");
		List<Stock> lista = new ArrayList<Stock>();
		
		lista.add(stock);
		
		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock a) throws Throwable
			{
				lista.remove(stock);
				return null;
				
			}
		}).when(repository).deleteById(stock.getId());
		
		stockService.delete(stock.getId());
		
		List<Stock> stockResponse = stockService.select();
		
		assertTrue(stockResponse.isEmpty());
		
	}
	
	@Test
	@DisplayName("Insertar elemento, IllegalArgumentException")
	void stockServiceInsert_IllegalArgumentException_Test()
	{
		Stock stock = new Stock(1, 200, "", "1");
		when(repository.save(Mockito.any(Stock.class))).thenThrow(new IllegalArgumentException());
		doThrow(new IllegalArgumentException()).when(repository).deleteById(Mockito.anyInt());
		
		stockService.insert(stock);
		stockService.update(stock.getId(), stock);
		stockService.delete(stock.getId());
		
		assertTrue(Boolean.TRUE);
	}
}
