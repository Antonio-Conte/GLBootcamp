package ar.com.gl.shop.product.services.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

public class StockDataSource {
	
	private static final String PROPERTIES_PATH = "db.properties";
	private static final String DB_URL = "jdbc:mysql://bootcamp.ckyzngvmlkpk.sa-east-1.rds.amazonaws.com:3306";
	private static final String DB_USERNAME = "celula2";
	private static final String DB_PASSWORD = "Celula02!!!";
	
	private static Properties properties = null;
	private static MysqlDataSource dataSource;
	
	static {
		try {
			properties = new Properties();
			properties.load(new FileInputStream(PROPERTIES_PATH));
			
			dataSource = new MysqlDataSource();
			dataSource.setUrl(properties.getProperty(DB_URL));
			dataSource.setUser(properties.getProperty(DB_USERNAME));
			dataSource.setPassword(properties.getProperty(DB_PASSWORD));
		}catch (IOException e){
			e.printStackTrace();
			
		}
	}

	public static DataSource getDataSource() {
		return dataSource;
		
	}
}
