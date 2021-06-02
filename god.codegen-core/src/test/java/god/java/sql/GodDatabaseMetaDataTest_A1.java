package god.java.sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.OracleConnection;

@Slf4j
public class GodDatabaseMetaDataTest_A1 {

	@Test
	public void test() throws Exception {
		log.debug("test");

//		Class.forName("com.mysql.jdbc.Driver");

		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/com", "com", "com01");
//		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.254:1521:orcl", "com", "com01");

		log.debug("con={}", con);
		log.debug("");

		if (con.isWrapperFor(OracleConnection.class)) {
			OracleConnection ocon = con.unwrap(OracleConnection.class);
			ocon.setRemarksReporting(true);
		}

		DatabaseMetaData dmd = con.getMetaData();

		ResultSet tables = dmd.getTables(null, "COM", "COMTC%", null);

		log.debug("tables={}", tables);
		log.debug("");

		while (tables.next()) {
			String tableCat = tables.getString("TABLE_CAT");
			String tableSchem = tables.getString("TABLE_SCHEM");
			String tableName = tables.getString("TABLE_NAME");
			String tableType = tables.getString("TABLE_TYPE");
			String remarks = tables.getString("REMARKS");
//			String typeCat = tables.getString("TYPE_CAT");
//			String typeSchem = tables.getString("TYPE_SCHEM");
//			String typeName = tables.getString("TYPE_NAME");
//			String selfReferencingColName = tables.getString("SELF_REFERENCING_COL_NAME");
//			String refGeneration = tables.getString("REF_GENERATION");

			log.debug("tableCat={}", tableCat);
			log.debug("tableSchem={}", tableSchem);
			log.debug("tableName={}", tableName);
			log.debug("tableType={}", tableType);
			log.debug("remarks={}", remarks);
//			log.debug("typeCat={}", typeCat);
//			log.debug("typeSchem={}", typeSchem);
//			log.debug("typeName={}", typeName);
//			log.debug("selfReferencingColName={}", selfReferencingColName);
//			log.debug("refGeneration={}", refGeneration);
			log.debug("");
		}
	}

}
