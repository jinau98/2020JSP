package kr.co.unnij.board.service;

import java.util.List;
import java.util.Map;

import kr.co.unnij.board.model.BoardVO;

public interface BoardService {
	//게시글 갯수
	public int getBoardCount(Map<String, String> paramMap) throws Exception;
	
	//게시글 목록 조회
	public List<BoardVO> getBoardList(Map<String, String> paramMap) throws Exception;
	
	//게시글 조회
	public BoardVO getBoard(int boSeqNo) throws Exception;
}
