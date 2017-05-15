package com.mxg.bybo.dao;  
import com.mxg.bybo.model.Classify;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 *  ClassifyDao 专家分类
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11
 */
@Repository
public interface ClassifyDao {
	
	int insertClassify(Classify classify);
	
	int insertClassifyBatch(List<Classify> list);
	
	int updateClassifyById(Classify classify);
	
	int deleteClassifyById(@Param("id")  Long id  );
	
 	Classify getClassifyById(@Param("id")  Long id  );

 	List<Classify> getClassifys(@Param("classify")  Classify classify);

 	Page<Classify> getClassifysForPage(@Param("classify")  Classify classify, Pageable pageable);

}
