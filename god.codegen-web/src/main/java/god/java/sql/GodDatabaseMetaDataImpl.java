package god.java.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.OracleConnection;

@Slf4j
public class GodDatabaseMetaDataImpl implements GodDatabaseMetaData {

	private Connection con;
	private java.sql.DatabaseMetaData dmd;

	public GodDatabaseMetaDataImpl() {
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.254:1521:orcl", "com", "com01");
//			con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.254:1521:orcl", "test", "test");

			log.debug("con={}", con);
			log.debug("");

			if (con.isWrapperFor(OracleConnection.class)) {
				OracleConnection ocon = con.unwrap(OracleConnection.class);
				ocon.setRemarksReporting(true);
			}

			dmd = con.getMetaData();
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public List<TableVO> getTables(String catalog, String schemaPattern, String tableNamePattern, String[] types) {
		try {
			List<TableVO> tables = new ArrayList<>();

			ResultSet rs = dmd.getTables(catalog, schemaPattern, tableNamePattern, types);

			while (rs.next()) {
				TableVO table = new TableVO();

				table.setTableCat(rs.getString("TABLE_CAT"));
				table.setTableSchem(rs.getString("TABLE_SCHEM"));
				table.setTableName(rs.getString("TABLE_NAME"));
				table.setTableType(rs.getString("TABLE_TYPE"));
				table.setRemarks(rs.getString("REMARKS"));

				tables.add(table);
			}

			return tables;
		} catch (SQLException e) {
			log.error(e.getMessage());
			return null;
		}
	}

	@Override
	public List<ColumnVO> getColumns(String catalog, String schemaPattern, String tableNamePattern,
			String columnNamePattern) {
		try {
			List<ColumnVO> columns = new ArrayList<>();

			ResultSet rs = dmd.getColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern);

			while (rs.next()) {
				ColumnVO column = new ColumnVO();

				column.setTableCat(rs.getString("TABLE_CAT"));
				column.setTableSchem(rs.getString("TABLE_SCHEM"));
				column.setTableName(rs.getString("TABLE_NAME"));
				column.setColumnName(rs.getString("COLUMN_NAME"));
				column.setDataType(rs.getInt("DATA_TYPE"));
				column.setTypeName(rs.getString("TYPE_NAME"));
				column.setColumnSize(rs.getInt("COLUMN_SIZE"));
				column.setBufferLength(rs.getInt("BUFFER_LENGTH"));
				column.setDecimalDigits(rs.getInt("DECIMAL_DIGITS"));
				column.setNumPrecRadix(rs.getInt("NUM_PREC_RADIX"));
				column.setNullable(rs.getInt("NULLABLE"));
				column.setRemarks(rs.getString("REMARKS"));
				column.setColumnDef(rs.getString("COLUMN_DEF"));
				column.setSqlDataType(rs.getInt("SQL_DATA_TYPE"));
				column.setSqlDatetimeSub(rs.getInt("SQL_DATETIME_SUB"));
				column.setCharOctetLength(rs.getInt("CHAR_OCTET_LENGTH"));
				column.setOrdinalPosition(rs.getInt("ORDINAL_POSITION"));
				column.setIsNullable(rs.getString("IS_NULLABLE"));

				columns.add(column);
			}

			return columns;
		} catch (SQLException e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
