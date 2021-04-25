package god.codegen.oracle.alltables.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

import egovframework.rte.fdl.excel.EgovExcelService;
import egovframework.rte.fdl.excel.impl.EgovExcelServiceImpl;
import egovframework.rte.fdl.excel.util.EgovExcelUtil;
import egovframework.rte.fdl.string.EgovDateUtil;
import god.codegen.oracle.alltabcols.service.impl.AllTabColsMapper;
import lombok.extern.slf4j.Slf4j;
import model.Attribute;
import model.DataModelContext;
import model.Entity;
import operation.CrudCodeGen;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CrudCodeGenTest_C1_src.class })
@ActiveProfiles({ "oracle", "dummy" })

@Configuration

@ImportResource({ "classpath*:/egovframework/spring/com/context-crypto.xml",
		"classpath*:/egovframework/spring/com/context-datasource.xml",
		"classpath*:/egovframework/spring/com/context-mapper.xml",
		"classpath*:/egovframework/spring/com/context-mapper-god-oracle.xml",
		"classpath*:/egovframework/spring/com/test-context-common.xml" })

@ComponentScan(useDefaultFilters = false, basePackages = {
		"god.codegen.oracle.alltables.service.impl" }, includeFilters = {
				@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { AllTablesMapper.class,
						AllTabColsMapper.class }) })

public class CrudCodeGenTest_C1_src {

	private static final StopWatch STOP_WATCH = new StopWatch();

	private static final String FILE_PATHNAME = SystemUtils.USER_HOME + "/Desktop/god.codegen/src";

	private static final String TABLE_FILE_PATHNAME = SystemUtils.USER_HOME + SystemUtils.FILE_SEPARATOR + "Desktop"
			+ SystemUtils.FILE_SEPARATOR + "god.codegen" + SystemUtils.FILE_SEPARATOR + "excel"
			+ SystemUtils.FILE_SEPARATOR + "테이블.xlsx";

	private static final String COLUMN_FILE_PATHNAME = SystemUtils.USER_HOME + SystemUtils.FILE_SEPARATOR + "Desktop"
			+ SystemUtils.FILE_SEPARATOR + "god.codegen" + SystemUtils.FILE_SEPARATOR + "excel"
			+ SystemUtils.FILE_SEPARATOR + "컬럼.xlsx";

	@Autowired
	private ApplicationContext context;

	private EgovExcelService egovExcelService = new EgovExcelServiceImpl();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		log.info("start");
		log.info("setUpBeforeClass");
		STOP_WATCH.start();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		log.info("stop");
		log.info("tearDownAfterClass");

		STOP_WATCH.stop();

		log.info("getTotalTimeMillis={}", STOP_WATCH.getTotalTimeMillis());
		log.info("getTotalTimeSeconds={}", STOP_WATCH.getTotalTimeSeconds());
	}

	@Before
	public void setUp() throws Exception {
		log.info("setUp");

		String[] beanDefinitionNames = context.getBeanDefinitionNames();

		for (String beanDefinitionName : beanDefinitionNames) {
			log.debug("beanDefinitionName={}", beanDefinitionName);
		}

		try {
			FileUtils.forceDelete(new File(FILE_PATHNAME));
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	@After
	public void tearDown() throws Exception {
		log.info("tearDown");
	}

	@Test
	public void test() throws Exception {
		String templateName = TABLE_FILE_PATHNAME;
		XSSFWorkbook type = new XSSFWorkbook();
		XSSFWorkbook wb = egovExcelService.loadExcelTemplate(templateName, type);

		Sheet sheet = wb.getSheet("테이블");
		Sheet columnSheet = columnSheet();

		List<DataModelContext> dataModels = new ArrayList<>();

		String createDate = EgovDateUtil.toString(new Date(), "", null);

		int i = 1;
		int size = sheet.getPhysicalNumberOfRows();

		for (Row row : sheet) {
			if (row.getRowNum() == 0) {
				continue;
			}

			String owner = EgovExcelUtil.getValue(row.getCell(0));
			String tableName = EgovExcelUtil.getValue(row.getCell(1));
			String tableComments = EgovExcelUtil.getValue(row.getCell(2));
			String class1 = EgovExcelUtil.getValue(row.getCell(3));
			String package1 = EgovExcelUtil.getValue(row.getCell(4));
			String package2 = EgovExcelUtil.getValue(row.getCell(5));
			String package3 = EgovExcelUtil.getValue(row.getCell(6));

			DataModelContext dataModel = new DataModelContext();

			Entity entity = new Entity(class1);
			entity.setOwner(owner);
			entity.setTableName(tableName);
			entity.setTableComments(tableComments);
			dataModel.setEntity(entity);

			dataModel.setPackageName("god.test." + package1 + "." + package2 + "." + package3);
			dataModel.setAuthor("이백행");
			dataModel.setTeam("갓팀");
			dataModel.setCreateDate(createDate);

			List<Attribute> attributes = new ArrayList<Attribute>();
			List<Attribute> primaryKeys = new ArrayList<Attribute>();

			for (Row columnRow : columnSheet) {
				if (columnRow.getRowNum() == 0) {
					continue;
				}

				String allTabColOwner = EgovExcelUtil.getValue(columnRow.getCell(0));
				String allTabColTableName = EgovExcelUtil.getValue(columnRow.getCell(1));
				String columnName = EgovExcelUtil.getValue(columnRow.getCell(2));
				String dataType = EgovExcelUtil.getValue(columnRow.getCell(3));
				int dataLength = Integer.valueOf(EgovExcelUtil.getValue(columnRow.getCell(4)));
				String nullable = EgovExcelUtil.getValue(columnRow.getCell(5));
//				String columnId = EgovExcelUtil.getValue(columnRow.getCell(6));
				String allTabColTableComments = EgovExcelUtil.getValue(columnRow.getCell(7));
				String columnComments = EgovExcelUtil.getValue(columnRow.getCell(8));

				if (allTabColOwner.equals(owner) && tableName.equals(allTabColTableName)) {
					Attribute attr = new Attribute(columnName);
					attr.setType(dataType);
					attr.setNullable(nullable);
					attr.setDataLength(dataLength);
					attr.setTableComments(allTabColTableComments);
					attr.setColumnComments(columnComments);
					attributes.add(attr);
					primaryKeys.add(attr);
				}
			}

			dataModel.setAttributes(attributes);
			dataModel.setPrimaryKeys(primaryKeys);

			dataModels.add(dataModel);

			log.info("select={} of {}, {}, {}, {}", i, size, dataModel.getEntity().getOwner(),
					dataModel.getEntity().getName(), dataModel.getEntity().getTableComments());

			i++;
		}

		CrudCodeGen crudCodeGen = new CrudCodeGen();

		log.info("writeStringToFile");

		int j = 1;

		for (DataModelContext dataModel : dataModels) {
			String data = crudCodeGen.generate(dataModel, "god/templates/crud/src/main/java/pkg/service/Sample2.vm");
			writeStringToFile(dataModel, data, ".java");

			data = crudCodeGen.generate(dataModel, "god/templates/crud/src/main/java/pkg/service/Sample2VO.vm");
			writeStringToFile(dataModel, data, "VO.java");

			data = crudCodeGen.generate(dataModel,
					"god/templates/crud/src/main/resources/pkg/EgovSample_Sample2_MAPPER.vm");
			writeStringToFile(dataModel, data, "_SQL_oracle.xml");

			data = crudCodeGen.generate(dataModel,
					"god/templates/crud/src/main/java/pkg/service/impl/Sample2Mapper.vm");
			writeStringToFile(dataModel, data, "Mapper.java");

			data = crudCodeGen.generate(dataModel,
					"god/templates/crud/src/main/java/pkg/service/EgovSample2Service.vm");
			writeStringToFile(dataModel, data, "Service.java");

			data = crudCodeGen.generate(dataModel,
					"god/templates/crud/src/main/java/pkg/service/impl/EgovSample2ServiceImpl.vm");
			writeStringToFile(dataModel, data, "ServiceImpl.java");

//			data = crudCodeGen.generate(dataModel, "god/templates/crud/src/main/java/pkg/web/EgovSample2Controller.vm");
//			writeStringToFile(dataModel, data, "Controller.java");

			data = crudCodeGen.generate(dataModel,
					"god/templates/crud/src/main/java/pkg/web/EgovSample2RestController.vm");
			writeStringToFile(dataModel, data, "RestController.java");

			log.info("writeStringToFile={} of {}, {}, {}, {}", j, size, dataModel.getEntity().getOwner(),
					dataModel.getEntity().getName(), dataModel.getEntity().getTableComments());

			j++;
		}
	}

	private void writeStringToFile(DataModelContext dataModel, String data, String pathnameSuffix) {
		File file = getFile(dataModel, pathnameSuffix);
		Charset encoding = StandardCharsets.UTF_8;
		log.debug("name={}", encoding.name());

		try {
			FileUtils.writeStringToFile(file, data, encoding);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	private File getFile(DataModelContext dataModel, String pathnameSuffix) {
		StringBuffer sb = new StringBuffer();
		sb.append(FILE_PATHNAME);

		if ("_SQL_oracle.xml".equals(pathnameSuffix)) {
			sb.append("/main/resources/god/mapper/test/");
			sb.append(dataModel.getPackageName().replaceAll("god.test", "").replaceAll("\\.", "/"));
			sb.append(SystemUtils.FILE_SEPARATOR);
			sb.append(dataModel.getEntity().getName());
			sb.append(pathnameSuffix);
		} else {
			sb.append("/main/java/");
			sb.append(dataModel.getPackageName().replaceAll("\\.", "/"));
			if (".java".equals(pathnameSuffix)) {
				sb.append("/service");
			} else if ("VO.java".equals(pathnameSuffix)) {
				sb.append("/service");
			} else if ("Mapper.java".equals(pathnameSuffix)) {
				sb.append("/service/impl");
			} else if ("Service.java".equals(pathnameSuffix)) {
				sb.append("/service");
			} else if ("ServiceImpl.java".equals(pathnameSuffix)) {
				sb.append("/service/impl");
			} else if ("Controller.java".equals(pathnameSuffix)) {
				sb.append("/web");
			} else if ("RestController.java".equals(pathnameSuffix)) {
				sb.append("/web");
			}
			sb.append(SystemUtils.FILE_SEPARATOR);
			sb.append(dataModel.getEntity().getName());
			sb.append(pathnameSuffix);
		}

		log.debug("FILE_PATHNAME={}", FILE_PATHNAME);
		log.debug("pathname={}", sb);

		return new File(sb.toString());
	}

	private Sheet columnSheet() throws Exception {
		String templateName = COLUMN_FILE_PATHNAME;
		XSSFWorkbook type = new XSSFWorkbook();
		XSSFWorkbook wb = egovExcelService.loadExcelTemplate(templateName, type);

		return wb.getSheet("컬럼");
	}

}
