package egovframework.com.god.codegen.oracle.alltabcols.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.god.codegen.oracle.alltabcols.service.AllTabColsVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Repository
public class AllTabColsDAO extends EgovComAbstractDAO {

	public List<EgovMap> selectList(AllTabColsVO vo) {
		return selectList("AllTabColsDAO.selectList", vo);
	}

}
