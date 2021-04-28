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
	public List<Table> getTables(String catalog, String schemaPattern, String tableNamePattern, String[] types) {

		List<Table> tables = new ArrayList<>();

		AllTablesVO vo = new AllTablesVO();
		vo.setOwner(schemaPattern);

//		List<String> owners = new ArrayList<>();
//		owners.add(schemaPattern);
//		vo.setOwners(owners);

		List<EgovMap> allTables = allTablesMapper.selectAllTablesList(vo);

		for (EgovMap allTable : allTables) {
			Table table = new Table();
			table.setTableName((String) allTable.get("tableName"));
			table.setRemarks((String) allTable.get("tableComments"));
			tables.add(table);
		}

		return tables;
	}

	@Override
	public List<Column> getColumns(String catalog, String schemaPattern, String tableNamePattern,
			String columnNamePattern) {

		List<Column> columns = new ArrayList<>();

		AllTabColsVO vo = new AllTabColsVO();
		vo.setOwner(schemaPattern);
		vo.setTableName(tableNamePattern);

		List<EgovMap> allTabCols = allTabColsMapper.selectAllTabColsList(vo);

		for (EgovMap allTabCol : allTabCols) {
			Column column = new Column();
			column.setTableName((String) allTabCol.get("tableName"));
			column.setColumnName((String) allTabCol.get("columnName"));
			column.setRemarks((String) allTabCol.get("columnComments"));
			columns.add(column);
		}

		return columns;
	}

	@Override
	public List<Schema> getSchemas(String catalog, String schemaPattern) {

		List<Schema> schemas = new ArrayList<>();

		return schemas;
	}

}
