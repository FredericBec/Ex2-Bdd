package fr.fms.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Logger;

public interface Dao<T> {

	public static final Logger logger = Logger.getLogger(Dao.class.getName());
	public Connection connection = BddConnection.getConnection();
	public void create(T obj);
	public T read(int id);
	public boolean update(T obj);
	public boolean delete(T obj);
	public ArrayList<T> readAll();
}
