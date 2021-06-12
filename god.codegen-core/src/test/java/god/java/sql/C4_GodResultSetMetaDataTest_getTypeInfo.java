package god.java.sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.junit.Test;

import egovframework.dev.imp.codegen.template.model.DbModelElement;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class C4_GodResultSetMetaDataTest_getTypeInfo {

	@Test
	public void test() throws SQLException {
		log.debug("test");

		Connection con = A1_GodDriverManagerTest.getConnection();

		DatabaseMetaData dmd = con.getMetaData();
		log.debug("dmd={}", dmd);
		log.debug("");

		ResultSet typeInfo = dmd.getTypeInfo();
		log.debug("typeInfo={}", typeInfo);
		log.debug("");

		testA(typeInfo);
		testB(typeInfo);
	}

	private void testA(ResultSet typeInfo) throws SQLException {
		ResultSetMetaData rsmd = typeInfo.getMetaData();
		log.debug("rsmd={}", rsmd);
		log.debug("");

		int columnCount = rsmd.getColumnCount();
		log.debug("columnCount={}", columnCount);
		log.debug("");

		testAA(rsmd, columnCount);
		testAB(rsmd, columnCount);
	}

	private void testAA(ResultSetMetaData rsmd, int columnCount) {
		try {
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
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}

	private void testAB(ResultSetMetaData rsmd, int columnCount) {
		try {
			StringBuffer sb = new StringBuffer("\n");
			StringBuffer sb2 = new StringBuffer("\n");

			for (int column = 1; column < columnCount; column++) {
				String columnLabel = rsmd.getColumnLabel(column);
//				String columnName = rsmd.getColumnName(column);
//				int columnType = rsmd.getColumnType(column);
//				String columnTypeName = rsmd.getColumnTypeName(column);
				String columnClassName = rsmd.getColumnClassName(column);

				DbModelElement columnLabelDbModelElement = new DbModelElement(columnLabel);

				if ("java.lang.Integer".equals(columnClassName)) {
					sb.append("int ");
					sb.append(columnLabelDbModelElement.getCcName());
					sb.append(" = typeInfo.getInt(\"");
				} else if ("java.lang.Boolean".equals(columnClassName)) {
					sb.append("boolean ");
					sb.append(columnLabelDbModelElement.getCcName());
					sb.append(" = typeInfo.getBoolean(\"");
				} else {
					sb.append(columnClassName.substring(columnClassName.lastIndexOf(".") + 1));
					sb.append(" ");
					sb.append(columnLabelDbModelElement.getCcName());
					sb.append(" = typeInfo.getString(\"");
				}

				sb.append(columnLabel);
				sb.append("\");");
				sb.append("\n");

				sb2.append("log.debug(\"");
				sb2.append(columnLabelDbModelElement.getCcName());
				sb2.append("={}\", ");
				sb2.append(columnLabelDbModelElement.getCcName());
				sb2.append(");");
				sb2.append("\n");
			}

			sb.append(sb2);

			log.debug("sb={}", sb);
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}

	private void testB(ResultSet typeInfo) {
		try {
			while (typeInfo.next()) {
				String typeName = typeInfo.getString("TYPE_NAME");
				int dataType = typeInfo.getInt("DATA_TYPE");
				int precision = typeInfo.getInt("PRECISION");
				String literalPrefix = typeInfo.getString("LITERAL_PREFIX");
				String literalSuffix = typeInfo.getString("LITERAL_SUFFIX");
				String createParams = typeInfo.getString("CREATE_PARAMS");
				int nullable = typeInfo.getInt("NULLABLE");
				boolean caseSensitive = typeInfo.getBoolean("CASE_SENSITIVE");
				int searchable = typeInfo.getInt("SEARCHABLE");
				boolean unsignedAttribute = typeInfo.getBoolean("UNSIGNED_ATTRIBUTE");
				boolean fixedPrecScale = typeInfo.getBoolean("FIXED_PREC_SCALE");
				boolean autoIncrement = typeInfo.getBoolean("AUTO_INCREMENT");
				String localTypeName = typeInfo.getString("LOCAL_TYPE_NAME");
				int minimumScale = typeInfo.getInt("MINIMUM_SCALE");
				int maximumScale = typeInfo.getInt("MAXIMUM_SCALE");
				int sqlDataType = typeInfo.getInt("SQL_DATA_TYPE");
				int sqlDatetimeSub = typeInfo.getInt("SQL_DATETIME_SUB");

				log.debug("typeName={}", typeName);
				log.debug("dataType={}", dataType);
				log.debug("precision={}", precision);
				log.debug("literalPrefix={}", literalPrefix);
				log.debug("literalSuffix={}", literalSuffix);
				log.debug("createParams={}", createParams);
				log.debug("nullable={}", nullable);
				log.debug("caseSensitive={}", caseSensitive);
				log.debug("searchable={}", searchable);
				log.debug("unsignedAttribute={}", unsignedAttribute);
				log.debug("fixedPrecScale={}", fixedPrecScale);
				log.debug("autoIncrement={}", autoIncrement);
				log.debug("localTypeName={}", localTypeName);
				log.debug("minimumScale={}", minimumScale);
				log.debug("maximumScale={}", maximumScale);
				log.debug("sqlDataType={}", sqlDataType);
				log.debug("sqlDatetimeSub={}", sqlDatetimeSub);
				log.debug("");
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}

}
