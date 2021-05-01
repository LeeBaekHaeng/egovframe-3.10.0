package god.java.sql;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import lombok.extern.slf4j.Slf4j;
import model.NameCasing;

@Slf4j
public class ResultSetMetaDataTest {

	public static void debug(ResultSetMetaData rsmd) {
		try {
			int columnCount = rsmd.getColumnCount();

			log.debug("columnCount={}\n", columnCount);

			for (int column = 1; column < columnCount; column++) {
				boolean isAutoIncrement = rsmd.isAutoIncrement(column);
				boolean isCaseSensitive = rsmd.isCaseSensitive(column);
				boolean isSearchable = rsmd.isSearchable(column);
				boolean isCurrency = rsmd.isCurrency(column);
				int isNullable = rsmd.isNullable(column);
				boolean isSigned = rsmd.isCurrency(column);
				int columnDisplaySize = rsmd.getColumnDisplaySize(column);
				String columnLabel = rsmd.getColumnLabel(column);
				String columnName = rsmd.getColumnName(column);
				String schemaName = rsmd.getSchemaName(column);
				int precision = rsmd.getPrecision(column);
				int scale = rsmd.getScale(column);
				String tableName = rsmd.getTableName(column);
				String catalogName = rsmd.getCatalogName(column);
				int columnType = rsmd.getColumnType(column);
				String columnTypeName = rsmd.getColumnTypeName(column);
				boolean isReadOnly = rsmd.isReadOnly(column);
				boolean isWritable = rsmd.isWritable(column);
				boolean isDefinitelyWritable = rsmd.isDefinitelyWritable(column);
				String columnClassName = rsmd.getColumnClassName(column);

				log.debug("column={}", column);
				log.debug("isAutoIncrement={}", isAutoIncrement);
				log.debug("isCaseSensitive={}", isCaseSensitive);
				log.debug("isSearchable={}", isSearchable);
				log.debug("isCurrency={}", isCurrency);
				log.debug("isNullable={}", isNullable);
				log.debug("isSigned={}", isSigned);
				log.debug("columnDisplaySize={}", columnDisplaySize);
				log.debug("columnLabel={}", columnLabel);
				log.debug("columnName={}", columnName);
				log.debug("schemaName={}", schemaName);
				log.debug("precision={}", precision);
				log.debug("scale={}", scale);
				log.debug("tableName={}", tableName);
				log.debug("catalogName={}", catalogName);
				log.debug("columnType={}", columnType);
				log.debug("columnTypeName={}", columnTypeName);
				log.debug("isReadOnly={}", isReadOnly);
				log.debug("isWritable={}", isWritable);
				log.debug("isDefinitelyWritable={}", isDefinitelyWritable);
				log.debug("columnClassName={}", columnClassName);
				log.debug("");
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}

	public static void debug2(ResultSetMetaData rsmd) {
		try {
			int columnCount = rsmd.getColumnCount();

			log.debug("columnCount={}\n", columnCount);

			StringBuffer sb = new StringBuffer("\n");

			for (int column = 1; column < columnCount; column++) {
				String columnLabel = rsmd.getColumnLabel(column);

				NameCasing nameCasing = new NameCasing(columnLabel);

				sb.append("log.debug(\"" + nameCasing.getCcName() + "={}\", rs.getString(\"" + columnLabel + "\"));\n");
			}
			sb.append("log.debug(\"\");");

			log.debug("{}", sb);
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}

	public static void debug3(ResultSetMetaData rsmd) {
		try {
			int columnCount = rsmd.getColumnCount();

			log.debug("columnCount={}\n", columnCount);

			StringBuffer sb = new StringBuffer("\n");

			for (int column = 1; column < columnCount; column++) {
				String columnLabel = rsmd.getColumnLabel(column);

				NameCasing nameCasing = new NameCasing(columnLabel);

				sb.append(
						"log.debug(\"" + nameCasing.getCcName() + "={}\", vo.get" + nameCasing.getPcName() + "());\n");
			}
			sb.append("log.debug(\"\");");

			log.debug("{}", sb);
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}

	public static void get(ResultSetMetaData rsmd) {
		try {
			int columnCount = rsmd.getColumnCount();

			log.debug("columnCount={}\n", columnCount);

			StringBuffer sb = new StringBuffer("\n");
			StringBuffer sb2 = new StringBuffer("\n");

			for (int column = 1; column < columnCount; column++) {
				String columnLabel = rsmd.getColumnLabel(column);
				String columnClassName = rsmd.getColumnClassName(column);
				String columnClassName2 = columnClassName.substring(columnClassName.lastIndexOf(".") + 1);

				NameCasing nameCasing = new NameCasing(columnLabel);

				sb.append(columnClassName2);
				sb.append(" ");
				sb.append(nameCasing.getCcName());

				if ("java.lang.Integer".equals(columnClassName)) {
					sb.append(" = rs.getInt(\"");
				} else {
					sb.append(" = rs.getString(\"");
				}

				sb.append(columnLabel);
				sb.append("\");\n");

				sb2.append("log.debug(\"" + nameCasing.getCcName() + "={}\", " + nameCasing.getCcName() + ");\n");
			}

			sb2.append("log.debug(\"\");");

			sb.append(sb2);

			log.debug("{}", sb);
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}

	public static void get2(ResultSetMetaData rsmd) {
		try {
			int columnCount = rsmd.getColumnCount();

			log.debug("columnCount={}\n", columnCount);

			StringBuffer sb = new StringBuffer("\n");

			for (int column = 1; column < columnCount; column++) {
				String columnLabel = rsmd.getColumnLabel(column);
				String columnClassName = rsmd.getColumnClassName(column);
				String columnClassName2 = columnClassName.substring(columnClassName.lastIndexOf(".") + 1);

				NameCasing nameCasing = new NameCasing(columnLabel);

				sb.append(columnClassName2);
				sb.append(" ");
				sb.append(nameCasing.getCcName());
				sb.append(" = vo.get");
				sb.append(nameCasing.getPcName());
				sb.append("();\n");
			}

			log.debug("{}", sb);
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}

	public static void set(ResultSetMetaData rsmd) {
		try {
			int columnCount = rsmd.getColumnCount();

			log.debug("columnCount={}\n", columnCount);

			StringBuffer sb = new StringBuffer("\n");

			for (int column = 1; column < columnCount; column++) {
				String columnLabel = rsmd.getColumnLabel(column);
				String columnClassName = rsmd.getColumnClassName(column);
				String columnClassName2 = columnClassName.substring(columnClassName.lastIndexOf(".") + 1);

				NameCasing nameCasing = new NameCasing(columnLabel);

				sb.append("columnVO.set" + nameCasing.getPcName() + "((" + columnClassName2 + ") allTabCol.get(\""
						+ nameCasing.getCcName() + "\"));\n");
			}

			log.debug("{}", sb);
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}

}
