package god.codegen.oracle.alltables.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
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

import egovframework.rte.psl.dataaccess.util.EgovMap;
import god.codegen.oracle.alltables.service.AllTablesVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AllTablesMapperTest_selectList.class })
@ActiveProfiles({ "oracle", "dummy" })

@Configuration

//@ImportResource({ "classpath*:/egovframework/spring/com/test-context-dao.xml" })
@ImportResource({ "classpath*:/egovframework/spring/com/context-crypto.xml",
		"classpath*:/egovframework/spring/com/context-datasource.xml",
		"classpath*:/egovframework/spring/com/context-mapper.xml",
		"classpath*:/egovframework/spring/com/context-mapper-god-oracle.xml",
		"classpath*:/egovframework/spring/com/test-context-common.xml" })

@ComponentScan(useDefaultFilters = false, basePackages = {
		"god.codegen.oracle.alltables.service.impl" }, includeFilters = {
				@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { AllTablesMapper.class }) })

public class AllTablesMapperTest_selectList {

	@Autowired
	ApplicationContext context;

	@Autowired
	AllTablesMapper mapper;

	@Before
	public void setUp() throws Exception {
		String[] beanDefinitionNames = context.getBeanDefinitionNames();

		for (String beanDefinitionName : beanDefinitionNames) {
			log.debug("beanDefinitionName={}", beanDefinitionName);
		}
	}

	@Test
	public void test() throws Exception {
		log.debug("test");

		// given
		AllTablesVO vo = new AllTablesVO();

		List<String> owners = new ArrayList<>();
		owners.add("COM");
		owners.add("COM320");
		vo.setOwners(owners);

//		vo.setOwner("COM");

		// when
		List<EgovMap> results = mapper.selectList(vo);

		// then
		assertEquals(results.get(0).get("owner"), vo.getOwner());

		log.debug("results={}", results);

		results.forEach(result -> {
			log.debug("result={}", result);
			log.debug("owner={}", result.get("owner"));
			log.debug("tableName={}", result.get("tableName"));
			log.debug("tableComments={}", result.get("tableComments"));
		});
	}

}
