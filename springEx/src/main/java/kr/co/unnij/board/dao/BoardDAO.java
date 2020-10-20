package kr.co.unnij.board.dao;

import java.util.List;
import java.util.Map;

import kr.co.unnij.board.model.BoardVO;

public interface BoardDAO {
	public int selectBoardCount(Map<String, String> paramMap) throws Exception;
	
	public List<BoardVO> selectBoardList(Map<String, String> paramMap) throws Exception;
}
