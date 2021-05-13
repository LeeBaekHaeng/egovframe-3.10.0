package god.java.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GodDriverManagerTest {

	public static Connection getConnection() {
//		return getConnectionA1();
		return getConnectionA2();
	}

	static Connection getConnectionA1() {
		try {
			return DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.254:1521:orcl", "com", "com01");
		} catch (SQLException e) {
			log.error(e.getMessage());
			return null;
		}
	}

	static Connection getConnectionA2() {
		try {
			return DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.254:1521:orcl", "test", "test");
		} catch (SQLException e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
