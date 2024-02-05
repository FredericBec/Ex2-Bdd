package fr.fms.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BddConnection {

	private static Properties prop;
	private static Connection connection = null;

	private BddConnection() {
		try {
			prop = readPropertiesFile("config.properties");
			Class.forName(prop.getProperty("db.driver.class"));
			connection = DriverManager.getConnection(prop.getProperty("db.url"), 
					prop.getProperty("db.login"), prop.getProperty("db.password"));
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static synchronized Connection getConnection() {
		
		if(connection == null) new BddConnection();
		return connection;
	}
	
	public static Properties readPropertiesFile(String fileName) throws IOException{
		Properties prop = null;
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(fileName);
			prop = new Properties();
			prop.load(fis);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			fis.close();
		}
		return prop;
	}

}
