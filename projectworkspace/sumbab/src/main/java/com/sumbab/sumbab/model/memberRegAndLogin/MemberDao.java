package com.sumbab.sumbab.model.memberRegAndLogin;

import javax.servlet.http.HttpSession;

public interface MemberDao {
	
	public boolean loginCheck(MemberVo vo);
	
	public MemberVo viewMember(MemberVo vo);
	
	public void logout(HttpSession session);
	//====================================
	//마이페이지에서 사용될 부분
	public MemberVo memberInfolist(String id);
	//=========================================
	//회원가입부분
	public void memberJoinMethod(MemberVo memberVO);

	public int idCheck(String id);
	
	//아이디 찾기
	public String findMemberId(String email);
	//비밀번호 찾기
	public String findMemberPwd(String id);
	
	//회원탈퇴부분
	public void memberDelete(MemberVo memberVO);
	
	//회원 정보 수정 - 이메일, 비밀번호
	public void modEmailPwd(MemberVo memberVO);
	
	//회원 정보 수정 - 이메일
	public void modEmail(MemberVo memberVO);
	
	//회원 정보 수정 - 비밀번호
	public void modPwd(MemberVo memberVO);
	
	public int passChk(MemberVo memberVO);
}
