package god.java.sql;

import java.util.List;

public interface DatabaseMetaData {

	List<ResultSeTable> getTables(String catalog, String schemaPattern, String tableNamePattern, String types[]);

	List<ResultSetColumn> getColumns(String catalog, String schemaPattern, String tableNamePattern,
			String columnNamePattern);

	List<ResultSetSchema> getSchemas(String catalog, String schemaPattern);

}
