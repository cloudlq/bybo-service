package com.mxg.bybo.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
/**
 *  Article
 *
 * @version : Ver 1.0
 * @date	: 2017-4-3
 */
public class Article  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 编号     
     */	
	private Long id;
	
	/**
     * 标题     
     */	
	private java.lang.String title;
	
	/**
     *      
     */	
	private java.lang.String content;
	
	/**
     *      
     */	
	private java.util.Date addTime;
	
	/**
     *      
     */	
	private java.util.Date updateTime;
	
	/**
     * 分类id     
     */	
	private java.lang.String categoryId;
	
	/**
     * 分类名称     
     */	
	private java.lang.String categoryName;
	
	/**
     * 所属机构     
     */	
	private Long regionId;
	
	/**
     * 标题图片     
     */	
	private java.lang.String imageUrl;
	
	/**
     * 中英文标示  cn：中文  en：英文     
     */	
	private java.lang.String language;
	
	/**
     * 作者     
     */	
	private java.lang.String author;
	
	/**
     * 描述     
     */	
	private java.lang.String summary;
	
	/**
     *      
     */	
	private java.lang.String imageUrl2;
	
	/**
     * 关联科室     
     */	
	private java.lang.String department;
	
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
	 * @param id 编号
	 */
	@ApiModelProperty("编号")
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return 编号
	 */
	@ApiModelProperty("编号")
	public Long getId() {
		return this.id;
	}
	
	/**
	 * @param title 标题
	 */
	@ApiModelProperty("标题")
	public void setTitle(java.lang.String title) {
		this.title = title;
	}
	
	/**
	 * @return 标题
	 */
	@ApiModelProperty("标题")
	public java.lang.String getTitle() {
		return this.title;
	}
	
	/**
	 * @param content 
	 */
	@ApiModelProperty("")
	public void setContent(java.lang.String content) {
		this.content = content;
	}
	
	/**
	 * @return 
	 */
	@ApiModelProperty("")
	public java.lang.String getContent() {
		return this.content;
	}
	
	/**
	 * @param addTime 
	 */
	@ApiModelProperty("")
	public void setAddTime(java.util.Date addTime) {
		this.addTime = addTime;
	}
	
	/**
	 * @return 
	 */
	@ApiModelProperty("")
	public java.util.Date getAddTime() {
		return this.addTime;
	}
	 
	
	/**
	 * @param updateTime 
	 */
	@ApiModelProperty("")
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	
	/**
	 * @return 
	 */
	@ApiModelProperty("")
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	 
	
	/**
	 * @param categoryId 分类id
	 */
	@ApiModelProperty("分类id")
	public void setCategoryId(java.lang.String categoryId) {
		this.categoryId = categoryId;
	}
	
	/**
	 * @return 分类id
	 */
	@ApiModelProperty("分类id")
	public java.lang.String getCategoryId() {
		return this.categoryId;
	}
	
	/**
	 * @param categoryName 分类名称
	 */
	@ApiModelProperty("分类名称")
	public void setCategoryName(java.lang.String categoryName) {
		this.categoryName = categoryName;
	}
	
	/**
	 * @return 分类名称
	 */
	@ApiModelProperty("分类名称")
	public java.lang.String getCategoryName() {
		return this.categoryName;
	}
	
	/**
	 * @param regionId 所属机构
	 */
	@ApiModelProperty("所属机构")
	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}
	
	/**
	 * @return 所属机构
	 */
	@ApiModelProperty("所属机构")
	public Long getRegionId() {
		return this.regionId;
	}
	
	/**
	 * @param imageUrl 标题图片
	 */
	@ApiModelProperty("标题图片")
	public void setImageUrl(java.lang.String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	/**
	 * @return 标题图片
	 */
	@ApiModelProperty("标题图片")
	public java.lang.String getImageUrl() {
		return this.imageUrl;
	}
	
	/**
	 * @param language 中英文标示  cn：中文  en：英文
	 */
	@ApiModelProperty("中英文标示  cn：中文  en：英文")
	public void setLanguage(java.lang.String language) {
		this.language = language;
	}
	
	/**
	 * @return 中英文标示  cn：中文  en：英文
	 */
	@ApiModelProperty("中英文标示  cn：中文  en：英文")
	public java.lang.String getLanguage() {
		return this.language;
	}
	
	/**
	 * @param author 作者
	 */
	@ApiModelProperty("作者")
	public void setAuthor(java.lang.String author) {
		this.author = author;
	}
	
	/**
	 * @return 作者
	 */
	@ApiModelProperty("作者")
	public java.lang.String getAuthor() {
		return this.author;
	}
	
	/**
	 * @param summary 描述
	 */
	@ApiModelProperty("描述")
	public void setSummary(java.lang.String summary) {
		this.summary = summary;
	}
	
	/**
	 * @return 描述
	 */
	@ApiModelProperty("描述")
	public java.lang.String getSummary() {
		return this.summary;
	}
	
	/**
	 * @param imageUrl2 
	 */
	@ApiModelProperty("")
	public void setImageUrl2(java.lang.String imageUrl2) {
		this.imageUrl2 = imageUrl2;
	}
	
	/**
	 * @return 
	 */
	@ApiModelProperty("")
	public java.lang.String getImageUrl2() {
		return this.imageUrl2;
	}
	
	/**
	 * @param department 关联科室
	 */
	@ApiModelProperty("关联科室")
	public void setDepartment(java.lang.String department) {
		this.department = department;
	}
	
	/**
	 * @return 关联科室
	 */
	@ApiModelProperty("关联科室")
	public java.lang.String getDepartment() {
		return this.department;
	}
}
