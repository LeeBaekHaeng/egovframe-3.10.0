package god.java.sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Date;

import org.junit.Test;

import egovframework.dev.imp.codegen.template.model.DataModelContext;
import egovframework.dev.imp.codegen.template.model.Entity;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;
import operation.CrudCodeGen;
import operation.CrudCodeGen.WizardModel;
import oracle.jdbc.OracleConnection;

@Slf4j
public class GodDatabaseMetaDataTest_A1 {

	@Test
	public void test() throws Exception {
		log.debug("test");

//		Class.forName("com.mysql.jdbc.Driver");

		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/com", "com", "com01");
//		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.254:1521:orcl", "com", "com01");

		log.debug("con={}", con);
		log.debug("");

		if (con.isWrapperFor(OracleConnection.class)) {
			OracleConnection ocon = con.unwrap(OracleConnection.class);
			ocon.setRemarksReporting(true);
		}

		DatabaseMetaData dmd = con.getMetaData();

		String driverName = dmd.getDriverName();
		String driverVersion = dmd.getDriverVersion();
		int driverMajorVersion = dmd.getDriverMajorVersion();
		int driverMinorVersion = dmd.getDriverMinorVersion();
		log.debug("driverName={}", driverName);
		log.debug("driverVersion={}", driverVersion);
		log.debug("driverMajorVersion={}", driverMajorVersion);
		log.debug("driverMinorVersion={}", driverMinorVersion);
		log.debug("");

		String vender = dmd.getDatabaseProductVersion();
		String databaseProductName = dmd.getDatabaseProductName();
		log.debug("vender={}", vender);
		log.debug("databaseProductName={}", databaseProductName);
		log.debug("");

		ResultSet tables = dmd.getTables(null, "COM", "COMTC%", null);

		log.debug("tables={}", tables);
		log.debug("");

		CrudCodeGen crudCodeGen = new CrudCodeGen();

		String createDate = EgovDateUtil.toString(new Date(), null, null);

		while (tables.next()) {
			String tableCat = tables.getString("TABLE_CAT");
			String tableSchem = tables.getString("TABLE_SCHEM");
			String tableName = tables.getString("TABLE_NAME");
			String tableType = tables.getString("TABLE_TYPE");
			String remarks = tables.getString("REMARKS");

			log.debug("tableCat={}", tableCat);
			log.debug("tableSchem={}", tableSchem);
			log.debug("tableName={}", tableName);
			log.debug("tableType={}", tableType);
			log.debug("remarks={}", remarks);
			log.debug("");

			if ("MySQL Connector Java".equals(driverName)) {
				String typeCat = tables.getString("TYPE_CAT");
				String typeSchem = tables.getString("TYPE_SCHEM");
				String typeName = tables.getString("TYPE_NAME");
				String selfReferencingColName = tables.getString("SELF_REFERENCING_COL_NAME");
				String refGeneration = tables.getString("REF_GENERATION");

				log.debug("typeCat={}", typeCat);
				log.debug("typeSchem={}", typeSchem);
				log.debug("typeName={}", typeName);
				log.debug("selfReferencingColName={}", selfReferencingColName);
				log.debug("refGeneration={}", refGeneration);
			}

			log.debug("");

			DataModelContext dataModel = new DataModelContext();
			dataModel.setVender(vender);
			dataModel.setDatabaseProductName(databaseProductName);

			Entity entity = new Entity(tableName);
			dataModel.setEntity(entity);

			WizardModel wizardModel = new CrudCodeGen.WizardModel();
			wizardModel.setAuthor("공통개발팀 이백행");
			wizardModel.setCreateDate(createDate);

			// DataAccess
			wizardModel.setCheckDataAccess("Y");
			wizardModel.setSqlMapFolder(entity.getLcName() + "/sqlmap");
			wizardModel.setMapperFolder(entity.getLcName() + "/mapper");
			wizardModel.setDaoPackage(entity.getLcName() + ".service.impl");
			wizardModel.setMapperPackage(entity.getLcName() + ".mapper");
			wizardModel.setVoPackage(entity.getLcName() + ".service");

			// Service
			wizardModel.setCheckService("Y");
			wizardModel.setServicePackage(entity.getLcName() + ".service.impl");
			wizardModel.setImplPackage(entity.getLcName() + ".service.impl");

			// Web
			wizardModel.setCheckWeb("Y");
			wizardModel.setControllerPackage(entity.getLcName());
			wizardModel.setJspFolder(entity.getLcName() + "/jsp");

			String templateFile = "eGovFrameTemplates/crud/resource/pkg/EgovSample_Sample2_MAPPER.vm";
			String result = crudCodeGen.generate(dataModel, templateFile, wizardModel);

			log.debug(result);
		}
	}

}
