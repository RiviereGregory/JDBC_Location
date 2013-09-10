package fr.treeptik.jdbclocation.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
	
	private static Connection connection;

	public static Connection getConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException  {
		if (connection == null || connection.isClosed()) {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/location", "root", "root");
			connection.setAutoCommit(false);

		}

		return connection;
	}

}
