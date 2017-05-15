package com.mxg.bybo.dao;  
import com.mxg.bybo.model.ExpertClassifyRel;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  ExpertClassifyRelDao 专家-分类关联
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11
 */
@Repository
public interface ExpertClassifyRelDao {
	
	int insertExpertClassifyRel(ExpertClassifyRel expertClassifyRel);
	
	int insertExpertClassifyRelBatch(List<ExpertClassifyRel> list);
	
	int updateExpertClassifyRelById(ExpertClassifyRel expertClassifyRel);
	
	int deleteExpertClassifyRelById(@Param("expertId")  Long expertId  );
	
 	ExpertClassifyRel getExpertClassifyRelById(@Param("expertId")  Long expertId  );

 	List<ExpertClassifyRel> getExpertClassifyRels(@Param("expertClassifyRel")  ExpertClassifyRel expertClassifyRel);

 	Page<ExpertClassifyRel> getExpertClassifyRelsForPage(@Param("expertClassifyRel")  ExpertClassifyRel expertClassifyRel, Pageable pageable);

	void deleteRel(@Param("expertId") long expertId);

	List<Long> getDepartmentIds(@Param("expertId") Long id);

}
