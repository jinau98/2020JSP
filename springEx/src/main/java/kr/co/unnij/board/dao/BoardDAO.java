package kr.co.unnij.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.unnij.board.model.BoardVO;

public interface BoardDAO {
	public int selectBoardCount(Map<String, String> paramMap) throws Exception;
	
	public List<BoardVO> selectBoardList(Map<String, String> paramMap) throws Exception;
	
	public BoardVO selectBoard(int bo_seq_no) throws Exception;				//넘기는 값이 하나일 때는 이름이 달라도 됨. mapper에서 하나라는 걸 보장할 때.
	
	//조회수 증가
	public int updateHitCnt(int boSeqNo) throws Exception;
	
	//글쓰기
	public int insertBoard(BoardVO board) throws Exception;
	
	//글 수정
	public int updateBoard(BoardVO board) throws Exception;
	
	public int deleteBoard(BoardVO board) throws Exception;
	
	public List<BoardVO> selectGalleryList(Map<String, String> paramMap) throws Exception;

}
