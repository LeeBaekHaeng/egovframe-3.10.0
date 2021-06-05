package god.java.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.OracleConnection;

@Slf4j
public class A1_GodDriverManagerTest {

	public static Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://127.0.0.1:3306/com";
//		String url = "jdbc:oracle:thin:@192.168.0.254:1521:orcl";

		Connection con = DriverManager.getConnection(url, "com", "com01");

		log.debug("con={}", con);
		log.debug("");

		if (con.isWrapperFor(OracleConnection.class)) {
			OracleConnection ocon = con.unwrap(OracleConnection.class);
			ocon.setRemarksReporting(true);
		}

		return con;
	}

}
