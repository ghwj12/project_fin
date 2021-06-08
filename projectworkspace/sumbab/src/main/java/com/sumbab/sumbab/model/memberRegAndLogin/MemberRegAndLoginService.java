package com.sumbab.sumbab.model.memberRegAndLogin;

import javax.servlet.http.HttpSession;

public interface MemberRegAndLoginService {
	
	public boolean loginCheck(MemberVo vo, HttpSession session);
	
	public MemberVo viewMember(MemberVo vo);
	
	public void logout(HttpSession session);
	//==========================================================
	//마이페이지에서 활용될부분

	
	//=================================
	//회원가입부분
	public void memberJoinProcess(MemberVo memberVO);

	public int idCheck(String id);
	
	//아이디 찾기
	public String findMemberId(String email);
	//비밀번호 찾기
	public String findMemberPwd(String id);
	
	//회원탈퇴
	public void memberDelete(MemberVo memberVO);
	
	public void modify(MemberVo memberVO, HttpSession session);
	
	public int passChk(MemberVo memberVO);
	}
