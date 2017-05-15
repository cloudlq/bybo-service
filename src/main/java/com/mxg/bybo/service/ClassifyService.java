package com.mxg.bybo.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.mxg.bybo.model.Classify;
import java.util.List;

/**
 *  ClassifyService
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11 
 */
public interface ClassifyService {
	
	int insertClassify(Classify classify);
	
	int insertClassifyBatch(List<Classify> list);
	
	int updateClassifyById(Classify classify);
	
	int deleteClassifyById(Long id);
	
 	Classify getClassifyById(Long id);
 
 	List<Classify> getClassifys(Classify classify);

 	Page<Classify> getClassifysForPage(Classify classify, Pageable pageable);
}
