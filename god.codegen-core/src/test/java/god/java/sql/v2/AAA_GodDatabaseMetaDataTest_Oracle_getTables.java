package god.java.sql.v2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import egovframework.com.cmm.util.EgovResourceCloseHelper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AAA_GodDatabaseMetaDataTest_Oracle_getTables {

	@Test
	public void test() {
		Connection conn = GodDriverManagerTest.getConnectionOracle();
		ResultSet tables = null;
		try {
			DatabaseMetaData dmd = conn.getMetaData();

			String databaseProductName = dmd.getDatabaseProductName();

			log.debug("databaseProductName={}", databaseProductName);

			tables = dmd.getTables(null, "COM", null, null);

			while (tables.next()) {
				String tableCat = tables.getString("TABLE_CAT");
				String tableSchem = tables.getString("TABLE_SCHEM");
				String tableName = tables.getString("TABLE_NAME");
				String tableType = tables.getString("TABLE_TYPE");
				String remarks = tables.getString("REMARKS");

				log.debug("tableCat={}", tableCat);
				log.debug("tableSchem={}", tableSchem);
				log.debug("tableName={}", tableName);
				log.debug("tableType={}", tableType);
				log.debug("remarks={}", remarks);

				log.debug("");
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		} finally {
			EgovResourceCloseHelper.closeDBObjects(tables, conn);
		}
	}

}