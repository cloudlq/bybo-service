package com.mxg.bybo.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
/**
 *  SimilarKnowledge
 *
 * @version : Ver 1.0
 * @date	: 2017-3-29
 */
public class SimilarKnowledge  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     *      
     */	
	private Long knowledgeId;
	
	/**
     *      
     */	
	private java.lang.Long similarId;
	
	 
	
	/**
	 * @param knowledgeId 
	 */
	@ApiModelProperty("")
	public void setKnowledgeId(Long knowledgeId) {
		this.knowledgeId = knowledgeId;
	}
	
	/**
	 * @return 
	 */
	@ApiModelProperty("")
	public Long getKnowledgeId() {
		return this.knowledgeId;
	}
	
	/**
	 * @param similarId 
	 */
	@ApiModelProperty("")
	public void setSimilarId(java.lang.Long similarId) {
		this.similarId = similarId;
	}
	
	/**
	 * @return 
	 */
	@ApiModelProperty("")
	public java.lang.Long getSimilarId() {
		return this.similarId;
	}
}
