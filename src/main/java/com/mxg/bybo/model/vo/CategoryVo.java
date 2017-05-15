package com.mxg.bybo.model.vo;

import java.util.List;

import com.mxg.bybo.model.Category;
import com.mxg.bybo.model.Knowledge;

public class CategoryVo {
	
	private Category category;
	
	private List<Knowledge> knowledges;
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public List<Knowledge> getKnowledges() {
		return knowledges;
	}
	public void setKnowledges(List<Knowledge> knowledges) {
		this.knowledges = knowledges;
	}

}
