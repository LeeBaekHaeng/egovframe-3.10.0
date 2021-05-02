package god.codegen.datamodel.service.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.string.EgovDateUtil;
import god.codegen.datamodel.service.DataModelService;
import god.codegen.datamodel.service.DataModelVO;
import god.java.sql.ColumnVO;
import god.java.sql.TableVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.Attribute;
import model.DataModelContext;
import model.Entity;
import model.GodPathname;
import oracle.jdbc.OracleConnection;

@Service

@RequiredArgsConstructor
@Slf4j

public class V2DataModelServiceImpl implements DataModelService {

	private final DataSource ds;

	private DatabaseMetaData dmd;

	@Override
	public List<DataModelContext> getDataModel(DataModelVO dataModelVO) {
		List<DataModelContext> dataModels = new ArrayList<>();

		try {
			Connection con = ds.getConnection();

			if (con.isWrapperFor(OracleConnection.class)) {
				OracleConnection oracleConnection = con.unwrap(OracleConnection.class);
				log.debug("getRemarksReporting={}", oracleConnection.getRemarksReporting());
				oracleConnection.setRemarksReporting(true);
				log.debug("getRemarksReporting={}", oracleConnection.getRemarksReporting());
			}

			dmd = con.getMetaData();
		} catch (SQLException e) {
			log.error(e.getMessage());
		}

		List<TableVO> tables = getTables(dataModelVO);
		List<ColumnVO> columns = getColumns(dataModelVO);

		for (TableVO table : tables) {
//			debug(table);
			dataModels.add(getDataModel(table, columns));
		}

		return dataModels;
	}

	List<TableVO> getTables(DataModelVO dataModelVO) {
		List<TableVO> tables = new ArrayList<>();

		try {
			ResultSet rs = dmd.getTables(dataModelVO.getCatalog(), dataModelVO.getSchemaPattern(),
					dataModelVO.getTableNamePattern(), dataModelVO.getTypes());

			while (rs.next()) {
				TableVO table = new TableVO();

				table.setTableSchem(rs.getString("TABLE_SCHEM"));
				table.setTableName(rs.getString("TABLE_NAME"));
				table.setRemarks(rs.getString("REMARKS"));

				tables.add(table);
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}

		log.debug("tables={}", tables);
		log.debug("size={}", tables.size());
		log.debug("");

		return tables;
	}

	List<ColumnVO> getColumns(DataModelVO dataModelVO) {
//		List<ColumnVO> columns = dmd.getColumns(dataModelVO.getCatalog(), dataModelVO.getSchemaPattern(),
//				dataModelVO.getTableNamePattern(), dataModelVO.getColumnNamePattern());
//		log.debug("columns={}", columns);
//		log.debug("size={}", columns.size());
//		log.debug("");
//		return columns;
		return null;
	}

	void debug(TableVO table) {
		log.debug("table={}", table);

		log.debug("getTableSchem={}", table.getTableSchem());
		log.debug("getTableName={}", table.getTableName());
		log.debug("getRemarks={}", table.getRemarks());

		log.debug("getPkName={}", table.getPkName());

		log.debug("");
	}

	DataModelContext getDataModel(TableVO table, List<ColumnVO> columns) {
		DataModelContext dataModel = new DataModelContext();

		dataModel.setPackageName("egovframework.com.test");
		dataModel.setAuthor("이백행");
		dataModel.setTeam("공통 개발팀");
		dataModel.setCreateDate(EgovDateUtil.toString(new Date(), null, null));

		Entity entity = new Entity(table.getTableName());
		entity.setOwner(table.getTableSchem());
		entity.setTableName(table.getTableName());
		entity.setTableComments(table.getRemarks());
		entity.setPkName(table.getPkName());

		dataModel.setEntity(entity);

		List<Attribute> attributes = new ArrayList<Attribute>();
		List<Attribute> primaryKeys = new ArrayList<Attribute>();

		for (ColumnVO column : columns) {
			if (column.getTableSchem().equals(table.getTableSchem())
					&& column.getTableName().equals(table.getTableName())) {
				Attribute attr = new Attribute(column.getColumnName());
				attr.setType(column.getTypeName());
				attr.setNullable(column.getIsNullable());
				attr.setDataLength(column.getColumnSize());
				attr.setTableComments(table.getRemarks());
				attr.setColumnComments(column.getRemarks());
				attr.setPk(column.getPk());

				attributes.add(attr);

				if ("Y".equals(column.getPk())) {
					primaryKeys.add(attr);
				}
			}
		}

		dataModel.setAttributes(attributes);
		dataModel.setPrimaryKeys(primaryKeys);

		dataModel.setGodPathname(new GodPathname(entity));

		return dataModel;
	}

}
