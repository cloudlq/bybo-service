package com.mxg.bybo.service.impl;  
import com.mxg.bybo.service.GreenChanneService;
import com.mxg.bybo.dao.GreenChanneDao;
import com.mxg.bybo.model.GreenChanne;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 *  GreenChanneServiceImpl
 *
 * @version : Ver 1.0
 * @date	: 2017-3-30 
 */
@Service
public class GreenChanneServiceImpl  implements GreenChanneService { 
	
	@Autowired
	private GreenChanneDao greenChanneDao;
	
	public int insertGreenChanne(GreenChanne greenChanne){
		return greenChanneDao.insertGreenChanne(greenChanne);
	}
	public int insertGreenChanneBatch(List<GreenChanne> list){
		return greenChanneDao.insertGreenChanneBatch(list);
	}
	public int updateGreenChanneById(GreenChanne greenChanne){
		return greenChanneDao.updateGreenChanneById(greenChanne);
	}
	public int deleteGreenChanneById(  Long id  ){
		return greenChanneDao.deleteGreenChanneById(  id  );
	}
	public GreenChanne getGreenChanneById(  Long id  ){
		return greenChanneDao.getGreenChanneById(  id  );
	}
 
 	public List<GreenChanne> getGreenChannes(GreenChanne greenChanne){
		return greenChanneDao.getGreenChannes(greenChanne);

 	}

 	public Page<GreenChanne> getGreenChannesForPage(GreenChanne greenChanne, Pageable pageable){
		return greenChanneDao.getGreenChannesForPage(greenChanne,pageable);

 	}
}
