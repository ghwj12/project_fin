package com.sumbab.sumbab.model.kakao;

import com.sumbab.sumbab.model.memberRegAndLogin.MemberVo;

public interface KakaoDao {
	
	public  boolean ExMemberCheck(String email); 
	
	public MemberVo getMemberById(String id);
	
	public void deleteMember(MemberVo memberVO);

}
