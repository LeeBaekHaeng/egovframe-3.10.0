package egovframework.com.cmm.service.impl;

import javax.annotation.Resource;

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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionTemplate;

import egovframework.com.cmm.service.FileVO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { 트랜잭션_테스트.class })
@ActiveProfiles({ "mysql", "dummy" })
//@ActiveProfiles({ "oracle", "dummy" })

@Configuration
@ImportResource({ "classpath*:/egovframework/spring/com/test-context-dao.xml",
		"/egovframework/spring/com/idgn/context-idgn-File.xml" })
@ComponentScan(useDefaultFilters = false, basePackages = { "egovframework.com.cmm.service.impl" }, includeFilters = {
		@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { FileManageDAO.class }) })
public class 트랜잭션_테스트 {

	@Autowired
	private ApplicationContext context;

	@Autowired
	private DataSourceTransactionManager txManager;

//	txAdvice
//	requiredTx

	@Autowired
	private TransactionTemplate transactionTemplate;

	@Resource(name = "FileManageDAO")
	private FileManageDAO fileManageDAO;

	@Resource(name = "egovFileIdGnrService")
	private EgovIdGnrService egovFileIdGnrService;

	@Before
	public void setUp() throws Exception {
		String[] beanDefinitionNames = context.getBeanDefinitionNames();

		for (String beanDefinitionName : beanDefinitionNames) {
			log.debug("beanDefinitionName={}", beanDefinitionName);
		}
	}

	@Test
//	@Transactional
	public void test() {
		try {
			log.debug("test");

			log.debug("txManager={}", txManager);
			log.debug("isFailEarlyOnGlobalRollbackOnly={}", txManager.isFailEarlyOnGlobalRollbackOnly());

			log.debug("transactionTemplate={}", transactionTemplate);
			log.debug("getIsolationLevel={}", transactionTemplate.getIsolationLevel());
			log.debug("getName={}", transactionTemplate.getName());
			log.debug("getPropagationBehavior={}", transactionTemplate.getPropagationBehavior());
			log.debug("getTransactionManager={}", transactionTemplate.getTransactionManager());

			FileVO vo = new FileVO();
			vo.setAtchFileId(egovFileIdGnrService.getNextStringId());
			vo.setFileSn("0");
			vo.setFileMg("0");
			fileManageDAO.insertFileInf(vo);

			vo = new FileVO();
			vo.setAtchFileId(egovFileIdGnrService.getNextStringId());
			vo.setFileSn("0");
			vo.setFileMg("0");
			fileManageDAO.insertFileInf(vo);

			vo.getAtchFileId().substring(0, 1000);

			vo = new FileVO();
			vo.setAtchFileId(egovFileIdGnrService.getNextStringId());
			vo.setFileSn("0");
			vo.setFileMg("0");
			fileManageDAO.insertFileInf(vo);

			vo = new FileVO();
			vo.setAtchFileId(egovFileIdGnrService.getNextStringId());
			vo.setFileSn("0");
			vo.setFileMg("0");
			fileManageDAO.insertFileInf(vo);
		} catch (Exception e) {
			log.error("Exception");
		}
	}

}
