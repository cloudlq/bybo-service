package com.mxg.bybo.dao;  
import com.mxg.bybo.model.Doctor;
import com.mxg.common.mybatis.QueryCondition;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  DoctorDao 医生
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11
 */
@Repository
public interface DoctorDao {
	
	int insertDoctor(Doctor doctor);
	
	int insertDoctorBatch(List<Doctor> list);
	
	int updateDoctorById(Doctor doctor);
	
	int deleteDoctorById(@Param("id")  Long id  );
	
 	Doctor getDoctorById(@Param("id")  Long id  );

 	List<Doctor> getDoctors(@Param("doctor")  Doctor doctor);

 	Page<Doctor> getDoctorsForPage(@Param("conditions") List<QueryCondition> conditions, Pageable pageable);

 	List<Doctor> getDoctorsByDepartmentId(@Param("regionId") long regionId,@Param("departmentId") long departmentId);

 	List<Doctor> getAllDoctorsByDepartmentId(@Param("departmentId") long departmentId);
 	
}
