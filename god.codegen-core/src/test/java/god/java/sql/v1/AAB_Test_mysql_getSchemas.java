package god.java.sql.v1;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AAB_Test_mysql_getSchemas {

	@Test
	public void test() throws SQLException {
//		Connection con = GodDriverManagerTest.getConnectionMysql();
		Connection con = GodDriverManagerTest.getConnectionOracle();

		DatabaseMetaData dmd = con.getMetaData();

		String databaseProductName = dmd.getDatabaseProductName();
		log.debug("databaseProductName={}", databaseProductName);

		ResultSet schemas = dmd.getSchemas();

		log.debug("schemas={}", schemas);
		log.debug("getFetchSize={}", schemas.getFetchSize());

		ResultSetMetaData rsmd = schemas.getMetaData();
		int columnCount = rsmd.getColumnCount() + 1;
		for (int column = 1; column < columnCount; column++) {
			log.debug("getColumnLabel={}", rsmd.getColumnLabel(column));
		}

		while (schemas.next()) {
			String tableSchem = schemas.getString("TABLE_SCHEM");

			log.debug("tableSchem={}", tableSchem);

			if ("MySQL".equals(databaseProductName)) {
				String tableCatalog = schemas.getString("TABLE_CATALOG");

				log.debug("tableCatalog={}", tableCatalog);
			}
		}
	}

}