package com.mxg.bybo.rest;  
import com.mxg.bybo.service.StoreService;
import com.mxg.bybo.model.Store;
import com.mxg.common.mybatis.QueryCondition;

import basic.common.core.dto.PageDto;
import basic.common.core.id.IdUtil;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.google.common.collect.Lists;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  StoreController
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11
 */
@RestController
@Api(value = "StoreController", description = "门店相关")
@RequestMapping(value = "/store")
public class StoreController { 
	
	   @InitBinder   
	    public void initBinder(WebDataBinder binder) {   
	        DateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
	        dateFormat.setLenient(true);   
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   
	    }  
	
	@Autowired
	private StoreService storeService;
	
	@ApiOperation(value = "新增门店", notes = "新增门店")
	@RequestMapping(value = "/insertStore", method = RequestMethod.POST)
	public int insertStore(@RequestBody Store store){
		store.setId(IdUtil.getId());
		return storeService.insertStore(store);
	}
	@ApiOperation(value = "批量新增门店", notes = "批量新增门店")
	@RequestMapping(value = "/insertStoreBatch", method = RequestMethod.POST)
	public int insertStoreBatch(@RequestBody List<Store> list){
		return storeService.insertStoreBatch(list);
	}
	@ApiOperation(value = "根据ID修改门店", notes = "根据ID修改门店")
	@RequestMapping(value = "/updateStoreById", method = RequestMethod.POST)
	public int updateStoreById(@RequestBody Store store){
		return storeService.updateStoreById(store);
	}
	@ApiOperation(value = "根据ID删除门店", notes = "根据ID删除门店")
	@RequestMapping(value = "/deleteStoreById", method = RequestMethod.GET)
	public int deleteStoreById( @RequestParam Long id  ){
		return storeService.deleteStoreById(  id  );
	}
	@ApiOperation(value = "根据ID获取门店", notes = "根据ID获取门店")
	@RequestMapping(value = "/getStoreById", method = RequestMethod.GET)
	public Store getStoreById( @RequestParam Long id  ){
		return storeService.getStoreById(  id  );
	}
 
	@ApiOperation(value = "根据对象获取门店", notes = "根据对象获取门店")
	@RequestMapping(value = "/getStores", method = RequestMethod.POST)
	public List<Store> getStores( @RequestBody Store store){
		return storeService.getStores(store);

 	}

	@ApiOperation(value = "根据对象分页获取门店", notes = "根据对象分页获取门店")
	@RequestMapping(value = "/getStoresForPage", method = RequestMethod.POST)
	public PageDto<Store> getStoresForPage(@RequestBody Store store,
			@RequestParam(value="page", defaultValue="1")  int page,
			@RequestParam(value="rows", defaultValue="10") int rows,
			@RequestParam(required=false) String sort,
			@RequestParam(required=false) String order){
		Map<String,String> cols = new HashMap<String,String>();
		cols.put("id", "id");
		cols.put("name", "name");
		cols.put("address", "address");
		cols.put("phone", "phone");
		cols.put("telphone", "telphone");
		cols.put("userName", "user_name");
		cols.put("regionId", "region_id");
		cols.put("language", "language");
		Pageable pageable =null;
		if(!StringUtils.isEmpty(order)&&!StringUtils.isEmpty(sort)){
			Sort sortObj =new Sort(Sort.Direction.fromStringOrNull(order), cols.get(sort));
			pageable = new PageRequest(page-1, rows, sortObj);
		}else{
			pageable=new PageRequest(page-1, rows);
		}
		List<QueryCondition> conditions = new ArrayList<QueryCondition>();
		Page<Store> stores= storeService.getStoresForPage(conditions,pageable);
	
		PageDto<Store> pageDto = new PageDto<Store>();
		if (stores != null) {
			pageDto.setRows( stores.getContent());
			pageDto.setTotal(stores.getTotalElements());
		} else {
			pageDto.setRows(new ArrayList<Store>());
			pageDto.setTotal(0l);
		}
		 
		return pageDto;
 

 	}
}
