package com.mxg.bybo.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
/**
 *  Rollpic
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11
 */
public class Rollpic  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 编号     
     */	
	private Long id;
	
	/**
     * 名称     
     */	
	private java.lang.String name;
	
	/**
     *      
     */	
	private java.lang.String imagPath;
	
	/**
     * 优先级     
     */	
	private java.lang.Integer priority;
	
	/**
     * 0：待上线 1：上线 2：下线     
     */	
	private java.lang.Integer status;
	
	/**
     * 添加时间     
     */	
	private java.lang.String addTime;
	
	/**
     * 链接地址     
     */	
	private java.lang.String url;
	
	/**
     * 机构id     
     */	
	private java.lang.Long regionId;
	
	/**
     * 中英文标示  cn：中文  en：英文     
     */	
	private java.lang.String language;
	
	 
	
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
	 * @param imagPath 
	 */
	@ApiModelProperty("")
	public void setImagPath(java.lang.String imagPath) {
		this.imagPath = imagPath;
	}
	
	/**
	 * @return 
	 */
	@ApiModelProperty("")
	public java.lang.String getImagPath() {
		return this.imagPath;
	}
	
	/**
	 * @param priority 优先级
	 */
	@ApiModelProperty("优先级")
	public void setPriority(java.lang.Integer priority) {
		this.priority = priority;
	}
	
	/**
	 * @return 优先级
	 */
	@ApiModelProperty("优先级")
	public java.lang.Integer getPriority() {
		return this.priority;
	}
	
	/**
	 * @param status 0：待上线 1：上线 2：下线
	 */
	@ApiModelProperty("0：待上线 1：上线 2：下线")
	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}
	
	/**
	 * @return 0：待上线 1：上线 2：下线
	 */
	@ApiModelProperty("0：待上线 1：上线 2：下线")
	public java.lang.Integer getStatus() {
		return this.status;
	}
	
	/**
	 * @param addTime 添加时间
	 */
	@ApiModelProperty("添加时间")
	public void setAddTime(java.lang.String addTime) {
		this.addTime = addTime;
	}
	
	/**
	 * @return 添加时间
	 */
	@ApiModelProperty("添加时间")
	public java.lang.String getAddTime() {
		return this.addTime;
	}
	
	/**
	 * @param url 链接地址
	 */
	@ApiModelProperty("链接地址")
	public void setUrl(java.lang.String url) {
		this.url = url;
	}
	
	/**
	 * @return 链接地址
	 */
	@ApiModelProperty("链接地址")
	public java.lang.String getUrl() {
		return this.url;
	}
	
	/**
	 * @param regionId 机构id
	 */
	@ApiModelProperty("机构id")
	public void setRegionId(java.lang.Long regionId) {
		this.regionId = regionId;
	}
	
	/**
	 * @return 机构id
	 */
	@ApiModelProperty("机构id")
	public java.lang.Long getRegionId() {
		return this.regionId;
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
}
