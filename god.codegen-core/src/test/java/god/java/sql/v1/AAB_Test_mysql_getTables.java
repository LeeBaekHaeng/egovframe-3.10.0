package god.java.sql.v1;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import egovframework.com.cmm.util.EgovResourceCloseHelper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AAB_Test_mysql_getTables {

	@Test
	public void test() throws SQLException {
		Connection conn = null;
		ResultSet tables = null;
		try {
			conn = GodDriverManagerTest.getConnectionMySQL();
//			conn = GodDriverManagerTest.getConnectionOracle();

			DatabaseMetaData dmd = conn.getMetaData();

			String databaseProductName = dmd.getDatabaseProductName();
			log.debug("databaseProductName={}", databaseProductName);

			String catalog = null;
			String schemaPattern = null;
			String tableNamePattern = null;
			String types[] = null;

			if ("MySQL".equals(databaseProductName)) {
				catalog = "com";
			} else if ("Oracle".equals(databaseProductName)) {
				schemaPattern = "COM";
			}

			tables = dmd.getTables(catalog, schemaPattern, tableNamePattern, types);

			log.debug("tables={}", tables);
			log.debug("getFetchSize={}", tables.getFetchSize());

//			GodResultSetMetaDataTest.debug(tables, false);
			GodResultSetMetaDataTest.debug(tables, true);

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

				if ("MySQL".equals(databaseProductName)) {
					String typeCat = tables.getString("TYPE_CAT");
					String typeSchem = tables.getString("TYPE_SCHEM");
					String typeName = tables.getString("TYPE_NAME");
					String selfReferencingColName = tables.getString("SELF_REFERENCING_COL_NAME");
					String refGeneration = tables.getString("REF_GENERATION");

					log.debug("typeCat={}", typeCat);
					log.debug("typeSchem={}", typeSchem);
					log.debug("typeName={}", typeName);
					log.debug("selfReferencingColName={}", selfReferencingColName);
					log.debug("refGeneration={}", refGeneration);
				}

				log.debug("");
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
			log.error("getErrorCode={}", e.getErrorCode());
			log.error("getNextException={}", e.getNextException());
			log.error("getSQLState={}", e.getSQLState());
		} finally {
			EgovResourceCloseHelper.closeDBObjects(tables, conn);
		}
	}

}