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
public class C2_GodResultSetMetaDataTest_getColumns {

	@Test
	public void test() throws SQLException {
		log.debug("test");

		Connection con = A1_GodDriverManagerTest.getConnection();

		DatabaseMetaData dmd = con.getMetaData();
		log.debug("dmd={}", dmd);
		log.debug("");

		ResultSet columns = dmd.getColumns(null, "COM", "COMTC%", null);
		log.debug("columns={}", columns);
		log.debug("");

		ResultSetMetaData rsmd = columns.getMetaData();
		log.debug("rsmd={}", rsmd);
		log.debug("");

		int columnCount = rsmd.getColumnCount();
		log.debug("columnCount={}", columnCount);
		log.debug("");

		StringBuffer sb = new StringBuffer("\n");
		StringBuffer sb2 = new StringBuffer("\n");
		StringBuffer sb3 = new StringBuffer("\n");

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

			DbModelElement columnNameDbModelElement = new DbModelElement(columnName);

			sb3.append("private ");

			if ("java.lang.Integer".equals(columnClassName)) {
				sb.append("int ");
				sb.append(columnNameDbModelElement.getCcName());
				sb.append(" = columns.getInt(\"");

				sb3.append("int ");
			} else {
				sb.append(columnClassName.substring(columnClassName.lastIndexOf(".") + 1));
				sb.append(" ");
				sb.append(columnNameDbModelElement.getCcName());
				sb.append(" = columns.getString(\"");

				sb3.append(columnClassName.substring(columnClassName.lastIndexOf(".") + 1));
				sb3.append(" ");
			}

			sb.append(columnName);
			sb.append("\");");
			sb.append("\n");

			sb2.append("log.debug(\"");
			sb2.append(columnNameDbModelElement.getCcName());
			sb2.append("={}\", ");
			sb2.append(columnNameDbModelElement.getCcName());
			sb2.append(");");
			sb2.append("\n");

			sb3.append(columnNameDbModelElement.getCcName());
			sb3.append(";\n");
		}

		log.debug("{}", sb);
		log.debug("{}", sb2);
		log.debug("{}", sb3);
	}

}
