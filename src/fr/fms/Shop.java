package fr.fms;

import java.util.ArrayList;
import java.util.Scanner;

import fr.fms.business.ArticleCart;
//import fr.fms.dao.ArticleDao;
//import fr.fms.dao.UserDao;
import fr.fms.entities.Article;
import fr.fms.entities.User;

public class Shop {

	public static Scanner scan = new Scanner(System.in);
	private static ArticleCart cart = new ArticleCart();
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
		int choice = 0;
		System.out.println("Que souhaitez-vous faire ?");
		choice = getInt();
		while(choice != 5) {
			
			switch(choice) {
			case 1 :
				addArticle();
				break;
			case 2 :
				
			case 3 :
				displayArticleList();
				break;
			case 4 :
				order();
				break;
			case 5 :
				System.exit(0);
			default :
				System.out.println("Veuillez saisir une valeur compris entre 1 et 5 !");
			}
		}
		
		
	}
	
	public static void welcome() {
		System.out.println("+-----------------------------------------------------------+");
		System.out.println("|" + "\t\tBienvenue dans le bon coin du gamer\t    " + "|");
		System.out.println("+-----------------------------------------------------------+");
		displayArticleList();

	}
	
	public static void displayMenu() {
		menu.add("Ajouter un article");
		menu.add("Retirer un article");
		menu.add("Commander");
		menu.add("Sortir");
		int index = 1;
		for(String m : menu) {
			System.out.println(index++ + " - " + m);
		}
	}
	
	public static void displayArticleList() {
		System.out.println("+----------------- Liste des articles ----------------------+");
		cart.readArticles().stream().forEach(System.out::println);
		System.out.println("+-----------------------------------------------------------+");
	}
	
	public static void addArticle() {
		displayArticleList();
		System.out.println("Sélectionner l'article");
		int id = getInt();
		Article article = cart.readOneArticle(id);
		if(article != null) {
			cart.addToCart(article);
		}else
			System.out.println("L'article que vous souhaitez n'existe pas !!");
	}
	
	public static void removeArticle() {
		System.out.println("Selectionner l'id de l'artircle à retirer");
		int id = getInt();
		Article article = cart.readOneArticle(id);
		cart.deleteFromCart(article);
	}
	
	public static int getInt() {
		if(!scan.hasNext()) {
			System.out.println("Saississez ue valeur entière !!");
			scan.next();
		}
		
		return scan.nextInt();
	}
	
	public static void order() {
		
	}

}
