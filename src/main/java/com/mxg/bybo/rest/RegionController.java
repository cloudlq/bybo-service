package com.mxg.bybo.rest;  
import com.mxg.bybo.service.RegionService;
import com.mxg.bybo.model.Region;
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
 *  RegionController
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11
 */
@RestController
@Api(value = "RegionController", description = "区域相关")
@RequestMapping(value = "/region")
public class RegionController { 
	
	   @InitBinder   
	    public void initBinder(WebDataBinder binder) {   
	        DateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
	        dateFormat.setLenient(true);   
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   
	    }  
	
	@Autowired
	private RegionService regionService;
	
	@ApiOperation(value = "新增区域", notes = "新增区域")
	@RequestMapping(value = "/insertRegion", method = RequestMethod.POST)
	public int insertRegion(@RequestBody Region region){
		region.setRegionId(IdUtil.getId());
		return regionService.insertRegion(region);
	}
	@ApiOperation(value = "批量新增区域", notes = "批量新增区域")
	@RequestMapping(value = "/insertRegionBatch", method = RequestMethod.POST)
	public int insertRegionBatch(@RequestBody List<Region> list){
		return regionService.insertRegionBatch(list);
	}
	@ApiOperation(value = "根据ID修改区域", notes = "根据ID修改区域")
	@RequestMapping(value = "/updateRegionById", method = RequestMethod.POST)
	public int updateRegionById(@RequestBody Region region){
		return regionService.updateRegionById(region);
	}
	@ApiOperation(value = "根据ID删除区域", notes = "根据ID删除区域")
	@RequestMapping(value = "/deleteRegionById", method = RequestMethod.GET)
	public int deleteRegionById( @RequestParam Long regionId  ){
		return regionService.deleteRegionById(  regionId  );
	}
	@ApiOperation(value = "根据ID获取区域", notes = "根据ID获取区域")
	@RequestMapping(value = "/getRegionById", method = RequestMethod.GET)
	public Region getRegionById( @RequestParam Long regionId  ){
		return regionService.getRegionById(  regionId  );
	}
 
	@ApiOperation(value = "根据对象获取区域", notes = "根据对象获取区域")
	@RequestMapping(value = "/getRegions", method = RequestMethod.POST)
	public List<Region> getRegions( @RequestBody Region region){
		return regionService.getRegions(region);

 	}

	@ApiOperation(value = "根据对象分页获取区域", notes = "根据对象分页获取区域")
	@RequestMapping(value = "/getRegionsForPage", method = RequestMethod.POST)
	public PageDto<Region> getRegionsForPage(@RequestBody Region region,
			@RequestParam(value="page", defaultValue="1")  int page,
			@RequestParam(value="rows", defaultValue="10") int rows,
			@RequestParam(required=false) String sort,
			@RequestParam(required=false) String order){
		Map<String,String> cols = new HashMap<String,String>();
		cols.put("regionId", "region_id");
		cols.put("cnName", "cn_name");
		cols.put("parentRegionId", "parent_region_id");
		cols.put("cnAddress", "cn_address");
		cols.put("phone", "phone");
		cols.put("email", "email");
		cols.put("logoImageUrl", "logo_image_url");
		cols.put("url", "url");
		cols.put("enName", "en_name");
		cols.put("enAddress", "en_address");
		Pageable pageable =null;
		if(!StringUtils.isEmpty(order)&&!StringUtils.isEmpty(sort)){
			Sort sortObj =new Sort(Sort.Direction.fromStringOrNull(order), cols.get(sort));
			pageable = new PageRequest(page-1, rows, sortObj);
		}else{
			pageable=new PageRequest(page-1, rows);
		}
		List<QueryCondition> conditions = new ArrayList<QueryCondition>();
		Page<Region> regions= regionService.getRegionsForPage(conditions,pageable);
	
		PageDto<Region> pageDto = new PageDto<Region>();
		if (regions != null) {
			pageDto.setRows( regions.getContent());
			pageDto.setTotal(regions.getTotalElements());
		} else {
			pageDto.setRows(new ArrayList<Region>());
			pageDto.setTotal(0l);
		}
		 
		return pageDto;

 	}
	
	
	
	
}
