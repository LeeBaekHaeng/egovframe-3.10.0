package god.codegen;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class GodDatabaseMetaDataTest_getColumns {

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

//					ResultSet columns = databaseMetaData.getColumns(null, null, null, null);
//					ResultSet columns = databaseMetaData.getColumns("com", null, null, null); // catalog
//					ResultSet columns = databaseMetaData.getColumns(null, "com%", null, null); // schemaPattern
					ResultSet columns = databaseMetaData.getColumns(null, null, "%", null); // tableNamePattern

			) {
				int i = 1;

				while (columns.next()) {
					String tableCat = columns.getString("TABLE_CAT");
					String tableSchem = columns.getString("TABLE_SCHEM");
					String tableName = columns.getString("TABLE_NAME");
					String columnName = columns.getString("COLUMN_NAME");
//					String dataType = columns.getString("DATA_TYPE");
					String typeName = columns.getString("TYPE_NAME");
//					int columnSize = columns.getInt("COLUMN_SIZE");
//					int decimalDigits = columns.getInt("DECIMAL_DIGITS");
//					int numPrecRadix = columns.getInt("NUM_PREC_RADIX");
//					int nullable = columns.getInt("NULLABLE");
					String remarks = columns.getString("REMARKS");
					String isNullable = columns.getString("IS_NULLABLE");

					System.out.println("i=" + i);
					System.out.println("tableCat=" + tableCat);
					System.out.println("tableSchem=" + tableSchem);
					System.out.println("tableName=" + tableName);
					System.out.println("columnName=" + columnName);
//					System.out.println("dataType=" + dataType);
					System.out.println("typeName=" + typeName);
//					System.out.println("columnSize=" + columnSize);
//					System.out.println("decimalDigits=" + decimalDigits);
//					System.out.println("numPrecRadix=" + numPrecRadix);
//					System.out.println("nullable=" + nullable);
					System.out.println("remarks=" + remarks);
					System.out.println("isNullable=" + isNullable);
					System.out.println("");

					i++;
				}

//				columns.close();
			}

//			con.close();
		}
	}

}
