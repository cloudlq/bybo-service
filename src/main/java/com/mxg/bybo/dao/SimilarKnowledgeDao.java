package com.mxg.bybo.dao;  
import com.mxg.bybo.model.SimilarKnowledge;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 *  SimilarKnowledgeDao 相似文章关联
 *
 * @version : Ver 1.0
 * @date	: 2017-3-29
 */
@Repository
public interface SimilarKnowledgeDao {
	
	int insertSimilarKnowledge(SimilarKnowledge similarKnowledge);
	
	int insertSimilarKnowledgeBatch(List<SimilarKnowledge> list);
	
	int updateSimilarKnowledgeById(SimilarKnowledge similarKnowledge);
	
	int deleteSimilarKnowledgeById(@Param("knowledgeId")  Long knowledgeId  );
	
	List<SimilarKnowledge> getSimilarKnowledgeById(@Param("knowledgeId")  Long knowledgeId  );

 	List<SimilarKnowledge> getSimilarKnowledges(@Param("similarKnowledge")  SimilarKnowledge similarKnowledge);

 	Page<SimilarKnowledge> getSimilarKnowledgesForPage(@Param("similarKnowledge")  SimilarKnowledge similarKnowledge, Pageable pageable);

}
