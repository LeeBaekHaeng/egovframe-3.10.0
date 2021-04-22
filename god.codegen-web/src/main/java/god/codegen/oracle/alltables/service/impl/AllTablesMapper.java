package god.codegen.oracle.alltables.service.impl;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import god.codegen.oracle.alltables.service.AllTablesVO;

@Mapper
public interface AllTablesMapper {

	List<EgovMap> selectList(AllTablesVO vo);

}
