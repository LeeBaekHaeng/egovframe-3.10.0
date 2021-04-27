package god.codegen.oracle.alltables.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
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

import egovframework.rte.psl.dataaccess.util.EgovMap;
import god.codegen.oracle.alltables.service.AllTablesVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AllTablesMapperTest_selectList.class })
@ActiveProfiles({ "oracle", "dummy" })

@Configuration

//@ImportResource({ "classpath*:/egovframework/spring/com/test-context-dao.xml" })
@ImportResource({

		"classpath*:/egovframework/spring/com/context-crypto.xml",
		"classpath*:/egovframework/spring/com/context-datasource.xml",
		"classpath*:/egovframework/spring/com/context-mapper.xml",
		"classpath*:/egovframework/spring/com/context-mapper-god-oracle.xml",

		"classpath*:/egovframework/spring/com/test-context-common.xml",

})

@ComponentScan(useDefaultFilters = false, basePackages = {
		"god.codegen.oracle.alltables.service.impl" }, includeFilters = {
				@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { AllTablesMapper.class }) })

public class AllTablesMapperTest_selectList {

	private static final StopWatch STOP_WATCH = new StopWatch();

	@Autowired
	private ApplicationContext context;

	@Autowired
	private AllTablesMapper mapper;

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
		log.debug("test");

		// given
		AllTablesVO vo = new AllTablesVO();

//		vo.setOwner("COM");
//		vo.setOwner("COM320");
//		vo.setTableName("COMTCADMINISTCODE");
//		vo.setTableName("COMTCADMINISTCODERECPTNLOG");

		List<String> owners = new ArrayList<>();
		owners.add("COM");
		owners.add("COM320");
		vo.setOwners(owners);

		List<String> tableNames = new ArrayList<>();
		tableNames.add("COMTCADMINISTCODE");
		tableNames.add("COMTCADMINISTCODERECPTNLOG");
		vo.setTableNames(tableNames);

		// when
		List<EgovMap> results = mapper.selectAllTablesList(vo);

		// then
		assertEquals(results.get(0).get("owner"), owners.get(0));

		log.debug("results={}", results);
		log.debug("size={}", results.size());

		results.forEach(result -> {
			log.debug("result={}", result);
			log.debug("owner={}", result.get("owner"));
			log.debug("tableName={}", result.get("tableName"));
			log.debug("tableComments={}", result.get("tableComments"));
			log.debug("pkName={}", result.get("pkName"));
			log.debug("");
		});
	}

}
