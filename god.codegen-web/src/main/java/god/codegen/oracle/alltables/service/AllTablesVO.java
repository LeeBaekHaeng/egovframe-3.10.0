package god.codegen.oracle.alltables.service;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public class AllTablesVO extends AllTables {

	private List<String> owners;

	private List<String> tableNames;

}
