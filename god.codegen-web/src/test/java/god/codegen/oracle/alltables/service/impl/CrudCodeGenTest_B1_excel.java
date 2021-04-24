package god.codegen.oracle.alltables.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
import egovframework.rte.fdl.string.EgovDateUtil;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import god.codegen.oracle.alltabcols.service.AllTabColsVO;
import god.codegen.oracle.alltabcols.service.impl.AllTabColsMapper;
import god.codegen.oracle.alltables.service.AllTablesVO;
import lombok.extern.slf4j.Slf4j;
import model.DataModelContext;
import model.Entity;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CrudCodeGenTest_B1_excel.class })
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

public class CrudCodeGenTest_B1_excel {

	private static final String FILE_PATHNAME = SystemUtils.USER_HOME + SystemUtils.FILE_SEPARATOR + "Desktop"
			+ SystemUtils.FILE_SEPARATOR + "god.codegen" + SystemUtils.FILE_SEPARATOR + "excel"
			+ SystemUtils.FILE_SEPARATOR + "테이블.xlsx";

	private static final StopWatch STOP_WATCH = new StopWatch();

	@Autowired
	private ApplicationContext context;

	@Autowired
	private AllTablesMapper allTablesMapper;

//	@Autowired
//	private AllTabColsMapper allTabColsMapper;

	private EgovExcelService egovExcelService = new EgovExcelServiceImpl();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		log.info("setUpBeforeClass");
		STOP_WATCH.start();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
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
		log.info("test");

		log.info("start");
		log.info("select 중");

		// given
		AllTablesVO allTablesVO = new AllTablesVO();

		List<String> owners = new ArrayList<>();
		owners.add("COM");
//		owners.add("COM320");
		allTablesVO.setOwners(owners);

//		List<String> tableNames = new ArrayList<>();
//		tableNames.add("DEPARTMENT");
//		tableNames.add("EMPLOYEE");
//		tableNames.add("IDS");
//		tableNames.add("IMGTEMP");
//		tableNames.add("J_ATTACHFILE");
//		tableNames.add("RTETNGOODS");
//		tableNames.add("TEST_CHILD");
//		tableNames.add("TEST_PARENT");
//		tableNames.add("TEST_RECURSIVE");
//		tableNames.add("TOAD_PLAN_TABLE");
//		// COM320
//		tableNames.add("IDS");
//		tableNames.add("IMGTEMP");
//		tableNames.add("J_ATTACHFILE");
//		allTablesVO.setTableNames(tableNames);

//		allTablesVO.setOwner("COM");
////		allTablesVO.setTableName("COMTCADMINISTCODE");
		allTablesVO.setTableName("COMTCADMINISTCODERECPTNLOG");

		AllTabColsVO allTabColsVO = new AllTabColsVO();
		allTabColsVO.setOwner(allTablesVO.getOwner());
		allTabColsVO.setTableName(allTablesVO.getTableName());

		// when
		List<EgovMap> allTables = allTablesMapper.selectList(allTablesVO);
//		List<EgovMap> allTabCols = allTabColsMapper.selectList(allTabColsVO);

		// then
//		assertEquals(results.get(0).get("owner"), vo.getOwner());

		List<DataModelContext> dataModels = new ArrayList<>();

		String createDate = EgovDateUtil.toString(new Date(), "", null);

		int i = 1;
		int size = allTables.size();

		Workbook wb = new XSSFWorkbook();
		wb.createSheet("테이블");
		Sheet sheet = wb.getSheet("테이블");
		int rownum = 0;

		Row row = sheet.createRow(rownum++);
		int column = 0;
		Cell cell = row.createCell(column++);
		cell.setCellValue("OWNER");

		cell = row.createCell(column++);
		cell.setCellValue("TABLE_NAME");

		cell = row.createCell(column++);
		cell.setCellValue("TABLE_COMMENTS");

		cell = row.createCell(column++);
		cell.setCellValue("class");

		cell = row.createCell(column++);
		cell.setCellValue("package1");

		cell = row.createCell(column++);
		cell.setCellValue("package2");

		cell = row.createCell(column++);
		cell.setCellValue("package3");

		for (EgovMap allTable : allTables) {
			String allTableOwner = (String) allTable.get("owner");
			String allTableTableName = (String) allTable.get("tableName");
			String allTableTableComments = (String) allTable.get("tableComments");

			DataModelContext dataModel = new DataModelContext();

			Entity entity = new Entity(allTableTableName);
			entity.setOwner(allTableOwner);
			entity.setTableComments(allTableTableComments);
			dataModel.setEntity(entity);

			dataModel.setPackageName("god.codegen." + entity.getLcName());
			dataModel.setAuthor("이백행");
			dataModel.setTeam("갓팀");
			dataModel.setCreateDate(createDate);

//			List<Attribute> attributes = new ArrayList<Attribute>();
//			List<Attribute> primaryKeys = new ArrayList<Attribute>();
//
//			for (EgovMap allTabCol : allTabCols) {
//				String allTabColOwner = (String) allTabCol.get("owner");
//				String allTabColTableName = (String) allTabCol.get("tableName");
//				String columnName = (String) allTabCol.get("columnName");
//				String dataType = (String) allTabCol.get("dataType");
//				int dataLength = MapUtils.getIntValue(allTabCol, "dataLength");
//				String nullable = (String) allTabCol.get("nullable");
//				String allTabColTableComments = (String) allTabCol.get("tableComments");
//				String columnComments = (String) allTabCol.get("columnComments");
//
//				if (allTabColOwner.equals(allTableOwner) && allTableTableName.equals(allTabColTableName)) {
//					Attribute attr = new Attribute(columnName);
//					attr.setType(dataType);
//					attr.setNullable(nullable);
//					attr.setDataLength(dataLength);
//					attr.setTableComments(allTabColTableComments);
//					attr.setColumnComments(columnComments);
//					attributes.add(attr);
//					primaryKeys.add(attr);
//				}
//			}
//
//			dataModel.setAttributes(attributes);
//			dataModel.setPrimaryKeys(primaryKeys);

			dataModels.add(dataModel);

			row = sheet.createRow(rownum);
			column = 0;
			cell = row.createCell(column++);
			cell.setCellValue(allTableOwner);

			cell = row.createCell(column++);
			cell.setCellValue(allTableTableName);

			cell = row.createCell(column++);
			cell.setCellValue(allTableTableComments);

			cell = row.createCell(column++);
			cell.setCellValue(entity.getPcName());

			if (allTableTableName.startsWith("COM")) {
				cell = row.createCell(column++);
				cell.setCellValue(entity.getLcName().substring(0, 3));

				cell = row.createCell(column++);
				cell.setCellValue(entity.getLcName().substring(3, 5));

				cell = row.createCell(column++);
				cell.setCellValue(entity.getLcName().substring(5));
			} else {
				cell = row.createCell(column++);
				cell.setCellValue(entity.getLcName().replaceAll("_", ""));
			}

			rownum++;

			log.info("select={} of {}, {}, {}, {}", i, size, dataModel.getEntity().getOwner(),
					dataModel.getEntity().getName(), dataModel.getEntity().getTableComments());

			i++;
		}

		String filepath = FILE_PATHNAME;
		egovExcelService.createWorkbook(wb, filepath);
	}

}
