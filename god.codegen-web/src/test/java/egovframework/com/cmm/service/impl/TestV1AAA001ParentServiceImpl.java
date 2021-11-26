package egovframework.com.cmm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.FileVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TestV1AAA001ParentServiceImpl extends EgovAbstractServiceImpl {

	@Resource
	private TestV1AAA002ChildServiceImpl aaa002Service;

	@Resource
	private TestV1AAA003ChildServiceImpl aaa003Service;

	public boolean insertFileInf(FileVO vo) {
		try {
			aaa002Service.insertFileInf(vo);
			aaa003Service.insertFileInf(vo);
			return true;
		} catch (EgovBizException e) {
			log.error(e.getMessage());
			log.error(e.getMessageKey());
//			log.error(e.getMessageParameters().toString());
//			log.error(e.getWrappedException().toString());
//			log.error(e.toString());
//			log.error("insertFileInf EgovBizException");
			return false;
		}
	}

}
