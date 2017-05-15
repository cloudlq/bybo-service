package com.mxg.bybo.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
/**
 *  Region
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11
 */
public class Region  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 区域ID     
     */	
	private Long regionId;
	
	/**
     * 区域名称     
     */	
	private java.lang.String cnName;
	
	/**
     * 上级区域ID     
     */	
	private java.lang.String parentRegionId;
	
	/**
     *      
     */	
	private java.lang.String cnAddress;
	
	/**
     * 区域编码PATH     
     */	
	private java.lang.String phone;
	
	/**
     * 区域描述     
     */	
	private java.lang.String email;
	
	/**
     * logo图片     
     */	
	private java.lang.String logoImageUrl;
	
	/**
     * 网址     
     */	
	private java.lang.String url;
	
	/**
     *      
     */	
	private java.lang.String enName;
	
	/**
     *      
     */	
	private java.lang.String enAddress;
	
	 
	
	/**
	 * @param regionId 区域ID
	 */
	@ApiModelProperty("区域ID")
	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}
	
	/**
	 * @return 区域ID
	 */
	@ApiModelProperty("区域ID")
	public Long getRegionId() {
		return this.regionId;
	}
	
	/**
	 * @param cnName 区域名称
	 */
	@ApiModelProperty("区域名称")
	public void setCnName(java.lang.String cnName) {
		this.cnName = cnName;
	}
	
	/**
	 * @return 区域名称
	 */
	@ApiModelProperty("区域名称")
	public java.lang.String getCnName() {
		return this.cnName;
	}
	
	/**
	 * @param parentRegionId 上级区域ID
	 */
	@ApiModelProperty("上级区域ID")
	public void setParentRegionId(java.lang.String parentRegionId) {
		this.parentRegionId = parentRegionId;
	}
	
	/**
	 * @return 上级区域ID
	 */
	@ApiModelProperty("上级区域ID")
	public java.lang.String getParentRegionId() {
		return this.parentRegionId;
	}
	
	/**
	 * @param cnAddress 
	 */
	@ApiModelProperty("")
	public void setCnAddress(java.lang.String cnAddress) {
		this.cnAddress = cnAddress;
	}
	
	/**
	 * @return 
	 */
	@ApiModelProperty("")
	public java.lang.String getCnAddress() {
		return this.cnAddress;
	}
	
	/**
	 * @param phone 区域编码PATH
	 */
	@ApiModelProperty("区域编码PATH")
	public void setPhone(java.lang.String phone) {
		this.phone = phone;
	}
	
	/**
	 * @return 区域编码PATH
	 */
	@ApiModelProperty("区域编码PATH")
	public java.lang.String getPhone() {
		return this.phone;
	}
	
	/**
	 * @param email 区域描述
	 */
	@ApiModelProperty("区域描述")
	public void setEmail(java.lang.String email) {
		this.email = email;
	}
	
	/**
	 * @return 区域描述
	 */
	@ApiModelProperty("区域描述")
	public java.lang.String getEmail() {
		return this.email;
	}
	
	/**
	 * @param logoImageUrl logo图片
	 */
	@ApiModelProperty("logo图片")
	public void setLogoImageUrl(java.lang.String logoImageUrl) {
		this.logoImageUrl = logoImageUrl;
	}
	
	/**
	 * @return logo图片
	 */
	@ApiModelProperty("logo图片")
	public java.lang.String getLogoImageUrl() {
		return this.logoImageUrl;
	}
	
	/**
	 * @param url 网址
	 */
	@ApiModelProperty("网址")
	public void setUrl(java.lang.String url) {
		this.url = url;
	}
	
	/**
	 * @return 网址
	 */
	@ApiModelProperty("网址")
	public java.lang.String getUrl() {
		return this.url;
	}
	
	/**
	 * @param enName 
	 */
	@ApiModelProperty("")
	public void setEnName(java.lang.String enName) {
		this.enName = enName;
	}
	
	/**
	 * @return 
	 */
	@ApiModelProperty("")
	public java.lang.String getEnName() {
		return this.enName;
	}
	
	/**
	 * @param enAddress 
	 */
	@ApiModelProperty("")
	public void setEnAddress(java.lang.String enAddress) {
		this.enAddress = enAddress;
	}
	
	/**
	 * @return 
	 */
	@ApiModelProperty("")
	public java.lang.String getEnAddress() {
		return this.enAddress;
	}
}
