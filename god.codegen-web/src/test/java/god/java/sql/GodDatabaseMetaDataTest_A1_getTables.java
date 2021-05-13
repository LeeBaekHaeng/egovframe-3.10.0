package god.java.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import model.NameCasing;
import oracle.jdbc.OracleConnection;

@Slf4j
public class GodDatabaseMetaDataTest_A1_getTables {

	private static Connection con;
	private static java.sql.DatabaseMetaData dmd;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		con = GodDriverManagerTest.getConnection();

		log.debug("con={}", con);
		log.debug("");

		if (con.isWrapperFor(OracleConnection.class)) {
			OracleConnection ocon = con.unwrap(OracleConnection.class);
			ocon.setRemarksReporting(true);
		}

		dmd = con.getMetaData();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws SQLException {
//		ResultSet tables = dmd.getTables(null, "COM%", null, null);
		ResultSet tables = dmd.getTables(null, "COM", null, null);
		log.debug("tables={}\n", tables);

		ResultSetMetaData rsmd = tables.getMetaData();
		log.debug("rsmd={}\n", rsmd);

		int columnCount = rsmd.getColumnCount() + 1;
		log.debug("columnCount={}\n", columnCount);

		testA1(rsmd, columnCount);

		testA2(tables, columnCount);
	}

	void testA1(ResultSetMetaData rsmd, int columnCount) throws SQLException {
		StringBuffer sb = new StringBuffer("\n");
		StringBuffer sb2 = new StringBuffer("\n");

		for (int column = 1; column < columnCount; column++) {
			String columnName = rsmd.getColumnName(column);
			NameCasing columnNameNameCasing = new NameCasing(columnName);
			String columnClassName = rsmd.getColumnClassName(column);
			String columnClassName2 = columnClassName.substring(columnClassName.lastIndexOf(".") + 1);

			log.debug("getSchemaName={}", rsmd.getSchemaName(column));
			log.debug("getTableName={}", rsmd.getTableName(column));
			log.debug("columnName={}", columnName);
			log.debug("getColumnLabel={}", rsmd.getColumnLabel(column));
			log.debug("getColumnTypeName={}", rsmd.getColumnTypeName(column));
			log.debug("columnClassName={}", columnClassName);

			if (columnClassName.endsWith("Integer")) {
				// TODO god
			} else {
				sb.append(columnClassName2 + " " + columnNameNameCasing.getCcName() + " = schemas.getString(\""
						+ columnName + "\");\n");

				sb2.append("log.debug(\"" + columnNameNameCasing.getCcName() + "={}\", "
						+ columnNameNameCasing.getCcName() + ");\n");
			}
		}

		log.debug("");

		sb.append("\n");
		sb.append(sb2);

		log.debug("sb={}\n", sb);
	}

	void testA2(ResultSet schemas, int columnCount) throws SQLException {
		log.debug("schemas\n");

		while (schemas.next()) {
//			testA2A1(schemas, columnCount);
			testA2A2(schemas);
		}
	}

	void testA2A1(ResultSet schemas, int columnCount) throws SQLException {
		for (int columnIndex = 1; columnIndex < columnCount; columnIndex++) {
			log.debug("{}={}", schemas.getRow(), schemas.getString(columnIndex));
		}
	}

	void testA2A2(ResultSet schemas) throws SQLException {
		String tableCat = schemas.getString("TABLE_CAT");
		String tableSchem = schemas.getString("TABLE_SCHEM");
		String tableName = schemas.getString("TABLE_NAME");
		String tableType = schemas.getString("TABLE_TYPE");
		String remarks = schemas.getString("REMARKS");

		log.debug("tableCat={}", tableCat);
		log.debug("tableSchem={}", tableSchem);
		log.debug("tableName={}", tableName);
		log.debug("tableType={}", tableType);
		log.debug("remarks={}", remarks);
		log.debug("");
	}

}
