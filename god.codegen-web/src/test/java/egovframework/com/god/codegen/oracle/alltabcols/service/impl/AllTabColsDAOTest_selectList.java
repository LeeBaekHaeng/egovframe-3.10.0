package egovframework.com.god.codegen.oracle.alltabcols.service.impl;

import static org.junit.Assert.assertEquals;

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

import egovframework.com.god.codegen.oracle.alltabcols.service.AllTabColsVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AllTabColsDAOTest_selectList.class })
@ActiveProfiles({ "oracle", "dummy" })

@Configuration
@ImportResource({ "classpath*:/egovframework/spring/com/test-context-dao.xml" })
@ComponentScan(useDefaultFilters = false, basePackages = {
		"egovframework.com.god.codegen.oracle.alltabcols.service.impl" }, includeFilters = {
				@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { AllTabColsDAO.class }) })
public class AllTabColsDAOTest_selectList {

	@Autowired
	ApplicationContext context;

	@Autowired
	AllTabColsDAO dao;

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
		AllTabColsVO vo = new AllTabColsVO();
		vo.setOwner("COM");

		// when
		List<EgovMap> results = dao.selectList(vo);

		// then
		assertEquals(results.get(0).get("owner"), vo.getOwner());

		log.debug("results={}", results);

		results.forEach(result -> {
			log.debug("result={}", result);
			log.debug("owner={}", result.get("owner"));
			log.debug("tableName={}", result.get("tableName"));
			log.debug("columnName={}", result.get("columnName"));
			log.debug("dataType={}", result.get("dataType"));
			log.debug("dataLength={}", result.get("dataLength"));
			log.debug("dataPrecision={}", result.get("dataPrecision"));
			log.debug("dataScale={}", result.get("dataScale"));
			log.debug("nullable={}", result.get("nullable"));
			log.debug("columnId={}", result.get("columnId"));
			log.debug("dataDefault={}", result.get("dataDefault"));
			log.debug("columnComments={}", result.get("columnComments"));
		});
	}

}
