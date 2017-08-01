package com.mxg.bybo.service.impl;  
import com.mxg.bybo.service.DepartmentService;
import com.mxg.bybo.service.ToHtmlService;
import com.mxg.bybo.dao.DepartmentDao;
import com.mxg.bybo.model.Department;
import com.mxg.common.mybatis.QueryCondition;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  DepartmentServiceImpl
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11 
 */
@Service
public class DepartmentServiceImpl  implements DepartmentService { 
	
	@Autowired
	private DepartmentDao departmentDao;
	
	@Autowired
	private ToHtmlService toHtmlService;
	
	public int insertDepartment(Department department){
		toHtmlService.toSubjectDetail(department);
		return departmentDao.insertDepartment(department);
	}
	public int insertDepartmentBatch(List<Department> list){
		return departmentDao.insertDepartmentBatch(list);
	}
	public int updateDepartmentById(Department department){
		departmentDao.updateDepartmentById(department);
		toHtmlService.toSubjectDetail(getDepartmentById(department.getId()));
		return 1;
	}
	public int deleteDepartmentById(  Long id  ){
		return departmentDao.deleteDepartmentById(  id  );
	}
	public Department getDepartmentById(  Long id  ){
		return departmentDao.getDepartmentById(  id  );
	}
 
 	public List<Department> getDepartments(Department department){
		return departmentDao.getDepartments(department);

 	}

 	public Page<Department> getDepartmentsForPage(List<QueryCondition> conditions , Pageable pageable){
		return departmentDao.getDepartmentsForPage(conditions,pageable);

 	}
	@Override
	public List<Department> getDepartmentsByCondition(
			List<QueryCondition> conditions) {
		return departmentDao.getDepartmentsByCondition(conditions);
	}
}
