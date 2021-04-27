package god.codegen.oracle.alltables.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.SystemUtils;
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
import org.springframework.util.StringUtils;

import egovframework.rte.fdl.string.EgovDateUtil;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import god.codegen.oracle.alltabcols.service.AllTabColsVO;
import god.codegen.oracle.alltabcols.service.impl.AllTabColsMapper;
import god.codegen.oracle.alltables.service.AllTablesVO;
import lombok.extern.slf4j.Slf4j;
import model.Attribute;
import model.DataModelContext;
import model.Entity;
import operation.CrudCodeGen;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CrudCodeGenTest_A1_sql.class })
@ActiveProfiles({ "oracle", "dummy" })

@Configuration

@ImportResource({

		"classpath*:/egovframework/spring/com/context-crypto.xml",
		"classpath*:/egovframework/spring/com/context-datasource.xml",
		"classpath*:/egovframework/spring/com/context-mapper.xml",
		"classpath*:/egovframework/spring/com/context-mapper-god-oracle.xml",

		"classpath*:/egovframework/spring/com/test-context-common.xml",

})

@ComponentScan(useDefaultFilters = false, basePackages = {
		"god.codegen.oracle.alltables.service.impl" }, includeFilters = {
				@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { AllTablesMapper.class,
						AllTabColsMapper.class }) })

public class CrudCodeGenTest_A1_sql {

	private static final StopWatch STOP_WATCH = new StopWatch();

	private static final String FILE_PATHNAME = SystemUtils.USER_HOME + "/Desktop/god.codegen/sql";

	@Autowired
	private ApplicationContext context;

	@Autowired
	private AllTablesMapper allTablesMapper;

	@Autowired
	private AllTabColsMapper allTabColsMapper;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		log.debug("setUpBeforeClass");

		log.debug("start");
		STOP_WATCH.start();

		try {
			FileUtils.forceDelete(new File(FILE_PATHNAME));
		} catch (IOException e) {
			log.error(e.getMessage());
		}
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
		log.info("start");
		log.info("select 중");

		// given
		AllTablesVO allTablesVO = new AllTablesVO();

//		allTablesVO.setOwner("COM");
//		allTablesVO.setOwner("COM320");
//		allTablesVO.setTableName("COMTCADMINISTCODE");
//		allTablesVO.setTableName("COMTCADMINISTCODERECPTNLOG");

		List<String> owners = new ArrayList<>();
		owners.add("COM");
		owners.add("COM320");
		allTablesVO.setOwners(owners);

		List<String> tableNames = new ArrayList<>();
//		tableNames.add("COMTCADMINISTCODE");
//		tableNames.add("COMTCADMINISTCODERECPTNLOG");
		allTablesVO.setTableNames(tableNames);

		AllTabColsVO allTabColsVO = new AllTabColsVO();
		allTabColsVO.setOwner(allTablesVO.getOwner());
		allTabColsVO.setTableName(allTablesVO.getTableName());
		allTabColsVO.setOwners(owners);
		allTabColsVO.setTableNames(tableNames);

		// when
		List<EgovMap> allTables = allTablesMapper.selectAllTablesList(allTablesVO);
		List<EgovMap> allTabCols = allTabColsMapper.selectAllTabColsList(allTabColsVO);

		// then
//		assertEquals(results.get(0).get("owner"), vo.getOwner());

		List<DataModelContext> dataModels = new ArrayList<>();

		String createDate = EgovDateUtil.toString(new Date(), "", null);

		int i = 1;
		int size = allTables.size();

		for (EgovMap allTable : allTables) {
			String allTableOwner = (String) allTable.get("owner");
			String allTableTableName = (String) allTable.get("tableName");
			String allTableTableComments = (String) allTable.get("tableComments");
			String pkName = (String) allTable.get("pkName");

			DataModelContext dataModel = new DataModelContext();

			Entity entity = new Entity(allTableTableName);
			entity.setOwner(allTableOwner);
			entity.setTableComments(allTableTableComments);
			entity.setPkName(pkName);
			dataModel.setEntity(entity);

			dataModel.setPackageName("god.codegen." + entity.getLcName());
			dataModel.setAuthor("이백행");
			dataModel.setTeam("갓팀");
			dataModel.setCreateDate(createDate);

			List<Attribute> attributes = new ArrayList<Attribute>();
			List<Attribute> primaryKeys = new ArrayList<Attribute>();

			for (EgovMap allTabCol : allTabCols) {
				String allTabColOwner = (String) allTabCol.get("owner");
				String allTabColTableName = (String) allTabCol.get("tableName");
				String columnName = (String) allTabCol.get("columnName");
				String dataType = (String) allTabCol.get("dataType");
				int dataLength = MapUtils.getIntValue(allTabCol, "dataLength");
				String nullable = (String) allTabCol.get("nullable");
				String allTabColTableComments = (String) allTabCol.get("tableComments");
				String columnComments = (String) allTabCol.get("columnComments");
				String pk = (String) allTabCol.get("pk");

				if (allTabColOwner.equals(allTableOwner) && allTableTableName.equals(allTabColTableName)) {
					Attribute attr = new Attribute(columnName);
					attr.setOwner(allTabColOwner);
					attr.setTableName(allTabColTableName);
					attr.setType(dataType);
					attr.setNullable(nullable);
					attr.setDataLength(dataLength);
					attr.setTableComments(allTabColTableComments);
					attr.setColumnComments(columnComments);
					attributes.add(attr);
					if ("Y".equals(pk)) {
						primaryKeys.add(attr);
					}
				}
			}

			dataModel.setAttributes(attributes);
			dataModel.setPrimaryKeys(primaryKeys);

			dataModels.add(dataModel);

			log.info("select={} of {}, {}, {}, {}", i, size, dataModel.getEntity().getOwner(),
					dataModel.getEntity().getName(), dataModel.getEntity().getTableComments());
			log.info("");

			i++;
		}

		CrudCodeGen crudCodeGen = new CrudCodeGen();

		log.info("writeStringToFile");

		int j = 1;

		for (DataModelContext dataModel : dataModels) {
			String data = crudCodeGen.generate(dataModel, "god/templates/crud/sql/sql.vm");
			writeStringToFile(dataModel, data);

			log.info("writeStringToFile={} of {}, {}, {}, {}", j, size, dataModel.getEntity().getOwner(),
					dataModel.getEntity().getName(), dataModel.getEntity().getTableComments());
			log.info("");

			j++;
		}
	}

	private void writeStringToFile(DataModelContext dataModel, String data) {
		File file = getFile(dataModel);
		Charset encoding = StandardCharsets.UTF_8;
		log.debug("name={}", encoding.name());

		try {
			FileUtils.writeStringToFile(file, data, encoding);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	private File getFile(DataModelContext dataModel) {
		boolean hasText = StringUtils.hasText(dataModel.getEntity().getTableComments());

		StringBuffer sb = new StringBuffer(FILE_PATHNAME);

		sb.append("/");
		sb.append(dataModel.getEntity().getOwner());

		sb.append("/");
		sb.append(dataModel.getEntity().getName());
		if (hasText) {
			sb.append(" ");
			sb.append(dataModel.getEntity().getTableComments());
		}

		sb.append("/");
		sb.append(dataModel.getEntity().getName());
		if (hasText) {
			sb.append(" ");
			sb.append(dataModel.getEntity().getTableComments());
		}

		sb.append(".sql");

		log.debug("FILE_PATHNAME={}", FILE_PATHNAME);
		log.debug("pathname={}", sb);

		return new File(sb.toString());
	}

}
