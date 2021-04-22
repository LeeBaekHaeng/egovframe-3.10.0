package god.codegen.oracle.alltabcols.service;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public class AllTabColsVO extends AllTabCols {

	private List<String> owners;

	private List<String> tableNames;

}
