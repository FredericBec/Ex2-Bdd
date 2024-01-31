package fr.fms;

import fr.fms.dao.ArticleDao;
import fr.fms.entities.Article;

public class Shop {

	public static void main(String[] args) {
		ArticleDao articles = new ArticleDao();
		
		
		articles.readAll();
		
		//articles.create(new Article("Office 365", "Micrsoft", 180.0, 2));
		
		System.out.println(articles.read(14));
	}

}
