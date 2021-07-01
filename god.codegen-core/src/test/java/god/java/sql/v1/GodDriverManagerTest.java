package god.java.sql.v1;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.OracleConnection;

@Slf4j
public class GodDriverManagerTest {

	@Test
	public void test() {
		Enumeration<Driver> drivers = DriverManager.getDrivers();

		log.debug("drivers={}", drivers);

		StringBuffer sb = new StringBuffer("\n");

		while (drivers.hasMoreElements()) {
			Driver driver = drivers.nextElement();

			log.debug("driver={}", driver);
			log.debug("driverClassName={}", driver.getClass().getName());
			log.debug("getMajorVersion={}", driver.getMajorVersion());
			log.debug("getMinorVersion={}", driver.getMinorVersion());

			sb.append(driver);
			sb.append("\n");
		}

		log.debug("sb={}", sb);
	}

	public static Connection getConnectionMySQL() {
		try {
			return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306?useInformationSchema=true", "root", "");
		} catch (SQLException e) {
			return null;
		}
	}

	public static Connection getConnectionOracle() {
		try {
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.254:1521:orcl", "com", "com01");

			if (con.isWrapperFor(OracleConnection.class)) {
				OracleConnection ocon = con.unwrap(OracleConnection.class);
				ocon.setRemarksReporting(true);
			}

			return con;
		} catch (SQLException e) {
			return null;
		}
	}

}