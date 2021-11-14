package god.codegen;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;
import org.junit.jupiter.api.Test;

class GodSqlA1InsertTest {

	private final GodDriverManagerTest godDriverManagerTest = new GodDriverManagerTest();

	@Test
	void test() {
		System.out.println("test");
		System.out.println("");

		StringBuffer sb = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		StringBuffer sb3 = new StringBuffer();

		try (

				Connection con = godDriverManagerTest.getConnectionMysql();

		) {
			DatabaseMetaData dmd = con.getMetaData();

			String catalog = null;
			String schemaPattern = null;

			String tableNamePattern = null;
//			tableNamePattern = "comtccmmnclcode"; // 공통분류코드
//			tableNamePattern = "comtccmmncode"; // 공통코드
//			tableNamePattern = "comtccmmndetailcode"; // 공통상세코드
//			tableNamePattern = "comtczip"; // 우편번호
//			tableNamePattern = "comtecopseq"; // COMTECOPSEQ

			String[] types = null;

			try (

					ResultSet tables = dmd.getTables(catalog, schemaPattern, tableNamePattern, types);

			) {
				int table = 1;

				while (tables.next()) {
					String tableCat = tables.getString("TABLE_CAT");
					String tableSchem = tables.getString("TABLE_SCHEM");
					String tableName = tables.getString("TABLE_NAME");
					String tableType = tables.getString("TABLE_TYPE");
					String remarks = tables.getString("REMARKS");

					System.out.println("table=" + table);
					System.out.println("tableCat=" + tableCat);
					System.out.println("tableSchem=" + tableSchem);
					System.out.println("tableName=" + tableName);
					System.out.println("tableType=" + tableType);
					System.out.println("remarks=" + remarks);
					System.out.println("");

					sb.setLength(0);
					sb2.setLength(0);
					sb3.setLength(0);

					sb2.append("INSERT INTO ");
					sb2.append(tableName);
					sb2.append(" ( /* ");
					sb2.append(remarks);
					sb2.append(" */\n");

					sb3.append(") VALUES (\n");

					try (

							ResultSet columns = dmd.getColumns(tableCat, tableSchem, tableName, null);

					) {
						int row = 0;
						if (columns.last()) {
							row = columns.getRow();
							columns.beforeFirst();
						}
						System.out.println("row=" + row);

						int column = 1;

						while (columns.next()) {
//							String tableCat = columns.getString("TABLE_CAT");
//							String tableSchem = columns.getString("TABLE_SCHEM");
//							String tableName = columns.getString("TABLE_NAME");
							String columnName = columns.getString("COLUMN_NAME");
//							String dataType = columns.getString("DATA_TYPE");
							String typeName = columns.getString("TYPE_NAME");
//							int columnSize = columns.getInt("COLUMN_SIZE");
//							int decimalDigits = columns.getInt("DECIMAL_DIGITS");
//							int numPrecRadix = columns.getInt("NUM_PREC_RADIX");
//							int nullable = columns.getInt("NULLABLE");
							String columnRemarks = columns.getString("REMARKS");
							String isNullable = columns.getString("IS_NULLABLE");

							System.out.println("column=" + column);
							System.out.println("tableCat=" + tableCat);
							System.out.println("tableSchem=" + tableSchem);
							System.out.println("tableName=" + tableName);
							System.out.println("columnName=" + columnName);
//							System.out.println("dataType=" + dataType);
							System.out.println("typeName=" + typeName);
//							System.out.println("columnSize=" + columnSize);
//							System.out.println("decimalDigits=" + decimalDigits);
//							System.out.println("numPrecRadix=" + numPrecRadix);
//							System.out.println("nullable=" + nullable);
							System.out.println("columnRemarks=" + columnRemarks);
							System.out.println("isNullable=" + isNullable);
							System.out.println("");

							String value = GodSqlUtilsTest.getValue(columnName, typeName);

							sb2.append("    ");
							sb2.append(columnName);

							if (column < row) {
								sb2.append(",");
							}

							sb2.append(" /* ");
							sb2.append(columnRemarks);
							sb2.append(" */\n");

							sb3.append("    ");
							sb3.append(value);

							if (column < row) {
								sb3.append(",");
							}

							sb3.append(" /* ");
							sb3.append(columnName);
							sb3.append(", ");
							sb3.append(columnRemarks);
							sb3.append(" */\n");

							column++;
						}
					}

					sb.append(sb2);
					sb.append(sb3);
					sb.append(")\n;");

					System.out.println(sb);

					FileUtils.writeStringToFile(
							new File(SystemUtils.USER_HOME + "/Desktop/god.codegen/" + tableName + " " + remarks + "/"
									+ tableName + " " + remarks + "-a1-insert.sql"),
							sb.toString(), StandardCharsets.UTF_8);

					table++;
				}
			} catch (IOException e) {
				System.err.println("IOException");
			}
		} catch (SQLException e) {
			System.err.println("SQLException");
		}
	}

}
