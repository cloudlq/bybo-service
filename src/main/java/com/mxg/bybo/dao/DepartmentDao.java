package com.mxg.bybo.dao;  
import com.mxg.bybo.model.Department;
import com.mxg.common.mybatis.QueryCondition;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  DepartmentDao 科室
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11
 */
@Repository
public interface DepartmentDao {
	
	int insertDepartment(Department department);
	
	int insertDepartmentBatch(List<Department> list);
	
	int updateDepartmentById(Department department);
	
	int deleteDepartmentById(@Param("id")  Long id  );
	
 	Department getDepartmentById(@Param("id")  Long id  );

 	List<Department> getDepartments(@Param("department")  Department department);

 	Page<Department> getDepartmentsForPage(@Param("conditions") List<QueryCondition> conditions, Pageable pageable);

	List<Department> getDepartmentsByCondition(@Param("conditions") List<QueryCondition> conditions);

}
