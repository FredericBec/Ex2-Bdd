package fr.fms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.fms.entities.Article;

public class ArticleDao implements Dao<Article>{
	public ArrayList<Article> articles = new ArrayList<Article>();
	public Connection connection = BddConnection.getConnection();
	
	@Override
	public void create(Article article) {
		String createArticle = "INSERT INTO T_Articles (Description, Brand, UnitaryPrice, IdCategory) VALUES (?,?,?,?);";
		try(PreparedStatement ps = connection.prepareStatement(createArticle)){
			ps.setString(1, article.getDescription());
			ps.setString(2, article.getBrand());
			ps.setDouble(3, article.getUnitaryPrice());
			ps.setInt(4, article.getIdCategory());
			if(ps.executeUpdate() == 1) 
				System.out.println("Nouvel article " + article.getDescription() +  " créé");
		}catch(SQLException e) {
			logger.severe("Problème de création d'un article" + e.getMessage());
		}
		
	}
	
	@Override
	public Article read(int id) {
		Article article = null;
		String readArticle = "SELECT IdArticle, Description, Brand, UnitaryPrice, IdCategory FROM T_Articles WHERE IdArticle = ?";
		try(PreparedStatement ps = connection.prepareStatement(readArticle)) {
			ps.setInt(1, articles.get(id - 1).getIdArticle());
			try(ResultSet resultSet = ps.executeQuery()) {
				if(resultSet.next()) {
					int rsIdArticle = resultSet.getInt(1);
					String rsDescription = resultSet.getNString("Description");
					String rsBrand = resultSet.getNString("Brand");
					double rsUnitaryPrice = resultSet.getDouble("UnitaryPrice");
					int rsIdCategory = resultSet.getInt("IdCategory");
					
					System.out.println("Article trouvé");
					return article = new Article(rsIdArticle, rsDescription, rsBrand, rsUnitaryPrice, rsIdCategory);
				}else
					System.out.println("Article non trouvé");
			}
		} catch (SQLException e) {
			logger.severe("Problème de lecture de l'article" + e.getMessage());
		}
		return article;
	}
	
	@Override
	public boolean update(Article article) {
		String updateArticle = "UPDATE T_Articles SET Description = ?, Brand = ?, UnitaryPrice = ? WHERE IdArticle = ?";
		try(PreparedStatement ps = connection.prepareStatement(updateArticle)){
			ps.setString(1, article.getDescription());
			ps.setString(2, article.getBrand());
			ps.setDouble(3, article.getUnitaryPrice());			
			ps.setInt(4, article.getIdArticle());
			if(ps.executeUpdate() == 1) {
				System.out.println("Mise à jour effectuée");
				return true;
			} 
		}catch(SQLException e) {
			logger.severe("Problème de modification de l'article" + e.getMessage());
		}
		return false;
	}
	
	@Override
	public boolean delete(Article article) {
		String removearticle = "DELETE FROM T_Articles WHERE IdArticle = ?";
		try(PreparedStatement ps = connection.prepareStatement(removearticle)){
			ps.setInt(1, (article.getIdArticle()-1));
			if(ps.executeUpdate() == 1) {
				System.out.println("Suppréssion effectuée");
				return true;
			}
		}catch(SQLException e) {
			logger.severe("Problème de suppression de l'article" + e.getMessage());
		}
		return false;
	}
	
	@Override
	public ArrayList<Article> readAll(){
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
			
		} catch (SQLException e) {
			logger.severe("Problème d'affichage des articles" + e.getMessage());
		}
		return articles;
		
	}
}
