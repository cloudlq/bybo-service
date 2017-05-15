package com.mxg.bybo.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**
 * RegionDepartment
 *
 * @version : Ver 1.0
 * @date : 2017-4-20
 */
public class RegionDepartment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 事业id
	 */
	private Long regionId;

	/**
	 * 科室id
	 */
	private java.lang.String departmentId;

	/**
	 * 医院环境地址
	 */
	private java.lang.String pictures;

	/**
	 * 环境描述
	 */
	private java.lang.String content;

	/**
	 * @param regionId
	 *            事业id
	 */
	@ApiModelProperty("事业id")
	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	/**
	 * @return 事业id
	 */
	@ApiModelProperty("事业id")
	public Long getRegionId() {
		return this.regionId;
	}

	/**
	 * @param departmentId
	 *            科室id
	 */
	@ApiModelProperty("科室id")
	public void setDepartmentId(java.lang.String departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * @return 科室id
	 */
	@ApiModelProperty("科室id")
	public java.lang.String getDepartmentId() {
		return this.departmentId;
	}

	/**
	 * @param pictures
	 *            医院环境地址
	 */
	@ApiModelProperty("医院环境地址")
	public void setPictures(java.lang.String pictures) {
		this.pictures = pictures;
	}

	/**
	 * @return 医院环境地址
	 */
	@ApiModelProperty("医院环境地址")
	public java.lang.String getPictures() {
		return this.pictures;
	}

	public java.lang.String getContent() {
		return content;
	}

	public void setContent(java.lang.String content) {
		this.content = content;
	}

}
