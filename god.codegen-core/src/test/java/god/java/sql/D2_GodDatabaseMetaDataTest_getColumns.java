package god.java.sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class D2_GodDatabaseMetaDataTest_getColumns {

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

		ResultSet columns = dmd.getColumns(null, "COM", "COMTC%", null);
		log.debug("columns={}", columns);
		log.debug("");

		while (columns.next()) {
			String tableCat = columns.getString("TABLE_CAT");
			String tableSchem = columns.getString("TABLE_SCHEM");
			String tableName = columns.getString("TABLE_NAME");
			String columnName = columns.getString("COLUMN_NAME");
			int dataType = columns.getInt("DATA_TYPE");
			String typeName = columns.getString("TYPE_NAME");
			int columnSize = columns.getInt("COLUMN_SIZE");
			int bufferLength = columns.getInt("BUFFER_LENGTH");
			int decimalDigits = columns.getInt("DECIMAL_DIGITS");
			int numPrecRadix = columns.getInt("NUM_PREC_RADIX");
			int nullable = columns.getInt("NULLABLE");
			String remarks = columns.getString("REMARKS");
			String columnDef = columns.getString("COLUMN_DEF");
			int sqlDataType = columns.getInt("SQL_DATA_TYPE");
			int sqlDatetimeSub = columns.getInt("SQL_DATETIME_SUB");
			int charOctetLength = columns.getInt("CHAR_OCTET_LENGTH");
			int ordinalPosition = columns.getInt("ORDINAL_POSITION");
			String isNullable = columns.getString("IS_NULLABLE");

			log.debug("tableCat={}", tableCat);
			log.debug("tableSchem={}", tableSchem);
			log.debug("tableName={}", tableName);
			log.debug("columnName={}", columnName);
			log.debug("dataType={}", dataType);
			log.debug("typeName={}", typeName);
			log.debug("columnSize={}", columnSize);
			log.debug("bufferLength={}", bufferLength);
			log.debug("decimalDigits={}", decimalDigits);
			log.debug("numPrecRadix={}", numPrecRadix);
			log.debug("nullable={}", nullable);
			log.debug("remarks={}", remarks);
			log.debug("columnDef={}", columnDef);
			log.debug("sqlDataType={}", sqlDataType);
			log.debug("sqlDatetimeSub={}", sqlDatetimeSub);
			log.debug("charOctetLength={}", charOctetLength);
			log.debug("ordinalPosition={}", ordinalPosition);
			log.debug("isNullable={}", isNullable);

			if ("MySQL".equals(databaseProductName)) {
				String scopeCatalog = columns.getString("SCOPE_CATALOG");
				String scopeSchema = columns.getString("SCOPE_SCHEMA");
				String scopeTable = columns.getString("SCOPE_TABLE");
				int sourceDataType = columns.getInt("SOURCE_DATA_TYPE");
				String isAutoincrement = columns.getString("IS_AUTOINCREMENT");

				log.debug("");

				log.debug("scopeCatalog={}", scopeCatalog);
				log.debug("scopeSchema={}", scopeSchema);
				log.debug("scopeTable={}", scopeTable);
				log.debug("sourceDataType={}", sourceDataType);
				log.debug("isAutoincrement={}", isAutoincrement);

			}

			log.debug("");
		}
	}

}
