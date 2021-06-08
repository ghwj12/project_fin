package com.sumbab.sumbab.model.memberRegAndLogin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberRegAndLoginServiceImpl implements MemberRegAndLoginService {

	@Autowired
	MemberDaoImpl memberDao;
	
	@Override
	public boolean loginCheck(MemberVo vo, HttpSession session) {
		boolean result = memberDao.loginCheck(vo);
		if(result) { //트루일경우에세션등록
			MemberVo sessionVo = viewMember(vo);
			//세션에 들어갈 용도입니다
			
			session.setAttribute("id", sessionVo.getId());
			session.setAttribute("classify", sessionVo.getClassify());
			session.setAttribute("pwd", sessionVo.getPwd());
			session.setAttribute("email", sessionVo.getEmail());
		}
		return result;
	}

	@Override
	public MemberVo viewMember(MemberVo vo) {
		return memberDao.viewMember(vo);
	}

	@Override
	public void logout(HttpSession session) {
		session.invalidate();
	}
//====================================================================
	
	
//===============================================================	
	@Override
	public void memberJoinProcess(MemberVo memberVO) {
		memberDao.memberJoinMethod(memberVO);
	}

	@Override
	public int idCheck(String id) {
		int result = memberDao.idCheck(id);

		return result;
	}

	@Override
	public void memberDelete(MemberVo memberVO) {
		memberDao.memberDelete(memberVO);
		
	}

	@Override
	public String findMemberId(String email) {
		return memberDao.findMemberId(email);
	}

	@Override
	public String findMemberPwd(String id) {
		return memberDao.findMemberPwd(id);
	}

	@Override
	public void modify(MemberVo memberVo, HttpSession session) {
		if(memberVo.getNewPwd() != null) {
			if(memberVo.getEmail() != null) {
				memberDao.modEmailPwd(memberVo);
			}else {
				memberDao.modPwd(memberVo);
			}
			session.setAttribute("pwd", memberVo.getNewPwd());
		}else {
			memberDao.modEmail(memberVo);
		}
	}

	@Override
	public int passChk(MemberVo memberVO) {
		int result = memberDao.passChk(memberVO);
		return result;
	}
	
}
