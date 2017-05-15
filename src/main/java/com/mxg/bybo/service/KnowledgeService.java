package com.mxg.bybo.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mxg.bybo.model.Knowledge;
import com.mxg.common.mybatis.QueryCondition;

import java.util.List;

/**
 *  KnowledgeService
 *
 * @version : Ver 1.0
 * @date	: 2017-3-29 
 */
public interface KnowledgeService {
	
	int insertKnowledge(Knowledge knowledge);
	
	int insertKnowledgeBatch(List<Knowledge> list);
	
	int updateKnowledgeById(Knowledge knowledge);
	
	int deleteKnowledgeById(Long id);
	
 	Knowledge getKnowledgeById(Long id);
 
 	List<Knowledge> getKnowledges(Knowledge knowledge);

 	Page<Knowledge> getKnowledgesForPage(Knowledge knowledge, Pageable pageable);
 	
 	Page<Knowledge> getKnowledgesByConditions(List<QueryCondition> conditions , Pageable pageable);
}
