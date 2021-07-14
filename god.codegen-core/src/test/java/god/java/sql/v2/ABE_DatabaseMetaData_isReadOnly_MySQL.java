package god.java.sql.v2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import org.junit.Test;

import egovframework.com.cmm.util.EgovResourceCloseHelper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ABE_DatabaseMetaData_isReadOnly_MySQL {

	@Test
	public void test() {
		Connection conn = GodDriverManagerTest.getConnectionMySQL();
		try {
			DatabaseMetaData dmd = conn.getMetaData();

			boolean isReadOnly = dmd.isReadOnly();

			log.debug("isReadOnly={}", isReadOnly);
		} catch (SQLException e) {
			log.error(e.getMessage());
		} finally {
			EgovResourceCloseHelper.closeDBObjects(conn);
		}
	}

}