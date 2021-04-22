package god.codegen.oracle.alltabcols.service;

import egovframework.com.cmm.ComDefaultVO;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public class AllTabCols extends ComDefaultVO {

	private String owner;

	private String tableName;

}
