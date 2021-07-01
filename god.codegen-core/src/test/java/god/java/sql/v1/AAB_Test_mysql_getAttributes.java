package god.java.sql.v1;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import egovframework.com.cmm.util.EgovResourceCloseHelper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AAB_Test_mysql_getAttributes {

	@Test
	public void test() {
		Connection conn = null;
		ResultSet attributes = null;
		try {
			conn = GodDriverManagerTest.getConnectionMySQL();
//			conn = GodDriverManagerTest.getConnectionOracle();

			DatabaseMetaData dmd = conn.getMetaData();

			String databaseProductName = dmd.getDatabaseProductName();
			log.debug("databaseProductName={}", databaseProductName);

			attributes = dmd.getAttributes("com", null, null, "USE_AT");

			log.debug("schemas={}", attributes);
			log.debug("getFetchSize={}", attributes.getFetchSize());

			GodResultSetMetaDataTest.debug(attributes, false);
			GodResultSetMetaDataTest.debug(attributes, true);

			while (attributes.next()) {
				String TYPE_CAT = attributes.getString("TYPE_CAT");
				String TYPE_SCHEM = attributes.getString("TYPE_SCHEM");
				String TYPE_NAME = attributes.getString("TYPE_NAME");
				String ATTR_NAME = attributes.getString("ATTR_NAME");
				Integer DATA_TYPE = attributes.getInt("DATA_TYPE");
				String ATTR_TYPE_NAME = attributes.getString("ATTR_TYPE_NAME");
				Integer ATTR_SIZE = attributes.getInt("ATTR_SIZE");
				Integer DECIMAL_DIGITS = attributes.getInt("DECIMAL_DIGITS");
				Integer NUM_PREC_RADIX = attributes.getInt("NUM_PREC_RADIX");
				Integer NULLABLE = attributes.getInt("NULLABLE ");
				String REMARKS = attributes.getString("REMARKS");
				String ATTR_DEF = attributes.getString("ATTR_DEF");
				Integer SQL_DATA_TYPE = attributes.getInt("SQL_DATA_TYPE");
				Integer SQL_DATETIME_SUB = attributes.getInt("SQL_DATETIME_SUB");
				Integer CHAR_OCTET_LENGTH = attributes.getInt("CHAR_OCTET_LENGTH");
				Integer ORDINAL_POSITION = attributes.getInt("ORDINAL_POSITION");
				String IS_NULLABLE = attributes.getString("IS_NULLABLE");
				String SCOPE_CATALOG = attributes.getString("SCOPE_CATALOG");
				String SCOPE_SCHEMA = attributes.getString("SCOPE_SCHEMA");
				String SCOPE_TABLE = attributes.getString("SCOPE_TABLE");
				Integer SOURCE_DATA_TYPE = attributes.getInt("SOURCE_DATA_TYPE");

				log.debug("TYPE_CAT={}", TYPE_CAT);
				log.debug("TYPE_SCHEM={}", TYPE_SCHEM);
				log.debug("TYPE_NAME={}", TYPE_NAME);
				log.debug("ATTR_NAME={}", ATTR_NAME);
				log.debug("DATA_TYPE={}", DATA_TYPE);
				log.debug("ATTR_TYPE_NAME={}", ATTR_TYPE_NAME);
				log.debug("ATTR_SIZE={}", ATTR_SIZE);
				log.debug("DECIMAL_DIGITS={}", DECIMAL_DIGITS);
				log.debug("NUM_PREC_RADIX={}", NUM_PREC_RADIX);
				log.debug("NULLABLE ={}", NULLABLE);
				log.debug("REMARKS={}", REMARKS);
				log.debug("ATTR_DEF={}", ATTR_DEF);
				log.debug("SQL_DATA_TYPE={}", SQL_DATA_TYPE);
				log.debug("SQL_DATETIME_SUB={}", SQL_DATETIME_SUB);
				log.debug("CHAR_OCTET_LENGTH={}", CHAR_OCTET_LENGTH);
				log.debug("ORDINAL_POSITION={}", ORDINAL_POSITION);
				log.debug("IS_NULLABLE={}", IS_NULLABLE);
				log.debug("SCOPE_CATALOG={}", SCOPE_CATALOG);
				log.debug("SCOPE_SCHEMA={}", SCOPE_SCHEMA);
				log.debug("SCOPE_TABLE={}", SCOPE_TABLE);
				log.debug("SOURCE_DATA_TYPE={}", SOURCE_DATA_TYPE);

				String typeCat = attributes.getString("typeCat");
				String typeSchem = attributes.getString("typeSchem");
				String typeName = attributes.getString("typeName");
				String attrName = attributes.getString("attrName");
				Integer dataType = attributes.getInt("dataType");
				String attrTypeName = attributes.getString("attrTypeName");
				Integer attrSize = attributes.getInt("attrSize");
				Integer decimalDigits = attributes.getInt("decimalDigits");
				Integer numPrecRadix = attributes.getInt("numPrecRadix");
				Integer nullable = attributes.getInt("nullable ");
				String remarks = attributes.getString("remarks");
				String attrDef = attributes.getString("attrDef");
				Integer sqlDataType = attributes.getInt("sqlDataType");
				Integer sqlDatetimeSub = attributes.getInt("sqlDatetimeSub");
				Integer charOctetLength = attributes.getInt("charOctetLength");
				Integer ordinalPosition = attributes.getInt("ordinalPosition");
				String isNullable = attributes.getString("isNullable");
				String scopeCatalog = attributes.getString("scopeCatalog");
				String scopeSchema = attributes.getString("scopeSchema");
				String scopeTable = attributes.getString("scopeTable");
				Integer sourceDataType = attributes.getInt("sourceDataType");

				log.debug("typeCat={}", typeCat);
				log.debug("typeSchem={}", typeSchem);
				log.debug("typeName={}", typeName);
				log.debug("attrName={}", attrName);
				log.debug("dataType={}", dataType);
				log.debug("attrTypeName={}", attrTypeName);
				log.debug("attrSize={}", attrSize);
				log.debug("decimalDigits={}", decimalDigits);
				log.debug("numPrecRadix={}", numPrecRadix);
				log.debug("nullable ={}", nullable);
				log.debug("remarks={}", remarks);
				log.debug("attrDef={}", attrDef);
				log.debug("sqlDataType={}", sqlDataType);
				log.debug("sqlDatetimeSub={}", sqlDatetimeSub);
				log.debug("charOctetLength={}", charOctetLength);
				log.debug("ordinalPosition={}", ordinalPosition);
				log.debug("isNullable={}", isNullable);
				log.debug("scopeCatalog={}", scopeCatalog);
				log.debug("scopeSchema={}", scopeSchema);
				log.debug("scopeTable={}", scopeTable);
				log.debug("sourceDataType={}", sourceDataType);

//				if ("MySQL".equals(databaseProductName)) {
//				}
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		} finally {
			EgovResourceCloseHelper.closeDBObjects(attributes, conn);
		}
	}

}