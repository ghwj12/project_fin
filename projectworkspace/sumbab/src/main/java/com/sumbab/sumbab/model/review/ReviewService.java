package com.sumbab.sumbab.model.review;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumbab.sumbab.model.store.Store;



@Service
public class ReviewService {
	
	public ReviewService() {}
	private ReviewDao reviewDao;
	
	@Autowired
	public ReviewService(ReviewDao reviewDao) {
		this.reviewDao = reviewDao;
	}
	
	public void regit(Review review, HttpSession session, int storeNum) {
		
		String id = (String) session.getAttribute("id");
		
		Review newReview = new Review(
				storeNum,
				id,
				review.getMenu(),
				review.getStar(),
				review.getContent(),
				review.isRevisit(),
				review.getPicture(),
				review.getBlob()
				);
		reviewDao.insert(newReview);
	}
	
	public byte[] imageToByteArray(String filePath)throws Exception {
		byte[] value = null;
		
		ByteArrayOutputStream baos = null;
		FileInputStream fis = null;
		
		try {
			baos = new ByteArrayOutputStream();
			fis = new FileInputStream(filePath);
			
			byte[] buf = new byte[1024];
			int read = 0;
			
			while((read=fis.read(buf, 0, buf.length)) != -1){
				baos.write(buf, 0, read);
			}
			value = baos.toByteArray();
		}
		catch(Exception e) {
			if(baos!=null) {
				baos.close();
			}
			if(fis!=null) {
				fis.close();
			}
		}
		return value;
	}
	
	public void editReview(Review review) {
		reviewDao.update(review);
	}
	public void deleteReview(int reviewNum) {
		reviewDao.delete(reviewNum);
	}
	
	public int getReviewCount(int storeNum) {
		return reviewDao.getReviewCount(storeNum);
	}
	
	public List<Review> getList(int storeNum){
		return reviewDao.getReviewList(storeNum);
	}

	public float getAvgStar(int storeNum) {
		return reviewDao.getAvgStar(storeNum);
	}
	
	public float getAvgRevisit(int storeNum) {
		return reviewDao.getAvgRevisit(storeNum);
	}
	
	public List<Review> getMyReviewList(String id){
		return reviewDao.getMyReviewList(id);
	}
	
	public List<Store> getStoreName(String id){
		return reviewDao.getStoreName(id);
	}
	
	public Review getReviewDetail(int reviewNum) {
		return reviewDao.reviewDetail(reviewNum);
	}
}
