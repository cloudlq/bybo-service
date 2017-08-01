package com.mxg.bybo.service.impl;  
import com.mxg.bybo.service.KnowledgeService;
import com.mxg.bybo.service.ToHtmlService;
import com.mxg.bybo.dao.KnowledgeDao;
import com.mxg.bybo.model.Knowledge;
import com.mxg.bybo.model.vo.KnowledgeVo;
import com.mxg.common.mybatis.QueryCondition;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  KnowledgeServiceImpl
 *
 * @version : Ver 1.0
 * @date	: 2017-3-29 
 */
@Service
public class KnowledgeServiceImpl  implements KnowledgeService { 
	
	@Autowired
	private KnowledgeDao knowledgeDao;
	
	@Autowired
	private ToHtmlService toHtmlService;
	
	public int insertKnowledge(Knowledge knowledge){
		return knowledgeDao.insertKnowledge(knowledge);
	}
	public int insertKnowledgeBatch(List<Knowledge> list){
		return knowledgeDao.insertKnowledgeBatch(list);
	}
	public int updateKnowledgeById(Knowledge knowledge){
		return knowledgeDao.updateKnowledgeById(knowledge);
	}
	public int deleteKnowledgeById(  Long id  ){
		return knowledgeDao.deleteKnowledgeById(  id  );
	}
	public Knowledge getKnowledgeById(  Long id  ){
		return knowledgeDao.getKnowledgeById(  id  );
	}
 
 	public List<Knowledge> getKnowledges(Knowledge knowledge){
		return knowledgeDao.getKnowledges(knowledge);

 	}

 	public Page<Knowledge> getKnowledgesForPage(Knowledge knowledge, Pageable pageable){
		return knowledgeDao.getKnowledgesForPage(knowledge,pageable);

 	}
	@Override
	public Page<Knowledge> getKnowledgesByConditions(
			List<QueryCondition> conditions, Pageable pageable) {
		return knowledgeDao.getKnowledgesByConditions(conditions,pageable);
	}
}
