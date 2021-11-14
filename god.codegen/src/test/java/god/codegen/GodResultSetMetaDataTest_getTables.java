package god.codegen;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class GodResultSetMetaDataTest_getTables {

	private final GodDriverManagerTest godDriverManagerTest = new GodDriverManagerTest();

	@Test
	void test() throws SQLException {
		System.out.println("test");
		System.out.println("");

		try (

				Connection con = godDriverManagerTest.getConnectionMysql();

		) {
			DatabaseMetaData dmd = con.getMetaData();

			try (

					ResultSet tables = dmd.getTables(null, null, null, null);

			) {
				ResultSetMetaData rsmd = tables.getMetaData();

				int columnCount = rsmd.getColumnCount();
				System.out.println("columnCount=" + columnCount);
				System.out.println("");

				int i = 1;

				while (tables.next()) {
					System.out.println("i=" + i);

					for (int column = 1; column <= columnCount; column++) {
						String columnLabel = rsmd.getColumnLabel(column);
						String columnClassName = rsmd.getColumnClassName(column);

						System.out.println("column=" + column);
						System.out.println("columnLabel=" + columnLabel);
						System.out.println("columnClassName=" + columnClassName);
						System.out.println("");

						if ("java.lang.Integer".equals(columnClassName)) {
							System.out.println(columnLabel + "=" + tables.getInt(columnLabel));
						} else {
							// java.lang.String
							System.out.println(columnLabel + "=" + tables.getString(columnLabel));
						}

						System.out.println("");
					}

					System.out.println("");

					i++;
				}
			}
		}
	}

}
