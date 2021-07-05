package god.java.sql.v2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import egovframework.com.cmm.util.EgovResourceCloseHelper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AAA_GodDatabaseMetaDataTest_MySQL_getTables {

	@Test
	public void test() {
		Connection conn = null;
		ResultSet tables = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306?useInformationSchema=true", "root", "");

			DatabaseMetaData dmd = conn.getMetaData();

			String databaseProductName = dmd.getDatabaseProductName();

			log.debug("databaseProductName={}", databaseProductName);

			tables = dmd.getTables("com", null, null, null);

			while (tables.next()) {
				String tableCat = tables.getString("TABLE_CAT");
				String tableSchem = tables.getString("TABLE_SCHEM");
				String tableName = tables.getString("TABLE_NAME");
				String tableType = tables.getString("TABLE_TYPE");
				String remarks = tables.getString("REMARKS");

				String typeCat = tables.getString("TYPE_CAT");
				String typeSchem = tables.getString("TYPE_SCHEM");
				String typeName = tables.getString("TYPE_NAME");
				String selfReferencingColName = tables.getString("SELF_REFERENCING_COL_NAME");
				String refGeneration = tables.getString("REF_GENERATION");

				log.debug("tableCat={}", tableCat);
				log.debug("tableSchem={}", tableSchem);
				log.debug("tableName={}", tableName);
				log.debug("tableType={}", tableType);
				log.debug("remarks={}", remarks);

				log.debug("typeCat={}", typeCat);
				log.debug("typeSchem={}", typeSchem);
				log.debug("typeName={}", typeName);
				log.debug("selfReferencingColName={}", selfReferencingColName);
				log.debug("refGeneration={}", refGeneration);

				log.debug("");
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		} finally {
			EgovResourceCloseHelper.closeDBObjects(tables, conn);
		}
	}

}