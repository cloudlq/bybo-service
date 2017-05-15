package com.mxg.bybo.service.impl;  
import com.mxg.bybo.service.StoreService;
import com.mxg.bybo.dao.StoreDao;
import com.mxg.bybo.model.Store;
import com.mxg.common.mybatis.QueryCondition;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  StoreServiceImpl
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11 
 */
@Service
public class StoreServiceImpl  implements StoreService { 
	
	@Autowired
	private StoreDao storeDao;
	
	public int insertStore(Store store){
		return storeDao.insertStore(store);
	}
	public int insertStoreBatch(List<Store> list){
		return storeDao.insertStoreBatch(list);
	}
	public int updateStoreById(Store store){
		return storeDao.updateStoreById(store);
	}
	public int deleteStoreById(  Long id  ){
		return storeDao.deleteStoreById(  id  );
	}
	public Store getStoreById(  Long id  ){
		return storeDao.getStoreById(  id  );
	}
 
 	public List<Store> getStores(Store store){
		return storeDao.getStores(store);

 	}

 	public Page<Store> getStoresForPage(List<QueryCondition> conditions, Pageable pageable){
		return storeDao.getStoresForPage(conditions,pageable);

 	}
}
