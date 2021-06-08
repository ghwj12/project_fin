package com.sumbab.sumbab.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sumbab.sumbab.model.memberRegAndLogin.MemberRegAndLoginServiceImpl;
import com.sumbab.sumbab.model.memberRegAndLogin.MemberVo;

@Controller
public class LoginController {
	
	
	@Autowired 
	MemberRegAndLoginServiceImpl myloginService;
	
	@RequestMapping(value = "/login/login", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView loginCheck(@ModelAttribute MemberVo vo, HttpSession session) {
		boolean result = myloginService.loginCheck(vo, session);
		ModelAndView mav = new ModelAndView();
		if (result == true) {
			mav.setViewName("main");
		}else {
			mav.setViewName("login/login");
		}
		return mav;
	}
	
	@RequestMapping(value = "/login/logout")
	public ModelAndView logout(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		//카카오 연동 가입자라면 카카오 토큰 연결을 끊어야 하기 때문에 kakaoLogout으로 redirect
		if(session.getAttribute("id").toString().contains("_kakao")) {
			mav.setViewName("redirect:/kakaoLogout");
			return mav;
		}
		myloginService.logout(session);
		mav.setViewName("main");
		return mav;
	}

	//아이디비번찾기 추가하자잉
	//아이디 찾기
	@RequestMapping(value="/login/findMemberId", method=RequestMethod.GET)
	public String findId() {
		return "/login/findMemberId";
	}
	@RequestMapping(value="/login/idFindResult", method=RequestMethod.POST)
	public String resultOfFindId(@ModelAttribute("email")String email, Model model) {
		model.addAttribute("id", myloginService.findMemberId(email));
		
		return "/login/idFindResult";
	}
	
	//비밀번호 찾기
	@RequestMapping(value="/login/findMemberPwd", method=RequestMethod.GET)
	public String findPwd() {
		return "/login/findMemberPwd";
	}
	@RequestMapping(value="/login/pwdFindResult", method=RequestMethod.POST)
	public String resultOfFindPwd(@ModelAttribute("id")String id, Model model) {
		
		model.addAttribute("pwd", myloginService.findMemberPwd(id));
		return "/login/pwdFindResult";
	}
	
	//=========================================================================================================
	//회원탈퇴 get 
			@RequestMapping(value = "/member/memberDeleteView", method = RequestMethod.GET)
			public String memberDeleteView(HttpSession session) {
				String pwd = null;
				if (session.getAttribute("pwd") != null) {
					pwd = (String)session.getAttribute("pwd");
					System.out.println(pwd+"세션비번?");
				}
				if(session.getAttribute("id").toString().contains("_kakao")){
					return "redirect:/deleteKakaoMember";
					
				}else {
					return "member/memberDeleteView";
				}
			}
			 
			@RequestMapping(value = "/memberDelete", method = {RequestMethod.GET, RequestMethod.POST})
			public String memberDeleteView(MemberVo memberVo, HttpSession session, RedirectAttributes rttr) {
				System.out.println("post 실행확인");
				String pwd = null;
				if (session.getAttribute("pwd") != null) {
					pwd = (String)session.getAttribute("pwd");
					System.out.println(pwd+"세션비번?");
				}
				
				String voPwd = memberVo.getPwd();
				
				System.out.println(voPwd + "폼값비번");

				
				if(!(pwd.equals(voPwd))) {
					rttr.addFlashAttribute("msg", false);
					return "redirect:/membmer/memberDeleteView";
				}
				myloginService.memberDelete(memberVo);
				session.invalidate();
				return "main";
			}
			
			@ResponseBody
			@RequestMapping(value = "/memberDelete2", method = RequestMethod.POST)
			public int passChk (MemberVo memberVO) {
				int result = myloginService.passChk(memberVO);
				return result;
			}
						
			
			@RequestMapping(value ="/login/modify", method = RequestMethod.GET)
			public String modifyGET(Model model) {
				model.addAttribute("memberVo", new MemberVo());
				return "/login/modify";
			}
			
			@RequestMapping(value ="/login/modify", method = RequestMethod.POST)
			public String modifyPOST(MemberVo memberVo, HttpSession session) {
				myloginService.modify(memberVo, session);
				return "redirect:/mypage/memberMyPage";
			}
	
			
			
}
