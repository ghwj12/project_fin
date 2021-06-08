package com.sumbab.sumbab.model.pick;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumbab.sumbab.model.store.Store;

@Service
public class PickService {
	
	@Autowired
	private PickDaoImpl pickDao;
	
	public int addPick(String id, int storeNum){
		List<Integer> picked = pickDao.bringPick(id);
		for(int i=0; i<picked.size(); i++) {
			if(picked.get(i) == storeNum) {
				return 1;
			}
		}
		Pick pick = new Pick();
		pick.setId(id);
		pick.setStoreNum(storeNum);
		pickDao.addPick(pick);
		return 0;
	}

	public List<Integer> bringPick(String id){
		return pickDao.bringPick(id);
	}
	
	
	public List<Store> selectPickStores(String id){
		return pickDao.pickStores(id);
	}
	
	public List<Store> anonPickStores(String[] storeNums){
		List<Store> lists = new ArrayList<>();
		for(int i=0; i<storeNums.length; i++) {
			int storeNum = Integer.parseInt(storeNums[i]);
			if(storeNum == 0) {
				continue;
			}else {
				lists.add(pickDao.anonPickStores(storeNum));
			}
		}
		return lists;
	}
	
	public void deletePick(String id, int storeNum) {
		Pick pick = new Pick();
		pick.setId(id);
		pick.setStoreNum(storeNum);
		pickDao.deletePick(pick);
	}
	
	
}
