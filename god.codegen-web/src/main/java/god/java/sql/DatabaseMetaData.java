package god.java.sql;

import java.util.List;

public interface DatabaseMetaData {

	List<TableVO> getTables(String catalog, String schemaPattern, String tableNamePattern, String types[]);

	List<ColumnVO> getColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern);

	List<SchemaVO> getSchemas(String catalog, String schemaPattern);

}
