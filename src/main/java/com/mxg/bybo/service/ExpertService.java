package com.mxg.bybo.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mxg.bybo.model.Expert;
import com.mxg.common.mybatis.QueryCondition;

import java.util.List;

/**
 *  ExpertService
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11 
 */
public interface ExpertService {
	
	int insertExpert(Expert expert);
	
	int insertExpertBatch(List<Expert> list);
	
	int updateExpertById(Expert expert);
	
	int deleteExpertById(Long id);
	
 	Expert getExpertById(Long id);
 
 	List<Expert> getExperts(Expert expert);

 	Page<Expert> getExpertsForPage(List<QueryCondition> conditions , Pageable pageable);
}
