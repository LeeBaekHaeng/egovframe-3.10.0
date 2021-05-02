package god.codegen.datamodel.service.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import god.codegen.datamodel.service.DataModelService;
import god.codegen.datamodel.service.DataModelVO;
import god.test.GodTestV1;
import lombok.extern.slf4j.Slf4j;
import model.DataModelContext;

@Slf4j
@ContextConfiguration(classes = { V2DataModelServiceImplConfigurationTest.class })
public class V2DataModelServiceImplTest extends GodTestV1 {

	private static final String SCHEMA_PATTERN = "COM";
//	private static final String SCHEMA_PATTERN = "COM320";

//	private static final String TABLE_NAME_PATTERN = null;
	private static final String TABLE_NAME_PATTERN = "COMTCADMINISTCODE";

	@Autowired
	private DataModelService dataModelService;

	@Test
	public void test() {
		log.debug("test");
		log.debug("");

		DataModelVO dataModelVO = new DataModelVO();
//		dataModelVO.setSchemaPattern(SCHEMA_PATTERN);
		dataModelVO.setSchemaPattern(SCHEMA_PATTERN);
		dataModelVO.setTableNamePattern(TABLE_NAME_PATTERN);

		List<DataModelContext> dataModels = dataModelService.getDataModel(dataModelVO);
		int size = dataModels.size();

		log.debug("dataModels={}", dataModels);
		log.debug("size={}", size);
		log.debug("");
	}

}
