package com.mxg.bybo.service.impl;  
import com.mxg.bybo.service.SimilarKnowledgeService;
import com.mxg.bybo.dao.SimilarKnowledgeDao;
import com.mxg.bybo.model.SimilarKnowledge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 *  SimilarKnowledgeServiceImpl
 *
 * @version : Ver 1.0
 * @date	: 2017-3-29 
 */
@Service
public class SimilarKnowledgeServiceImpl  implements SimilarKnowledgeService { 
	
	@Autowired
	private SimilarKnowledgeDao similarKnowledgeDao;
	
	public int insertSimilarKnowledge(SimilarKnowledge similarKnowledge){
		return similarKnowledgeDao.insertSimilarKnowledge(similarKnowledge);
	}
	public int insertSimilarKnowledgeBatch(List<SimilarKnowledge> list){
		return similarKnowledgeDao.insertSimilarKnowledgeBatch(list);
	}
	public int updateSimilarKnowledgeById(SimilarKnowledge similarKnowledge){
		return similarKnowledgeDao.updateSimilarKnowledgeById(similarKnowledge);
	}
	public int deleteSimilarKnowledgeById(  Long knowledgeId  ){
		return similarKnowledgeDao.deleteSimilarKnowledgeById(  knowledgeId  );
	}
	public List<SimilarKnowledge> getSimilarKnowledgeById(  Long knowledgeId  ){
		return similarKnowledgeDao.getSimilarKnowledgeById(  knowledgeId  );
	}
 
 	public List<SimilarKnowledge> getSimilarKnowledges(SimilarKnowledge similarKnowledge){
		return similarKnowledgeDao.getSimilarKnowledges(similarKnowledge);

 	}

 	public Page<SimilarKnowledge> getSimilarKnowledgesForPage(SimilarKnowledge similarKnowledge, Pageable pageable){
		return similarKnowledgeDao.getSimilarKnowledgesForPage(similarKnowledge,pageable);

 	}
}
