package god.codegen.datamodel.service;

import lombok.Data;

@Data
public class DataModelVO {

	// getTables
	private String catalog;
	private String schemaPattern;
	private String tableNamePattern;
	private String types[];

	// getColumns
	private String columnNamePattern;

}
