package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import model.Attribute;
import model.DataModelContext;
import model.Entity;
import operation.CrudCodeGen;

@Slf4j
public class CrudCodeGenTest {
	private CrudCodeGen crudCodeGen;
	private DataModelContext dataModel;

	@Before
	public void setUp() throws Exception {
		crudCodeGen = new CrudCodeGen();

		dataModel = new DataModelContext();

		dataModel.setPackageName("pkg");
		dataModel.setAuthor("홍길동");
		dataModel.setTeam("실행환경 개발팀");
		dataModel.setCreateDate("2009.02.01");

		Entity entity = new Entity("SAMPLE2");

		dataModel.setEntity(entity);

		List<Attribute> attributes = new ArrayList<Attribute>();
		List<Attribute> primaryKeys = new ArrayList<Attribute>();

		Attribute attr = new Attribute("ID");
		attr.setJavaType("String");
		attributes.add(attr);
		primaryKeys.add(attr);

		attr = new Attribute("NAME");
		attr.setJavaType("String");
		attributes.add(attr);
//		primaryKeys.add(attr);

		attr = new Attribute("DESCRIPTION");
		attr.setJavaType("String");
		attributes.add(attr);

		attr = new Attribute("USE_YN");
		attr.setJavaType("String");
		attributes.add(attr);

		attr = new Attribute("REG_USER");
		attr.setJavaType("String");
		attributes.add(attr);

		dataModel.setAttributes(attributes);
		dataModel.setPrimaryKeys(primaryKeys);

	}

	private void genAndDiff(String templateFile, String targetFile) throws Exception {
		String result = crudCodeGen.generate(dataModel, templateFile);
		log.debug("result={}", result);
	}

	@Test
	public void testSQLMap() throws Exception {
		String templateFile = "templates/crud/src/main/resources/pkg/EgovSample_Sample2_SQL.vm";
		String targetFile = "templates/crud/src/main/resources/pkg/EgovSample_Sample2_SQL.xml";

		genAndDiff(templateFile, targetFile);
	}

	@Test
	public void testService() throws Exception {
		String templateFile = "templates/crud/src/main/java/pkg/service/EgovSample2Service.vm";
		String targetFile = "templates/crud/src/main/java/pkg/service/EgovSample2Service.jav";

		genAndDiff(templateFile, targetFile);
	}

	@Test
	public void testVO() throws Exception {
		String templateFile = "templates/crud/src/main/java/pkg/service/Sample2VO.vm";
		String targetFile = "templates/crud/src/main/java/pkg/service/Sample2VO.jav";

		genAndDiff(templateFile, targetFile);
	}

	@Test
	public void testServiceImpl() throws Exception {
		String templateFile = "templates/crud/src/main/java/pkg/service/impl/EgovSample2ServiceImpl.vm";
		String targetFile = "templates/crud/src/main/java/pkg/service/impl/EgovSample2ServiceImpl.jav";

		genAndDiff(templateFile, targetFile);
	}

	@Test
	public void testDAO() throws Exception {
		String templateFile = "templates/crud/src/main/java/pkg/service/impl/Sample2DAO.vm";
		String targetFile = "templates/crud/src/main/java/pkg/service/impl/Sample2DAO.jav";

		genAndDiff(templateFile, targetFile);
	}

	@Test
	public void testController() throws Exception {
		String templateFile = "templates/crud/src/main/java/pkg/web/EgovSample2Controller.vm";
		String targetFile = "templates/crud/src/main/java/pkg/web/EgovSample2Controller.jav";

		genAndDiff(templateFile, targetFile);
	}

	@Test
	public void testListView() throws Exception {
		String templateFile = "templates/crud/src/webapp/WEB-INF/jsp/pkg/egovSample2List.vm";
		String targetFile = "templates/crud/src/webapp/WEB-INF/jsp/pkg/egovSample2List.jsp";

		genAndDiff(templateFile, targetFile);
	}

	@Test
	public void testRegisterView() throws Exception {
		String templateFile = "templates/crud/src/webapp/WEB-INF/jsp/pkg/egovSample2Register.vm";
		String targetFile = "templates/crud/src/webapp/WEB-INF/jsp/pkg/egovSample2Register.jsp";

		genAndDiff(templateFile, targetFile);
	}

}
