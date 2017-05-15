package com.mxg.bybo.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
/**
 *  Category
 *
 * @version : Ver 1.0
 * @date	: 2017-4-9
 */
public class Category  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     *      
     */	
	private Long id;
	
	/**
     *      
     */	
	private java.lang.String name;
	
	/**
     *      
     */	
	private Long departmentId;
	
	/**
     * 中英文标示 cn：中文 en：英文     
     */	
	private java.lang.String language;
	
	/**
     * 科室     
     */	
	private java.lang.String department;
	
	/**
     * 0:不显示 1：显示     
     */	
	private java.lang.Integer isShow;
	
	/**
     * 简介     
     */	
	private java.lang.String summary;
	
	private java.lang.String icon;
	
	private java.lang.String icon2;
	 
	
	
	public java.lang.String getIcon() {
		return icon;
	}

	public void setIcon(java.lang.String icon) {
		this.icon = icon;
	}

	public java.lang.String getIcon2() {
		return icon2;
	}

	public void setIcon2(java.lang.String icon2) {
		this.icon2 = icon2;
	}

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
	 * @param name 
	 */
	@ApiModelProperty("")
	public void setName(java.lang.String name) {
		this.name = name;
	}
	
	/**
	 * @return 
	 */
	@ApiModelProperty("")
	public java.lang.String getName() {
		return this.name;
	}
	
	/**
	 * @param departmentId 
	 */
	@ApiModelProperty("")
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	
	/**
	 * @return 
	 */
	@ApiModelProperty("")
	public Long getDepartmentId() {
		return this.departmentId;
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
	
	/**
	 * @param department 科室
	 */
	@ApiModelProperty("科室")
	public void setDepartment(java.lang.String department) {
		this.department = department;
	}
	
	/**
	 * @return 科室
	 */
	@ApiModelProperty("科室")
	public java.lang.String getDepartment() {
		return this.department;
	}
	
	/**
	 * @param isShow 0:不显示 1：显示
	 */
	@ApiModelProperty("0:不显示 1：显示")
	public void setIsShow(java.lang.Integer isShow) {
		this.isShow = isShow;
	}
	
	/**
	 * @return 0:不显示 1：显示
	 */
	@ApiModelProperty("0:不显示 1：显示")
	public java.lang.Integer getIsShow() {
		return this.isShow;
	}
	
	/**
	 * @param summary 简介
	 */
	@ApiModelProperty("简介")
	public void setSummary(java.lang.String summary) {
		this.summary = summary;
	}
	
	/**
	 * @return 简介
	 */
	@ApiModelProperty("简介")
	public java.lang.String getSummary() {
		return this.summary;
	}
}
