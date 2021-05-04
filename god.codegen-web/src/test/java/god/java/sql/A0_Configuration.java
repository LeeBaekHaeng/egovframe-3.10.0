package god.java.sql;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;

import god.codegen.datamodel.service.impl.V2DataModelServiceImpl;

@Configuration

@ImportResource({

//		"classpath*:egovframework/spring/com/**/context-*.xml",

		"classpath*:/egovframework/spring/com/context-crypto.xml",
		"classpath*:/egovframework/spring/com/context-datasource.xml",

		"classpath*:egovframework/spring/com/context-mapper.xml",
		"classpath*:egovframework/spring/com/context-mapper-god-oracle.xml",

		"classpath*:egovframework/spring/com/context-transaction.xml",

		"classpath*:egovframework/spring/com/test-context-common.xml",

})

@ComponentScan(useDefaultFilters = false, basePackages = { "god" }, includeFilters = {
		@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {

//				DataModelService.class,

//				DataModelServiceImpl.class,

				V2DataModelServiceImpl.class,

				DatabaseMetaData.class,

		}) })

public class A0_Configuration {

}
