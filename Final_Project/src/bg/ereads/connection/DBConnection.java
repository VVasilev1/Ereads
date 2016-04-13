package bg.ereads.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static final String MYSQL_USER = "root";
	private static final String MYSQL_PASS = "signature1";

	private static final String MYDB = "goodreads";
	private static final String JDBC_MYSQL_LOCALHOST = "jdbc:mysql://127.0.0.1:3306/";
	private static DBConnection instance = null;
	private Connection conn;

	private DBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(JDBC_MYSQL_LOCALHOST + MYDB, MYSQL_USER, MYSQL_PASS);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static synchronized DBConnection getInstance() {
		if (instance == null) {
			instance = new DBConnection();
		}
		return instance;
	}

	public Connection getConn() {
		return conn;
	}
}
