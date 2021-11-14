package god.codegen;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class GodDatabaseMetaDataTest_getTables {

	private final GodDriverManagerTest godDriverManagerTest = new GodDriverManagerTest();

	@Test
	void test() throws SQLException {
		System.out.println("test");
		System.out.println("");

		try (

				Connection con = godDriverManagerTest.getConnectionMysql();

		) {
			DatabaseMetaData databaseMetaData = con.getMetaData();

			try (

//					ResultSet tables = databaseMetaData.getTables(null, null, null, null);
//					ResultSet tables = databaseMetaData.getTables("com", null, null, null); // catalog
//					ResultSet tables = databaseMetaData.getTables(null, "com", null, null); // schemaPattern
					ResultSet tables = databaseMetaData.getTables(null, null, "%comtc%", null); // tableNamePattern

			) {
				int i = 1;

				while (tables.next()) {
					String tableCat = tables.getString("TABLE_CAT");
					String tableSchem = tables.getString("TABLE_SCHEM");
					String tableName = tables.getString("TABLE_NAME");
					String tableType = tables.getString("TABLE_TYPE");
					String remarks = tables.getString("REMARKS");

					System.out.println("i=" + i);
					System.out.println("tableCat=" + tableCat);
					System.out.println("tableSchem=" + tableSchem);
					System.out.println("tableName=" + tableName);
					System.out.println("tableType=" + tableType);
					System.out.println("remarks=" + remarks);
					System.out.println("");

					i++;
				}

//				tables.close();
			}

//			con.close();
		}
	}

}
