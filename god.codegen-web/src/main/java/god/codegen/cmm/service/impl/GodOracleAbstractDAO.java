package god.codegen.cmm.service.impl;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

public abstract class GodOracleAbstractDAO extends EgovAbstractMapper {

	@Override
	@Resource(name = "egov.sqlSession.oracle")
	public void setSqlSessionFactory(SqlSessionFactory sqlSession) {
		super.setSqlSessionFactory(sqlSession);
	}

}
