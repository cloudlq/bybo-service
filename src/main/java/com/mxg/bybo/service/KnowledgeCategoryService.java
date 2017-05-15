package com.mxg.bybo.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.mxg.bybo.model.KnowledgeCategory;
import java.util.List;

/**
 *  KnowledgeCategoryService
 *
 * @version : Ver 1.0
 * @date	: 2017-3-29 
 */
public interface KnowledgeCategoryService {
	
	int insertKnowledgeCategory(KnowledgeCategory knowledgeCategory);
	
	int insertKnowledgeCategoryBatch(List<KnowledgeCategory> list);
	
	int updateKnowledgeCategoryById(KnowledgeCategory knowledgeCategory);
	
	int deleteKnowledgeCategoryById(Long knowledgeId);
	
	List<KnowledgeCategory> getKnowledgeCategoryById(Long knowledgeId);
 
 	List<KnowledgeCategory> getKnowledgeCategorys(KnowledgeCategory knowledgeCategory);

 	Page<KnowledgeCategory> getKnowledgeCategorysForPage(KnowledgeCategory knowledgeCategory, Pageable pageable);
}
