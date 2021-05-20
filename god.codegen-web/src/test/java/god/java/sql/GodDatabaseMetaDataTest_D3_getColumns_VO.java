package god.java.sql;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import model.NameCasing;

@Slf4j
public class GodDatabaseMetaDataTest_D3_getColumns_VO {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		GodDatabaseMetaData gdmd = new GodDatabaseMetaDataImpl();

		String catalog = null;
		String schemaPattern = "COM";
//		String tableNamePattern = "COMTCADMINISTCODE";
//		String tableNamePattern = "COMTCADMINISTCODERECPTNLOG";
		String tableNamePattern = "COMTCCMMNCLCODE";
		String columnNamePattern = null;

		List<ColumnVO> columns = gdmd.getColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern);

		StringBuffer sb = new StringBuffer("\n");

		NameCasing tableNamePatternNameCasing = new NameCasing(tableNamePattern);
		sb.append("god.com." + tableNamePatternNameCasing.getLcName() + ".service");
		sb.append("\n\n");
		sb.append(tableNamePatternNameCasing.getPcName() + "VO");
		sb.append("\n\n");

		for (ColumnVO column : columns) {
			log.debug("column={}", column);
			log.debug("tableCat={}", column.getTableCat());
			log.debug("tableSchem={}", column.getTableSchem());
			log.debug("tableName={}", column.getTableName());
			log.debug("columnName={}", column.getColumnName());
			log.debug("dataType={}", column.getDataType());
			log.debug("typeName={}", column.getTypeName());
			log.debug("columnSize={}", column.getColumnSize());
			log.debug("bufferLength={}", column.getBufferLength());
			log.debug("decimalDigits={}", column.getDecimalDigits());
			log.debug("numPrecRadix={}", column.getNumPrecRadix());
			log.debug("nullable={}", column.getNullable());
			log.debug("remarks={}", column.getRemarks());
			log.debug("columnDef={}", column.getColumnDef());
			log.debug("sqlDataType={}", column.getSqlDataType());
			log.debug("sqlDatetimeSub={}", column.getSqlDatetimeSub());
			log.debug("charOctetLength={}", column.getCharOctetLength());
			log.debug("ordinalPosition={}", column.getOrdinalPosition());
			log.debug("isNullable={}", column.getIsNullable());
			log.debug("");

			NameCasing columnNameNameCasing = new NameCasing(column.getColumnName());

			sb.append("private ");

			if ("NUMBER".equals(column.getTypeName())) {
				sb.append("Long");
			} else if ("DATE".equals(column.getTypeName())) {
				sb.append("LocalDateTime");
			} else {
				sb.append("String");
			}

			sb.append(" ");
			sb.append(columnNameNameCasing.getCcName());

			sb.append(";\n");
		}

		log.debug("sb={}", sb);
	}

}
