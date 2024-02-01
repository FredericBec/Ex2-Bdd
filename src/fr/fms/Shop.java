package fr.fms;

import fr.fms.dao.ArticleDao;
import fr.fms.entities.Article;

public class Shop {

	public static void main(String[] args) {
		ArticleDao articles = new ArticleDao();
		
		for(Article a : articles.readAll())
			System.out.println(a.getIdArticle() + " - " + a.getDescription() + " - " + a.getBrand() + " - " + a.getUnitaryPrice());
		
		//articles.create(new Article("Office 365", "Micrsoft", 180.0, 2));
		
		System.out.println(articles.read(14));
		
		//Article antivirus = new Article(15, "Antivirus", "Bitedefender", 65.0, 2);
		//articles.update(antivirus);
		
		
		//articles.delete(articles.readAll().get(13));
		
		
		
	}

}
