package god.codegen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GodDriverManagerTest {

	public Connection getConnectionMysql() {
		Connection con = null;
		try {
//			String url = "jdbc:mysql://127.0.0.1:3306/information_schema?useInformationSchema=true";
//			String url = "jdbc:mysql://127.0.0.1:3306/mysql?useInformationSchema=true";
//			String url = "jdbc:mysql://127.0.0.1:3306/performance_schema?useInformationSchema=true";
//			String url = "jdbc:mysql://127.0.0.1:3306/sys?useInformationSchema=true";
			String url = "jdbc:mysql://127.0.0.1:3306/com?useInformationSchema=true"; // host:port, database, properties

//			con = DriverManager.getConnection(url, "root", "");
			con = DriverManager.getConnection(url, "com", "com01"); // user,password
		} catch (SQLException e) {
			System.err.println("SQLException");
		}
		return con;
	}

}
