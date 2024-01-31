package fr.fms.dao;

import java.sql.Connection;
import java.util.ArrayList;

import fr.fms.entities.Article;

public class ArticleDao implements Dao<Article>{
	
	public Connection connection = BddConnection.getConnection();
	
	@Override
	public void create(Article article) {
		
	}
	
	@Override
	public Article read(int id) {
		Article article = null;
		return article;
	}
	
	@Override
	public boolean update(Article article) {
		return true;
	}
	
	@Override
	public boolean delete(Article article) {
		return true;
	}
	
	@Override
	public ArrayList<Article> readAll(){
		return null;
		
	}
}
