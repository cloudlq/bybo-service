package com.mxg.bybo.service.impl;  
import com.mxg.bybo.service.RegionDepartmentService;
import com.mxg.bybo.dao.RegionDepartmentDao;
import com.mxg.bybo.model.RegionDepartment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 *  RegionDepartmentServiceImpl
 *
 * @version : Ver 1.0
 * @date	: 2017-4-11 
 */
@Service
public class RegionDepartmentServiceImpl  implements RegionDepartmentService { 
	
	@Autowired
	private RegionDepartmentDao regionDepartmentDao;
	
	public int insertRegionDepartment(RegionDepartment regionDepartment){
		return regionDepartmentDao.insertRegionDepartment(regionDepartment);
	}
	public int insertRegionDepartmentBatch(List<RegionDepartment> list){
		return regionDepartmentDao.insertRegionDepartmentBatch(list);
	}
	public int updateRegionDepartmentById(RegionDepartment regionDepartment){
		return regionDepartmentDao.updateRegionDepartmentById(regionDepartment);
	}
	public int deleteRegionDepartmentById(  Long regionId  ){
		return regionDepartmentDao.deleteRegionDepartmentById(  regionId  );
	}
	public RegionDepartment getRegionDepartmentById(  Long regionId  ){
		return regionDepartmentDao.getRegionDepartmentById(  regionId  );
	}
 
 	public List<RegionDepartment> getRegionDepartments(RegionDepartment regionDepartment){
		return regionDepartmentDao.getRegionDepartments(regionDepartment);

 	}

 	public Page<RegionDepartment> getRegionDepartmentsForPage(RegionDepartment regionDepartment, Pageable pageable){
		return regionDepartmentDao.getRegionDepartmentsForPage(regionDepartment,pageable);

 	}
}
