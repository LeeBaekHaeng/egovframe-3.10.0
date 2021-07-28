package god.java.sql.v2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import org.junit.Test;

import egovframework.com.cmm.util.EgovResourceCloseHelper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ABN_DatabaseMetaData_getDriverMajorVersion_MySQL {

	@Test
	public void test() {
		Connection conn = GodDriverManagerTest.getConnectionMySQL();
		try {
			DatabaseMetaData dmd = conn.getMetaData();

			int driverMajorVersion = dmd.getDriverMajorVersion();

			log.debug("driverMajorVersion={}", driverMajorVersion);
		} catch (SQLException e) {
			log.error(e.getMessage());
		} finally {
			EgovResourceCloseHelper.closeDBObjects(conn);
		}
	}

}