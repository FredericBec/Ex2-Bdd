package fr.fms.entities;

public class User {

	private int IdUser;
	private String Login;
	private String password;
	
	public User(int idUser, String login, String password) {
		IdUser = idUser;
		Login = login;
		this.password = password;
	}

	public User(int idUser, String login) {
		IdUser = idUser;
		Login = login;
	}

	public User(String login, String password) {
		super();
		Login = login;
		this.password = password;
	}

	public int getIdUser() {
		return IdUser;
	}

	public void setIdUser(int idUser) {
		IdUser = idUser;
	}

	public String getLogin() {
		return Login;
	}

	public void setLogin(String login) {
		Login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [IdUser=" + IdUser + ", Login=" + Login + ", password=" + password + "]";
	}

}
