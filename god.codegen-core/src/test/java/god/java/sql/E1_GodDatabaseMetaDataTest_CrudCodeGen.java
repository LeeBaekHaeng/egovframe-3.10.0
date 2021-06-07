package god.java.sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import egovframework.dev.imp.codegen.template.model.Attribute;
import egovframework.dev.imp.codegen.template.model.DataModelContext;
import egovframework.dev.imp.codegen.template.model.Entity;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;
import operation.CrudCodeGen;
import operation.CrudCodeGen.WizardModel;

@Slf4j
public class E1_GodDatabaseMetaDataTest_CrudCodeGen {

	@Test
	public void test() throws Exception {
		log.debug("test");

		Connection con = A1_GodDriverManagerTest.getConnection();

		DatabaseMetaData dmd = con.getMetaData();

		String vender = dmd.getDatabaseProductVersion();
		// HSQLDB
		// Oracle
		// MySql
		// postgres

		String databaseProductName = dmd.getDatabaseProductName();
		log.debug("vender={}", vender);
		log.debug("databaseProductName={}", databaseProductName);
		log.debug("");

		if ("Oracle".equals(databaseProductName)) {
			vender = "Oracle";
		} else if ("MySQL".equals(databaseProductName)) {
			vender = "MySql";
		}

		String schemaPattern = "COM";
		String tableNamePattern = "COMTC%";

		ResultSet tables = dmd.getTables(null, schemaPattern, tableNamePattern, null);
		ResultSet columns = dmd.getColumns(null, schemaPattern, tableNamePattern, null);

		log.debug("tables={}", tables);
		log.debug("");

		log.debug("columns={}", columns);
		log.debug("");

		List<Attribute> attributeList = new ArrayList<Attribute>();

		while (columns.next()) {
			String tableCat = columns.getString("TABLE_CAT");
			String tableSchem = columns.getString("TABLE_SCHEM");
			String tableName = columns.getString("TABLE_NAME");

			String columnName = columns.getString("COLUMN_NAME");
			int dataType = columns.getInt("DATA_TYPE");
			String typeName = columns.getString("TYPE_NAME");
			int columnSize = columns.getInt("COLUMN_SIZE");
			int bufferLength = columns.getInt("BUFFER_LENGTH");
			int decimalDigits = columns.getInt("DECIMAL_DIGITS");
			int numPrecRadix = columns.getInt("NUM_PREC_RADIX");
			int nullable = columns.getInt("NULLABLE");
			String columnsRemarks = columns.getString("REMARKS");
			String columnDef = columns.getString("COLUMN_DEF");
			int sqlDataType = columns.getInt("SQL_DATA_TYPE");
			int sqlDatetimeSub = columns.getInt("SQL_DATETIME_SUB");
			int charOctetLength = columns.getInt("CHAR_OCTET_LENGTH");
			int ordinalPosition = columns.getInt("ORDINAL_POSITION");
			String isNullable = columns.getString("IS_NULLABLE");

			log.debug("tableCat={}", tableCat);
			log.debug("tableSchem={}", tableSchem);
			log.debug("tableName={}", tableName);
			log.debug("columnName={}", columnName);
			log.debug("dataType={}", dataType);
			log.debug("typeName={}", typeName);
			log.debug("columnSize={}", columnSize);
			log.debug("bufferLength={}", bufferLength);
			log.debug("decimalDigits={}", decimalDigits);
			log.debug("numPrecRadix={}", numPrecRadix);
			log.debug("nullable={}", nullable);
			log.debug("columnsRemarks={}", columnsRemarks);
			log.debug("columnDef={}", columnDef);
			log.debug("sqlDataType={}", sqlDataType);
			log.debug("sqlDatetimeSub={}", sqlDatetimeSub);
			log.debug("charOctetLength={}", charOctetLength);
			log.debug("ordinalPosition={}", ordinalPosition);
			log.debug("isNullable={}", isNullable);

			if ("MySQL".equals(databaseProductName)) {
				String scopeCatalog = columns.getString("SCOPE_CATALOG");
				String scopeSchema = columns.getString("SCOPE_SCHEMA");
				String scopeTable = columns.getString("SCOPE_TABLE");
				int sourceDataType = columns.getInt("SOURCE_DATA_TYPE");
				String isAutoincrement = columns.getString("IS_AUTOINCREMENT");

				log.debug("");

				log.debug("scopeCatalog={}", scopeCatalog);
				log.debug("scopeSchema={}", scopeSchema);
				log.debug("scopeTable={}", scopeTable);
				log.debug("sourceDataType={}", sourceDataType);
				log.debug("isAutoincrement={}", isAutoincrement);

			}

			log.debug("");

			Attribute attr = new Attribute(columnName);
			attr.setType(typeName);
//				attr.setJavaType(getJavaClassName(colExpr.getDataType().getName()));
			attr.setTableName(tableName);
			attributeList.add(attr);

//				Column column = TableHelper.getColumnForColumnExpression(tableExpr, colExpr);
//				if ((column != null) && TableHelper.isPrimaryKey(column)) {
//					attr.setPrimaryKey(true);
//					pkAttributes.add(attr);
//				}

		}

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

			if ("MySQL".equals(databaseProductName)) {
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

			List<Attribute> attributes = new ArrayList<Attribute>();
			List<Attribute> pkAttributes = new ArrayList<Attribute>();

			for (Attribute attribute : attributeList) {
				if (attribute.getTableName().equals(tableName)) {
					attributes.add(attribute);
				}
			}

			ResultSet primaryKeys = dmd.getPrimaryKeys(null, schemaPattern, tableName);
			while (primaryKeys.next()) {
				for (Attribute attribute : attributeList) {
					if (attribute.getTableName().equals(tableName)
							&& attribute.getName().equals(primaryKeys.getString("COLUMN_NAME"))) {
						pkAttributes.add(attribute);
					}
				}
			}

			dataModel.setAttributes(attributes);
			dataModel.setPrimaryKeys(pkAttributes);

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
