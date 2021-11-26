package egovframework.com.cmm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovMybaitsUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service
public class TestV1AAA003ChildServiceImpl extends EgovAbstractServiceImpl {

	@Resource(name = "FileManageDAO")
	private FileManageDAO fileManageDAO;

	@Resource(name = "egovFileIdGnrService")
	private EgovIdGnrService egovFileIdGnrService;

	public void insertFileInf(FileVO vo) throws EgovBizException {
//		FileVO vo = new FileVO();

		try {
			vo.setAtchFileId(egovFileIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new EgovBizException(this + " egovFileIdGnrService FdlException");
		}

//		vo.setFileSn("0");
//		vo.setFileMg(vo.getFileSn());

		if (EgovMybaitsUtil.isEmpty(vo.getFileMg())) {
			throw new EgovBizException(this + " getFileMg isEmpty");
		}

		try {
			fileManageDAO.insertFileInf(vo);
		} catch (Exception e) {
			throw new EgovBizException(this + " insertFileInf Exception");
		}
	}

}
