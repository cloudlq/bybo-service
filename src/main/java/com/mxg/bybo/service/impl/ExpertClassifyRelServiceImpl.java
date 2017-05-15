package com.mxg.bybo.service.impl;  
import com.mxg.bybo.service.ExpertClassifyRelService;
import com.mxg.bybo.dao.ExpertClassifyRelDao;
import com.mxg.bybo.model.ExpertClassifyRel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  ExpertClassifyRelServiceImpl
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11 
 */
@Service
public class ExpertClassifyRelServiceImpl  implements ExpertClassifyRelService { 
	
	@Autowired
	private ExpertClassifyRelDao expertClassifyRelDao;
	
	public int insertExpertClassifyRel(ExpertClassifyRel expertClassifyRel){
		return expertClassifyRelDao.insertExpertClassifyRel(expertClassifyRel);
	}
	public int insertExpertClassifyRelBatch(List<ExpertClassifyRel> list){
		return expertClassifyRelDao.insertExpertClassifyRelBatch(list);
	}
	public int updateExpertClassifyRelById(ExpertClassifyRel expertClassifyRel){
		return expertClassifyRelDao.updateExpertClassifyRelById(expertClassifyRel);
	}
	public int deleteExpertClassifyRelById(  Long expertId  ){
		return expertClassifyRelDao.deleteExpertClassifyRelById(  expertId  );
	}
	public ExpertClassifyRel getExpertClassifyRelById(  Long expertId  ){
		return expertClassifyRelDao.getExpertClassifyRelById(  expertId  );
	}
 
 	public List<ExpertClassifyRel> getExpertClassifyRels(ExpertClassifyRel expertClassifyRel){
		return expertClassifyRelDao.getExpertClassifyRels(expertClassifyRel);

 	}

 	public Page<ExpertClassifyRel> getExpertClassifyRelsForPage(ExpertClassifyRel expertClassifyRel, Pageable pageable){
		return expertClassifyRelDao.getExpertClassifyRelsForPage(expertClassifyRel,pageable);

 	}
	@Override
	public void deleteRel(long expertId) {
		 expertClassifyRelDao.deleteRel(expertId);
		
	}
	@Override
	public List<Long> getDepartmentIds(Long id) {
		// TODO Auto-generated method stub
		return  expertClassifyRelDao.getDepartmentIds(id);
	}
}
