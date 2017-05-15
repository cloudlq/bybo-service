package com.mxg.bybo.service.impl;  
import com.mxg.bybo.service.KnowledgeCategoryService;
import com.mxg.bybo.dao.KnowledgeCategoryDao;
import com.mxg.bybo.model.KnowledgeCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 *  KnowledgeCategoryServiceImpl
 *
 * @version : Ver 1.0
 * @date	: 2017-3-29 
 */
@Service
public class KnowledgeCategoryServiceImpl  implements KnowledgeCategoryService { 
	
	@Autowired
	private KnowledgeCategoryDao knowledgeCategoryDao;
	
	public int insertKnowledgeCategory(KnowledgeCategory knowledgeCategory){
		return knowledgeCategoryDao.insertKnowledgeCategory(knowledgeCategory);
	}
	public int insertKnowledgeCategoryBatch(List<KnowledgeCategory> list){
		return knowledgeCategoryDao.insertKnowledgeCategoryBatch(list);
	}
	public int updateKnowledgeCategoryById(KnowledgeCategory knowledgeCategory){
		return knowledgeCategoryDao.updateKnowledgeCategoryById(knowledgeCategory);
	}
	public int deleteKnowledgeCategoryById(  Long knowledgeId  ){
		return knowledgeCategoryDao.deleteKnowledgeCategoryById(  knowledgeId  );
	}
	public List<KnowledgeCategory> getKnowledgeCategoryById(  Long knowledgeId  ){
		return knowledgeCategoryDao.getKnowledgeCategoryById(  knowledgeId  );
	}
 
 	public List<KnowledgeCategory> getKnowledgeCategorys(KnowledgeCategory knowledgeCategory){
		return knowledgeCategoryDao.getKnowledgeCategorys(knowledgeCategory);

 	}

 	public Page<KnowledgeCategory> getKnowledgeCategorysForPage(KnowledgeCategory knowledgeCategory, Pageable pageable){
		return knowledgeCategoryDao.getKnowledgeCategorysForPage(knowledgeCategory,pageable);

 	}
}
