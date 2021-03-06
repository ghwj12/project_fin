package com.sumbab.sumbab.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sumbab.sumbab.model.review.Review;
import com.sumbab.sumbab.model.review.ReviewService;
import com.sumbab.sumbab.model.store.Store;
import com.sumbab.sumbab.model.store.StoreService;
import com.sumbab.sumbab.model.tag.TagService;

@Controller
public class ReviewController {
	
	@Autowired
	private StoreService storeService;
	@Autowired
	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}
	
	@RequestMapping(value="/review/writeReview/{storeNum}", method=RequestMethod.GET)
	public String writeReview(@PathVariable int storeNum, Model model, @ModelAttribute("storelist")Store store) {
		
		model.addAttribute("reviewDTO", new Review());
		model.addAttribute("storelist", storeService.storeView(storeNum));//selectOne
		
		return "/review/writeReview";
	}
	
	@Autowired
	private ReviewService reviewService;
	@Autowired
	public void setReviewService(ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	
	@Autowired
	private TagService tagService;
	@Autowired
	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}
	
	@RequestMapping(value="/review/completeWrReview", method=RequestMethod.POST)
	public String completeWriteReview(@ModelAttribute("reviewDTO")Review review,
			@ModelAttribute("tag")ArrayList<String> tag, Model model, 
			@ModelAttribute("storeNum")int storeNum, HttpSession session,
			MultipartHttpServletRequest mtpReq) {
		
		//???????????? ?????? method=====================================
		MultipartFile mf = mtpReq.getFile("attached");
		
		String path = "C:/02review/"; //?????? ???????????? ?????? ??????
		String originFileName = mf.getOriginalFilename();
		String picture = "";
		
		if(originFileName == "") {
			picture = ""; // attached ????????? ???????????? x
			review.setPicture(picture);
		}else {
			//?????? ?????? ????????? ?????? ????????? ????????? string?????? ??????
			picture = System.currentTimeMillis() + originFileName;
			review.setPicture(picture);
			
			try {
				mf.transferTo(new File(path+picture));
			}catch (IllegalStateException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			review.setBlob(mf.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		reviewService.regit(review, session, storeNum);
		
		
		//?????? ?????? method =====================================
		for (int i = 0; i < tag.size(); i++) {
			tagService.regist(tag.get(i));
			tagService.insertReview_Tag(tag.get(i));
			tagService.insertStore_Tag(storeNum, tag.get(i));
		}
		
		return "/review/completeWrReview";
	}
	
	@RequestMapping(value="/review/myReviewList", method = RequestMethod.GET)
	public String myReviewList(Model model, HttpSession session) {
		String id = null;
				if (session.getAttribute("id") != null) {
					id = (String)session.getAttribute("id");
					System.out.println(id+"??");
				}
		model.addAttribute("reviewList", reviewService.getMyReviewList(id));

		return "/review/myReviewList";
	}
	
	@RequestMapping(value="/review/reviewDetail/{reviewNum}", method = RequestMethod.GET)
	public String reviewDetail(Model model, @PathVariable int reviewNum) {
		model.addAttribute("Review", reviewService.getReviewDetail(reviewNum));
		model.addAttribute("tags", tagService.selectTagByReview(reviewNum));
		return "/review/reviewDetail";
	}
	
	@RequestMapping(value="/review/editReview/{reviewNum}", method = RequestMethod.GET)
	public String editReview(Model model, Review review, @PathVariable int reviewNum) {
		review = reviewService.getReviewDetail(reviewNum);
		model.addAttribute("Review", review);
		model.addAttribute("tags", tagService.selectTagByReview(reviewNum));
		return "/review/editReview";
	}
	
	@RequestMapping(value="/review/editReview/{reviewNum}", method=RequestMethod.POST)
	public String completeEditReview(@PathVariable int reviewNum, 
			@ModelAttribute("review") Review review, MultipartHttpServletRequest mtpReq) {
		
		MultipartFile mf = mtpReq.getFile("attached");
		String path = "C:/02review/"; //?????? ???????????? ?????? ??????
		String originFileName = mf.getOriginalFilename();
		String picture="";
		
		if(!(originFileName.isEmpty())) {//??????????????? ?????????(?????? ????????? ?????????, ?????????)
			picture = System.currentTimeMillis() + originFileName;
			review.setPicture(picture);		
			try {
				mf.transferTo(new File(path+picture));
			}catch (IllegalStateException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
			reviewService.editReview(review);
		}else if(originFileName.isEmpty()){
			reviewService.editReview(review);
		}
		return "/review/completeEditReview";
	}
	
	@RequestMapping(value="/review/deleteReview/{reviewNum}", method = RequestMethod.GET)
	public String delReviewProc(@PathVariable int reviewNum) {
		
		return "/review/deleteReviewProc";
	}
	@RequestMapping(value="/review/deleteReview/{reviewNum}", method = RequestMethod.POST)
	public String completeDelReview(@PathVariable int reviewNum) {
		reviewService.deleteReview(reviewNum);
		
		return "/review/completeDelReview";
	}

}
