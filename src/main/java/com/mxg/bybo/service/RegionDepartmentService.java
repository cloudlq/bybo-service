package com.mxg.bybo.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.mxg.bybo.model.RegionDepartment;
import java.util.List;

/**
 *  RegionDepartmentService
 *
 * @version : Ver 1.0
 * @date	: 2017-4-11 
 */
public interface RegionDepartmentService {
	
	int insertRegionDepartment(RegionDepartment regionDepartment);
	
	int insertRegionDepartmentBatch(List<RegionDepartment> list);
	
	int updateRegionDepartmentById(RegionDepartment regionDepartment);
	
	int deleteRegionDepartmentById(Long regionId);
	
 	RegionDepartment getRegionDepartmentById(Long regionId);
 
 	List<RegionDepartment> getRegionDepartments(RegionDepartment regionDepartment);

 	Page<RegionDepartment> getRegionDepartmentsForPage(RegionDepartment regionDepartment, Pageable pageable);
}
