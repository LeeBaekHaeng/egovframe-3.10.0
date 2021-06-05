package god.java.sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class C1_GodResultSetMetaDataTest_getTables {

	@Test
	public void test() throws SQLException {
		log.debug("test");

		Connection con = A1_GodDriverManagerTest.getConnection();

		DatabaseMetaData dmd = con.getMetaData();
		log.debug("dmd={}", dmd);
		log.debug("");

		ResultSet tables = dmd.getTables(null, "COM", "COMTC%", null);
		log.debug("tables={}", tables);
		log.debug("");

		ResultSetMetaData rsmd = tables.getMetaData();
		log.debug("rsmd={}", rsmd);
		log.debug("");

		int columnCount = rsmd.getColumnCount();
		log.debug("columnCount={}", columnCount);
		log.debug("");

		for (int column = 1; column < columnCount; column++) {
			String columnLabel = rsmd.getColumnLabel(column);
			String columnName = rsmd.getColumnName(column);
			int columnType = rsmd.getColumnType(column);
			String columnTypeName = rsmd.getColumnTypeName(column);
			String columnClassName = rsmd.getColumnClassName(column);

			log.debug("columnLabel={}", columnLabel);
			log.debug("columnName={}", columnName);
			log.debug("columnType={}", columnType);
			log.debug("columnTypeName={}", columnTypeName);
			log.debug("columnClassName={}", columnClassName);
			log.debug("");
		}
	}

}
