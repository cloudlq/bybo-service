package com.mxg.bybo.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import basic.authority.model.Party;

import com.mxg.bybo.model.Region;
import com.mxg.common.mybatis.QueryCondition;

import java.util.List;

/**
 *  RegionService
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11 
 */
public interface RegionService {
	
	int insertRegion(Region region);
	
	int insertRegionBatch(List<Region> list);
	
	int updateRegionById(Region region);
	
	int deleteRegionById(Long regionId);
	
 	Region getRegionById(Long regionId);
 
 	List<Region> getRegions(Region region);

 	Page<Region> getRegionsForPage(List<QueryCondition> conditions , Pageable pageable);

	Page<Party> getPartysForPage(List<QueryCondition> conditions,
			Pageable pageable);
}
