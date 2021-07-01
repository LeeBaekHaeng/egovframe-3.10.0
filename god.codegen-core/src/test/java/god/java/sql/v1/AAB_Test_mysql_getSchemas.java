package god.java.sql.v1;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.junit.Test;

import egovframework.com.cmm.util.EgovResourceCloseHelper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AAB_Test_mysql_getSchemas {

	@Test
	public void test() throws SQLException {
//		Connection con = GodDriverManagerTest.getConnectionMysql();
		Connection con = GodDriverManagerTest.getConnectionOracle();
//		con.close();

		DatabaseMetaData dmd = con.getMetaData();
//		con.close();

		String databaseProductName = dmd.getDatabaseProductName();
		log.debug("databaseProductName={}", databaseProductName);

//		con.close();

		ResultSet schemas = dmd.getSchemas();

//		con.close();

		log.debug("schemas={}", schemas);
		log.debug("getFetchSize={}", schemas.getFetchSize());

//		con.close();

		ResultSetMetaData rsmd = schemas.getMetaData();
//		con.close();
		int columnCount = rsmd.getColumnCount() + 1;
		for (int column = 1; column < columnCount; column++) {
			log.debug("getColumnLabel={}", rsmd.getColumnLabel(column));
		}
//		con.close();
		while (schemas.next()) {
			String tableSchem = schemas.getString("TABLE_SCHEM");

			log.debug("tableSchem={}", tableSchem);

			if ("MySQL".equals(databaseProductName)) {
				String tableCatalog = schemas.getString("TABLE_CATALOG");

				log.debug("tableCatalog={}", tableCatalog);
			}
		}
		con.close();
	}

	@Test
	public void test2() {
		Connection con = null;
		ResultSet schemas = null;
		try {
//		Connection con = GodDriverManagerTest.getConnectionMysql();
			con = GodDriverManagerTest.getConnectionOracle();

			DatabaseMetaData dmd = con.getMetaData();

			String databaseProductName = dmd.getDatabaseProductName();
			log.debug("databaseProductName={}", databaseProductName);

			schemas = dmd.getSchemas();

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
		} catch (SQLException e) {
			log.error(e.getMessage());
		} finally {
//			EgovResourceCloseHelper.closeDBObjects(con);
			EgovResourceCloseHelper.closeDBObjects(schemas, con);
//			EgovResourceCloseHelper.closeDBObjects(con, schemas);
		}
	}

}