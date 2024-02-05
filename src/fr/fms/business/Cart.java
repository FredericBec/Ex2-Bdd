package fr.fms.business;

import java.util.ArrayList;

import fr.fms.entities.Article;

public interface Cart<T> {

	public void addToCart(Article article);
	public void deleteFromCart(Article article);
	public void clearCart();
	public ArrayList<T> showCart();
	public ArrayList<Article> readArticles();
	public Article readOneArticle(int id);
}
