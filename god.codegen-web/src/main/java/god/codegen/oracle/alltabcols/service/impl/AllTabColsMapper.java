package god.codegen.oracle.alltabcols.service.impl;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import god.codegen.oracle.alltabcols.service.AllTabColsVO;

@Mapper
public interface AllTabColsMapper {

	List<EgovMap> selectAllTabColsList(AllTabColsVO vo);

}
