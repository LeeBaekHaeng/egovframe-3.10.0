package god.java.sql;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.SystemUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import god.codegen.datamodel.service.DataModelService;
import god.codegen.datamodel.service.DataModelVO;
import god.codegen.oracle.alltabcols.service.impl.AllTabColsMapper;
import god.codegen.oracle.alltables.service.impl.AllTablesMapper;
import god.test.GodTestV1;
import lombok.extern.slf4j.Slf4j;
import model.Attribute;
import model.DataModelContext;
import model.Entity;
import operation.CrudCodeGen;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { A1_sql.class })

//@ActiveProfiles({ "altibase", "dummy" })
//@ActiveProfiles({ "cubrid", "dummy" })
//@ActiveProfiles({ "maria", "dummy" })
//@ActiveProfiles({ "mysql", "dummy" })
@ActiveProfiles({ "oracle", "dummy" })
//@ActiveProfiles({ "postgres", "dummy" })
//@ActiveProfiles({ "tibero", "dummy" })

@Configuration

@ImportResource({

//		"classpath*:egovframework/spring/com/**/context-*.xml",

		"classpath*:/egovframework/spring/com/context-crypto.xml",
		"classpath*:/egovframework/spring/com/context-datasource.xml",

		"classpath*:egovframework/spring/com/context-mapper.xml",
		"classpath*:egovframework/spring/com/context-mapper-god-oracle.xml",

		"classpath*:egovframework/spring/com/test-context-common.xml",

})

@ComponentScan(useDefaultFilters = false, basePackages = { "god" }, includeFilters = {
		@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { AllTablesMapper.class, AllTabColsMapper.class,
				DatabaseMetaDataOracle.class, DataModelService.class }) })

public class A1_sql extends GodTestV1 {

	private static final String FILE_PATHNAME = SystemUtils.USER_HOME + "/Desktop/god.codegen/sql";

//	private static final String SCHEMA_PATTERN = "COM";
	private static final String SCHEMA_PATTERN = "COM320";

	private static final String TABLE_NAME_PATTERN = null;
//	private static final String TABLE_NAME_PATTERN = "COMTCADMINISTCODE";

	@Autowired
	private DataModelService dataModelService;

	@Override
	public void setUp() throws Exception {
		super.setUp();
//		FileUtils.forceDelete(new File(FILE_PATHNAME));
//		FileUtils.forceDeleteOnExit(new File(FILE_PATHNAME));
//		FileUtils.deleteDirectory(new File(FILE_PATHNAME));
		FileUtils.deleteQuietly(new File(FILE_PATHNAME));
	}

	@Override
	public void test() {

		DataModelVO dataModelVO = new DataModelVO();
//		dataModelVO.setSchemaPattern(SCHEMA_PATTERN);
		dataModelVO.setSchemaPattern(SCHEMA_PATTERN);
		dataModelVO.setTableNamePattern(TABLE_NAME_PATTERN);

		List<DataModelContext> dataModels = dataModelService.getDataModel(dataModelVO);
		int size = dataModels.size();

		log.debug("dataModels={}", dataModels);
		log.debug("size={}", size);
		log.debug("");

		CrudCodeGen crudCodeGen = new CrudCodeGen();

		int i = 1;

		for (DataModelContext dataModel : dataModels) {
//			debug(dataModel);
//			debugGodPathname(dataModel);
			generate(crudCodeGen, dataModel, i, size);
			info(dataModel, i, size);
			i++;
		}

	}

	private void generate(CrudCodeGen crudCodeGen, DataModelContext dataModel, int i, int size) {
		try {
			String data = crudCodeGen.generate(dataModel, "god/templates/crud/sql/sql.vm");
			writeStringToFile(dataModel, "", data);

			data = crudCodeGen.generate(dataModel, "god/templates/crud/sql/insert.vm");
			writeStringToFile(dataModel, "A1-insert", data);

			data = crudCodeGen.generate(dataModel, "god/templates/crud/sql/select.vm");
			writeStringToFile(dataModel, "B1-select", data);

			data = crudCodeGen.generate(dataModel, "god/templates/crud/sql/selectList.vm");
			writeStringToFile(dataModel, "C1-selectList", data);

			data = crudCodeGen.generate(dataModel, "god/templates/crud/sql/selectListCount.vm");
			writeStringToFile(dataModel, "D1-selectListCount", data);

			data = crudCodeGen.generate(dataModel, "god/templates/crud/sql/update.vm");
			writeStringToFile(dataModel, "E1-update", data);

			data = crudCodeGen.generate(dataModel, "god/templates/crud/sql/delete.vm");
			writeStringToFile(dataModel, "F1-delete", data);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	private void writeStringToFile(DataModelContext dataModel, String filename, String data) {
		File file = new File(FILE_PATHNAME + "/" + dataModel.getGodPathname().getSql(filename));
		Charset encoding = StandardCharsets.UTF_8;
//		log.debug("name={}", encoding.name());
//		log.debug("file={}", file);

		try {
			FileUtils.writeStringToFile(file, data, encoding);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	private void info(DataModelContext dataModel, int i, int size) {
		log.info("writeStringToFile={} of {}, {}, {}, {}", i, size, dataModel.getEntity().getOwner(),
				dataModel.getEntity().getName(), dataModel.getEntity().getTableComments());
	}

	// debug

	void debug(DataModelContext dataModel) {
		log.debug("dataModel={}", dataModel);

		Entity entity = dataModel.getEntity();
		log.debug("entity={}", entity);
		log.debug("getOwner={}", entity.getOwner());
		log.debug("getTableName={}", entity.getTableName());
		log.debug("getTableComments={}", entity.getTableComments());
		log.debug("getPkName={}", entity.getPkName());

		List<Attribute> attributes = dataModel.getAttributes();
		for (Attribute attribute : attributes) {
			log.debug("attribute={}", attribute);
			log.debug("getName={}", attribute.getName());
			log.debug("");
		}

		log.debug("");
	}

	void debugGodPathname(DataModelContext dataModel) {
		log.debug("dataModel={}", dataModel);
		log.debug("getSql={}", dataModel.getGodPathname().getSql());
		log.debug("insert={}", dataModel.getGodPathname().getSql("A1-insert"));
		log.debug("select={}", dataModel.getGodPathname().getSql("B1-select"));
		log.debug("selectList={}", dataModel.getGodPathname().getSql("C1-selectList"));
		log.debug("selectListCount={}", dataModel.getGodPathname().getSql("D1-selectListCount"));
		log.debug("update={}", dataModel.getGodPathname().getSql("E1-update"));
		log.debug("delete={}", dataModel.getGodPathname().getSql("F1-delete"));
		log.debug("");
	}

}
