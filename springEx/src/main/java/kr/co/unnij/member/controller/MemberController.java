package kr.co.unnij.member.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.unnij.common.util.PagingUtil;
import kr.co.unnij.member.model.MemberVO;
import kr.co.unnij.service.MemberService;

@Controller
@RequestMapping(value = "/member") // 메서드들에 공통적으로 사용하는 url 뽑아서 매핑
public class MemberController { // requestmapping 이용해서 하나의 컨트롤러 만으로 구분 가능 (EJB 방식에서는 다 각자 만들어줘야 했음)
	@Autowired
	MemberService memberService;

	@RequestMapping(value = "/memberTest")
	public String memberTest(Model model) throws Exception {
		ArrayList<MemberVO> memberList = memberService.selectMemberTest();
		model.addAttribute("memberList", memberList);
		return "/member/memberTest";
	}

	@RequestMapping(value = "/memberList")
	public String memberList(@RequestParam(value = "searchType", required = false, defaultValue = "") String searchType,
			@RequestParam(value = "searchWord", required = false, defaultValue = "") String searchWord,
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize, Model model)
			throws Exception {

		int pageCount = 5;
		int totalCount = 0;

		List<MemberVO> memberList = null;

		Map<String, Object> paramMap = new HashMap<String, Object>();

		if (!StringUtils.isBlank(searchType) && !StringUtils.isBlank(searchType)) {
			paramMap.put("searchType", searchType);
			paramMap.put("searchWord", searchWord);
		}

		totalCount = memberService.getMemberCount(paramMap);

		PagingUtil pagingUtil = new PagingUtil(currentPage, totalCount, pageSize, pageCount); // currentPage, pageSize 는
																								// 받아올 것(RequestParam)

		paramMap.put("startRow", pagingUtil.getStartRow());
		paramMap.put("endRow", pagingUtil.getEndRow());

		memberList = memberService.getMemberList(paramMap);
		model.addAttribute("memberList", memberList);
		model.addAttribute("pagingUtil", pagingUtil);

		return "/member/memberList"; // 리턴하고자 하는 jsp의 위치
	}

	@RequestMapping(value = "/memberView")
	public String memberView(@RequestParam(value = "seqNo", required = true) int seqNo,
			@RequestParam(value = "currentPage", required = true, defaultValue = "1") int currentPage, Model model) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("mem_seq_no", seqNo);
		MemberVO member = memberService.getMember(paramMap);
		model.addAttribute("member", member);
		model.addAttribute("currentPage", currentPage);
		return "/member/memberView";
	}

	@RequestMapping(value = "/memberForm")
	public ModelAndView memberForm(@RequestParam(value = "seqNo", required = false, defaultValue = "0") int seqNo,
			Model model) throws Exception {
		MemberVO member = new MemberVO();

		if (seqNo != 0) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("mem_seq_no", seqNo);
			member = memberService.getMember(paramMap);
		}

		ModelAndView mav = new ModelAndView();
		//Model addAttribute("member", member);
		mav.addObject("member", member);			//회원정보가 있으면 jsp 출력 , 없으면 빈칸
		mav.setViewName("/member/memberForm");

		return mav;
	}

	@RequestMapping(value = "/memberExists", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> memberExists(@RequestParam(value = "mem_id", required = true) String mem_id)
			throws Exception { // 페이지가 아니라 결과를 받기 때문에 Map

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("mem_id", mem_id);
		MemberVO member = memberService.getMember(paramMap);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (member != null) {
			resultMap.put("result", "true");
		} else {
			resultMap.put("result", "false");
		}
		return resultMap;
	}

	@RequestMapping(value = "/memberInsert", method = RequestMethod.POST)
	public String memberInsert(MemberVO member, Model model) throws Exception {
		// name = "mem_id" -> MemberVO mem_id
		boolean isError = false;

		try {
			int updCnt = memberService.insertMember(member);

			if (updCnt == 0) { // insert가 되지 않음
				isError = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			isError = true;
		}

		String viewPage = "/common/message";
		String message = "환영합니다~!";

		if (isError) {
			message = "회원가입 실패";
			model.addAttribute("isError", isError);
			model.addAttribute("message", message);
		} else {
			model.addAttribute("isError", isError);
			model.addAttribute("message", message);
			model.addAttribute("locationURL", "/member/memberList");
		}
		return viewPage;
	}

	@RequestMapping(value = "/memberUpdate", method = RequestMethod.POST)
	public String memberUpdate(MemberVO member, Model model) throws Exception {
		// name = "mem_id" -> MemberVO mem_id
		boolean isError = false;

		try {
			int updCnt = memberService.updateMember(member);

			if (updCnt == 0) { // update가 되지 않음
				isError = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			isError = true;
		}

		String viewPage = "redirect:/member/memberView?seqNo="+member.getMem_seq_no();		//redirect 요청과 응답 모두 새로 생성
		String message = "회원정보 수정되었습니다.";

		if (isError) {
			message = "회원정보 수정 실패했습니다.";
			model.addAttribute("isError", isError);
			model.addAttribute("message", message);
			viewPage ="/common/message";
		}														//실패했을 때만 message로 이동, 성공하면 memberView로 이동
		
		return viewPage;
	}
}
