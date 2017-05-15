package com.mxg.bybo.dao;  
import com.mxg.bybo.model.GreenChanne;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 *  GreenChanneDao 绿色通道
 *
 * @version : Ver 1.0
 * @date	: 2017-3-30
 */
@Repository
public interface GreenChanneDao {
	
	int insertGreenChanne(GreenChanne greenChanne);
	
	int insertGreenChanneBatch(List<GreenChanne> list);
	
	int updateGreenChanneById(GreenChanne greenChanne);
	
	int deleteGreenChanneById(@Param("id")  Long id  );
	
 	GreenChanne getGreenChanneById(@Param("id")  Long id  );

 	List<GreenChanne> getGreenChannes(@Param("greenChanne")  GreenChanne greenChanne);

 	Page<GreenChanne> getGreenChannesForPage(@Param("greenChanne")  GreenChanne greenChanne, Pageable pageable);

}
