package com.mxg.bybo.service.impl;  
import com.mxg.bybo.service.ExpertService;
import com.mxg.bybo.service.ToHtmlService;
import com.mxg.bybo.dao.ExpertDao;
import com.mxg.bybo.model.Doctor;
import com.mxg.bybo.model.Expert;
import com.mxg.common.mybatis.QueryCondition;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  ExpertServiceImpl
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11 
 */
@Service
public class ExpertServiceImpl  implements ExpertService { 
	
	@Autowired
	private ExpertDao expertDao;
	
	@Autowired
	private ToHtmlService toHtmlService;
	
	public int insertExpert(Expert expert){
		toHtmlService.toExpertDetail(expert);
		return expertDao.insertExpert(expert);
	}
	public int insertExpertBatch(List<Expert> list){
		return expertDao.insertExpertBatch(list);
	}
	public int updateExpertById(Expert expert){
		expertDao.updateExpertById(expert);
		Expert d = getExpertById(expert.getId());
		toHtmlService.toExpertDetail(d);
		return 1;
	}
	public int deleteExpertById(  Long id  ){
		return expertDao.deleteExpertById(  id  );
	}
	public Expert getExpertById(  Long id  ){
		return expertDao.getExpertById(  id  );
	}
 
 	public List<Expert> getExperts(Expert expert){
		return expertDao.getExperts(expert);

 	}

 	public Page<Expert> getExpertsForPage(List<QueryCondition> conditions, Pageable pageable){
		return expertDao.getExpertsForPage(conditions,pageable);

 	}
}
