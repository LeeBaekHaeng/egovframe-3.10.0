package god.java.sql.v2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import org.junit.Test;

import egovframework.com.cmm.util.EgovResourceCloseHelper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ABH_DatabaseMetaData_nullsAreSortedAtStart_Oracle {

	@Test
	public void test() {
		Connection conn = GodDriverManagerTest.getConnectionOracle();
		try {
			DatabaseMetaData dmd = conn.getMetaData();

			boolean nullsAreSortedAtStart = dmd.nullsAreSortedAtStart();

			log.debug("nullsAreSortedAtStart={}", nullsAreSortedAtStart);
		} catch (SQLException e) {
			log.error(e.getMessage());
		} finally {
			EgovResourceCloseHelper.closeDBObjects(conn);
		}
	}

}