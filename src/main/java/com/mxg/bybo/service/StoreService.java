package com.mxg.bybo.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mxg.bybo.model.Store;
import com.mxg.common.mybatis.QueryCondition;

import java.util.List;

/**
 *  StoreService
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11 
 */
public interface StoreService {
	
	int insertStore(Store store);
	
	int insertStoreBatch(List<Store> list);
	
	int updateStoreById(Store store);
	
	int deleteStoreById(Long id);
	
 	Store getStoreById(Long id);
 
 	List<Store> getStores(Store store);

 	Page<Store> getStoresForPage(List<QueryCondition> conditions, Pageable pageable);
}
