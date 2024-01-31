package fr.fms.jdbc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class CreateConfigFile {

	public static void main(String[] args) {
		
		Properties props = new Properties();
		
		props.put("db.driver.class", "org.mariadb.jdbc.Driver");
		props.put("db.url", "jdbc:mariadb://localhost:3306/shop");
		props.put("db.login", "root");
		props.put("db.password", "fms2024");
		try {
			FileOutputStream config = new FileOutputStream(new File("config.properties"));
			props.store(config, "Configuration de la base de données");
			System.out.println("Fichier de configuration créé .....");
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
