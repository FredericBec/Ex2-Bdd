package fr.fms.dao;

//import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.fms.entities.User;

public class UserDao implements Dao<User>{
	public static ArrayList<User> users = new ArrayList<User>();
	//public Connection connection = BddConnection.getConnection();
	
	public boolean isLogin(String login, String password) {
		String loginQuery = "SELECT * FROM T_Users WHERE Login = ? AND Password = ?;";
		try(PreparedStatement ps = connection.prepareStatement(loginQuery)) {
			ps.setString(1, login);
			ps.setString(2, password);
			try(ResultSet resultSet = ps.executeQuery()){
				return resultSet.next();
			}
		} catch (SQLException e) {
			logger.severe("Erreur de saisie" + e.getMessage());
		}
		return false;
	}
	
	@Override
	public void create(User user) {
		String createUser = "INSERT INTO T_Users(Login, Password) VALUES (?,?);";
		try(PreparedStatement ps = connection.prepareStatement(createUser)) {
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());
			if(ps.executeUpdate() == 1)
				System.out.println("Nouvel utilisateur " + user.getLogin() + " créé");
		} catch (SQLException e) {
			logger.severe("Problème de création d'un utilisateur" + e.getMessage());
		}
		
	}

	@Override
	public User read(int id) {
		User user = null;
		String readUser = "SELECT IdUser, Login, Password FROM T_Users WHERE IdUser = ?;";
		try(PreparedStatement ps = connection.prepareStatement(readUser)) {
			ps.setInt(1, users.get(id - 1).getIdUser());
			try(ResultSet resultSet = ps.executeQuery()){
				if(resultSet.next()) {
					int rsIdUser = resultSet.getInt("IdUser");
					String rsLogin = resultSet.getString("Login");
					String rsPassword = resultSet.getString("Password");
					System.out.println("Utilisateur trouvé");
					return user = new User(rsIdUser,rsLogin, rsPassword);
				}else
					System.out.println("Utilisateur non trouvé");
			}
		} catch (SQLException e) {
			logger.severe("Problème de lecture de l'utilisateur" + e.getMessage());
		}
		return user;
	}

	@Override
	public boolean update(User user) {
		String updateUser = "UPDATE T_Users SET Login = ?, Password = ? WHERE IdUser = ?;";
		try(PreparedStatement ps = connection.prepareStatement(updateUser)) {
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());
			ps.setInt(3, user.getIdUser());
			if(ps.executeUpdate() == 1) {
				System.out.println("Mise à jour effectuée");
				return true;
			}
		} catch (SQLException e) {
			logger.severe("Problème de mise à jour de l'utilisateur" + e.getMessage());
		}
		return false;
	}

	@Override
	public boolean delete(User user) {
		String removeUser = "DELETE FROM T_Users WHERE IdUser = ?";
		try(PreparedStatement ps = connection.prepareStatement(removeUser)) {
			ps.setInt(1, user.getIdUser());
			if(ps.executeUpdate() == 1) {
				System.out.println("Supréssion effectué");
				return true;
			}
		} catch (SQLException e) {
			logger.severe("Problème de suppression de l'utilisateur" + e.getMessage());
		}
		return false;
	}

	@Override
	public ArrayList<User> readAll() {
		String usersList = "SELECT * FROM T_Users;";
		try(Statement st = connection.createStatement()) {
			try(ResultSet resultSet = st.executeQuery(usersList)){
				while(resultSet.next()) {
					int rsIdUser = resultSet.getInt("IdUser");
					String rsLogin = resultSet.getString("Login");
					String rsPassword = resultSet.getString("Password");
					users.add(new User(rsIdUser, rsLogin,rsPassword));
				}
			}
		} catch (SQLException e) {
			logger.severe("Liste d'utilisateurs non trouvée " + e.getMessage());
		}
		return users;
	}

	
}
