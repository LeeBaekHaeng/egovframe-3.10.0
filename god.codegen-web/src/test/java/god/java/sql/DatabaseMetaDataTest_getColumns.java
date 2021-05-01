package god.java.sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DatabaseMetaDataTest_getColumns.class })
//@ActiveProfiles({ "oracle", "dummy" })
@ActiveProfiles({ "mysql", "dummy" })

@Configuration

@ImportResource({

//		"classpath*:egovframework/spring/com/**/context-*.xml",

		"classpath*:/egovframework/spring/com/context-crypto.xml",
		"classpath*:/egovframework/spring/com/context-datasource.xml",

		"classpath*:egovframework/spring/com/test-context-common.xml",

})

@ComponentScan(useDefaultFilters = false, basePackages = { "god" }, includeFilters = {
		@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {}) })

public class DatabaseMetaDataTest_getColumns {

	private static final StopWatch STOP_WATCH = new StopWatch();

	@Autowired
	private ApplicationContext context;

	@Autowired
	private DataSource dataSource;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		log.debug("setUpBeforeClass");

		log.debug("start");
		STOP_WATCH.start();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		log.debug("tearDownAfterClass");

		log.debug("stop");
		STOP_WATCH.stop();

		log.debug("getTotalTimeMillis={}", STOP_WATCH.getTotalTimeMillis());
		log.debug("getTotalTimeSeconds={}", STOP_WATCH.getTotalTimeSeconds());
	}

	@Before
	public void setUp() throws Exception {
		log.debug("setUp");

		String[] beanDefinitionNames = context.getBeanDefinitionNames();

		log.debug("beanDefinitionNames.length={}", beanDefinitionNames.length);

		for (String beanDefinitionName : beanDefinitionNames) {
			log.debug("beanDefinitionName={}", beanDefinitionName);
		}
	}

	@After
	public void tearDown() throws Exception {
		log.debug("tearDown");
	}

	@Test
	public void test() {
		try {
			Connection connection = dataSource.getConnection();

			DatabaseMetaData dmd = connection.getMetaData();
			log.debug("dmd={}", dmd);

			ResultSet columns = getColumns(dmd);
//			ResultSet columns = getColumns2(dmd);
//			ResultSet columns = getColumns3(dmd);
			log.debug("columns={}", columns);
//			ResultSetTest.debug(columns);

			ResultSetMetaData rsmd = columns.getMetaData();
//			ResultSetMetaDataTest.debug(rsmd);
//			ResultSetMetaDataTest.debug2(rsmd);
//			ResultSetMetaDataTest.debug3(rsmd);
//			ResultSetMetaDataTest.get(rsmd);
//			ResultSetMetaDataTest.get2(rsmd);
			ResultSetMetaDataTest.set(rsmd);

//			while (columns.next()) {
////				debug(columns);
//				get(columns);
//			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}

	ResultSet getColumns(DatabaseMetaData dmd) {
		try {
			String catalog = "";
			String schemaPattern = "COM";
			String tableNamePattern = "COMTCADMINISTCODE";
			String columnNamePattern = "";
			return dmd.getColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern);
		} catch (SQLException e) {
			log.error(e.getMessage());
			return null;
		}
	}

	ResultSet getColumns2(DatabaseMetaData dmd) {
		try {
			String catalog = "";
			String schemaPattern = "COM";
			String tableNamePattern = "COMTCADMINISTCODERECPTNLOG";
			String columnNamePattern = "";
			return dmd.getColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern);
		} catch (SQLException e) {
			log.error(e.getMessage());
			return null;
		}
	}

	ResultSet getColumns3(DatabaseMetaData dmd) {
		try {
			String catalog = "";
			String schemaPattern = "SYS";
			String tableNamePattern = "ALL_TAB_COLS";
			String columnNamePattern = "";
			return dmd.getColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern);
		} catch (SQLException e) {
			log.error(e.getMessage());
			return null;
		}
	}

	void debug(ResultSet columns) {
		try {
			log.debug("tableCat={}", columns.getString("TABLE_CAT"));
			log.debug("tableSchem={}", columns.getString("TABLE_SCHEM"));
			log.debug("tableName={}", columns.getString("TABLE_NAME"));
			log.debug("columnName={}", columns.getString("COLUMN_NAME"));
			log.debug("dataType={}", columns.getString("DATA_TYPE"));
			log.debug("typeName={}", columns.getString("TYPE_NAME"));
			log.debug("columnSize={}", columns.getString("COLUMN_SIZE"));
			log.debug("bufferLength={}", columns.getString("BUFFER_LENGTH"));
			log.debug("decimalDigits={}", columns.getString("DECIMAL_DIGITS"));
			log.debug("numPrecRadix={}", columns.getString("NUM_PREC_RADIX"));
			log.debug("nullable={}", columns.getString("NULLABLE"));
			log.debug("remarks={}", columns.getString("REMARKS"));
			log.debug("columnDef={}", columns.getString("COLUMN_DEF"));
			log.debug("sqlDataType={}", columns.getString("SQL_DATA_TYPE"));
			log.debug("sqlDatetimeSub={}", columns.getString("SQL_DATETIME_SUB"));
			log.debug("charOctetLength={}", columns.getString("CHAR_OCTET_LENGTH"));
			log.debug("ordinalPosition={}", columns.getString("ORDINAL_POSITION"));
			log.debug("isNullable={}", columns.getString("IS_NULLABLE"));
			log.debug("scopeCatalog={}", columns.getString("SCOPE_CATALOG"));
			log.debug("scopeSchema={}", columns.getString("SCOPE_SCHEMA"));
			log.debug("scopeTable={}", columns.getString("SCOPE_TABLE"));
			log.debug("sourceDataType={}", columns.getString("SOURCE_DATA_TYPE"));
			log.debug("isAutoincrement={}", columns.getString("IS_AUTOINCREMENT"));
			log.debug("");
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}

	void get(ResultSet columns) {
		try {
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
			String scopeCatalog = columns.getString("SCOPE_CATALOG");
			String scopeSchema = columns.getString("SCOPE_SCHEMA");
			String scopeTable = columns.getString("SCOPE_TABLE");
			Integer sourceDataType = columns.getInt("SOURCE_DATA_TYPE");
			String isAutoincrement = columns.getString("IS_AUTOINCREMENT");

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
			log.debug("scopeCatalog={}", scopeCatalog);
			log.debug("scopeSchema={}", scopeSchema);
			log.debug("scopeTable={}", scopeTable);
			log.debug("sourceDataType={}", sourceDataType);
			log.debug("isAutoincrement={}", isAutoincrement);
			log.debug("");
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}

}
