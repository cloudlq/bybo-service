package com.mxg.bybo.dao;  
import com.mxg.bybo.model.Rollpic;
import com.mxg.common.mybatis.QueryCondition;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  RollpicDao 
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11
 */
@Repository
public interface RollpicDao {
	
	int insertRollpic(Rollpic rollpic);
	
	int insertRollpicBatch(List<Rollpic> list);
	
	int updateRollpicById(Rollpic rollpic);
	
	int deleteRollpicById(@Param("id")  Long id  );
	
 	Rollpic getRollpicById(@Param("id")  Long id  );

 	List<Rollpic> getRollpics(@Param("rollpic")  Rollpic rollpic);

 	Page<Rollpic> getRollpicsForPage(@Param("conditions") List<QueryCondition> conditions, Pageable pageable);

}
