package com.sumbab.sumbab.model.recommend;

import java.util.List;

import com.sumbab.sumbab.model.store.Store;

public interface RecommendDao {

	//음식점 카테고리 추천
	public List<Store> selectRest(String category);
	
	//카페 카테고리 추천(영업 시간별 제외)
	public List<Store> selectCafe1(String category);
	
	//카페 카테고리 추천(영업 시간별)
	public List<Store> selectCafe2(String category);
}
