package kr.co.unnij.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.unnij.board.model.BoardVO;
import kr.co.unnij.board.service.BoardService;
import kr.co.unnij.common.util.PagingUtil;

@Controller
@RequestMapping(value="/board")
public class BoardController {

	@Autowired
	BoardService boardService;
	
	@RequestMapping(value="/boardList")
	public String boardList(
			@RequestParam(value = "searchType", required = false, defaultValue = "") String searchType,
			@RequestParam(value = "searchWord", required = false, defaultValue = "") String searchWord,
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize, 
			@RequestParam(value = "bo_type", required = true) String boType, 
			Model model ) throws Exception{
		
		int pageCount = 5;
		int totalCount = 0;
		
		List<BoardVO> boardList = null;
		
		Map<String, String> paramMap = new HashMap<String, String>();
		
		if(!StringUtils.isAllBlank(searchType) && !StringUtils.isBlank(searchWord)) {
			paramMap.put("searchType", searchType);
			paramMap.put("searchWord", searchWord);
		}
		paramMap.put("bo_type", boType);
		
		//페이징 처리 위한 총 게시글 구하기
		totalCount = boardService.getBoardCount(paramMap);
		
		PagingUtil pagingUtil = new PagingUtil(currentPage, totalCount, pageSize, pageCount);
		
		paramMap.put("startRow", pagingUtil.getStartRow() + "");
		paramMap.put("endRow", pagingUtil.getEndRow() + "");
		
		boardList = boardService.getBoardList(paramMap);
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("pagingUtil", pagingUtil);
		
		return "board/boardList";
	}
}
