package fr.fms.business;

import java.sql.Connection;
import java.util.logging.Logger;

import fr.fms.dao.BddConnection;

public interface Cart<T> {

	public static final Logger logger = Logger.getLogger(Cart.class.getName());
	public Connection connection = BddConnection.getConnection();
	public void addCart(int id);
	public boolean updateCart(T obj);
	public boolean deleteCart(T obj);
	public boolean clearCart();
	public void showCart();
}
