package com.mxg.bybo.dao;  
import basic.authority.model.Party;

import com.mxg.bybo.model.Region;
import com.mxg.common.mybatis.QueryCondition;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  RegionDao 区域
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11
 */
@Repository
public interface RegionDao {
	
	int insertRegion(Region region);
	
	int insertRegionBatch(List<Region> list);
	
	int updateRegionById(Region region);
	
	int deleteRegionById(@Param("regionId")  Long regionId  );
	
 	Region getRegionById(@Param("regionId")  Long regionId  );

 	List<Region> getRegions(@Param("region")  Region region);

 	Page<Region> getRegionsForPage(@Param("conditions")  List<QueryCondition> conditions, Pageable pageable);

	Page<Party> getPartysForPage(@Param("conditions") List<QueryCondition> conditions,
			Pageable pageable);

}
