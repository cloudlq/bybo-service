package com.mxg.bybo.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
/**
 *  ExpertClassifyRel
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11
 */
public class ExpertClassifyRel  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 专家编号     
     */	
	private Long expertId;
	
	/**
     * 分类id     
     */	
	private Long classifyId;
	
	 
	
	/**
	 * @param expertId 专家编号
	 */
	@ApiModelProperty("专家编号")
	public void setExpertId(Long expertId) {
		this.expertId = expertId;
	}
	
	/**
	 * @return 专家编号
	 */
	@ApiModelProperty("专家编号")
	public Long getExpertId() {
		return this.expertId;
	}
	
	/**
	 * @param classifyId 分类id
	 */
	@ApiModelProperty("分类id")
	public void setClassifyId(Long classifyId) {
		this.classifyId = classifyId;
	}
	
	/**
	 * @return 分类id
	 */
	@ApiModelProperty("分类id")
	public Long getClassifyId() {
		return this.classifyId;
	}
}
