package com.mxg.bybo.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
/**
 *  DoctorDepartmentRel
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11
 */
public class DoctorDepartmentRel  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 医生编号     
     */	
	private Long doctorId;
	
	/**
     * 科室编码     
     */	
	private Long departmentId;
	
	 
	
	/**
	 * @param doctorId 医生编号
	 */
	@ApiModelProperty("医生编号")
	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}
	
	/**
	 * @return 医生编号
	 */
	@ApiModelProperty("医生编号")
	public Long getDoctorId() {
		return this.doctorId;
	}
	
	/**
	 * @param departmentId 科室编码
	 */
	@ApiModelProperty("科室编码")
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	
	/**
	 * @return 科室编码
	 */
	@ApiModelProperty("科室编码")
	public Long getDepartmentId() {
		return this.departmentId;
	}
}
