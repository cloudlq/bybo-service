package com.mxg.bybo.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mxg.bybo.model.ExpertClassifyRel;

import java.util.List;

/**
 *  ExpertClassifyRelService
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11 
 */
public interface ExpertClassifyRelService {
	
	int insertExpertClassifyRel(ExpertClassifyRel expertClassifyRel);
	
	int insertExpertClassifyRelBatch(List<ExpertClassifyRel> list);
	
	int updateExpertClassifyRelById(ExpertClassifyRel expertClassifyRel);
	
	int deleteExpertClassifyRelById(Long expertId);
	
 	ExpertClassifyRel getExpertClassifyRelById(Long expertId);
 
 	List<ExpertClassifyRel> getExpertClassifyRels(ExpertClassifyRel expertClassifyRel);

 	Page<ExpertClassifyRel> getExpertClassifyRelsForPage(ExpertClassifyRel expertClassifyRel, Pageable pageable);

	void deleteRel(long expertId);

	List<Long> getDepartmentIds(Long id);
}
