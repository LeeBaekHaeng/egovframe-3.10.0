package god.java.sql.v2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.OracleConnection;

@Slf4j
public class GodDriverManagerTest {

	public static Connection getConnectionMySQL() {
		Connection conn = null;

		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306?useInformationSchema=true", "root", "");
		} catch (SQLException e) {
			log.error(e.getMessage());
		}

		return conn;
	}

	public static Connection getConnectionOracle() {
		Connection conn = null;

		try {
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.254:1521:orcl", "system", "orcl");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.254:1521:orcl", "com", "com01");

			if (conn.isWrapperFor(OracleConnection.class)) {
				OracleConnection ocon = conn.unwrap(OracleConnection.class);
				ocon.setRemarksReporting(true);
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}

		return conn;
	}

}