package wallet.util;

import java.sql.SQLException;

public class DBUtil {
	
	public static java.sql.Connection getConnection() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		return java.sql.DriverManager.getConnection("jdbc:mysql://localhost/IBM", "root", "root");
	}

}
