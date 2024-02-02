package fr.fms.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.fms.dao.BddConnection;
import fr.fms.entities.Article;

public class ArticleCart implements Cart<Article>{

	public ArrayList<Article> cart = new ArrayList<Article>();
	public Connection connection = BddConnection.getConnection();
	
	@Override
	public void addCart(int id) {
		Article article = null;
		String add = "SELECT IdArticle, Description, Brand, UnitaryPrice FROM T_Articles WHERE IdArticle = ?;";
		try(PreparedStatement ps = connection.prepareStatement(add)) {
			ps.setInt(1, id);
			try(ResultSet resultSet = ps.executeQuery()){
				if(resultSet.next()) {
					int rsIdArticle = resultSet.getInt("IdArticle");
					String rsDescription = resultSet.getString("Description");
					String rsBrand = resultSet.getString("Brand");
					Double rsUnitPrice = resultSet.getDouble("UnitaryPrice");
					
					System.out.println("Article ajouté");
					article = new Article(rsIdArticle, rsDescription, rsBrand, rsUnitPrice);
					cart.add(article);
				}else
					System.out.println("L'article n'existe pas");
			}
		} catch (SQLException e) {
			logger.severe("Problème de sélection d'article " + e.getMessage());
		}
	}

	@Override
	public boolean updateCart(Article obj) {
		
		return false;
	}

	@Override
	public boolean deleteCart(Article article) {
		
		if(cart.contains(article)) {
			cart.remove(article);
			return true;						
		}
			
		return false;
	}

	@Override
	public boolean clearCart() {
		if(cart.isEmpty()) {
			return false;
		}else
			cart.clear();
		
		return true;
	}

	@Override
	public void showCart() {
		if(!cart.isEmpty()) {
			for(Article a : cart) {
				System.out.println(a.getIdArticle() + " - " + a.getDescription() + " - " + a.getBrand() + " - " + a.getUnitaryPrice());
			}
		}else
			System.out.println("Votre panier est vide");
		
	}

	
}
