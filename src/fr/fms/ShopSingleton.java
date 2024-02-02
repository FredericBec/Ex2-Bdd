package fr.fms;

import fr.fms.dao.ArticleCart;
import fr.fms.dao.ArticleDao;
import fr.fms.dao.UserDao;

public class ShopSingleton {

	private static ShopSingleton INSTANCE = null;
	
	private ArticleDao articles;
	private UserDao users;
	private ArticleCart cart;

	private ShopSingleton() {

		
	}
	
	public static ShopSingleton getInstance() {
		if(INSTANCE == null) {
			synchronized(ShopSingleton.class) {
				if(INSTANCE == null) {
					INSTANCE = new ShopSingleton();
					INSTANCE.articles = new ArticleDao();
					INSTANCE.users = new UserDao();
					INSTANCE.cart = new ArticleCart();
				}
			}
		}
		return INSTANCE;
	}

	public ArticleDao getArticles() {
		return articles;
	}

	public UserDao getUsers() {
		return users;
	}

	public ArticleCart getCart() {
		return cart;
	}
	
}
