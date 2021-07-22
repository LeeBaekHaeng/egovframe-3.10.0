package god.java.sql.v2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import org.junit.Test;

import egovframework.com.cmm.util.EgovResourceCloseHelper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ABJ_DatabaseMetaData_getDatabaseProductName_MySQL {

	@Test
	public void test() {
		Connection conn = GodDriverManagerTest.getConnectionMySQL();
		try {
			DatabaseMetaData dmd = conn.getMetaData();

			String databaseProductName = dmd.getDatabaseProductName();

			log.debug("databaseProductName={}", databaseProductName);
		} catch (SQLException e) {
			log.error(e.getMessage());
		} finally {
			EgovResourceCloseHelper.closeDBObjects(conn);
		}
	}

}