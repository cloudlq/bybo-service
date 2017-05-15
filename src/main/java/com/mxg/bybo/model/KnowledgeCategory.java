package com.mxg.bybo.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
/**
 *  KnowledgeCategory
 *
 * @version : Ver 1.0
 * @date	: 2017-3-29
 */
public class KnowledgeCategory  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     *      
     */	
	private Long knowledgeId;
	
	/**
     * 知识库与标签关联     
     */	
	private Long categoryId;
	
	 
	
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
	 * @param categoryId 知识库与标签关联
	 */
	@ApiModelProperty("知识库与标签关联")
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	/**
	 * @return 知识库与标签关联
	 */
	@ApiModelProperty("知识库与标签关联")
	public Long getCategoryId() {
		return this.categoryId;
	}
}
