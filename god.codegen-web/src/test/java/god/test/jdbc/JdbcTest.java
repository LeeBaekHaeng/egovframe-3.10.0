package god.test.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

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
@ContextConfiguration(classes = { JdbcTest.class })
@ActiveProfiles({ "oracle", "dummy" })
//@ActiveProfiles({ "mysql", "dummy" })

@Configuration

@ImportResource({ "classpath*:/egovframework/spring/com/context-crypto.xml",
		"classpath*:/egovframework/spring/com/context-datasource.xml",
		"classpath*:/egovframework/spring/com/context-mapper.xml",
		"classpath*:/egovframework/spring/com/context-mapper-god-oracle.xml",
		"classpath*:/egovframework/spring/com/test-context-common.xml" })

@ComponentScan(useDefaultFilters = false, basePackages = { "god" }, includeFilters = {
		@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {}) })

public class JdbcTest {

	private static final StopWatch STOP_WATCH = new StopWatch();

	@Autowired
	private ApplicationContext context;

	@Autowired
	private DataSource dataSource;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		log.debug("start");
		log.debug("setUpBeforeClass");
		STOP_WATCH.start();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		log.debug("stop");
		log.debug("tearDownAfterClass");

		STOP_WATCH.stop();

		log.debug("getTotalTimeMillis={}", STOP_WATCH.getTotalTimeMillis());
		log.debug("getTotalTimeSeconds={}", STOP_WATCH.getTotalTimeSeconds());
	}

	@Before
	public void setUp() throws Exception {
		log.debug("setUp");

		String[] beanDefinitionNames = context.getBeanDefinitionNames();

		for (String beanDefinitionName : beanDefinitionNames) {
			log.debug("beanDefinitionName={}", beanDefinitionName);
		}
	}

	@After
	public void tearDown() throws Exception {
		log.debug("tearDown");
	}

	@Test
	public void test() throws Exception {
		Connection connection = dataSource.getConnection();

		// https://www.baeldung.com/jdbc-database-metadata#databasemetadata-interface
		DatabaseMetaData databaseMetaData = connection.getMetaData();
		log.debug("databaseMetaData={}", databaseMetaData);

		getCatalogs(databaseMetaData);
		getSchemas(databaseMetaData);
		getTables(databaseMetaData);
	}

	private void debug(int columnCount, ResultSetMetaData rsmd) throws Exception {
		for (int i = 1; i < columnCount; i++) {
			log.debug("{}, {}", i, rsmd.getColumnName(i));
		}
		log.debug("");
		log.debug("");
		log.debug("");
	}

	private void debug(int columnCount, ResultSetMetaData rsmd, ResultSet rs) throws Exception {
		for (int i = 1; i < columnCount; i++) {
			log.debug("{}, {}, {}", i, rsmd.getColumnName(i), rs.getString(i));
		}
		log.debug("");
		log.debug("");
		log.debug("");

	}

	private void getCatalogs(DatabaseMetaData databaseMetaData) throws Exception {
		log.debug("getCatalogs");

		ResultSet catalogs = databaseMetaData.getCatalogs();
		log.debug("catalogs={}", catalogs);

		ResultSetMetaData rsmd = catalogs.getMetaData();
		int columnCount = rsmd.getColumnCount() + 1;
		log.debug("columnCount={}", columnCount);
		for (int i = 1; i < columnCount; i++) {
			log.debug("{}={}", i, rsmd.getColumnName(i));
		}

		while (catalogs.next()) {
			for (int i = 1; i < columnCount; i++) {
				log.debug("{}={}", i, catalogs.getString(i));
			}

			String TABLE_CAT = catalogs.getString("TABLE_CAT");

			log.debug("TABLE_CAT={}", TABLE_CAT);
		}
	}

	private void getSchemas(DatabaseMetaData databaseMetaData) throws Exception {
		log.debug("getSchemas");

		ResultSet schemas = databaseMetaData.getSchemas();
		log.debug("schemas={}", schemas);

		ResultSetMetaData rsmd = schemas.getMetaData();
		int columnCount = rsmd.getColumnCount() + 1;
		log.debug("columnCount={}", columnCount);
		for (int i = 1; i < columnCount; i++) {
//			log.debug("{}={}", i, rs.getString(i));
			log.debug("{}={}", i, rsmd.getColumnName(i));
		}

		while (schemas.next()) {
			String TABLE_SCHEM = schemas.getString("TABLE_SCHEM");
//			String TABLE_CATALOG = rs.getString("TABLE_CATALOG");

			log.debug("TABLE_SCHEM={}", TABLE_SCHEM);
//			log.debug("TABLE_CATALOG={}", TABLE_CATALOG);
		}
	}

	private void getTables(DatabaseMetaData databaseMetaData) throws Exception {
		// https://www.baeldung.com/jdbc-database-metadata#tables-metadata

		log.debug("getTables");

//		ResultSet tables = databaseMetaData.getTables(null, "COM", "COMTCADMINISTCODE", new String[] {});
		ResultSet tables = databaseMetaData.getTables(null, "COM", null, new String[] {});
		log.debug("tables={}", tables);

		ResultSetMetaData rsmd = tables.getMetaData();
		int columnCount = tables.getMetaData().getColumnCount() + 1;
		log.debug("columnCount={}", columnCount);
		debug(columnCount, rsmd);

		while (tables.next()) {
			debug(columnCount, rsmd, tables);

			String TABLE_SCHEM = tables.getString("TABLE_SCHEM");
////			String TABLE_CATALOG = rs.getString("TABLE_CATALOG");
			String TABLE_NAME = tables.getString("TABLE_NAME");
//			String REMARKS = rs.getString("REMARKS");
////
////			log.debug("TABLE_SCHEM={}", TABLE_SCHEM);
////			log.debug("TABLE_CATALOG={}", TABLE_CATALOG);
//			log.debug("TABLE_NAME={}", TABLE_NAME);
//			log.debug("REMARKS={}", REMARKS);

			log.debug("getPrimaryKeys");
			ResultSet primaryKeys = databaseMetaData.getPrimaryKeys(null, TABLE_SCHEM, TABLE_NAME);
			while (primaryKeys.next()) {
				String COLUMN_NAME = primaryKeys.getString("COLUMN_NAME");
				String KEY_SEQ = primaryKeys.getString("KEY_SEQ");
				String PK_NAME = primaryKeys.getString("PK_NAME");
				log.debug("COLUMN_NAME={}", COLUMN_NAME);
				log.debug("KEY_SEQ={}", KEY_SEQ);
				log.debug("PK_NAME={}", PK_NAME);
				log.debug("");
				log.debug("");
				log.debug("");
			}

			getColumns(databaseMetaData, TABLE_SCHEM, TABLE_NAME);
		}
	}

	private void getColumns(DatabaseMetaData databaseMetaData, String schemaPattern, String tableNamePattern)
			throws Exception {
		log.debug("getColumns");

		ResultSet columns = databaseMetaData.getColumns(null, schemaPattern, tableNamePattern, null);

		log.debug("rs={}", columns);

		ResultSetMetaData rsmd = columns.getMetaData();
		int columnCount = columns.getMetaData().getColumnCount() + 1;
		log.debug("columnCount={}", columnCount);
		debug(columnCount, rsmd);

		while (columns.next()) {
			debug(columnCount, rsmd, columns);

////			String TABLE_SCHEM = rs.getString("TABLE_SCHEM");
////			String TABLE_CATALOG = rs.getString("TABLE_CATALOG");
//			String TABLE_NAME = rs.getString("TABLE_NAME");
//			String REMARKS = rs.getString("REMARKS");
////
////			log.debug("TABLE_SCHEM={}", TABLE_SCHEM);
////			log.debug("TABLE_CATALOG={}", TABLE_CATALOG);
//			log.debug("TABLE_NAME={}", TABLE_NAME);
//			log.debug("REMARKS={}", REMARKS);
		}
	}

}
