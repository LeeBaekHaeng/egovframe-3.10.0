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
@ContextConfiguration(classes = { DatabaseMetaDataOracleTest_A1_getTables.class })
@ActiveProfiles({ "oracle", "dummy" })

@Configuration

@ImportResource({

//		"classpath*:egovframework/spring/com/**/context-*.xml",

		"classpath*:egovframework/spring/com/context-crypto.xml",
		"classpath*:egovframework/spring/com/context-datasource.xml",

		"classpath*:egovframework/spring/com/context-mapper.xml",
		"classpath*:egovframework/spring/com/context-mapper-god-oracle.xml",

		"classpath*:egovframework/spring/com/test-context-common.xml",

})

@ComponentScan(useDefaultFilters = false, basePackages = { "god" }, includeFilters = {
		@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { DatabaseMetaData.class }) })

public class DatabaseMetaDataOracleTest_A1_getTables {

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
	public void test() throws Exception {
		log.info("test");

		String catalog = "";
		String schemaPattern = "COM";
		String tableNamePattern = "COMTCADMINISTCODE";
		String types[] = { "", "" };

		List<TableVO> tables = databaseMetaData.getTables(catalog, schemaPattern, tableNamePattern, types);
		int size = tables.size();
		log.debug("tables");
		log.debug("\n\n\n");
		log.debug("size={}", size);
		log.debug("\n\n\n");

		for (TableVO table : tables) {
			log.debug("getTableCat={}", table.getTableCat());
			log.debug("getTableSchem={}", table.getTableSchem());
			log.debug("getTableName={}", table.getTableName());
			log.debug("getTableType={}", table.getTableType());
			log.debug("getRemarks={}", table.getRemarks());
			log.debug("getTypeCat={}", table.getTypeCat());
			log.debug("getTypeSchem={}", table.getTypeSchem());
			log.debug("getTypeName={}", table.getTypeName());
			log.debug("getSelfReferencingColName={}", table.getSelfReferencingColName());
			log.debug("getRefGeneration={}", table.getRefGeneration());
			log.debug("\n\n\n");
		}
	}

}
