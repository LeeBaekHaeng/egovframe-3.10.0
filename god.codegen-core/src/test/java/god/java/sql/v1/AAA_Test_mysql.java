package god.java.sql.v1;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AAA_Test_mysql {

	@Test
	public void test() {
		try {
//			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306", "root", "");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306?useInformationSchema=true",
					"root", "");
//			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/com", "com", "com01");
//			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/com?useInformationSchema=true",
//					"com", "com01");

			DatabaseMetaData dmd = con.getMetaData();

			String catalog = "com";
			String schemaPattern = null;
			String tableNamePattern = "comtcadministcode";

//		catalog = "COM";
//		tableNamePattern = "COMTCADMINISTCODE";

			catalog = null;
			tableNamePattern = null;

			getTables(dmd, catalog, schemaPattern, tableNamePattern);
			getColumns(dmd, catalog, schemaPattern, tableNamePattern);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	void getTables(DatabaseMetaData dmd, String catalog, String schemaPattern, String tableNamePattern)
			throws SQLException {
		ResultSet tables = dmd.getTables(catalog, schemaPattern, tableNamePattern, null);

		int i = 1;

		while (tables.next()) {
			String tableCat = tables.getString("TABLE_CAT");
			String tableSchem = tables.getString("TABLE_SCHEM");
			String tableName = tables.getString("TABLE_NAME");
			String remarks = tables.getString("REMARKS");

			log.debug("i={}", i);
			log.debug("tableCat={}", tableCat);
			log.debug("tableSchem={}", tableSchem);
			log.debug("tableName={}", tableName);
			log.debug("remarks={}", remarks);
			log.debug("");

			i++;
		}
	}

	void getColumns(DatabaseMetaData dmd, String catalog, String schemaPattern, String tableNamePattern)
			throws SQLException {
		ResultSet columns = dmd.getColumns(catalog, schemaPattern, tableNamePattern, null);

		int j = 1;

		while (columns.next()) {
			String tableCat = columns.getString("TABLE_CAT");
			String tableSchem = columns.getString("TABLE_SCHEM");
			String tableName = columns.getString("TABLE_NAME");
			String columnName = columns.getString("COLUMN_NAME");
			String remarks = columns.getString("REMARKS");

			log.debug("j={}", j);
			log.debug("tableCat={}", tableCat);
			log.debug("tableSchem={}", tableSchem);
			log.debug("tableName={}", tableName);
			log.debug("columnName={}", columnName);
			log.debug("remarks={}", remarks);
			log.debug("");

			j++;
		}
	}

}
