package god.java.sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class D1_GodDatabaseMetaDataTest_getTables {

	@Test
	public void test() throws SQLException {
		log.debug("test");

		Connection con = A1_GodDriverManagerTest.getConnection();

		DatabaseMetaData dmd = con.getMetaData();

		String vender = dmd.getDatabaseProductVersion();
		String databaseProductName = dmd.getDatabaseProductName();
		log.debug("vender={}", vender);
		log.debug("databaseProductName={}", databaseProductName);
		log.debug("");

		ResultSet tables = dmd.getTables(null, "COM", "COMTC%", null);

		log.debug("tables={}", tables);
		log.debug("");

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
	}

}
