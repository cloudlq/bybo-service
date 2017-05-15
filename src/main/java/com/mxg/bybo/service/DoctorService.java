package com.mxg.bybo.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mxg.bybo.model.Doctor;
import com.mxg.common.mybatis.QueryCondition;

import java.util.List;

/**
 *  DoctorService
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11 
 */
public interface DoctorService {
	
	int insertDoctor(Doctor doctor);
	
	int insertDoctorBatch(List<Doctor> list);
	
	int updateDoctorById(Doctor doctor);
	
	int deleteDoctorById(Long id);
	
 	Doctor getDoctorById(Long id);
 
 	List<Doctor> getDoctors(Doctor doctor);

 	Page<Doctor> getDoctorsForPage(List<QueryCondition> conditions, Pageable pageable);
}
