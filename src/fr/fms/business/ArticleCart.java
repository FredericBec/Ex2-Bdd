package fr.fms.business;

import java.util.ArrayList;
import java.util.HashMap;

import fr.fms.dao.ArticleDao;
import fr.fms.entities.Article;

public class ArticleCart implements Cart<Article>{

	private HashMap<Integer, Article> cart = new HashMap<Integer, Article>();
	private ArticleDao articleDao = new ArticleDao();
	
	@Override
	public void addToCart(Article article) {
		cart.put(article.getIdArticle(), article);
	}

	@Override
	public void deleteFromCart(Article article) {
		cart.remove(article.getIdArticle());	
	}

	@Override
	public void clearCart() {
		cart.clear();
	}

	@Override
	public ArrayList<Article> showCart() {
		return new ArrayList<Article>(cart.values());
		
	}
	
	@Override
	public ArrayList<Article> readArticles(){
		return articleDao.readAll();
	}
	
	@Override
	public Article readOneArticle(int id) {
		Article article = articleDao.read(id);
		
		return article;
	}

	
}
