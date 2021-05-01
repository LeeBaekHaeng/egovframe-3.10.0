package god.java.sql;

import java.util.List;

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
@ContextConfiguration(classes = { DatabaseMetaDataOracleTest_A2_getColumns.class })
@ActiveProfiles({ "oracle", "dummy" })

@Configuration

@ImportResource({

//		"classpath*:egovframework/spring/com/**/context-*.xml",

		"classpath*:/egovframework/spring/com/context-crypto.xml",
		"classpath*:/egovframework/spring/com/context-datasource.xml",

		"classpath*:/egovframework/spring/com/context-mapper.xml",
		"classpath*:/egovframework/spring/com/context-mapper-god-oracle.xml",

		"classpath*:/egovframework/spring/com/test-context-common.xml",

})

@ComponentScan(useDefaultFilters = false, basePackages = { "god" }, includeFilters = {
		@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { DatabaseMetaData.class }) })

public class DatabaseMetaDataOracleTest_A2_getColumns {

	private static final StopWatch STOP_WATCH = new StopWatch();

	@Autowired
	private ApplicationContext context;

	@Autowired
	private DatabaseMetaData databaseMetaData;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		log.info("setUpBeforeClass");

		log.info("start");
		STOP_WATCH.start();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		log.info("tearDownAfterClass");

		log.info("stop");
		STOP_WATCH.stop();

		log.info("getTotalTimeMillis={}", STOP_WATCH.getTotalTimeMillis());
		log.info("getTotalTimeSeconds={}", STOP_WATCH.getTotalTimeSeconds());
	}

	@Before
	public void setUp() throws Exception {
		log.info("setUp");

		String[] beanDefinitionNames = context.getBeanDefinitionNames();

		log.debug("beanDefinitionNames.length={}", beanDefinitionNames.length);

		for (String beanDefinitionName : beanDefinitionNames) {
			log.debug("beanDefinitionName={}", beanDefinitionName);
		}
	}

	@After
	public void tearDown() throws Exception {
		log.info("tearDown");
	}

	@Test
	public void test() {
		log.info("test");

		String catalog = "";
		String schemaPattern = "COM";
		String tableNamePattern = "COMTCADMINISTCODE";
		String columnNamePattern = "";

		List<ColumnVO> columns = databaseMetaData.getColumns(catalog, schemaPattern, tableNamePattern,
				columnNamePattern);

		for (ColumnVO columnVO : columns) {
			log.debug("tableCat={}", columnVO.getTableCat());
			log.debug("tableSchem={}", columnVO.getTableSchem());
			log.debug("tableName={}", columnVO.getTableName());
			log.debug("columnName={}", columnVO.getColumnName());
			log.debug("dataType={}", columnVO.getDataType());
			log.debug("typeName={}", columnVO.getTypeName());
			log.debug("columnSize={}", columnVO.getColumnSize());
			log.debug("bufferLength={}", columnVO.getBufferLength());
			log.debug("decimalDigits={}", columnVO.getDecimalDigits());
			log.debug("numPrecRadix={}", columnVO.getNumPrecRadix());
			log.debug("nullable={}", columnVO.getNullable());
			log.debug("remarks={}", columnVO.getRemarks());
			log.debug("columnDef={}", columnVO.getColumnDef());
			log.debug("sqlDataType={}", columnVO.getSqlDataType());
			log.debug("sqlDatetimeSub={}", columnVO.getSqlDatetimeSub());
			log.debug("charOctetLength={}", columnVO.getCharOctetLength());
			log.debug("ordinalPosition={}", columnVO.getOrdinalPosition());
			log.debug("isNullable={}", columnVO.getIsNullable());
			log.debug("scopeCatalog={}", columnVO.getScopeCatalog());
			log.debug("scopeSchema={}", columnVO.getScopeSchema());
			log.debug("scopeTable={}", columnVO.getScopeTable());
			log.debug("sourceDataType={}", columnVO.getSourceDataType());
			log.debug("isAutoincrement={}", columnVO.getIsAutoincrement());
			log.debug("");
		}
	}

}
