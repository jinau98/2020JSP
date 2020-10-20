package kr.co.unnij.board.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
}
