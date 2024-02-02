package fr.fms;

import java.util.ArrayList;
import java.util.Scanner;

//import fr.fms.dao.ArticleDao;
//import fr.fms.dao.UserDao;
import fr.fms.entities.Article;
import fr.fms.entities.User;

public class Shop {

	public static Scanner scan = new Scanner(System.in);
	public static ShopSingleton shop = ShopSingleton.getInstance();
	public static ArrayList<String> menu = new ArrayList<String>();
	
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
		
		
		//for(User u : users.getUsers().readAll())
			//System.out.println(u.getIdUser() + " - " + u.getLogin());
		
		//users.create(new User("Mohamed", "Tr0pF@rt3nJ@v@"));
		//users.create(new User("Mrtial", "Tr0pF@rt3nUm1"));
		//System.out.println(users.read(3));
		//users.update(new User(users.read(6).getIdUser(), "Martial", "Tr0pF@rt3nPyth@n"));
		//users.delete(users.read(4));
		
		
		/*
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
		*/
		welcome();
		start();
		
		scan.close();
		
	}
	
	public static void start() {
		displayMenu();
		System.out.println("Que souhaitez-vous faire ?");
		
		switch(getInfo(scan, menu)) {
		case "Afficher la liste des articles" :
			displayArticleList();
			break;
		case "Commander" :
			order();
			break;
		default :
			System.exit(0);
		
		}
		
		
	}
	
	public static void welcome() {
		System.out.println("+-----------------------------------------------------------+");
		System.out.println("|" + "\t\tBienvenue dans le bon coin du gamer\t    " + "|");
		System.out.println("+-----------------------------------------------------------+");
	}
	
	public static ArrayList<String> displayMenu() {
		menu.add("Afficher la liste des articles");
		menu.add("Commander");
		menu.add("Sortir");
		int index = 1;
		for(String m : menu)
			System.out.println(index++ + " - " + m);
		return menu;
	}
	
	public static ArrayList<Article> displayArticleList() {
		System.out.println("+----------------- Liste des articles ----------------------+");
		for(Article a : shop.getArticles().readAll())
			System.out.println("  " + a.getIdArticle() + " - " + a.getDescription() + " - " + a.getBrand() + " - " + a.getUnitaryPrice());
		return shop.getArticles().readAll();
	}
	
	public static <T> T getInfo(Scanner scan, ArrayList<T> list) {
		int maxChoice = list.size();
		
		int userChoice = 0;
		while(userChoice < 1 || userChoice > maxChoice) {
			userChoice = scan.nextInt();
		}
		
		return list.get(userChoice - 1);
	}
	
	public static void order() {
		boolean continueShop = true;
		while(continueShop) {
			System.out.println("Que souhaitez-vous commander");
			Article articleChoice = getInfo(scan, displayArticleList());
			shop.getCart().addCart(articleChoice.getIdArticle());
			System.out.println("Avez-vous fini vos achats ?");
			scan.nextLine();
			String response = scan.nextLine();
			if(response.equalsIgnoreCase("oui")) {
				continueShop = false;
				
			}
			
		}
		
		System.out.println("Souhaitez-vous afficher votre panier ?");
		String response = scan.nextLine();
		if(response.equalsIgnoreCase("oui"))
			shop.getCart().showCart();
		System.out.println("Pour retourner au menu, appuyer sur +");
		response = scan.nextLine();
		if(response.equalsIgnoreCase("+"))
			start();
		scan.nextLine();
	}

}
