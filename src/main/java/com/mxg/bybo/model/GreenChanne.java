package com.mxg.bybo.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
/**
 *  GreenChanne
 *
 * @version : Ver 1.0
 * @date	: 2017-4-4
 */
public class GreenChanne  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     *      
     */	
	private Long id;
	
	/**
     * 姓名     
     */	
	private java.lang.String name;
	
	/**
     * 性别     
     */	
	private java.lang.String sex;
	
	/**
     * 手机号     
     */	
	private java.lang.String phone;
	
	/**
     * 地址     
     */	
	private java.lang.String address;
	
	/**
     * 描述     
     */	
	private java.lang.String content;
	
	/**
     *      
     */	
	private java.lang.String picture;
	
	/**
     * 渠道信息     
     */	
	private Long regionId;
	
	/**
     * 创建时间     
     */	
	private java.util.Date createTime;
	
	/**
     * 处理状态 0：未处理 1：已处理     
     */	
	private java.lang.Integer status;
	
	 
	
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
	 * @param name 姓名
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
	 * @param sex 性别
	 */
	@ApiModelProperty("性别")
	public void setSex(java.lang.String sex) {
		this.sex = sex;
	}
	
	/**
	 * @return 性别
	 */
	@ApiModelProperty("性别")
	public java.lang.String getSex() {
		return this.sex;
	}
	
	/**
	 * @param phone 手机号
	 */
	@ApiModelProperty("手机号")
	public void setPhone(java.lang.String phone) {
		this.phone = phone;
	}
	
	/**
	 * @return 手机号
	 */
	@ApiModelProperty("手机号")
	public java.lang.String getPhone() {
		return this.phone;
	}
	
	/**
	 * @param address 地址
	 */
	@ApiModelProperty("地址")
	public void setAddress(java.lang.String address) {
		this.address = address;
	}
	
	/**
	 * @return 地址
	 */
	@ApiModelProperty("地址")
	public java.lang.String getAddress() {
		return this.address;
	}
	
	/**
	 * @param content 描述
	 */
	@ApiModelProperty("描述")
	public void setContent(java.lang.String content) {
		this.content = content;
	}
	
	/**
	 * @return 描述
	 */
	@ApiModelProperty("描述")
	public java.lang.String getContent() {
		return this.content;
	}
	
	/**
	 * @param picture 
	 */
	@ApiModelProperty("")
	public void setPicture(java.lang.String picture) {
		this.picture = picture;
	}
	
	/**
	 * @return 
	 */
	@ApiModelProperty("")
	public java.lang.String getPicture() {
		return this.picture;
	}
	
	/**
	 * @param regionId 渠道信息
	 */
	@ApiModelProperty("渠道信息")
	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}
	
	/**
	 * @return 渠道信息
	 */
	@ApiModelProperty("渠道信息")
	public Long getRegionId() {
		return this.regionId;
	}
	
	/**
	 * @param createTime 创建时间
	 */
	@ApiModelProperty("创建时间")
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * @return 创建时间
	 */
	@ApiModelProperty("创建时间")
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	 
	
	/**
	 * @param status 处理状态 0：未处理 1：已处理
	 */
	@ApiModelProperty("处理状态 0：未处理 1：已处理")
	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}
	
	/**
	 * @return 处理状态 0：未处理 1：已处理
	 */
	@ApiModelProperty("处理状态 0：未处理 1：已处理")
	public java.lang.Integer getStatus() {
		return this.status;
	}
}
