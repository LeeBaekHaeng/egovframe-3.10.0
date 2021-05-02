package god.java.sql;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import god.codegen.oracle.alltabcols.service.AllTabColsVO;
import god.codegen.oracle.alltabcols.service.impl.AllTabColsMapper;
import god.codegen.oracle.alltables.service.AllTablesVO;
import god.codegen.oracle.alltables.service.impl.AllTablesMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DatabaseMetaDataOracle implements DatabaseMetaData {

	private final AllTablesMapper allTablesMapper;
	private final AllTabColsMapper allTabColsMapper;

	@Override
	public List<TableVO> getTables(String catalog, String schemaPattern, String tableNamePattern, String[] types) {
		AllTablesVO vo = new AllTablesVO();
		vo.setOwner(schemaPattern);

//		List<String> owners = new ArrayList<>();
//		owners.add(schemaPattern);
//		vo.setOwners(owners);

		vo.setTableName(tableNamePattern);

//		return getTablesEgovMap(vo);
		return allTablesMapper.selectAllTablesList2(vo);
	}

	List<TableVO> getTablesEgovMap(AllTablesVO vo) {
		List<TableVO> tables = new ArrayList<>();

		List<EgovMap> allTables = allTablesMapper.selectAllTablesList(vo);

		for (EgovMap allTable : allTables) {
			TableVO tableVO = new TableVO();
			tableVO.setTableCat((String) allTable.get("tableCat"));
			tableVO.setTableSchem((String) allTable.get("owner"));
			tableVO.setTableName((String) allTable.get("tableName"));
			tableVO.setTableType((String) allTable.get("tableType"));
			tableVO.setRemarks((String) allTable.get("tableComments"));
			tableVO.setTypeCat((String) allTable.get("typeCat"));
			tableVO.setTypeSchem((String) allTable.get("typeSchem"));
			tableVO.setTypeName((String) allTable.get("typeName"));
			tableVO.setSelfReferencingColName((String) allTable.get("selfReferencingColName"));
			tableVO.setRefGeneration((String) allTable.get("refGeneration"));

			tableVO.setPkName((String) allTable.get("pkName"));

			tables.add(tableVO);
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
			ColumnVO columnVO = new ColumnVO();
//			columnVO.setTableName((String) allTabCol.get("tableName"));
//			columnVO.setColumnName((String) allTabCol.get("columnName"));
//			columnVO.setRemarks((String) allTabCol.get("columnComments"));

//			columnVO.setTableCat((String) allTabCol.get("tableCat"));

//			columnVO.setTableSchem((String) allTabCol.get("tableSchem"));
			columnVO.setTableSchem((String) allTabCol.get("owner"));

			columnVO.setTableName((String) allTabCol.get("tableName"));
			columnVO.setColumnName((String) allTabCol.get("columnName"));
//			columnVO.setDataType((Integer) allTabCol.get("dataType"));
			columnVO.setTypeName((String) allTabCol.get("dataType"));
			columnVO.setColumnSize(MapUtils.getInteger(allTabCol, "dataLength"));
//			columnVO.setBufferLength((Integer) allTabCol.get("bufferLength"));
//			columnVO.setBufferLength((String) allTabCol.get("bufferLength"));
//			columnVO.setDecimalDigits((Integer) allTabCol.get("decimalDigits"));
//			columnVO.setNumPrecRadix((Integer) allTabCol.get("numPrecRadix"));
//			columnVO.setNullable((Integer) allTabCol.get("nullable"));
//			columnVO.setRemarks((String) allTabCol.get("remarks"));
			columnVO.setRemarks((String) allTabCol.get("columnComments"));
//			columnVO.setRemarks((String) allTabCol.get("columnComments"));
//			columnVO.setColumnDef((String) allTabCol.get("columnDef"));
//			columnVO.setSqlDataType((Integer) allTabCol.get("sqlDataType"));
//			columnVO.setSqlDatetimeSub((Integer) allTabCol.get("sqlDatetimeSub"));
//			columnVO.setCharOctetLength((Integer) allTabCol.get("charOctetLength"));
//			columnVO.setOrdinalPosition((Integer) allTabCol.get("ordinalPosition"));
//			columnVO.setIsNullable((String) allTabCol.get("isNullable"));
//			columnVO.setScopeCatalog((String) allTabCol.get("scopeCatalog"));
//			columnVO.setScopeSchema((String) allTabCol.get("scopeSchema"));
//			columnVO.setScopeTable((String) allTabCol.get("scopeTable"));
//			columnVO.setSourceDataType((Integer) allTabCol.get("sourceDataType"));
//			columnVO.setSourceDataType((short) allTabCol.get("sourceDataType"));
//			columnVO.setIsAutoincrement((String) allTabCol.get("isAutoincrement"));

			columnVO.setPk((String) allTabCol.get("pk"));

			columns.add(columnVO);
		}

		return columns;
	}

	@Override
	public List<SchemaVO> getSchemas(String catalog, String schemaPattern) {

		List<SchemaVO> schemas = new ArrayList<>();

		return schemas;
	}

}
