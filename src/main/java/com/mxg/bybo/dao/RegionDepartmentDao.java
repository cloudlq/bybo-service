package com.mxg.bybo.dao;  
import com.mxg.bybo.model.RegionDepartment;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 *  RegionDepartmentDao 事业部关联科室
 *
 * @version : Ver 1.0
 * @date	: 2017-4-11
 */
@Repository
public interface RegionDepartmentDao {
	
	int insertRegionDepartment(RegionDepartment regionDepartment);
	
	int insertRegionDepartmentBatch(List<RegionDepartment> list);
	
	int updateRegionDepartmentById(RegionDepartment regionDepartment);
	
	int deleteRegionDepartmentById(@Param("regionId")  Long regionId  );
	
 	RegionDepartment getRegionDepartmentById(@Param("regionId")  Long regionId  );

 	List<RegionDepartment> getRegionDepartments(@Param("regionDepartment")  RegionDepartment regionDepartment);

 	Page<RegionDepartment> getRegionDepartmentsForPage(@Param("regionDepartment")  RegionDepartment regionDepartment, Pageable pageable);

}
