package com.sumbab.sumbab.model.pick;

import java.util.List;

import com.sumbab.sumbab.model.store.Store;

public interface PickDao {
	
	//보관함에  있는 가게번호 가져오기
	public List<Integer> bringPick(String id);
	
	//보관함에 가게번호 추가
	public void addPick(Pick pick);
	
	//회원 보관함에 있는 가게들 정보 가져오기
	public List<Store> pickStores(String id);
	
	//비회원 보관함에 있는 가게들 정보 가져오기
	public Store anonPickStores(int storeNum);
	
	//보관함에서 가게 삭제
	public void deletePick(Pick pick);

}
