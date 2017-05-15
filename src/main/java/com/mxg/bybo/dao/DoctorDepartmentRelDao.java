package com.mxg.bybo.dao;  
import com.mxg.bybo.model.DoctorDepartmentRel;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  DoctorDepartmentRelDao 医生科室关联
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11
 */
@Repository
public interface DoctorDepartmentRelDao {
	
	int insertDoctorDepartmentRel(DoctorDepartmentRel doctorDepartmentRel);
	
	int insertDoctorDepartmentRelBatch(List<DoctorDepartmentRel> list);
	
 	List<DoctorDepartmentRel> getDoctorDepartmentRels(@Param("doctorDepartmentRel")  DoctorDepartmentRel doctorDepartmentRel);

 	Page<DoctorDepartmentRel> getDoctorDepartmentRelsForPage(@Param("doctorDepartmentRel")  DoctorDepartmentRel doctorDepartmentRel, Pageable pageable);

	int deleteRel(@Param("doctorId") long doctorId);

	List<Long> getDepartmentIds(@Param("doctorId")long doctorId);

}
