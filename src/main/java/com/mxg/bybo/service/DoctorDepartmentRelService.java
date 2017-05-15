package com.mxg.bybo.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.mxg.bybo.model.DoctorDepartmentRel;
import java.util.List;

/**
 *  DoctorDepartmentRelService
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11 
 */
public interface DoctorDepartmentRelService {
	
	int insertDoctorDepartmentRel(DoctorDepartmentRel doctorDepartmentRel);
	
	int insertDoctorDepartmentRelBatch(List<DoctorDepartmentRel> list);
	
	int deleteRel(long doctorId);
	
	List<Long> getDepartmentIds(long doctorId); 
	
 	List<DoctorDepartmentRel> getDoctorDepartmentRels(DoctorDepartmentRel doctorDepartmentRel);

 	Page<DoctorDepartmentRel> getDoctorDepartmentRelsForPage(DoctorDepartmentRel doctorDepartmentRel, Pageable pageable);
}
