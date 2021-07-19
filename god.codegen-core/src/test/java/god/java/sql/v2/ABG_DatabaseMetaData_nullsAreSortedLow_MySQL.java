package god.java.sql.v2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import org.junit.Test;

import egovframework.com.cmm.util.EgovResourceCloseHelper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ABG_DatabaseMetaData_nullsAreSortedLow_MySQL {

	@Test
	public void test() {
		Connection conn = GodDriverManagerTest.getConnectionMySQL();
		try {
			DatabaseMetaData dmd = conn.getMetaData();

			boolean nullsAreSortedLow = dmd.nullsAreSortedLow();

			log.debug("nullsAreSortedLow={}", nullsAreSortedLow);
		} catch (SQLException e) {
			log.error(e.getMessage());
		} finally {
			EgovResourceCloseHelper.closeDBObjects(conn);
		}
	}

}