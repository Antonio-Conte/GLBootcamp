package ar.com.gl.shop.product.repository.impl;

import ar.com.gl.shop.product.repository.StockRepository;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

import ar.com.gl.shop.product.model.Stock;
import ar.com.gl.shop.product.services.util.StockDataSource;

import ar.com.gl.shop.product.services.exceptions.InsertStockException;

public class StockRepositoryImpl implements StockRepository {

	private static final long serialVersionUID = 1L;

	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	private static StockRepositoryImpl instance;
	
	public static StockRepositoryImpl getInstance()
	{
		if (Objects.isNull(instance))
		{
			instance = new StockRepositoryImpl();
		}
		return instance;
	}
	

	public ArrayList<Stock> getListaStock() throws SQLException {
		final String query = "SELECT * FROM STOCK";
		ArrayList<Stock> results = new ArrayList<Stock>();
		try {
			con = StockDataSource.getDataSource().getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query);

			while (rs.next()) {
				Stock stock = new Stock();
				stock.setId(rs.getInt("ST_ID"));
				stock.setQuantity(rs.getInt("ST_QUANTITY"));
				stock.setLocationCode(rs.getString("ST_LOCATION_CODE"));

				results.add(stock);

			}
		} catch (SQLException e) {
			System.out.println("Query:SELECT * Went wrong");

		} finally {
			con.close();
			st.close();
			rs.close();
		}

		return results;
	}

	public Stock getStock(final Long id) throws SQLException {

		final String query = "SELECT * FROM STOCK WHERE ST_ID = " + id;
		Stock stock = null;
		try {
			con = StockDataSource.getDataSource().getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query);
			if (!rs.next()) {
				return stock;
			} else {
				stock = new Stock();
				stock.setId(rs.getInt("ST_ID"));
				stock.setQuantity(rs.getInt("ST_QUANTITY"));
				stock.setLocationCode(rs.getString("ST_LOCATION_CODE"));
				return stock;

			}

		} catch (SQLException e) {
			System.out.println("Query: SELECT elemet Went wrong");

		} finally {
			con.close();
			st.close();
			rs.close();
		}
		return null;
	}

	// RETORNA true SI EL ELEMENTO YA EXISTE
	public boolean insert(final Stock element) throws SQLException {

		final String query = "SELECT * FROM STOCK";
		
		try {
			con = StockDataSource.getDataSource().getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query);

			while (rs.next()) {
				if (rs.getLong("ST_ID") == element.getId()) {
					throw new InsertStockException("Error: Elemento ya insertado!");
				}
				;
			}
		} catch (SQLException e) {
			System.out.println("Query: SELECT * Went wrong FROM INSERT()");

		} catch (InsertStockException e) {
			System.err.println("Error: Elemento ya insertado!");

		} catch (Exception e) {
			System.err.println("Error Generico :( ");
		} finally {
			con.close();
			st.close();
			rs.close();
		}

		try {

			String query2 = "INSERT INTO STOCK VALUES(" + element.getId() + "," + "'" + element.getQuantity() + "',"
					+ "'" + element.getLocationCode()+ "')";

			con = StockDataSource.getDataSource().getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query2);
			return false;

		} catch (SQLException e) {
			System.out.println("Query: INSERT Went wrong");

		} finally {
			con.close();
			st.close();
			rs.close();
		}

		return false;
	}

	// RETORNA true SI EL ELEMENTO NO EXISTE
	public boolean update(final Stock element) throws SQLException {
		try {
			
			String query = "UPDATE PRODUCT SET   ST_ID:" + element.getId() + "," + "ST_QUANTITY:" + "'"
					+ element.getQuantity() + "'," + "ST_LOCATION_CODE:" + "'" + element.getLocationCode() + "',"
					+ "WHERE ST_ID=" + element.getId();
			con = StockDataSource.getDataSource().getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query);
			return false;

		} catch (SQLException e) {
			System.out.println("Query: INSERT Went wrong");

		} finally {
			con.close();
			st.close();
			rs.close();
		}

		return true;
	}

	// RETORNA true SI EL ID NO EXISTE EN LA LISTA
	public boolean delete(final Long element) throws SQLException {

		try {
			String query = "DELETE FROM STOCK WHERE ST_ID=" + element;
			con = StockDataSource.getDataSource().getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query);
			return false;

		} catch (SQLException e) {
			System.out.println("Query: Delete Went wrong");

		} finally {
			con.close();
			st.close();
			rs.close();
		}
		return true;
	}
}
