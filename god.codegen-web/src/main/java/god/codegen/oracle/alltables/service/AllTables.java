package god.codegen.oracle.alltables.service;

import egovframework.com.cmm.ComDefaultVO;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public class AllTables extends ComDefaultVO {

	private String owner;

	private String tableName;

}
