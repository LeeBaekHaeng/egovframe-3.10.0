package god.java.sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class B1_GodDatabaseMetaDataTest {

	@Test
	public void test() throws SQLException {
		log.debug("test");

		Connection con = A1_GodDriverManagerTest.getConnection();

		DatabaseMetaData dmd = con.getMetaData();

		getDriver(dmd);
		getDatabaseProduct(dmd);
	}

	private void getDriver(DatabaseMetaData dmd) throws SQLException {
		log.debug("getDriver");

		String driverName = dmd.getDriverName();
		String driverVersion = dmd.getDriverVersion();
		int driverMajorVersion = dmd.getDriverMajorVersion();
		int driverMinorVersion = dmd.getDriverMinorVersion();
		log.debug("driverName={}", driverName);
		log.debug("driverVersion={}", driverVersion);
		log.debug("driverMajorVersion={}", driverMajorVersion);
		log.debug("driverMinorVersion={}", driverMinorVersion);
		log.debug("");
	}

	private void getDatabaseProduct(DatabaseMetaData dmd) throws SQLException {
		log.debug("getDatabaseProduct");

		String vender = dmd.getDatabaseProductVersion();
		String databaseProductName = dmd.getDatabaseProductName();
		log.debug("vender={}", vender);
		log.debug("databaseProductName={}", databaseProductName);
		log.debug("");
	}

}
