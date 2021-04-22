package god.codegen.oracle.alltabcols.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import god.codegen.cmm.service.impl.GodOracleAbstractDAO;
import god.codegen.oracle.alltabcols.service.AllTabColsVO;

@Repository
public class AllTabColsDAO extends GodOracleAbstractDAO {

	public List<EgovMap> selectList(AllTabColsVO vo) {
		return selectList("AllTabColsDAO.selectList", vo);
	}

}
