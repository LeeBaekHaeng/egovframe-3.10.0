package god.java.sql;

import java.util.List;

public interface DatabaseMetaData {

	List<Table> getTables(String catalog, String schemaPattern, String tableNamePattern, String types[]);

	List<Column> getColumns(String catalog, String schemaPattern, String tableNamePattern,
			String columnNamePattern);

	List<Schema> getSchemas(String catalog, String schemaPattern);

}
