package com.mxg.bybo.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
/**
 *  Classify
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11
 */
public class Classify  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     *      
     */	
	private Long id;
	
	/**
     * 名称     
     */	
	private java.lang.String name;
	
	/**
     * 中英文标示 cn：中文 en：英文     
     */	
	private java.lang.String language;
	
	 
	
	/**
	 * @param id 
	 */
	@ApiModelProperty("")
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return 
	 */
	@ApiModelProperty("")
	public Long getId() {
		return this.id;
	}
	
	/**
	 * @param name 名称
	 */
	@ApiModelProperty("名称")
	public void setName(java.lang.String name) {
		this.name = name;
	}
	
	/**
	 * @return 名称
	 */
	@ApiModelProperty("名称")
	public java.lang.String getName() {
		return this.name;
	}
	
	/**
	 * @param language 中英文标示 cn：中文 en：英文
	 */
	@ApiModelProperty("中英文标示 cn：中文 en：英文")
	public void setLanguage(java.lang.String language) {
		this.language = language;
	}
	
	/**
	 * @return 中英文标示 cn：中文 en：英文
	 */
	@ApiModelProperty("中英文标示 cn：中文 en：英文")
	public java.lang.String getLanguage() {
		return this.language;
	}
}
