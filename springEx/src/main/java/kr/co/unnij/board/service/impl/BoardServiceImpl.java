package kr.co.unnij.board.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.unnij.board.dao.BoardDAO;
import kr.co.unnij.board.model.BoardVO;
import kr.co.unnij.board.service.BoardService;
import kr.co.unnij.common.util.FileUtils;
import kr.co.unnij.file.dao.FileItemDAO;
import kr.co.unnij.file.model.FileItem;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	BoardDAO boardDao;
	
	@Autowired
	FileUtils fileUtils;
	
	@Autowired
	FileItemDAO fileItemDao;

	@Override
	public int getBoardCount(Map<String, String> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return boardDao.selectBoardCount(paramMap);
	}

	@Override
	public List<BoardVO> getBoardList(Map<String, String> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return boardDao.selectBoardList(paramMap);
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED)		//isolation 격리성 : 동시에 실행되는 쿼리가 있을 시 서로 영향이 미치지 않도록 격리시킴
	@Override
	public BoardVO getBoard(int boSeqNo) throws Exception {
		boardDao.updateHitCnt(boSeqNo);
		
		BoardVO board = boardDao.selectBoard(boSeqNo);
		
		//파일 목록 조회
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ref_seq_no", board.getBo_seq_no());
		paramMap.put("biz_type", board.getBo_type());
		
		List<FileItem> fileList = fileItemDao.selectFileItemList(paramMap);
		board.setFileList(fileList);
		
		return board;
	}
	
	@Transactional
	@Override
	public int insertBoard(BoardVO board, MultipartHttpServletRequest mRequest) throws Exception {
		int updCnt = boardDao.insertBoard(board);	//시퀀스번호가 bo_seq_no에 저장되어있음.(selectKey)
		//파일 저장(서버에 저장)
		List<FileItem> fileList = fileUtils.uploadFiles(board, mRequest);
		for(FileItem fileItem : fileList) {
			fileItemDao.insertFileItem(fileItem);
		}
		return updCnt;
	}
	

	@Transactional
	@Override
	public int updateBoard(BoardVO board, MultipartHttpServletRequest mRequest) throws Exception {
		String[] delFileSeq = board.getDelFileSeq();
		
		try {
			if(delFileSeq != null) {
				for(int i=0; i<delFileSeq.length; i++) {
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("delFileSeq", delFileSeq[i]);
					
					fileItemDao.deleteFileItem(paramMap);
				}
			}
			List<FileItem> fileList = fileUtils.uploadFiles(board, mRequest);
			for(FileItem fileItem : fileList) {
				fileItemDao.insertFileItem(fileItem);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return boardDao.updateBoard(board);
	}

	@Override
	public int deleteBoard(BoardVO board) throws Exception {
		return boardDao.deleteBoard(board);
	}
	
}
