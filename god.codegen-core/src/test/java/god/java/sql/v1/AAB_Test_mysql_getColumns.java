package god.java.sql.v1;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import egovframework.com.cmm.util.EgovResourceCloseHelper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AAB_Test_mysql_getColumns {

	@Test
	public void test() throws SQLException {
		Connection conn = null;
		ResultSet columns = null;
		try {
			conn = GodDriverManagerTest.getConnectionMySQL();
//			conn = GodDriverManagerTest.getConnectionOracle();

			DatabaseMetaData dmd = conn.getMetaData();

			String databaseProductName = dmd.getDatabaseProductName();
			log.debug("databaseProductName={}", databaseProductName);

			String catalog = null;
			String schemaPattern = null;
			String tableNamePattern = null;
			String columnNamePattern = null;

			if ("MySQL".equals(databaseProductName)) {
				catalog = "com";
			} else if ("Oracle".equals(databaseProductName)) {
				schemaPattern = "COM";
			}

			columns = dmd.getColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern);

			log.debug("columns={}", columns);
			log.debug("getFetchSize={}", columns.getFetchSize());

//			GodResultSetMetaDataTest.debug(columns, false);
			GodResultSetMetaDataTest.debug(columns, true);

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

				if ("MySQL".equals(databaseProductName)) {
					String scopeCatalog = columns.getString("SCOPE_CATALOG");
					String scopeSchema = columns.getString("SCOPE_SCHEMA");
					String scopeTable = columns.getString("SCOPE_TABLE");
					Integer sourceDataType = columns.getInt("SOURCE_DATA_TYPE");
					String isAutoincrement = columns.getString("IS_AUTOINCREMENT");
					String isGeneratedcolumn = columns.getString("IS_GENERATEDCOLUMN");

					log.debug("scopeCatalog={}", scopeCatalog);
					log.debug("scopeSchema={}", scopeSchema);
					log.debug("scopeTable={}", scopeTable);
					log.debug("sourceDataType={}", sourceDataType);
					log.debug("isAutoincrement={}", isAutoincrement);
					log.debug("isGeneratedcolumn={}", isGeneratedcolumn);
				}

				log.debug("");
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
			log.error("getErrorCode={}", e.getErrorCode());
			log.error("getNextException={}", e.getNextException());
			log.error("getSQLState={}", e.getSQLState());
		} finally {
			EgovResourceCloseHelper.closeDBObjects(columns, conn);
		}
	}

}