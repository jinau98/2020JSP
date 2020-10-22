package kr.co.unnij.board.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import kr.co.unnij.board.dao.BoardDAO;
import kr.co.unnij.board.model.BoardVO;
import kr.co.unnij.board.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	BoardDAO boardDao;

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
		
		return boardDao.selectBoard(boSeqNo);
	}

	@Override
	public int insertBoard(BoardVO board) throws Exception {
		return boardDao.insertBoard(board);
	}

	@Override
	public int updateBoard(BoardVO board) throws Exception {
		return boardDao.updateBoard(board);
	}

	@Override
	public int deleteBoard(BoardVO board) throws Exception {
		return boardDao.deleteBoard(board);
	}
	
}
