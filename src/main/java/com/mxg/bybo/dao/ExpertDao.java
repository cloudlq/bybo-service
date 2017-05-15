package com.mxg.bybo.dao;  
import com.mxg.bybo.model.Expert;
import com.mxg.common.mybatis.QueryCondition;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  ExpertDao 专家
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11
 */
@Repository
public interface ExpertDao {
	
	int insertExpert(Expert expert);
	
	int insertExpertBatch(List<Expert> list);
	
	int updateExpertById(Expert expert);
	
	int deleteExpertById(@Param("id")  Long id  );
	
 	Expert getExpertById(@Param("id")  Long id  );

 	List<Expert> getExperts(@Param("expert")  Expert expert);

 	Page<Expert> getExpertsForPage(@Param("conditions") List<QueryCondition> conditions, Pageable pageable);

}
