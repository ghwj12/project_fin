package com.sumbab.sumbab.model.kakao;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.JsonNode;
import com.sumbab.sumbab.model.memberRegAndLogin.MemberRegAndLoginService;
import com.sumbab.sumbab.model.memberRegAndLogin.MemberVo;

@Controller
public class KakaoController {

	@Autowired
	KakaoDao mykakaoDao;

	@Autowired
	private KakaoRestAPI kakaoRestApi;

	@Autowired
	private void setKakaoRestAPI(KakaoRestAPI kakaoRestApi) {
		this.kakaoRestApi = kakaoRestApi;
	}

	@Autowired
	MemberRegAndLoginService memberRegitService;

	@RequestMapping(value = "/kakaoLogin", produces = "application/json", method = RequestMethod.GET)
	public String kakaoLogin2(@RequestParam("code") String code, @ModelAttribute MemberVo memberVO, Model model,
			HttpSession session) {

		// 이미 세션에 id가 있으면(=로그인 상태면)
		if (session.getAttribute("id") != null) {
			return "redirect:/";
		} else {
			System.out.println("로그인 임시 코드값 : " + code);
			JsonNode node = kakaoRestApi.getAccessToken(code); // post 요청에 필요한 파라미터를 가져옴.
			JsonNode accessToken = node.get("access_token");
			System.out.println("결과값 출력" + node);
			String token = node.get("access_token").toString();
			session.setAttribute("token", token);

			// 카카오 유저 정보 가져오기
			JsonNode userInfo = kakaoRestApi.getKakaoUserInfo(accessToken);

			String kakao_id = userInfo.path("id").asText();
			String email = null; // 초기화

			JsonNode kakao_account = userInfo.path("kakao_account");
			email = kakao_account.path("email").asText();

			boolean result = mykakaoDao.ExMemberCheck(email);

			if (result == true) {
				String id = kakaoRestApi.getRandomId(email);
				memberVO.setId(id);
				MemberVo kakaoMember = mykakaoDao.getMemberById(id);
				memberRegitService.loginCheck(kakaoMember, session);
				return "redirect:/";
			} else {
				model.addAttribute("email", email);
				model.addAttribute("kakao_id", kakao_id);
				
				return "/member/memberjoin_kakao";
			}
		}
	}

	@RequestMapping(value = "/member/memberjoin_kakao", method = RequestMethod.POST)
	public String memberJoinWithKakao(Model model, @ModelAttribute("memberDTO") MemberVo memberVO,
			HttpSession session) {

		String id = kakaoRestApi.getRandomId(memberVO.getEmail());
		String pwd = kakaoRestApi.getRandomPwd(10);
		System.out.println(memberVO.getKakao_id()+" 카카오 아이디???");
		memberVO.setId(id);
		memberVO.setPwd(pwd);
		memberRegitService.memberJoinProcess(memberVO);

		model.addAttribute(memberRegitService.loginCheck(memberVO, session));

		return "redirect:/";
	}

	@RequestMapping(value = "/kakaoLogout", produces = "application/json")
	public String kakaoLogout(HttpSession session) {

		// 노드에 로그아웃한 결과값을 담아줌. 매개변수는 세션에 있는 token을 가져와서 문자열로 변환
		JsonNode node = kakaoRestApi.kakaoLogout(session.getAttribute("token").toString());
		// 결과 값 출력
		System.out.println("로그인 후 반환되는 아이디 : " + node.get("id"));
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping(value = "/deleteKakaoMember", method = RequestMethod.GET)
	public String deleteProc(HttpSession session) {
		if (session.getAttribute("id") == null) {
			return "redirect:/";

		} else {
			return "/member/kakaoMemberDeleteProc";
		}
	}

	// 카카오 연동 가입자 탈퇴 : 앱 연결 끊기 + member table에서 삭제
	@RequestMapping(value = "/deleteKakaoMember", method = RequestMethod.POST)
	public String deleteKakaoMember(HttpSession session, MemberVo memberVO, Model model) {
		
		if(memberVO.getEmail().equals(session.getAttribute("email"))) {
			
			String accessToken = session.getAttribute("token").toString();
			JsonNode returnNode = kakaoRestApi.deleteKakaoMember(accessToken);
			
			// 숨밥 앱 탈퇴한 카카오 멤버의 id 출력(확인용)
			System.out.println(returnNode.get("id"));
			
			mykakaoDao.deleteMember(memberVO);
			session.invalidate();
			
			return "redirect:/";
			
		}else {
			model.addAttribute("msg", "입력하신 메일이 일치하지 않습니다. ");
			return "/member/kakaoMemberDeleteProc";
		}
	}

}
