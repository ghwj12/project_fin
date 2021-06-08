package com.sumbab.sumbab.model.reserve;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumbab.sumbab.model.store.Store;



@Service
public class ReserveService {

	public ReserveService() {}

	private ReserveDaoImpl reserveDao;
	
	@Autowired
	public ReserveService(ReserveDaoImpl reserveDao) {
		this.reserveDao = reserveDao;
	}
	
	public void regit(Reserve reserve, String datepicker, String timepicker, HttpSession session, int storeNum) {
		String resDate = datepicker+" "+timepicker;

		String id = (String)session.getAttribute("id");
		
		Reserve newReserve = new Reserve(
				storeNum,
				id,
				reserve.getReserveName(),
				resDate,
				reserve.getPhone()
				);
		
		reserveDao.insert(newReserve);
	}
	
	public List<Reserve> getListF(String memberID) {
		return reserveDao.listFuture(memberID);
	}
	
	public List<Reserve> getListP(String memberID) {
		return reserveDao.listPast(memberID);
	}
	
	public List<Store> getStoreName(String memberID){
		return reserveDao.getStoreName(memberID);
	}
	
	public void delete(int reserveSeq) {
		reserveDao.delete(reserveSeq);
	}
	
}
