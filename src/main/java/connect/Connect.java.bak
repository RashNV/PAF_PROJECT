package connect;

import java.sql.*;

public class Connect {
	
	private static Connection connection;

	public static Connection getConnection() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.jdbc.Driver");
		if (connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/EGS_DB", "root", "");
		}
		
		return connection;
	}

}
