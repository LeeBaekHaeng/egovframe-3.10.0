package god.java.sql.v1;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.OracleConnection;

@Slf4j
public class AAA_Test_mysql {

	@Test
	public void test() throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306", "root", "");
//		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306", "com", "com01");
//		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.254:1521:orcl", "system", "orcl");

		if (con.isWrapperFor(OracleConnection.class)) {
			OracleConnection ocon = con.unwrap(OracleConnection.class);
			ocon.setRemarksReporting(true);
		}

		DatabaseMetaData dmd = con.getMetaData();

		ResultSet tables = dmd.getTables("com", null, "comtcadministcode", null);
//		ResultSet tables = dmd.getTables("COM", null, "COMTCADMINISTCODE", null);

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

		ResultSet columns = dmd.getColumns("com", null, "comtcadministcode", null);
//		ResultSet columns = dmd.getColumns("COM", null, "COMTCADMINISTCODE", null);

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
