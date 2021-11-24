package egovframework.com.cmm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.FileVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service
//public class TestEgovFileMngServiceImpl extends EgovAbstractServiceImpl implements EgovFileMngService {
public class Test3EgovFileMngServiceImpl extends EgovAbstractServiceImpl {

	@Resource(name = "FileManageDAO")
	private FileManageDAO fileManageDAO;

	@Resource(name = "egovFileIdGnrService")
	private EgovIdGnrService egovFileIdGnrService;

	public void insertFileInf(String s) throws Exception {
		FileVO vo = new FileVO();
		vo.setAtchFileId(egovFileIdGnrService.getNextStringId());
		int fileSn = 30;
		fileSn++;
		vo.setFileSn(String.valueOf(fileSn));
		vo.setFileMg(vo.getFileSn());
		fileManageDAO.insertFileInf(vo);

		vo = new FileVO();
		vo.setAtchFileId(egovFileIdGnrService.getNextStringId());
		fileSn++;
		vo.setFileSn(String.valueOf(fileSn));
		vo.setFileMg(vo.getFileSn());
		fileManageDAO.insertFileInf(vo);

//		vo.getAtchFileId().substring(0, 1000);
		if ("0".equals(s)) {
			throw new Exception("god Exception");
//			throw new RuntimeException();
		}

		vo = new FileVO();
		vo.setAtchFileId(egovFileIdGnrService.getNextStringId());
		fileSn++;
		vo.setFileSn(String.valueOf(fileSn));
		vo.setFileMg(vo.getFileSn());
		fileManageDAO.insertFileInf(vo);
	}

	public void insertFileInf2() {
		FileVO vo = new FileVO();
		try {
			vo.setAtchFileId(egovFileIdGnrService.getNextStringId());
		} catch (FdlException e) {
			egovLogger.error("FdlException");
		}
		int fileSn = 0;
		fileSn++;
		vo.setFileSn(String.valueOf(fileSn));
		vo.setFileMg(vo.getFileSn());
		try {
			fileManageDAO.insertFileInf(vo);
		} catch (Exception e) {
			egovLogger.error("Exception");
		}

		// 2
		vo = new FileVO();
		try {
			vo.setAtchFileId(egovFileIdGnrService.getNextStringId());
		} catch (FdlException e) {
			egovLogger.error("FdlException");
		}
		fileSn++;
		vo.setFileSn(String.valueOf(fileSn));
		vo.setFileMg(vo.getFileSn());
		try {
			fileManageDAO.insertFileInf(vo);

//			vo.getAtchFileId().substring(0, 1000);
			if (fileSn > 0) {
				throw new Exception();
//				throw new RuntimeException();
			}
		} catch (Exception e) {
			egovLogger.error("Exception");
			vo.getAtchFileId().substring(0, 1000);
		}

		// 3
		vo = new FileVO();
		try {
			vo.setAtchFileId(egovFileIdGnrService.getNextStringId());
		} catch (FdlException e) {
			egovLogger.error("FdlException");
		}
		fileSn++;
		vo.setFileSn(String.valueOf(fileSn));
		vo.setFileMg(vo.getFileSn());
		try {
			fileManageDAO.insertFileInf(vo);
		} catch (Exception e) {
			egovLogger.error("Exception");
		}
	}

}
