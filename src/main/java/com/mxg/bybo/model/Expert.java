package com.mxg.bybo.model;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * Expert
 *
 * @version : Ver 1.0
 * @author : panda
 * @date : 2017-3-12
 */
public class Expert implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 专家编号
	 */
	private Long id;

	/**
	 * 姓名
	 */
	private java.lang.String name;

	/**
	 * 专业
	 */
	private java.lang.String specialty;

	/**
	 * 职称
	 */
	private java.lang.String title;

	/**
	 * 机构id
	 */
	private Long regionId;

	/**
	 * 中英文标示 cn：中文 en：英文
	 */
	private java.lang.String language;

	/**
	 * 照片
	 */
	private java.lang.String photo;

	/**
	 * 头衔
	 */
	private java.lang.String honor;

	/**
	 * 擅长
	 */
	private java.lang.String adept;

	/**
	 * 简介
	 */
	private java.lang.String content;

	/**
	 * 分类
	 */
	private java.lang.String department;

	/**
	 * 关联的分类
	 */
	private List<Long> departmentIds;

	/**
     * 曾任职务     
     */	
	private java.lang.String duty;
	
	/**
	 * 灰色照片
	 */
	private java.lang.String grayPhone;

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
	
	public java.lang.String getDuty() {
		return duty;
	}

	public void setDuty(java.lang.String duty) {
		this.duty = duty;
	}

	public java.lang.String getGrayPhone() {
		return grayPhone;
	}

	public void setGrayPhone(java.lang.String grayPhone) {
		this.grayPhone = grayPhone;
	}

	public List<Long> getDepartmentIds() {
		return departmentIds;
	}

	public void setDepartmentIds(List<Long> departmentIds) {
		this.departmentIds = departmentIds;
	}

	/**
	 * @param id
	 *            专家编号
	 */
	@ApiModelProperty("专家编号")
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return 专家编号
	 */
	@ApiModelProperty("专家编号")
	public Long getId() {
		return this.id;
	}

	/**
	 * @param name
	 *            姓名
	 */
	@ApiModelProperty("姓名")
	public void setName(java.lang.String name) {
		this.name = name;
	}

	/**
	 * @return 姓名
	 */
	@ApiModelProperty("姓名")
	public java.lang.String getName() {
		return this.name;
	}

	/**
	 * @param specialty
	 *            专业
	 */
	@ApiModelProperty("专业")
	public void setSpecialty(java.lang.String specialty) {
		this.specialty = specialty;
	}

	/**
	 * @return 专业
	 */
	@ApiModelProperty("专业")
	public java.lang.String getSpecialty() {
		return this.specialty;
	}

	/**
	 * @param title
	 *            职称
	 */
	@ApiModelProperty("职称")
	public void setTitle(java.lang.String title) {
		this.title = title;
	}

	/**
	 * @return 职称
	 */
	@ApiModelProperty("职称")
	public java.lang.String getTitle() {
		return this.title;
	}

	/**
	 * @param regionId
	 *            机构id
	 */
	@ApiModelProperty("机构id")
	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	/**
	 * @return 机构id
	 */
	@ApiModelProperty("机构id")
	public Long getRegionId() {
		return this.regionId;
	}

	/**
	 * @param language
	 *            中英文标示 cn：中文 en：英文
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
	 * @param photo
	 *            照片
	 */
	@ApiModelProperty("照片")
	public void setPhoto(java.lang.String photo) {
		this.photo = photo;
	}

	/**
	 * @return 照片
	 */
	@ApiModelProperty("照片")
	public java.lang.String getPhoto() {
		return this.photo;
	}

	/**
	 * @param honor
	 *            头衔
	 */
	@ApiModelProperty("头衔")
	public void setHonor(java.lang.String honor) {
		this.honor = honor;
	}

	/**
	 * @return 头衔
	 */
	@ApiModelProperty("头衔")
	public java.lang.String getHonor() {
		return this.honor;
	}

	/**
	 * @param adept
	 *            擅长
	 */
	@ApiModelProperty("擅长")
	public void setAdept(java.lang.String adept) {
		this.adept = adept;
	}

	/**
	 * @return 擅长
	 */
	@ApiModelProperty("擅长")
	public java.lang.String getAdept() {
		return this.adept;
	}

	/**
	 * @param content
	 *            简介
	 */
	@ApiModelProperty("简介")
	public void setContent(java.lang.String content) {
		this.content = content;
	}

	/**
	 * @return 简介
	 */
	@ApiModelProperty("简介")
	public java.lang.String getContent() {
		return this.content;
	}

	/**
	 * @param department
	 *            分类
	 */
	@ApiModelProperty("分类")
	public void setDepartment(java.lang.String department) {
		this.department = department;
	}

	/**
	 * @return 分类
	 */
	@ApiModelProperty("分类")
	public java.lang.String getDepartment() {
		return this.department;
	}
}
