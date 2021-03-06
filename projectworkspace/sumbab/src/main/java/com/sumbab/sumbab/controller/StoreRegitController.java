package com.sumbab.sumbab.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sumbab.sumbab.model.cafeCat.CafeCat;
import com.sumbab.sumbab.model.cafeCat.CafeCatRegistReq;
import com.sumbab.sumbab.model.cafeCat.CafeCatService;
import com.sumbab.sumbab.model.restCat.RestCat;
import com.sumbab.sumbab.model.restCat.RestCatRegistReq;
import com.sumbab.sumbab.model.restCat.RestCatService;
import com.sumbab.sumbab.model.review.ReviewService;
import com.sumbab.sumbab.model.store.Store;
import com.sumbab.sumbab.model.store.StoreRegisterRequest;
import com.sumbab.sumbab.model.store.StoreService;
import com.sumbab.sumbab.model.storeview.StoreViewDao;
import com.sumbab.sumbab.model.tag.TagService;

@Controller
@RequestMapping("/store/*")
@SessionAttributes("storeDTO")
public class StoreRegitController {

	@RequestMapping(value = "/registerStep1", method = RequestMethod.GET)
	public String regitStore1(Model model) {
		model.addAttribute("storeDTO", new Store());
		return "store/registerStep1";
	}

	@Autowired
	private StoreService storeService;

	@Autowired
	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}

	@RequestMapping(value = "/registerStep1", method = RequestMethod.POST)
	public String regitStore2(@ModelAttribute("storeDTO") Store store, HttpSession session, StoreRegisterRequest regReq, String fullAddress,
			String extrAddress, MultipartHttpServletRequest mtpReq) {

		store.setId((String)session.getAttribute("id"));
		
		MultipartFile mf = mtpReq.getFile("attached");

		String path = "C:/01store/"; // ?????? ???????????? ?????? ??????
		String originFileName = mf.getOriginalFilename();
		String picture = "";

		if (originFileName == "") {
			picture = "";
		} else {
			picture = System.currentTimeMillis() + originFileName;
			try {
				mf.transferTo(new File(path + picture));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		storeService.regist(store, fullAddress, extrAddress, picture);

		if (regReq.getClassify().equals("?????????")) {
			return "store/registerStep2_res";
		} else {
			return "store/registerStep2_cafe";
		}
	}

	@Autowired
	private RestCatService restCatService;

	@Autowired
	public void setRestCatService(RestCatService restCatService) {
		this.restCatService = restCatService;
	}

	@Autowired
	private CafeCatService cafeCatService;

	@Autowired
	public void setCafeCatService(CafeCatService cafeCatService) {
		this.cafeCatService = cafeCatService;
	}

	@RequestMapping(value = "/completeRegisterR", method = RequestMethod.POST) // ???????????? ?????? ??? ????????? complete ????????? ??????
	public String regitComplete(RestCatRegistReq resCatRegitReq) {
		restCatService.resgist(resCatRegitReq);

		return "store/completeRegister";
	}

	@RequestMapping(value = "/completeRegisterC", method = RequestMethod.POST) // ???????????? ?????? ??? ????????? complete ????????? ??????
	public String regitComplete(CafeCatRegistReq cafeCatRegitReq) {

		cafeCatService.resgist(cafeCatRegitReq);
		return "store/completeRegister";
	}

	@RequestMapping(value = "/myStoreList", method = RequestMethod.GET)
	public String showMyRegitStoreList(Model model, HttpSession session) {
		String id = null;
		if (session.getAttribute("id") != null) {
			id = (String) session.getAttribute("id");
			System.out.println(id + "??");
		}
		model.addAttribute("myStoreList", storeService.getMyRegitStore(id));
		return "store/myStoreList";
	}

	@Autowired
	private StoreViewDao storeInfoDao;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private TagService tagService;

	@RequestMapping(value = "/storeDetail/{storeNum}", method = RequestMethod.GET)
	public String storeDetail(@PathVariable int storeNum, String classify, Model model) {

		classify = storeService.getClassify(storeNum);
		model.addAttribute("store", storeService.storeView(storeNum));

		if (classify.equals("?????????")) {
			model.addAttribute("restCatVO", restCatService.getRestCat(storeNum));
		} else {
			model.addAttribute("cafeCatVO", cafeCatService.getCafeCat(storeNum));
		}

		if (storeInfoDao.getReviewCount(storeNum) != 0) {
			model.addAttribute("avgStar", reviewService.getAvgStar(storeNum));
			model.addAttribute("avgRevisit", reviewService.getAvgRevisit(storeNum));
			model.addAttribute("tagList", tagService.selectTagEachReview(storeNum));
			model.addAttribute("allTags", tagService.selectTagByStore(storeNum));

		} else if (storeInfoDao.getReviewCount(storeNum) == 0) {
			model.addAttribute("avgStar", 0);
			model.addAttribute("avgRevisit", 0);
		}

		return "/store/storeDetail";
	}

	@RequestMapping(value = "/deleteStoreProc/{storeNum}", method = RequestMethod.GET)
	public String deleteProc(@PathVariable int storeNum, Model model) {
		model.addAttribute("store", storeService.storeView(storeNum));
		return "/store/deleteStoreProc";
	}

	@RequestMapping(value = "/deleteStoreProc/{storeNum}", method = RequestMethod.POST)
	public String deletedStore(@PathVariable int storeNum, String pwd, Model model) {
		Store store = storeService.storeView(storeNum);
		// cascade ?????? ????????? category??? ?????? ????????????.

		if (store.getPwd().equals(pwd)) {
			storeService.deleteStore(store);
			return "/store/deletedStore";
		} else {
			model.addAttribute("msg", "??????????????? ???????????? ????????????.");
			model.addAttribute("store", storeService.storeView(storeNum));
			return "/store/deleteStoreProc";
		}
	}

	@RequestMapping(value = "/editStore/{storeNum}", method = RequestMethod.GET)
	public String editStore(@PathVariable int storeNum, Model model) {
		model.addAttribute("store", storeService.storeView(storeNum));

		return "/store/editStore";
	}

	@RequestMapping(value = "/editStore/{storeNum}", method = RequestMethod.POST)
	public String completeEditStore(@PathVariable int storeNum, Model model, @ModelAttribute("store") Store store,
			MultipartHttpServletRequest mtpReq) {

		MultipartFile mf = mtpReq.getFile("attached");
		String path = "C:/01store/"; // ?????? ???????????? ?????? ??????
		String originFileName = mf.getOriginalFilename();
		String picture = "";

		if (!(originFileName.isEmpty())) {// ??????????????? ?????????(?????? ????????? ?????????, ?????????)
			System.out.println(originFileName);
			picture = System.currentTimeMillis() + originFileName;
			store.setPicture(picture);
			try {
				mf.transferTo(new File(path + picture));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			storeService.updateStore(store);
			model.addAttribute("store", storeService.storeView(storeNum));

		} else if (originFileName.isEmpty()) {
			storeService.updateStore(store);
		}
		return "/store/completeEditStore";
	}

	@RequestMapping(value = "/editCat/{storeNum}", method = RequestMethod.GET)
	public String editCategory(@PathVariable int storeNum, Model model) {

		model.addAttribute("storeVO", storeService.storeView(storeNum));
		if (storeService.getClassify(storeNum).equals("?????????")) {

			model.addAttribute("restCatVO", restCatService.getRestCat(storeNum));
		} else {
			model.addAttribute("cafeCatVO", cafeCatService.getCafeCat(storeNum));
		}
		return "/store/editCat";
	}

	@RequestMapping(value = "/editCat/{storeNum}", method = RequestMethod.POST)
	public String completedEditCategory(@ModelAttribute CafeCat cafeCat, @ModelAttribute RestCat restCat,
			@PathVariable int storeNum, Model model) {

		if (storeService.getClassify(storeNum).equals("?????????")) {

			if (restCatService.updateRestCat(restCat) == 0) {
				restCatService.insertAfter(restCat);
			} else {
				restCatService.updateRestCat(restCat);
			}
		} else if (storeService.getClassify(storeNum).equals("??????")) {
			if (cafeCatService.updateCafeCat(cafeCat) == 0) {
				cafeCatService.insertAfter(cafeCat);
			} else {
				cafeCatService.updateCafeCat(cafeCat);
			}
		}
		return "/store/completedEditCat";
	}
}
