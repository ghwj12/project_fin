package com.sumbab.sumbab.model.memberRegAndLogin;

import org.apache.ibatis.type.Alias;

@Alias(value = "memberVo")
public class MemberVo {

	private String id;
	private String pwd;
	private String email;
	private String classify;
	private String regdate;
	private String kakao_id;
	//비밀번호 변경을 위해
	private String newPwd;

	public MemberVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberVo(String id, String pwd, String email, String classify, String kakao_id) {

		this.id = id;
		this.pwd = pwd;
		this.email = email;
		this.classify = classify;
		this.kakao_id = kakao_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getKakao_id() {
		return kakao_id;
	}

	public void setKakao_id(String kakao_id) {
		this.kakao_id = kakao_id;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

}
