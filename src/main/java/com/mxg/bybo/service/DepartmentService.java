package com.mxg.bybo.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mxg.bybo.model.Department;
import com.mxg.common.mybatis.QueryCondition;

import java.util.List;

/**
 *  DepartmentService
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11 
 */
public interface DepartmentService {
	
	int insertDepartment(Department department);
	
	int insertDepartmentBatch(List<Department> list);
	
	int updateDepartmentById(Department department);
	
	int deleteDepartmentById(Long id);
	
 	Department getDepartmentById(Long id);
 
 	List<Department> getDepartments(Department department);
 	
 	List<Department> getDepartmentsByCondition(List<QueryCondition> conditions);

 	Page<Department> getDepartmentsForPage(List<QueryCondition> conditions , Pageable pageable);
}
