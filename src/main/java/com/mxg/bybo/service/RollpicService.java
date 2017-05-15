package com.mxg.bybo.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mxg.bybo.model.Rollpic;
import com.mxg.common.mybatis.QueryCondition;

import java.util.List;

/**
 *  RollpicService
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11 
 */
public interface RollpicService {
	
	int insertRollpic(Rollpic rollpic);
	
	int insertRollpicBatch(List<Rollpic> list);
	
	int updateRollpicById(Rollpic rollpic);
	
	int deleteRollpicById(Long id);
	
 	Rollpic getRollpicById(Long id);
 
 	List<Rollpic> getRollpics(Rollpic rollpic);

 	Page<Rollpic> getRollpicsForPage(List<QueryCondition> conditions, Pageable pageable);
}
