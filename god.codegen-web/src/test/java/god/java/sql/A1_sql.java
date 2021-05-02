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
import org.springframework.util.StringUtils;

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

	@Autowired
	private DataModelService dataModelService;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		FileUtils.forceDelete(new File(FILE_PATHNAME));
	}

	@Override
	public void test() {
		try {
			DataModelVO dataModelVO = new DataModelVO();
			dataModelVO.setSchemaPattern("COM");
			dataModelVO.setTableNamePattern("COMTCADMINISTCODE");

			List<DataModelContext> dataModels = dataModelService.getDataModel(dataModelVO);
			int size = dataModels.size();

			log.debug("dataModels={}", dataModels);
			log.debug("size={}", size);
			log.debug("");

			CrudCodeGen crudCodeGen = new CrudCodeGen();

			int i = 1;

			for (DataModelContext dataModel : dataModels) {

				String data = crudCodeGen.generate(dataModel, "god/templates/crud/sql/sql.vm");
				writeStringToFile(dataModel, data);

				log.info("writeStringToFile={} of {}, {}, {}, {}", i, size, dataModel.getEntity().getOwner(),
						dataModel.getEntity().getName(), dataModel.getEntity().getTableComments());
				log.info("");

				i++;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

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
