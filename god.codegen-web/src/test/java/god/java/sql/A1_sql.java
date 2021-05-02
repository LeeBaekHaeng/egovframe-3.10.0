package god.java.sql;

import java.util.List;

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

import god.codegen.oracle.alltabcols.service.impl.AllTabColsMapper;
import god.codegen.oracle.alltables.service.impl.AllTablesMapper;
import god.test.GodTestV1;
import lombok.extern.slf4j.Slf4j;

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
				DatabaseMetaDataOracle.class }) })

public class A1_sql extends GodTestV1 {

	@Autowired
	private DatabaseMetaData dmd;

	@Override
	public void test() {
		List<TableVO> tables = dmd.getTables(null, "COM", null, null);
		log.debug("tables={}", tables);
		log.debug("size={}", tables.size());
		log.debug("");

		for (TableVO table : tables) {
			log.debug("table={}", table);

			log.debug("getTableSchem={}", table.getTableSchem());
			log.debug("getTableName={}", table.getTableName());
			log.debug("getRemarks={}", table.getRemarks());

			log.debug("getPkName={}", table.getPkName());

			log.debug("");
		}
	}

}
