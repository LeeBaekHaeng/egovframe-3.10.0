package god.java.sql;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import god.codegen.oracle.alltabcols.service.AllTabColsVO;
import god.codegen.oracle.alltabcols.service.impl.AllTabColsMapper;
import god.codegen.oracle.alltables.service.AllTablesVO;
import god.codegen.oracle.alltables.service.impl.AllTablesMapper;

@Service
public class DatabaseMetaDataOracle implements DatabaseMetaData {

	private final AllTablesMapper allTablesMapper;
	private final AllTabColsMapper allTabColsMapper;

	public DatabaseMetaDataOracle(AllTablesMapper allTablesMapper, AllTabColsMapper allTabColsMapper) {
		this.allTablesMapper = allTablesMapper;
		this.allTabColsMapper = allTabColsMapper;
	}

	@Override
	public List<TableVO> getTables(String catalog, String schemaPattern, String tableNamePattern, String[] types) {

		List<TableVO> tables = new ArrayList<>();

		AllTablesVO vo = new AllTablesVO();
		vo.setOwner(schemaPattern);

//		List<String> owners = new ArrayList<>();
//		owners.add(schemaPattern);
//		vo.setOwners(owners);

		vo.setTableName(tableNamePattern);

		List<EgovMap> allTables = allTablesMapper.selectAllTablesList(vo);

		for (EgovMap allTable : allTables) {
			TableVO table = new TableVO();
			table.setTableCat((String) allTable.get("tableCat"));
			table.setTableSchem((String) allTable.get("owner"));
			table.setTableName((String) allTable.get("tableName"));
			table.setTableType((String) allTable.get("tableType"));
			table.setRemarks((String) allTable.get("tableComments"));
			table.setTypeCat((String) allTable.get("typeCat"));
			table.setTypeSchem((String) allTable.get("typeSchem"));
			table.setTypeName((String) allTable.get("typeName"));
			table.setSelfReferencingColName((String) allTable.get("selfReferencingColName"));
			table.setRefGeneration((String) allTable.get("refGeneration"));
			tables.add(table);
		}

		return tables;
	}

	@Override
	public List<ColumnVO> getColumns(String catalog, String schemaPattern, String tableNamePattern,
			String columnNamePattern) {

		List<ColumnVO> columns = new ArrayList<>();

		AllTabColsVO vo = new AllTabColsVO();
		vo.setOwner(schemaPattern);
		vo.setTableName(tableNamePattern);

		List<EgovMap> allTabCols = allTabColsMapper.selectAllTabColsList(vo);

		for (EgovMap allTabCol : allTabCols) {
			ColumnVO column = new ColumnVO();
			column.setTableName((String) allTabCol.get("tableName"));
			column.setColumnName((String) allTabCol.get("columnName"));
			column.setRemarks((String) allTabCol.get("columnComments"));
			columns.add(column);
		}

		return columns;
	}

	@Override
	public List<SchemaVO> getSchemas(String catalog, String schemaPattern) {

		List<SchemaVO> schemas = new ArrayList<>();

		return schemas;
	}

}
