package com.mxg.bybo.dao;  
import com.mxg.bybo.model.KnowledgeCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 *  KnowledgeCategoryDao 
 *
 * @version : Ver 1.0
 * @date	: 2017-3-29
 */
@Repository
public interface KnowledgeCategoryDao {
	
	int insertKnowledgeCategory(KnowledgeCategory knowledgeCategory);
	
	int insertKnowledgeCategoryBatch(List<KnowledgeCategory> list);
	
	int updateKnowledgeCategoryById(KnowledgeCategory knowledgeCategory);
	
	int deleteKnowledgeCategoryById(@Param("knowledgeId")  Long knowledgeId  );
	
	List<KnowledgeCategory> getKnowledgeCategoryById(@Param("knowledgeId")  Long knowledgeId  );

 	List<KnowledgeCategory> getKnowledgeCategorys(@Param("knowledgeCategory")  KnowledgeCategory knowledgeCategory);

 	Page<KnowledgeCategory> getKnowledgeCategorysForPage(@Param("knowledgeCategory")  KnowledgeCategory knowledgeCategory, Pageable pageable);

}
