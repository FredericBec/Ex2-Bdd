package fr.fms;

import java.util.Scanner;

//import fr.fms.dao.ArticleDao;
//import fr.fms.dao.UserDao;
import fr.fms.entities.Article;
import fr.fms.entities.User;

public class Shop {

	
	public static void main(String[] args) {
		//ArticleDao articles = new ArticleDao();
		
		//for(Article a : articles.readAll())
			//System.out.println(a.getIdArticle() + " - " + a.getDescription() + " - " + a.getBrand() + " - " + a.getUnitaryPrice());
		
		//articles.create(new Article("Office 365", "Micrsoft", 180.0, 2));
		
		//System.out.println(articles.read(14));
		
		//Article antivirus = new Article(articles.read(15).getIdarticle(), "Antivirus", "Bitedefender", 65.0);
		//articles.update(antivirus);
		//articles.delete(articles.read(13));
		//System.out.println("\n----------------------------------------------\n");
		
		//UserDao users = new UserDao();
		//for(User u : users.readAll())
			//System.out.println(u.getIdUser() + " - " + u.getLogin());
		ShopSingleton users = ShopSingleton.getInstance();
		ShopSingleton articles = ShopSingleton.getInstance();
		
		//for(User u : users.getUsers().readAll())
			//System.out.println(u.getIdUser() + " - " + u.getLogin());
		
		//users.create(new User("Mohamed", "Tr0pF@rt3nJ@v@"));
		//users.create(new User("Mrtial", "Tr0pF@rt3nUm1"));
		//System.out.println(users.read(3));
		//users.update(new User(users.read(6).getIdUser(), "Martial", "Tr0pF@rt3nPyth@n"));
		//users.delete(users.read(4));
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Veuillez entrer votre login :");
		String login = scan.nextLine();
		System.out.println("Veuillez entrer votre mot de passe :");
		String password = scan.nextLine();
		boolean validLogin = users.getUsers().isLogin(login, password);
		
		if(validLogin) {
			for(Article a : articles.getArticles().readAll())
				System.out.println(a.getIdArticle() + " - " + a.getDescription() + " - " + a.getBrand() + " - " + a.getUnitaryPrice());
		}else
			System.out.println("Accès refusé");
		
		scan.close();
		
	}

}
