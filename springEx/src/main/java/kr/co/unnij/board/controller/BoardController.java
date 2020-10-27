package kr.co.unnij.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.unnij.board.model.BoardVO;
import kr.co.unnij.board.service.BoardService;
import kr.co.unnij.common.util.PagingUtil;
import kr.co.unnij.member.model.MemberVO;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

	@Autowired
	BoardService boardService;

	@RequestMapping(value = "/boardList")
	public String boardList(@RequestParam(value = "searchType", required = false, defaultValue = "") String searchType,
			@RequestParam(value = "searchWord", required = false, defaultValue = "") String searchWord,
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
			@RequestParam(value = "bo_type", required = true) String boType, Model model) throws Exception {
		System.out.println(searchType + " / " + searchWord);
		int pageCount = 5;
		int totalCount = 0;

		List<BoardVO> boardList = null;

		Map<String, String> paramMap = new HashMap<String, String>();

		if (!StringUtils.isAllBlank(searchType) && !StringUtils.isBlank(searchWord)) {
			paramMap.put("searchType", searchType);
			paramMap.put("searchWord", searchWord);
		}
		paramMap.put("bo_type", boType);

		// 페이징 처리 위한 총 게시글 구하기
		totalCount = boardService.getBoardCount(paramMap);

		PagingUtil pagingUtil = new PagingUtil(currentPage, totalCount, pageSize, pageCount);

		paramMap.put("startRow", pagingUtil.getStartRow() + "");
		paramMap.put("endRow", pagingUtil.getEndRow() + "");

		boardList = boardService.getBoardList(paramMap);

		model.addAttribute("boardList", boardList);
		model.addAttribute("pagingUtil", pagingUtil);

		return "board/boardList";
	}
	
	@RequestMapping(value = "/noticeList")
	public String noticeList(@RequestParam(value = "searchType", required = false, defaultValue = "") String searchType,
			@RequestParam(value = "searchWord", required = false, defaultValue = "") String searchWord,
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
			@RequestParam(value = "bo_type", required = true) String boType, Model model) throws Exception {
		System.out.println(searchType + " / " + searchWord);
		int pageCount = 5;
		int totalCount = 0;

		List<BoardVO> boardList = null;

		Map<String, String> paramMap = new HashMap<String, String>();

		if (!StringUtils.isAllBlank(searchType) && !StringUtils.isBlank(searchWord)) {
			paramMap.put("searchType", searchType);
			paramMap.put("searchWord", searchWord);
		}
		paramMap.put("bo_type", boType);

		// 페이징 처리 위한 총 게시글 구하기
		totalCount = boardService.getBoardCount(paramMap);

		PagingUtil pagingUtil = new PagingUtil(currentPage, totalCount, pageSize, pageCount);

		paramMap.put("startRow", pagingUtil.getStartRow() + "");
		paramMap.put("endRow", pagingUtil.getEndRow() + "");

		boardList = boardService.getBoardList(paramMap);

		model.addAttribute("boardList", boardList);
		model.addAttribute("pagingUtil", pagingUtil);

		return "board/noticeList";
	}

	@RequestMapping(value = "/boardView")
	public String boardView(@RequestParam(value = "boSeqNo", required = true) int boSeqNo, Model model)
			throws Exception {
		BoardVO board = boardService.getBoard(boSeqNo);
		
		System.out.println(board.getBo_open_yn());
		
		model.addAttribute("board", board);
		return "board/boardView";
	}
	
	@RequestMapping(value = "/noticeView")
	public String noticeView(@RequestParam(value = "boSeqNo", required = true) int boSeqNo, Model model)
			throws Exception {
		BoardVO board = boardService.getBoard(boSeqNo);
		
		System.out.println(board.getBo_open_yn());
		
		model.addAttribute("board", board);
		return "board/noticeView";
	}
	
	@RequestMapping("/boardForm")
	public String boardForm(
			@RequestParam(value="boSeqNo", required=false, defaultValue="0") int boSeqNo, 
			HttpSession session, 
			Model model
			) throws Exception{
		BoardVO board = new BoardVO();
		
		if(boSeqNo != 0) {
			//데이터 조회
			board = boardService.getBoard(boSeqNo);
		}else {
			//입력
			MemberVO member = (MemberVO) session.getAttribute("LOGIN_USER");
			board.setBo_writer(member.getMem_id());
			board.setBo_writer_name(member.getMem_name());
		}
		
		model.addAttribute("board", board);
		return "board/boardForm";
	}
	
	
	
	@RequestMapping("/noticeForm")
	public String noticeForm(
			@RequestParam(value="boSeqNo", required=false, defaultValue="0") int boSeqNo, 
			HttpSession session, 
			Model model
			) throws Exception{
		BoardVO board = new BoardVO();
		
		if(boSeqNo != 0) {
			//데이터 조회
			board = boardService.getBoard(boSeqNo);
		}else {
			//입력
			MemberVO member = (MemberVO) session.getAttribute("LOGIN_USER");
			board.setBo_writer(member.getMem_id());
			board.setBo_writer_name(member.getMem_name());
		}
		
		model.addAttribute("board", board);
		return "board/noticeForm";
	}
	
	
	@RequestMapping(value="boardInsert")
	public String boardInsert(BoardVO board, MultipartHttpServletRequest mRequest, Model model) throws Exception{
		System.out.println("boardInsert");
		boolean isError = false;
		try {
			int updCnt = boardService.insertBoard(board, mRequest);
			if(updCnt==0) {
				isError = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
			isError = true;
		}
		
		String viewPage = "redirect:/board/boardList?bo_type=BBS";
		String message = "글이 등록되었습니다.";
		
		if(isError) {
			message = "글 등록에 실패했습니다.";
			viewPage = "common/message";
			model.addAttribute("message", message);
			model.addAttribute("isError", isError);
		}
		
		return viewPage;
		}
	@RequestMapping(value="noticeInsert")
		public String notieceInsert(BoardVO board, MultipartHttpServletRequest mRequest, Model model) throws Exception{
			System.out.println("noticeInsert");
			boolean isError = false;
			try {
				int updCnt = boardService.insertBoard(board, mRequest);
				if(updCnt==0) {
					isError = true;
				}
			}catch(Exception e) {
				e.printStackTrace();
				isError = true;
			}
			
			String viewPage = "redirect:/board/noticeList?bo_type=NOTICE";
			String message = "글이 등록되었습니다.";
			
			if(isError) {
				message = "글 등록에 실패했습니다.";
				viewPage = "common/message";
				model.addAttribute("message", message);
				model.addAttribute("isError", isError);
			}
			
			return viewPage;
			}
	
	@RequestMapping(value="boardUpdate")
	public String boardUpdate(BoardVO board, MultipartHttpServletRequest mRequest, HttpSession session, Model model) throws Exception{
		boolean isError = false;
		
		try {
			MemberVO member = (MemberVO) session.getAttribute("LOGIN_USER");
			board.setUpd_user(member.getMem_id());
			
			int updCnt = boardService.updateBoard(board, mRequest);
			if(updCnt ==0) {
				isError = true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			isError = true;
		}
		
		String viewPage = "redirect:/board/boardView?boSeqNo="+board.getBo_seq_no();
		String message = "글이 수정되었습니다.";
		
		if(isError) {
			viewPage = "common/message";
			message="글 수정에 실패했습니다.";
			model.addAttribute("message", message);
			model.addAttribute("isError", isError);
		}
		return viewPage;
	}
	
	@RequestMapping(value="noticeUpdate")
	public String noticeUpdate(BoardVO board, HttpSession session, Model model) throws Exception{
		boolean isError = false;
		
		try {
			MemberVO member = (MemberVO) session.getAttribute("LOGIN_USER");
			board.setUpd_user(member.getMem_id());
			
			int updCnt = boardService.updateBoard(board, null);
			
			if(updCnt ==0) {
				isError = true;
			}
			boardService.updateBoard(board, null);
		}catch(Exception e) {
			e.printStackTrace();
			isError = true;
		}
		
		String viewPage = "redirect:/board/noticeView?boSeqNo="+board.getBo_seq_no();
		String message = "글이 수정되었습니다.";
		
		if(isError) {
			viewPage = "common/message";
			message="글 수정에 실패했습니다.";
			model.addAttribute("message", message);
			model.addAttribute("isError", isError);
		}
		return viewPage;
	}
	
	@RequestMapping(value="boardDelete")
	public String boardDelete(
			@RequestParam(value="boSeqNo", required=true) int seqNo
			, Model model
			, HttpSession session
			) throws Exception{
		boolean isError = false;
		
		try {
			
			MemberVO member = (MemberVO) session.getAttribute("LOGIN_USER");
			
			BoardVO board = new BoardVO();
			board.setBo_seq_no(seqNo);
			board.setUpd_user(member.getMem_id());
			
			int updCnt = boardService.deleteBoard(board);
			
			if(updCnt ==0) {
				isError = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
			isError = true;
		}
		String viewPage = "redirect:/board/boardList?bo_type=BBS";
		String message = "글이 삭제되었습니다! 안녕~!";
		
		if(isError) {
			viewPage = "common/message";
			message="삭제 못했지롱ㅠㅠ";
			model.addAttribute("isError", isError);
			model.addAttribute("message", message);
		}
		
		return viewPage;
	}
	
	@RequestMapping(value="noticeDelete")
	public String noticeDelete(
			@RequestParam(value="boSeqNo", required=true) int seqNo
			, Model model
			, HttpSession session
			) throws Exception{
		boolean isError = false;
		
		try {
			
			MemberVO member = (MemberVO) session.getAttribute("LOGIN_USER");
			
			BoardVO board = new BoardVO();
			board.setBo_seq_no(seqNo);
			board.setUpd_user(member.getMem_id());
			
			int updCnt = boardService.deleteBoard(board);
			
			if(updCnt ==0) {
				isError = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
			isError = true;
		}
		String viewPage = "redirect:/board/noticeList?bo_type=NOTICE";
		String message = "글이 삭제되었습니다! 안녕~!";
		
		if(isError) {
			viewPage = "common/message";
			message="삭제 못했지롱ㅠㅠ";
			model.addAttribute("isError", isError);
			model.addAttribute("message", message);
		}
		
		return viewPage;
	}
	
}