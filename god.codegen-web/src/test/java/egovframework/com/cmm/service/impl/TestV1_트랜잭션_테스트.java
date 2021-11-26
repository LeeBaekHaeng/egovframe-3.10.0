package egovframework.com.cmm.service.impl;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.service.FileVO;
import god.test.GodTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@ContextConfiguration(classes = { TestV1_트랜잭션_테스트.class })

@ImportResource({

		"classpath*:/egovframework/spring/com/test-context-dao.xml",

		"classpath*:/egovframework/spring/com/idgn/context-idgn-File.xml"

})

@ComponentScan(useDefaultFilters = false, basePackages = { "egovframework.com.cmm.service.impl" }, includeFilters = {
		@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {

				TestV1AAA001ParentServiceImpl.class,

				TestV1AAA002ChildServiceImpl.class,

				TestV1AAA003ChildServiceImpl.class,

				FileManageDAO.class }) })
public class TestV1_트랜잭션_테스트 extends GodTestV1 {

	@Resource
	private TestV1AAA001ParentServiceImpl aaa001Service;

	@Test
//	@Commit
	public void test() {
		log.debug("test");

		FileVO vo = new FileVO();
		vo.setFileSn("0");
		vo.setFileMg(vo.getFileSn());

		boolean insertFileInf = aaa001Service.insertFileInf(vo);

		log.debug("insertFileInf={}", insertFileInf);

		assertEquals(insertFileInf, true);
	}

	@Test
//	@Commit
	public void test2() {
		log.debug("test2");

		FileVO vo = new FileVO();
		vo.setFileSn("0");

		boolean insertFileInf = aaa001Service.insertFileInf(vo);

		log.debug("insertFileInf={}", insertFileInf);

		assertEquals(insertFileInf, false);
	}

}
