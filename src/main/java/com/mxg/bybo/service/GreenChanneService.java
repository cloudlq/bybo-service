package com.mxg.bybo.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.mxg.bybo.model.GreenChanne;
import java.util.List;

/**
 *  GreenChanneService
 *
 * @version : Ver 1.0
 * @date	: 2017-3-30 
 */
public interface GreenChanneService {
	
	int insertGreenChanne(GreenChanne greenChanne);
	
	int insertGreenChanneBatch(List<GreenChanne> list);
	
	int updateGreenChanneById(GreenChanne greenChanne);
	
	int deleteGreenChanneById(Long id);
	
 	GreenChanne getGreenChanneById(Long id);
 
 	List<GreenChanne> getGreenChannes(GreenChanne greenChanne);

 	Page<GreenChanne> getGreenChannesForPage(GreenChanne greenChanne, Pageable pageable);
}
