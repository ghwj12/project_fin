package com.sumbab.sumbab.model.category;

import org.apache.ibatis.type.Alias;

@Alias("selectedCategory")
public class SelectedCategory {

	private String category1;
	private String category2;
	private String categoryText1;
	private String categoryText2;
	
	public String getCategory1() {
		return category1;
	}
	public void setCategory1(String category1) {
		this.category1 = category1;
	}
	public String getCategory2() {
		return category2;
	}
	public void setCategory2(String category2) {
		this.category2 = category2;
	}
	public String getCategoryText1() {
		return categoryText1;
	}
	public void setCategoryText1(String categoryText1) {
		this.categoryText1 = categoryText1;
	}
	public String getCategoryText2() {
		return categoryText2;
	}
	public void setCategoryText2(String categoryText2) {
		this.categoryText2 = categoryText2;
	}
	
}
