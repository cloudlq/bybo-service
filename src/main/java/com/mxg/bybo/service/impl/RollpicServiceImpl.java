package com.mxg.bybo.service.impl;  
import com.mxg.bybo.service.RollpicService;
import com.mxg.bybo.dao.RollpicDao;
import com.mxg.bybo.model.Rollpic;
import com.mxg.common.mybatis.QueryCondition;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  RollpicServiceImpl
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11 
 */
@Service
public class RollpicServiceImpl  implements RollpicService { 
	
	@Autowired
	private RollpicDao rollpicDao;
	
	public int insertRollpic(Rollpic rollpic){
		return rollpicDao.insertRollpic(rollpic);
	}
	public int insertRollpicBatch(List<Rollpic> list){
		return rollpicDao.insertRollpicBatch(list);
	}
	public int updateRollpicById(Rollpic rollpic){
		return rollpicDao.updateRollpicById(rollpic);
	}
	public int deleteRollpicById(  Long id  ){
		return rollpicDao.deleteRollpicById(  id  );
	}
	public Rollpic getRollpicById(  Long id  ){
		return rollpicDao.getRollpicById(  id  );
	}
 
 	public List<Rollpic> getRollpics(Rollpic rollpic){
		return rollpicDao.getRollpics(rollpic);

 	}

 	public Page<Rollpic> getRollpicsForPage(List<QueryCondition> conditions, Pageable pageable){
		return rollpicDao.getRollpicsForPage(conditions,pageable);

 	}
}
