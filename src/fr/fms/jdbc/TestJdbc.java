package fr.fms.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import fr.fms.entities.Article;

public class TestJdbc {

	//Initialisation de la base de données
	/*
	private static String url = "jdbc:mariadb://localhost:3306/shop";
	private static String login = "root";
	private static String password = "fms2024";
	*/
	private static Properties prop;
	
	public static void main(String[] args) {
		ArrayList<Article> articles = new ArrayList<Article>();
		
		//Appel du driver jdbc de mariaDB
		try {
			prop = readPropertiesFile("config.properties");
			Class.forName(prop.getProperty("db.driver.class"));
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}

		
		
		//Connexion à la base de données et affichage des articles
		try(Connection connection = DriverManager.getConnection(prop.getProperty("db.url"), prop.getProperty("db.login"), prop.getProperty("db.password"))) {
			String strSql = "SELECT * FROM T_Articles";
			try(Statement statement = connection.createStatement()){
				try(ResultSet resultSet = statement.executeQuery(strSql)){
					while(resultSet.next()) {
						int rsIdArticle = resultSet.getInt(1);
						String rsDescription = resultSet.getNString("Description");
						String rsBrand = resultSet.getNString("Brand");
						double rsUnitaryPrice = resultSet.getDouble("UnitaryPrice");
						int rsIdCategory = resultSet.getInt("IdCategory");
						articles.add(new Article(rsIdArticle, rsDescription, rsBrand, rsUnitaryPrice, rsIdCategory));
					}
				}
			}
			
			//Article ecranMsi = new Article("ecran PC", "MSI", 250, 3);
			//createArticle(ecranMsi);
			//updateArticle();
			//Article rmArticle = articles.get(15);
			//removeArticle(rmArticle);
			for(Article a : articles) {
				System.out.println(a.getIdArticle() + " - " + a.getDescription() + " - " + a.getBrand() + " - " + a.getUnitaryPrice());
			}
			System.out.println();
			Article article = articles.get(7);
			showInfoArticle(article);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
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
	
	public static void createArticle(Article article) {
		String create = "INSERT INTO T_Articles (Description, Brand, UnitaryPrice, IdCategory) VALUES (?,?,?,?);";
		try(Connection connection = DriverManager.getConnection(prop.getProperty("db.url"), prop.getProperty("db.login"), prop.getProperty("db.password"))){
			try(PreparedStatement ps = connection.prepareStatement(create)) {
				ps.setString(1, article.getDescription());
				ps.setString(2, article.getBrand());
				ps.setDouble(3, article.getUnitaryPrice());
				ps.setInt(4, article.getIdCategory());
				if(ps.executeUpdate() == 1) 
					System.out.println("insertion ok");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateArticle(Article article) {
		String update = "UPDATE T_Articles SET Brand = ? WHERE IdArticle = ?";
		try(Connection connection = DriverManager.getConnection(prop.getProperty("db.url"), prop.getProperty("db.login"), prop.getProperty("db.password"))){
			try(PreparedStatement ps = connection.prepareStatement(update)){
				ps.setString(1, "Avast");
				ps.setInt(2, 15);
				if(ps.executeUpdate() == 1)
					System.out.println("insertion ok");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void removeArticle(Article article) {
		String remove = "DELETE FROM T_Articles WHERE IdArticle = ?";
		try(Connection connection = DriverManager.getConnection(prop.getProperty("db.url"), prop.getProperty("db.login"), prop.getProperty("db.password"))){
			try(PreparedStatement ps = connection.prepareStatement(remove)){
				ps.setInt(1, article.getIdArticle());
				if(ps.executeUpdate() == 1)
					System.out.println("insertion ok");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void showInfoArticle(Article article) {
		String showInfo = "SELECT IdArticle, Description, Brand, UnitaryPrice, IdCategory FROM T_Articles WHERE IdArticle = ?";
		try(Connection connection = DriverManager.getConnection(prop.getProperty("db.url"), prop.getProperty("db.login"), prop.getProperty("db.password"))){
			try(PreparedStatement ps = connection.prepareStatement(showInfo)){
				ps.setInt(1, (article.getIdArticle() - 1));
				try(ResultSet resultSet = ps.executeQuery()){
					if(resultSet.next()) {
						int rsIdArticle = resultSet.getInt(1);
						String rsDescription = resultSet.getNString("Description");
						String rsBrand = resultSet.getNString("Brand");
						double rsUnitaryPrice = resultSet.getDouble("UnitaryPrice");
						
						System.out.println("insertion ok");
						System.out.println(rsIdArticle + " - " + rsDescription + " - " + rsBrand + " - " + rsUnitaryPrice);
					}
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}
