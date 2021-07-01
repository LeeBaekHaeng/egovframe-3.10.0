package god.java.sql.v1;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import egovframework.dev.imp.codegen.template.util.NamingUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GodResultSetMetaDataTest {

	public static void debug(ResultSet attributes, boolean convertUnderscoreNameToCamelcase) {
		StringBuffer sb = new StringBuffer("\n");
		StringBuffer sb2 = new StringBuffer("\n");

		try {
			ResultSetMetaData rsmd = attributes.getMetaData();

			int columnCount = rsmd.getColumnCount() + 1;

			for (int column = 1; column < columnCount; column++) {
				String columnLabel = rsmd.getColumnLabel(column);
				String columnClassName = rsmd.getColumnClassName(column);
//				String columnTypeName = rsmd.getColumnTypeName(column);
//				int columnType = rsmd.getColumnType(column);

//				log.debug("columnClassName={}", columnClassName);
//				log.debug("columnTypeName={}", columnTypeName);
//				log.debug("columnType={}", columnType);

				if (convertUnderscoreNameToCamelcase) {
					columnLabel = NamingUtils.convertUnderscoreNameToCamelcase(columnLabel);
				}

				String columnClassName2 = columnClassName.substring(columnClassName.lastIndexOf(".") + 1);

				sb.append(columnClassName2);
				sb.append(" ");
				sb.append(columnLabel);
				sb.append(" = attributes.get");
				if ("java.lang.Integer".equals(columnClassName)) {
					sb.append("Int");
				} else {
					sb.append(columnClassName2);
				}
				sb.append("(\"");
				sb.append(columnLabel);
				sb.append("\");");

				sb.append("\n");

				// sb2
				sb2.append("log.debug(\"");
				sb2.append(columnLabel);
				sb2.append("={}\", ");
				sb2.append(columnLabel);
				sb2.append(");");

				sb2.append("\n");
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}

		sb.append(sb2);

		log.debug("sb={}", sb);
	}

}