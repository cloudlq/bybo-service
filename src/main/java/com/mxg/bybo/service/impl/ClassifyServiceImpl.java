package com.mxg.bybo.service.impl;  
import com.mxg.bybo.service.ClassifyService;
import com.mxg.bybo.dao.ClassifyDao;
import com.mxg.bybo.model.Classify;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 *  ClassifyServiceImpl
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11 
 */
@Service
public class ClassifyServiceImpl  implements ClassifyService { 
	
	@Autowired
	private ClassifyDao classifyDao;
	
	public int insertClassify(Classify classify){
		return classifyDao.insertClassify(classify);
	}
	public int insertClassifyBatch(List<Classify> list){
		return classifyDao.insertClassifyBatch(list);
	}
	public int updateClassifyById(Classify classify){
		return classifyDao.updateClassifyById(classify);
	}
	public int deleteClassifyById(  Long id  ){
		return classifyDao.deleteClassifyById(  id  );
	}
	public Classify getClassifyById(  Long id  ){
		return classifyDao.getClassifyById(  id  );
	}
 
 	public List<Classify> getClassifys(Classify classify){
		return classifyDao.getClassifys(classify);

 	}

 	public Page<Classify> getClassifysForPage(Classify classify, Pageable pageable){
		return classifyDao.getClassifysForPage(classify,pageable);

 	}
}
