package com.mxg.bybo.dao;  
import com.mxg.bybo.model.Knowledge;
import com.mxg.common.mybatis.QueryCondition;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  KnowledgeDao 知识库
 *
 * @version : Ver 1.0
 * @date	: 2017-3-29
 */
@Repository
public interface KnowledgeDao {
	
	int insertKnowledge(Knowledge knowledge);
	
	int insertKnowledgeBatch(List<Knowledge> list);
	
	int updateKnowledgeById(Knowledge knowledge);
	
	int deleteKnowledgeById(@Param("id")  Long id  );
	
 	Knowledge getKnowledgeById(@Param("id")  Long id  );

 	List<Knowledge> getKnowledges(@Param("knowledge")  Knowledge knowledge);

 	Page<Knowledge> getKnowledgesForPage(@Param("knowledge")  Knowledge knowledge, Pageable pageable);

 	List<Knowledge> getKnowledgesByConditions(@Param("conditions") List<QueryCondition> conditions);
 	
 	Page<Knowledge> getKnowledgesByConditions(@Param("conditions") List<QueryCondition> conditions, Pageable pageable);

}
