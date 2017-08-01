package com.mxg.bybo.model;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
/**
 *  Knowledge
 *
 * @version : Ver 1.0
 * @date	: 2017-4-4
 */
public class Knowledge  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 编号id     
     */	
	private Long id;
	
	/**
     * 标题     
     */	
	private java.lang.String title;
	
	/**
     * 内容     
     */	
	private java.lang.String content;
	
	/**
     * 添加时间     
     */	
	private java.util.Date addTime;
	
	/**
     * 更新时间     
     */	
	private java.util.Date updateTime;
	
	/**
     * 作者     
     */	
	private java.lang.String author;
	
	/**
     * 中英文标示 0：中文 1：英文     
     */	
	private java.lang.String language;
	
	/**
     * 科室id     
     */	
	private Long departmentId;
	
	/**
     * 科室名称     
     */	
	private java.lang.String departmentName;
	
	/**
     * 分类标签id     
     */	
	private java.lang.String categoryIds;
	
	/**
     * 分类标签     
     */	
	private java.lang.String categoryNames;
	
	/**
	 * 相似文章
	 */
	private List<Long> similarIds;
	
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
	
	public List<Long> getSimilarIds() {
		return similarIds;
	}

	public void setSimilarIds(List<Long> similarIds) {
		this.similarIds = similarIds;
	}

	/**
	 * @param id 编号id
	 */
	@ApiModelProperty("编号id")
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return 编号id
	 */
	@ApiModelProperty("编号id")
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
	 * @param content 内容
	 */
	@ApiModelProperty("内容")
	public void setContent(java.lang.String content) {
		this.content = content;
	}
	
	/**
	 * @return 内容
	 */
	@ApiModelProperty("内容")
	public java.lang.String getContent() {
		return this.content;
	}
	
	/**
	 * @param addTime 添加时间
	 */
	@ApiModelProperty("添加时间")
	public void setAddTime(java.util.Date addTime) {
		this.addTime = addTime;
	}
	
	/**
	 * @return 添加时间
	 */
	@ApiModelProperty("添加时间")
	public java.util.Date getAddTime() {
		return this.addTime;
	}
	 
	
	/**
	 * @param updateTime 更新时间
	 */
	@ApiModelProperty("更新时间")
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	
	/**
	 * @return 更新时间
	 */
	@ApiModelProperty("更新时间")
	public java.util.Date getUpdateTime() {
		return this.updateTime;
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
	 * @param language 中英文标示 0：中文 1：英文
	 */
	@ApiModelProperty("中英文标示 0：中文 1：英文")
	public void setLanguage(java.lang.String language) {
		this.language = language;
	}
	
	/**
	 * @return 中英文标示 0：中文 1：英文
	 */
	@ApiModelProperty("中英文标示 0：中文 1：英文")
	public java.lang.String getLanguage() {
		return this.language;
	}
	
	/**
	 * @param departmentId 科室id
	 */
	@ApiModelProperty("科室id")
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	
	/**
	 * @return 科室id
	 */
	@ApiModelProperty("科室id")
	public Long getDepartmentId() {
		return this.departmentId;
	}
	
	/**
	 * @param departmentName 科室名称
	 */
	@ApiModelProperty("科室名称")
	public void setDepartmentName(java.lang.String departmentName) {
		this.departmentName = departmentName;
	}
	
	/**
	 * @return 科室名称
	 */
	@ApiModelProperty("科室名称")
	public java.lang.String getDepartmentName() {
		return this.departmentName;
	}
	
	/**
	 * @param categoryIds 分类标签id
	 */
	@ApiModelProperty("分类标签id")
	public void setCategoryIds(java.lang.String categoryIds) {
		this.categoryIds = categoryIds;
	}
	
	/**
	 * @return 分类标签id
	 */
	@ApiModelProperty("分类标签id")
	public java.lang.String getCategoryIds() {
		return this.categoryIds;
	}
	
	/**
	 * @param categoryNames 分类标签
	 */
	@ApiModelProperty("分类标签")
	public void setCategoryNames(java.lang.String categoryNames) {
		this.categoryNames = categoryNames;
	}
	
	/**
	 * @return 分类标签
	 */
	@ApiModelProperty("分类标签")
	public java.lang.String getCategoryNames() {
		return this.categoryNames;
	}
}
