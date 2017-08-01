package com.mxg.bybo.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
/**
 *  Department
 *
 * @version : Ver 1.0
 * @date	: 2017-4-9
 */
public class Department  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 科室编号     
     */	
	private Long id;
	
	/**
     * 科室名称     
     */	
	private java.lang.String name;
	
	/**
     * 简介     
     */	
	private java.lang.String summary;
	
	/**
     * 服务     
     */	
	private java.lang.String service;
	
	/**
     * 技术     
     */	
	private java.lang.String technical;
	
	/**
     * 标题图片     
     */	
	private java.lang.String picture;
	
	/**
     * 中英文标示 cn：中文 en：英文     
     */	
	private java.lang.String language;
	
	/**
     * 图标     
     */	
	private java.lang.String icon;
	
	/**
     * 图标2     
     */	
	private java.lang.String icon2;
	
	/**
     * 关键字     
     */	
	private java.lang.String keywords;
	
	/**
     * 描述     
     */	
	private java.lang.String description;
	
	
	/**
	 * @param keywords 关键字
	 */
	@ApiModelProperty("关键字")
	public void setKeywords(java.lang.String keywords) {
		this.keywords = keywords;
	}
	
	/**
	 * @return 关键字
	 */
	@ApiModelProperty("关键字")
	public java.lang.String getKeywords() {
		return this.keywords;
	}
	
	/**
	 * @param describe 描述
	 */
	@ApiModelProperty("描述")
	public void setDescription(java.lang.String description) {
		this.description = description;
	}
	
	/**
	 * @return 描述
	 */
	@ApiModelProperty("描述")
	public java.lang.String getDescription() {
		return this.description;
	} 
	
	/**
	 * @param id 科室编号
	 */
	@ApiModelProperty("科室编号")
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return 科室编号
	 */
	@ApiModelProperty("科室编号")
	public Long getId() {
		return this.id;
	}
	
	/**
	 * @param name 科室名称
	 */
	@ApiModelProperty("科室名称")
	public void setName(java.lang.String name) {
		this.name = name;
	}
	
	/**
	 * @return 科室名称
	 */
	@ApiModelProperty("科室名称")
	public java.lang.String getName() {
		return this.name;
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
	
	/**
	 * @param service 服务
	 */
	@ApiModelProperty("服务")
	public void setService(java.lang.String service) {
		this.service = service;
	}
	
	/**
	 * @return 服务
	 */
	@ApiModelProperty("服务")
	public java.lang.String getService() {
		return this.service;
	}
	
	/**
	 * @param technical 技术
	 */
	@ApiModelProperty("技术")
	public void setTechnical(java.lang.String technical) {
		this.technical = technical;
	}
	
	/**
	 * @return 技术
	 */
	@ApiModelProperty("技术")
	public java.lang.String getTechnical() {
		return this.technical;
	}
	
	/**
	 * @param picture 标题图片
	 */
	@ApiModelProperty("标题图片")
	public void setPicture(java.lang.String picture) {
		this.picture = picture;
	}
	
	/**
	 * @return 标题图片
	 */
	@ApiModelProperty("标题图片")
	public java.lang.String getPicture() {
		return this.picture;
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
	 * @param icon 图标
	 */
	@ApiModelProperty("图标")
	public void setIcon(java.lang.String icon) {
		this.icon = icon;
	}
	
	/**
	 * @return 图标
	 */
	@ApiModelProperty("图标")
	public java.lang.String getIcon() {
		return this.icon;
	}
	
	/**
	 * @param icon2 图标2
	 */
	@ApiModelProperty("图标2")
	public void setIcon2(java.lang.String icon2) {
		this.icon2 = icon2;
	}
	
	/**
	 * @return 图标2
	 */
	@ApiModelProperty("图标2")
	public java.lang.String getIcon2() {
		return this.icon2;
	}
}
