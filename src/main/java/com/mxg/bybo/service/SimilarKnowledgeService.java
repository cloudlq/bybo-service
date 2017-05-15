package com.mxg.bybo.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.mxg.bybo.model.SimilarKnowledge;
import java.util.List;

/**
 *  SimilarKnowledgeService
 *
 * @version : Ver 1.0
 * @date	: 2017-3-29 
 */
public interface SimilarKnowledgeService {
	
	int insertSimilarKnowledge(SimilarKnowledge similarKnowledge);
	
	int insertSimilarKnowledgeBatch(List<SimilarKnowledge> list);
	
	int updateSimilarKnowledgeById(SimilarKnowledge similarKnowledge);
	
	int deleteSimilarKnowledgeById(Long knowledgeId);
	
 	List<SimilarKnowledge> getSimilarKnowledgeById(Long knowledgeId);
 
 	List<SimilarKnowledge> getSimilarKnowledges(SimilarKnowledge similarKnowledge);

 	Page<SimilarKnowledge> getSimilarKnowledgesForPage(SimilarKnowledge similarKnowledge, Pageable pageable);
}
