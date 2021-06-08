package com.sumbab.sumbab.model.memberRegAndLogin;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDaoImpl implements MemberDao{

	@Autowired 
	SqlSession sqlSession;
	
	@Override
	public boolean loginCheck(MemberVo vo) {
		String name = sqlSession.selectOne("loginCheck", vo);
		return (name == null) ? false : true;
	}

	@Override
	public MemberVo viewMember(MemberVo vo) {
		return sqlSession.selectOne("viewMember", vo);
	}

	@Override
	public void logout(HttpSession session) {	
	}

//=============================================================	

	@Override
	public MemberVo memberInfolist(String id) {
		return sqlSession.selectOne("memberInfolist", id);
	}

	
	
//=============================================================
	@Override
	public void memberJoinMethod(MemberVo memberVO) {
		sqlSession.insert("join", memberVO);
	}

	@Override
	public int idCheck(String id) {

		int result = sqlSession.selectOne("idCheck", id);

		return result;

	}

	@Override
	public void memberDelete(MemberVo memberVO) {
		sqlSession.delete("memberDelete", memberVO);
		
	}
	
	@Override
	public String findMemberId(String email) {
		return sqlSession.selectOne("findMemberId", email);
	}

	@Override
	public String findMemberPwd(String id) {
		return sqlSession.selectOne("findMemberPwd", id);
	}

	@Override
	public void modEmailPwd(MemberVo memberVO) {
		sqlSession.update("modEmailPwd", memberVO);
	}
	
	@Override
	public void modEmail(MemberVo memberVO) {
		sqlSession.update("modEmail", memberVO);
	}

	@Override
	public void modPwd(MemberVo memberVO) {
		sqlSession.update("modPwd", memberVO);
	}

	@Override
	public int passChk(MemberVo memberVO) {
		int result = sqlSession.selectOne("passChk", memberVO);
		return result;
	}
}
