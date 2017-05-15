package com.mxg.bybo.model.req;

import java.util.List;

public class SimilarKnowledgeReq {
	private long knowledgeId;
	private List<Long> similarIds;
	public long getKnowledgeId() {
		return knowledgeId;
	}
	public void setKnowledgeId(long knowledgeId) {
		this.knowledgeId = knowledgeId;
	}
	public List<Long> getSimilarIds() {
		return similarIds;
	}
	public void setSimilarIds(List<Long> similarIds) {
		this.similarIds = similarIds;
	}

}
