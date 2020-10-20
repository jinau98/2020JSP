package kr.co.unnij.board.service;

import java.util.List;
import java.util.Map;

import kr.co.unnij.board.model.BoardVO;

public interface BoardService {
	public int getBoardCount(Map<String, String> parmMap) throws Exception;
	
	public List<BoardVO> getBoardList(Map<String, String> paramMap) throws Exception;
}
