package god.java.sql.v2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import org.junit.Test;

import egovframework.com.cmm.util.EgovResourceCloseHelper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ABA_DatabaseMetaData_allProceduresAreCallable_MySQL {

	@Test
	public void test() {
		Connection conn = GodDriverManagerTest.getConnectionMySQL();
		try {
			DatabaseMetaData dmd = conn.getMetaData();

			boolean allProceduresAreCallable = dmd.allProceduresAreCallable();

			log.debug("allProceduresAreCallable={}", allProceduresAreCallable);
		} catch (SQLException e) {
			log.error(e.getMessage());
		} finally {
			EgovResourceCloseHelper.closeDBObjects(conn);
		}
	}

}