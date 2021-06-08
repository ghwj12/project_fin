package com.sumbab.sumbab.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sumbab.sumbab.model.memberRegAndLogin.MemberDao;
import com.sumbab.sumbab.model.memberRegAndLogin.MemberVo;

@Controller
public class MypageController {
	
	@Autowired
	MemberDao myMemberDao;
	
	
	//일반 회원 & 가게 관리자 마이페이지 클릭 시
	@RequestMapping(value="/mypage/memberMyPage", method=RequestMethod.GET)
	public String goMypage1(MemberVo vo, Model model, HttpSession session) {
		String id = null;
		if (session.getAttribute("id") != null) {
			id = (String)session.getAttribute("id");
		}
		model.addAttribute("member",myMemberDao.memberInfolist(id) );

		return "/mypage/memberMyPage";
	}
	
	
}
