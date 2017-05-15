package com.mxg.bybo.service.impl;  
import com.mxg.bybo.service.DoctorDepartmentRelService;
import com.mxg.bybo.dao.DoctorDepartmentRelDao;
import com.mxg.bybo.model.DoctorDepartmentRel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  DoctorDepartmentRelServiceImpl
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11 
 */
@Service
public class DoctorDepartmentRelServiceImpl  implements DoctorDepartmentRelService { 
	
	@Autowired
	private DoctorDepartmentRelDao doctorDepartmentRelDao;
	
	public int insertDoctorDepartmentRel(DoctorDepartmentRel doctorDepartmentRel){
		return doctorDepartmentRelDao.insertDoctorDepartmentRel(doctorDepartmentRel);
	}
	public int insertDoctorDepartmentRelBatch(List<DoctorDepartmentRel> list){
		return doctorDepartmentRelDao.insertDoctorDepartmentRelBatch(list);
	}
	
 
 	public List<DoctorDepartmentRel> getDoctorDepartmentRels(DoctorDepartmentRel doctorDepartmentRel){
		return doctorDepartmentRelDao.getDoctorDepartmentRels(doctorDepartmentRel);

 	}

 	public Page<DoctorDepartmentRel> getDoctorDepartmentRelsForPage(DoctorDepartmentRel doctorDepartmentRel, Pageable pageable){
		return doctorDepartmentRelDao.getDoctorDepartmentRelsForPage(doctorDepartmentRel,pageable);

 	}
	@Override
	public int deleteRel(long doctorId) {
		return doctorDepartmentRelDao.deleteRel(doctorId);
	}
	@Override
	public List<Long> getDepartmentIds(long doctorId) {
		return doctorDepartmentRelDao.getDepartmentIds(doctorId);
	}
}
