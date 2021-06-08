package com.sumbab.sumbab.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sumbab.sumbab.model.reserve.Reserve;
import com.sumbab.sumbab.model.reserve.ReserveRegitReq;
import com.sumbab.sumbab.model.reserve.ReserveService;
import com.sumbab.sumbab.model.store.StoreService;

@Controller
@SessionAttributes("reserveDTO")
public class ReserveController {
	
	@Autowired
	private ReserveService reserveService;
	
	@Autowired
	public void setReserveService(ReserveService reserveService) {
		this.reserveService = reserveService;
	}
	@Autowired
	private StoreService storeService;
	@Autowired
	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}

	//마이페이지-->나의 예약한 음식점 리스트 보기:리스트 출력
	@RequestMapping(value="/reserve/reserveList", method=RequestMethod.GET)
	public String reserveList(Model model, HttpSession session) {
		
		String id = null;
		if (session.getAttribute("id") != null) {
			id = (String)session.getAttribute("id");
		}
		model.addAttribute("listFuture", reserveService.getListF(id));
		model.addAttribute("listPast", reserveService.getListP(id));
		model.addAttribute("storeName", reserveService.getStoreName(id));
		
		return "reserve/reserveList";
	}
	
	//식당 상세보기-->예약하기 요청
	@RequestMapping(value="/reserve/reserveForm/{storeNum}", method=RequestMethod.GET)
	public String reserve(@PathVariable int storeNum, Model model, HttpSession session) {
		
		if(session.getAttribute("id")!=null) {
			
		model.addAttribute("reserveDTO", new Reserve());
		model.addAttribute("storelist", storeService.storeView(storeNum));
		return "reserve/reserveForm";
		}else {
		
		return "redirect:/store/StoreView/{storeNum}";
		}
	}
	
	@RequestMapping(value="/reserve/completeReserve", method=RequestMethod.POST)
	public String alignValue(@ModelAttribute("reserveDTO")Reserve reserve, ReserveRegitReq resReq, 
			String datepicker, String timepicker, Model model, HttpSession session, @ModelAttribute("storeNum")int storeNum) {
		
		reserveService.regit(reserve, datepicker, timepicker, session, storeNum);
		return "reserve/completeReserve";
	}
	

	//예약(미래) 삭제하기---요청
	@RequestMapping(value="/reserve/cancelReserve/{reserveSeq}", method=RequestMethod.GET)
	public String cancelProcess(@PathVariable int reserveSeq, @ModelAttribute String memberID, Model model) {
		model.addAttribute("reserveSeq", reserveSeq);
		model.addAttribute("memberID", memberID);
		
		return "reserve/cancelReserve";
	}
	
	//예약(미래) 삭제하기---실행
	@RequestMapping(value="/reserve/cancelReserve/{reserveSeq}", method=RequestMethod.POST)
	public String completeCancel(@PathVariable int reserveSeq) {
		reserveService.delete(reserveSeq);
		
		return "reserve/completeCancel";
	}

}
