package kr.co.unnij.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.unnij.board.model.BoardVO;

public interface BoardService {
	//게시글 갯수
	public int getBoardCount(Map<String, String> paramMap) throws Exception;
	
	//게시글 목록 조회
	public List<BoardVO> getBoardList(Map<String, String> paramMap) throws Exception;

	//게시글 조회
	public BoardVO getBoard(int boSeqNo) throws Exception;
	
	//게시글 등록
	public int insertBoard(BoardVO board, MultipartHttpServletRequest mRequest) throws Exception;
	
	//게시글 수정
	public int updateBoard(BoardVO board, MultipartHttpServletRequest mRequest) throws Exception;
	
	//게시글 삭제
	public int deleteBoard(BoardVO board) throws  Exception;

	public int insertNotice(BoardVO board) throws Exception;

}
