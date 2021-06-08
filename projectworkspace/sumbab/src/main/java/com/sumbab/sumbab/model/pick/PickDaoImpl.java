package com.sumbab.sumbab.model.pick;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sumbab.sumbab.model.store.Store;

@Repository
public class PickDaoImpl implements PickDao{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<Integer> bringPick(String id) {
		return sqlSessionTemplate.selectList("bringPick", id);
	}

	@Override
	public void addPick(Pick pick) {
		sqlSessionTemplate.insert("addPick", pick);
	}

	@Override
	public List<Store> pickStores(String id) {
		return sqlSessionTemplate.selectList("storage", id);
	}

	@Override
	public Store anonPickStores(int storeNum) {
		return sqlSessionTemplate.selectOne("anonPickStores", storeNum);
	}

	@Override
	public void deletePick(Pick pick) {
		sqlSessionTemplate.delete("deletePick", pick);
	}

	

	
	
	
}
