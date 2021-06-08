package com.sumbab.sumbab.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sumbab.sumbab.model.pick.PickService;
import com.sumbab.sumbab.model.store.Store;



@Controller
public class PickController {

	@Autowired
	private PickService pickService;
	
	//보관함에 담기 버튼 클릭
	//반환값이 1:이미 보관함에 해당 가게가 담겨 있을 경우, 0:보관함에 해당 가게 추가
	@RequestMapping("store/StoreView/pick/pickProcess/{storeNum}")
	public String memberPick(HttpSession session, @PathVariable int storeNum, HttpServletResponse response, HttpServletRequest request, Model model) {
		String id = (String)session.getAttribute("id");
		int result=0;
		if(id != null) {		//로그인 했을 경우
			result = pickService.addPick(id, storeNum);
			model.addAttribute("identified", true);
		}else {						//비회원일 경우
			List<String> list = new ArrayList<>(); 		//쿠키에 담을 가게번호들 넣는 객체
			Cookie[] cookies = request.getCookies();	//클라이언트에 있는 쿠키들 가져오기
			String value = "";							
			String[] picked = null;
			if(cookies != null) {						//클라이언트에 쿠키가 존재할 경우
				for(int i=0; i<cookies.length; i++) {
					if(cookies[i].getName().equals("pick")) {	//보관함에 해당하는 쿠키가 존재할 경우
						value = cookies[i].getValue();	//쿠키에 저장되어 있는 가게번호들을 문자열로 저장
						picked = value.split("a");		//문자열로 저장한 가게번호들을 a로 구분하여 배열에 저장(,나 띄어쓰기는 쿠키에 안들어감)
						break;
					}
				}
			}
			if(value.equals("")) {						//보관함에 해당하는 쿠키가 존재하지 않을 경우
				Cookie cookie = new Cookie("pick", Integer.toString(storeNum));
				cookie.setMaxAge(60*60*24);
				cookie.setPath("/");
				response.addCookie(cookie);
				result = 0;
			} else {
				for(int i=0; i<picked.length; i++) {
					if(picked[i].equals(Integer.toString(storeNum))) {	//보관함에 추가할 가게번호가 이미 있을 경우
						result = 1;
						break;
					}
				}
				if(result != 1) {							//보관함에 추가할 가게번호가 없을 경우
					for(int i=0; i<picked.length; i++)		
						list.add(picked[i]);				//가게 번호를 추가하기 위해 크기가 정해져 있지 않은 List 타입으로 기존에 있던 가게번호들 담기
					list.add(Integer.toString(storeNum));	//가게 번호 추가
					String finalStorage = "";				
					for(int i=0; i<list.size(); i++)
						finalStorage += list.get(i) + "a";	//쿠키에 List 타입으로 저장할 수 없기 때문에 다시 String형으로 가게번호들 담기
					Cookie cookie = new Cookie("pick", finalStorage);
					cookie.setMaxAge(60*60*24);
					cookie.setPath("/");
					response.addCookie(cookie);
					result = 0;
				}
			}
		}
		model.addAttribute("result", result);
		return "pick/pickProcess";
	}
	
	
	//보관함 보기
	@RequestMapping("pick/storage")
	public String storageController(HttpSession session, Model model, HttpServletRequest request) {
		String id = (String)session.getAttribute("id");
		List<Store> pickList = null;
		List<Store> pickRest = new ArrayList<>();
		List<Store> pickCafe = new ArrayList<>();
		Cookie[] cookies = request.getCookies();	//클라이언트에 있는 쿠키들 가져오기
		String value = "";							
		String[] picked = null;
		if(cookies != null) {
			for(int i=0; i<cookies.length; i++) {
				if(cookies[i].getName().equals("pick")) {	//클라이언트에 쿠키가 존재할 경우
					value = cookies[i].getValue();	//쿠키에 저장되어 있는 가게번호들을 문자열로 저장
					picked = value.split("a");		//문자열로 저장한 가게번호들을 a로 구분하여 배열에 저장
					break;
				}
			}
		}
		if(id != null) {
			if(!value.equals("")) {						//보관함에 해당하는 쿠키가 존재할 경우
				for(int i=0; i<picked.length; i++) {
					if(Integer.parseInt(picked[i]) == 0) {
						continue;
					}
					pickService.addPick(id, Integer.parseInt(picked[i]));	//가게 번호 중복 처리
				}
			}
			pickList = pickService.selectPickStores(id);
		}else {
			if(!value.equals("")) {						//보관함에 해당하는 쿠키가 존재할 경우
				System.out.println("쿠키에 있는 가게 번호 : "+value);
				pickList = pickService.anonPickStores(picked);
			}
		}
		if(pickList != null) {
			for(int i=0; i<pickList.size(); i++) {
				if(pickList.get(i).getClassify().equals("음식점")) {
					pickRest.add(pickList.get(i));
				}else {
					pickCafe.add(pickList.get(i));
				}
			}
			model.addAttribute("pickRest", pickRest);
			model.addAttribute("pickCafe", pickCafe);
		}else {
			String msg = "보관함에 담은 가게가 없습니다!";
			model.addAttribute("msg", msg);
		}
		
		return "pick/storage";
	}
	
	//보관함에서 가게 삭제
	@RequestMapping(value="pick/storage/{storeNum}", method=RequestMethod.GET)
	public String deletePick(@PathVariable int storeNum, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		String id = (String)session.getAttribute("id");
		if(id != null) {
			pickService.deletePick(id, storeNum);
		}else {
			Cookie[] cookies = request.getCookies();	//클라이언트에 있는 쿠키들 가져오기
			String value = "";							
			String[] picked = null;
			if(cookies != null) {						
				for(int i=0; i<cookies.length; i++) {
					if(cookies[i].getName().equals("pick")) {	//클라이언트에 쿠키가 존재할 경우
						value = cookies[i].getValue();	//쿠키에 저장되어 있는 가게번호들을 문자열로 저장
						picked = value.split("a");		//문자열로 저장한 가게번호들을 a로 구분하여 배열에 저장
						break;
					}
				}
			}
			String finalStorage = "";
			if(!value.equals("")) {						//보관함에 해당하는 쿠키가 존재할 경우
				for(int i=0; i<picked.length; i++) {
					if(Integer.parseInt(picked[i]) == storeNum) {
						picked[i] = "0";				//삭제할 가게의 번호를 0으로 바꾸기
					}
					finalStorage += picked[i] + "a";
				}
				Cookie cookie = new Cookie("pick", finalStorage);
				cookie.setMaxAge(60*60*24);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}
		return "redirect:/pick/storage";
	}
	
	
}
