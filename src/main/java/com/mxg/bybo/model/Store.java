package com.mxg.bybo.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
/**
 *  Store
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-12
 */
public class Store  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 门店id     
     */	
	private Long id;
	
	/**
     * 名称     
     */	
	private java.lang.String name;
	
	/**
     * 地址     
     */	
	private java.lang.String address;
	
	/**
     * 座机号码     
     */	
	private java.lang.String phone;
	
	/**
     * 联系号码     
     */	
	private java.lang.String telphone;
	
	/**
     * 联系人     
     */	
	private java.lang.String userName;
	
	/**
     * 所属机构     
     */	
	private Long regionId;
	
	/**
     * 中英文标示 cn：中文 en：英文     
     */	
	private java.lang.String language;
	
	/**
     * 事业部名称     
     */	
	private java.lang.String regionName;
	
	/**
     * 图片     
     */	
	private java.lang.String picture;
	
	
	
	public java.lang.String getPicture() {
		return picture;
	}

	public void setPicture(java.lang.String picture) {
		this.picture = picture;
	}

	/**
	 * @param id 门店id
	 */
	@ApiModelProperty("门店id")
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return 门店id
	 */
	@ApiModelProperty("门店id")
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
	 * @param phone 座机号码
	 */
	@ApiModelProperty("座机号码")
	public void setPhone(java.lang.String phone) {
		this.phone = phone;
	}
	
	/**
	 * @return 座机号码
	 */
	@ApiModelProperty("座机号码")
	public java.lang.String getPhone() {
		return this.phone;
	}
	
	/**
	 * @param telphone 联系号码
	 */
	@ApiModelProperty("联系号码")
	public void setTelphone(java.lang.String telphone) {
		this.telphone = telphone;
	}
	
	/**
	 * @return 联系号码
	 */
	@ApiModelProperty("联系号码")
	public java.lang.String getTelphone() {
		return this.telphone;
	}
	
	/**
	 * @param userName 联系人
	 */
	@ApiModelProperty("联系人")
	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}
	
	/**
	 * @return 联系人
	 */
	@ApiModelProperty("联系人")
	public java.lang.String getUserName() {
		return this.userName;
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
	 * @param regionName 事业部名称
	 */
	@ApiModelProperty("事业部名称")
	public void setRegionName(java.lang.String regionName) {
		this.regionName = regionName;
	}
	
	/**
	 * @return 事业部名称
	 */
	@ApiModelProperty("事业部名称")
	public java.lang.String getRegionName() {
		return this.regionName;
	}
}
