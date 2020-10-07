package kr.ac.hit.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.hit.member.model.Member;
import kr.ac.hit.member.service.MemberService;
import kr.ac.hit.member.service.impl.MemberServiceImpl;
import kr.ac.hit.web.servlet.mvc.Controller;

public class MemberFormController implements Controller{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String strSeqNo = request.getParameter("seqNo");			//String 타입으로 들어왔다는 구분
		int seqNo = 0;												//int 타입 seqNo
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		Member member = new Member();
		
		if(strSeqNo != null) {										//NULL 체크
			seqNo = Integer.parseInt(strSeqNo);
			paramMap.put("mem_seq_no", seqNo);
			
			MemberService memberService = MemberServiceImpl.getInstance();		//Service객체 가져오기
			
			member = memberService.getMember(paramMap);					//어차피 한 명의 정보만 받아오기 때문에 list 안 써도 됨
		}
		
		
		request.setAttribute("member", member);
		
		String viewPage = "/member/memberForm.jsp";
		
		return viewPage;
	}
	
}
