package god.java.sql;

import java.util.List;

public interface GodDatabaseMetaData {

	List<TableVO> getTables(String catalog, String schemaPattern, String tableNamePattern, String types[]);

	List<ColumnVO> getColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern);

}
