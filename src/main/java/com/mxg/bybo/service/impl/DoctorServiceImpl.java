package com.mxg.bybo.service.impl;  
import com.mxg.bybo.service.DoctorService;
import com.mxg.bybo.service.ToHtmlService;
import com.mxg.bybo.dao.DoctorDao;
import com.mxg.bybo.model.Doctor;
import com.mxg.common.mybatis.QueryCondition;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  DoctorServiceImpl
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11 
 */
@Service
public class DoctorServiceImpl  implements DoctorService { 
	
	
	@Autowired
	private DoctorDao doctorDao;
	
	@Autowired
	private ToHtmlService toHtmlService;
	
	public int insertDoctor(Doctor doctor){
		
		toHtmlService.toDoctorDetail(doctor);
		
		return doctorDao.insertDoctor(doctor);
	}
	public int insertDoctorBatch(List<Doctor> list){
		return doctorDao.insertDoctorBatch(list);
	}
	public int updateDoctorById(Doctor doctor){
		doctorDao.updateDoctorById(doctor);
		Doctor d = getDoctorById(doctor.getId());
		toHtmlService.toDoctorDetail(d);
		return 1;
	}
	public int deleteDoctorById(  Long id  ){
		return doctorDao.deleteDoctorById(  id  );
	}
	public Doctor getDoctorById(  Long id  ){
		return doctorDao.getDoctorById(  id  );
	}
 
 	public List<Doctor> getDoctors(Doctor doctor){
		return doctorDao.getDoctors(doctor);

 	}

 	public Page<Doctor> getDoctorsForPage(List<QueryCondition> conditions, Pageable pageable){
		return doctorDao.getDoctorsForPage(conditions,pageable);

 	}
}
