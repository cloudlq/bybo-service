package com.mxg.bybo.model.vo;

import java.util.List;

import com.mxg.bybo.model.Category;
import com.mxg.bybo.model.Department;
import com.mxg.bybo.model.Knowledge;

public class KnowledgeVo {
	private List<Knowledge> knowledges;
	private List<Category> categories;
	private Knowledge knowledge;
	
	public Knowledge getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(Knowledge knowledge) {
		this.knowledge = knowledge;
	}

	public List<Knowledge> getKnowledges() {
		return knowledges;
	}

	public void setKnowledges(List<Knowledge> knowledges) {
		this.knowledges = knowledges;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

}
