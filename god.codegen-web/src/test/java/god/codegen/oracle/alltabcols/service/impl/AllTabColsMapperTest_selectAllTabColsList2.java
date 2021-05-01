package god.codegen.oracle.alltabcols.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
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

import god.codegen.oracle.alltabcols.service.AllTabColsVO;
import god.java.sql.ColumnVO;
import god.test.jdbc.GodTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AllTabColsMapperTest_selectAllTabColsList2.class })
@ActiveProfiles({ "oracle", "dummy" })

@Configuration

//@ImportResource({ "classpath*:/egovframework/spring/com/test-context-dao.xml" })
@ImportResource({

//		"classpath*:egovframework/spring/com/**/context-*.xml",

		"classpath*:/egovframework/spring/com/context-crypto.xml",
		"classpath*:/egovframework/spring/com/context-datasource.xml",
		"classpath*:/egovframework/spring/com/context-mapper.xml",
		"classpath*:/egovframework/spring/com/context-mapper-god-oracle.xml",

		"classpath*:/egovframework/spring/com/test-context-common.xml",

})

@ComponentScan(useDefaultFilters = false, basePackages = {
		"god.codegen.oracle.alltabcols.service.impl" }, includeFilters = {
				@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { AllTabColsMapper.class }) })

public class AllTabColsMapperTest_selectAllTabColsList2 extends GodTestV1 {

	@Autowired
	private AllTabColsMapper allTabColsMapper;

	@Override
	@Test
	public void test() {
		log.debug("test");

		// given
		AllTabColsVO allTabColsVO = new AllTabColsVO();

//		vo.setOwner("COM");
//		vo.setOwner("COM320");
//		vo.setTableName("COMTCADMINISTCODE");
//		vo.setTableName("COMTCADMINISTCODERECPTNLOG");

		List<String> owners = new ArrayList<>();
		owners.add("COM");
//		owners.add("COM320");
		allTabColsVO.setOwners(owners);

		List<String> tableNames = new ArrayList<>();
		tableNames.add("COMTCADMINISTCODE");
//		tableNames.add("COMTCADMINISTCODERECPTNLOG");
		allTabColsVO.setTableNames(tableNames);

		// when
		List<ColumnVO> allTabCols = allTabColsMapper.selectAllTabColsList2(allTabColsVO);

		// then
		assertEquals(allTabCols.get(0).getTableSchem(), owners.get(0));

		log.debug("allTabCols={}", allTabCols);
		log.debug("size={}", allTabCols.size());

//		debug(allTabCols);
//		debug2(allTabCols);
		debug3(allTabCols);
	}

	void debug(List<ColumnVO> allTabCols) {
		allTabCols.forEach(allTabCol -> {
			log.debug("allTabCol={}", allTabCol);

			log.debug("getTableSchem={}", allTabCol.getTableSchem());
			log.debug("getTableName={}", allTabCol.getTableName());
			log.debug("getColumnName={}", allTabCol.getColumnName());
			log.debug("getTypeName={}", allTabCol.getTypeName());
			log.debug("getColumnSize={}", allTabCol.getColumnSize());
			log.debug("getNullable={}", allTabCol.getNullable());
			log.debug("getIsNullable={}", allTabCol.getIsNullable());
			log.debug("getRemarks={}", allTabCol.getRemarks());

			log.debug("getColumnId={}", allTabCol.getColumnId());
			log.debug("getTableRemarks={}", allTabCol.getTableRemarks());
			log.debug("getPk={}", allTabCol.getPk());

			log.debug("");
		});
	}

	void debug2(List<ColumnVO> allTabCols) {
		allTabCols.forEach(allTabCol -> {
			log.debug("allTabCol={}", allTabCol);

			log.debug("getDataType={}", allTabCol.getDataType());
			log.debug("getColumnSize={}", allTabCol.getColumnSize());
			log.debug("getBufferLength={}", allTabCol.getBufferLength());
			log.debug("getDecimalDigits={}", allTabCol.getDecimalDigits());
			log.debug("getNumPrecRadix={}", allTabCol.getNumPrecRadix());
			log.debug("getNullable={}", allTabCol.getNullable());
			log.debug("getSqlDataType={}", allTabCol.getSqlDataType());
			log.debug("getSqlDatetimeSub={}", allTabCol.getSqlDatetimeSub());
			log.debug("getCharOctetLength={}", allTabCol.getCharOctetLength());
			log.debug("getOrdinalPosition={}", allTabCol.getOrdinalPosition());

			log.debug("");
		});
	}

	void debug3(List<ColumnVO> allTabCols) {
		allTabCols.forEach(allTabCol -> {
			log.debug("allTabCol={}", allTabCol);

			log.debug("tableCat={}", allTabCol.getTableCat());
			log.debug("tableSchem={}", allTabCol.getTableSchem());
			log.debug("tableName={}", allTabCol.getTableName());
			log.debug("columnName={}", allTabCol.getColumnName());
			log.debug("dataType={}", allTabCol.getDataType());
			log.debug("typeName={}", allTabCol.getTypeName());
			log.debug("columnSize={}", allTabCol.getColumnSize());
			log.debug("bufferLength={}", allTabCol.getBufferLength());
			log.debug("decimalDigits={}", allTabCol.getDecimalDigits());
			log.debug("numPrecRadix={}", allTabCol.getNumPrecRadix());
			log.debug("nullable={}", allTabCol.getNullable());
			log.debug("remarks={}", allTabCol.getRemarks());
			log.debug("columnDef={}", allTabCol.getColumnDef());
			log.debug("sqlDataType={}", allTabCol.getSqlDataType());
			log.debug("sqlDatetimeSub={}", allTabCol.getSqlDatetimeSub());
			log.debug("charOctetLength={}", allTabCol.getCharOctetLength());
			log.debug("ordinalPosition={}", allTabCol.getOrdinalPosition());
			log.debug("isNullable={}", allTabCol.getIsNullable());
			log.debug("scopeCatalog={}", allTabCol.getScopeCatalog());
			log.debug("scopeSchema={}", allTabCol.getScopeSchema());
			log.debug("scopeTable={}", allTabCol.getScopeTable());
			log.debug("sourceDataType={}", allTabCol.getSourceDataType());
			log.debug("isAutoincrement={}", allTabCol.getIsAutoincrement());

			log.debug("");
		});
	}

}
