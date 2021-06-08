package com.sumbab.sumbab.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sumbab.sumbab.model.memberRegAndLogin.MemberVo;
import com.sumbab.sumbab.model.memberRegAndLogin.MemberRegAndLoginService;



@Controller
public class MemberController {
	
	@Inject
	private MemberRegAndLoginService memberService;
	
	public void setBoardService(MemberRegAndLoginService memberService) {
		this.memberService = memberService;
	}
	
	@RequestMapping(value="/member/memberjoin", method=RequestMethod.GET)
	public String memberJoinPro() {
		return "member/memberjoin";
	}
	
	@RequestMapping(value="/memberjoin", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView memberJoinPro(MemberVo memberVO, HttpSession session) {

		ModelAndView mav = new ModelAndView();
		memberService.memberJoinProcess(memberVO);
		boolean result = memberService.loginCheck(memberVO, session);

		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping(value="/idCheck",method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String idCheck(HttpServletRequest request) {
		String id = request.getParameter("id");
		int result = memberService.idCheck(id);
		return Integer.toString(result);
	}
		
}
