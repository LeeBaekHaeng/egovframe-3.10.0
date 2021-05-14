package god.java.sql;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GodDatabaseMetaDataTest_C3_getColumns {

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

		List<ColumnVO> columns = gdmd.getColumns(null, "COM", "COMTCADMINISTCODE", null);

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
		}
	}

}
