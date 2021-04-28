package god.codegen.oracle.alltabcols.service.impl;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import god.codegen.oracle.alltabcols.service.AllTabColsVO;
import god.java.sql.ColumnVO;

@Mapper
public interface AllTabColsMapper {

	List<EgovMap> selectAllTabColsList(AllTabColsVO vo);

	List<ColumnVO> selectAllTabColsList2(AllTabColsVO vo);

}
