package com.mxg.bybo.dao;  
import com.mxg.bybo.model.Store;
import com.mxg.common.mybatis.QueryCondition;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  StoreDao 门店
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11
 */
@Repository
public interface StoreDao {
	
	int insertStore(Store store);
	
	int insertStoreBatch(List<Store> list);
	
	int updateStoreById(Store store);
	
	int deleteStoreById(@Param("id")  Long id  );
	
 	Store getStoreById(@Param("id")  Long id  );

 	List<Store> getStores(@Param("store")  Store store);

 	Page<Store> getStoresForPage(@Param("conditions") List<QueryCondition> conditionsR, Pageable pageable);

}
