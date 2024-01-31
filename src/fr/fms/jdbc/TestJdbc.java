package fr.fms.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import fr.fms.entities.Article;

public class TestJdbc {

	public static void main(String[] args) throws Exception {
		ArrayList<Article> articles = new ArrayList<Article>();
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}

		String url = "jdbc:mariadb://localhost:3306/shop";
		String login = "root";
		String password = "fms2024";
		
		try(Connection connection = DriverManager.getConnection(url, login, password)) {
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
			
			for(Article a : articles) {
				System.out.println(a.getIdArticle() + " - " + a.getDescription() + " - " + a.getBrand() + " - " + a.getUnitaryPrice());
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
