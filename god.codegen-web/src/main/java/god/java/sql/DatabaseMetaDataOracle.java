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
	public List<ResultSeTable> getTables(String catalog, String schemaPattern, String tableNamePattern,
			String[] types) {

		List<ResultSeTable> tables = new ArrayList<>();

		AllTablesVO vo = new AllTablesVO();
		vo.setOwner(schemaPattern);

//		List<String> owners = new ArrayList<>();
//		owners.add(schemaPattern);
//		vo.setOwners(owners);

		List<EgovMap> allTables = allTablesMapper.selectAllTablesList(vo);

		for (EgovMap allTable : allTables) {
			ResultSeTable table = new ResultSeTable();
			table.setTableName((String) allTable.get("tableName"));
			table.setRemarks((String) allTable.get("tableComments"));
			tables.add(table);
		}

		return tables;
	}

	@Override
	public List<ResultSetColumn> getColumns(String catalog, String schemaPattern, String tableNamePattern,
			String columnNamePattern) {

		List<ResultSetColumn> columns = new ArrayList<>();

		AllTabColsVO vo = new AllTabColsVO();
		vo.setOwner(schemaPattern);

		List<EgovMap> allTabCols = allTabColsMapper.selectAllTabColsList(vo);

		for (EgovMap allTabCol : allTabCols) {
			ResultSetColumn column = new ResultSetColumn();
			column.setTableName((String) allTabCol.get("tableName"));
			column.setColumnName((String) allTabCol.get("columnName"));
			column.setRemarks((String) allTabCol.get("tableComments"));
			columns.add(column);
		}

		return columns;
	}

	@Override
	public List<ResultSetSchema> getSchemas(String catalog, String schemaPattern) {

		List<ResultSetSchema> schemas = new ArrayList<>();

		return schemas;
	}

}
