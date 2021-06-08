package com.sumbab.sumbab.model.kakao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumbab.sumbab.model.memberRegAndLogin.MemberVo;

@Service
public class KakaoDaoImpl implements KakaoDao {

	@Autowired 
	SqlSession sqlSession;
	
	@Override
	public boolean ExMemberCheck(String email) {
		String result = sqlSession.selectOne("ExMemberCheck", email);
		return (result == null) ? false : true;
	}

	@Override
	public MemberVo getMemberById(String id) {
		return sqlSession.selectOne("getMemberById", id);
	}

	@Override
	public void deleteMember(MemberVo memberVO) {
		sqlSession.delete("kakaoMemberDelete", memberVO);
	}

}
