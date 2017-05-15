package com.mxg.bybo.model.req;

import java.util.List;

import com.mxg.bybo.model.Knowledge;

public class KnowledgeReq {
	private Knowledge knowledge;
	private List<Long> categoryIds;

	public Knowledge getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(Knowledge knowledge) {
		this.knowledge = knowledge;
	}

	public List<Long> getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(List<Long> categoryIds) {
		this.categoryIds = categoryIds;
	}

}
