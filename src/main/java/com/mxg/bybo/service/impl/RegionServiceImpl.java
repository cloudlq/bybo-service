package com.mxg.bybo.service.impl;  
import basic.authority.model.Party;

import com.mxg.bybo.service.RegionService;
import com.mxg.bybo.dao.RegionDao;
import com.mxg.bybo.model.Region;
import com.mxg.common.mybatis.QueryCondition;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  RegionServiceImpl
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11 
 */
@Service
public class RegionServiceImpl  implements RegionService { 
	
	@Autowired
	private RegionDao regionDao;
	
	public int insertRegion(Region region){
		return regionDao.insertRegion(region);
	}
	public int insertRegionBatch(List<Region> list){
		return regionDao.insertRegionBatch(list);
	}
	public int updateRegionById(Region region){
		return regionDao.updateRegionById(region);
	}
	public int deleteRegionById(  Long regionId  ){
		return regionDao.deleteRegionById(  regionId  );
	}
	public Region getRegionById(  Long regionId  ){
		return regionDao.getRegionById(  regionId  );
	}
 
 	public List<Region> getRegions(Region region){
		return regionDao.getRegions(region);

 	}

 	public Page<Region> getRegionsForPage(List<QueryCondition> conditions , Pageable pageable){
		return regionDao.getRegionsForPage(conditions,pageable);

 	}
	@Override
	public Page<Party> getPartysForPage(List<QueryCondition> conditions,
			Pageable pageable) {
		// TODO Auto-generated method stub
		return regionDao.getPartysForPage(conditions,
				 pageable);
	}
}
