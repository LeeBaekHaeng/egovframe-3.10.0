package god.java.sql.v2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import egovframework.com.cmm.util.EgovResourceCloseHelper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AAA_GodDatabaseMetaDataTest_Oracle_getColumns {

	@Test
	public void test() {
		Connection conn = GodDriverManagerTest.getConnectionOracle();
		ResultSet columns = null;
		try {
			DatabaseMetaData dmd = conn.getMetaData();

			columns = dmd.getColumns(null, "COM", null, null);

//			GodResultSetMetaDataTest.debug(columns, true);

			while (columns.next()) {
				String tableCat = columns.getString("TABLE_CAT");
				String tableSchem = columns.getString("TABLE_SCHEM");
				String tableName = columns.getString("TABLE_NAME");
				String columnName = columns.getString("COLUMN_NAME");
				Integer dataType = columns.getInt("DATA_TYPE");
				String typeName = columns.getString("TYPE_NAME");
				Integer columnSize = columns.getInt("COLUMN_SIZE");
				Integer bufferLength = columns.getInt("BUFFER_LENGTH");
				Integer decimalDigits = columns.getInt("DECIMAL_DIGITS");
				Integer numPrecRadix = columns.getInt("NUM_PREC_RADIX");
				Integer nullable = columns.getInt("NULLABLE");
				String remarks = columns.getString("REMARKS");
				String columnDef = columns.getString("COLUMN_DEF");
				Integer sqlDataType = columns.getInt("SQL_DATA_TYPE");
				Integer sqlDatetimeSub = columns.getInt("SQL_DATETIME_SUB");
				Integer charOctetLength = columns.getInt("CHAR_OCTET_LENGTH");
				Integer ordinalPosition = columns.getInt("ORDINAL_POSITION");
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

				log.debug("");
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		} finally {
			EgovResourceCloseHelper.closeDBObjects(columns, conn);
		}
	}

}