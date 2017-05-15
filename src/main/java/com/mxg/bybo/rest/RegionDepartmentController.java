package com.mxg.bybo.rest;  
import com.mxg.bybo.service.RegionDepartmentService;
import com.mxg.bybo.model.RegionDepartment;
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
 *  RegionDepartmentController
 *
 * @version : Ver 1.0
 * @date	: 2017-4-11
 */
@RestController
@Api(value = "RegionDepartmentController", description = "事业部关联科室相关")
@RequestMapping(value = "/regionDepartment")
public class RegionDepartmentController { 
	
	   @InitBinder   
	    public void initBinder(WebDataBinder binder) {   
	        DateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
	        dateFormat.setLenient(true);   
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   
	    }  
	
	@Autowired
	private RegionDepartmentService regionDepartmentService;
	
	@ApiOperation(value = "新增事业部关联科室", notes = "新增事业部关联科室")
	@RequestMapping(value = "/insertRegionDepartment", method = RequestMethod.POST)
	public int insertRegionDepartment(@RequestBody RegionDepartment regionDepartment){
		regionDepartment.setRegionId(IdUtil.getId());
		return regionDepartmentService.insertRegionDepartment(regionDepartment);
	}
	@ApiOperation(value = "批量新增事业部关联科室", notes = "批量新增事业部关联科室")
	@RequestMapping(value = "/insertRegionDepartmentBatch", method = RequestMethod.POST)
	public int insertRegionDepartmentBatch(@RequestBody List<RegionDepartment> list){
		return regionDepartmentService.insertRegionDepartmentBatch(list);
	}
	@ApiOperation(value = "根据ID修改事业部关联科室", notes = "根据ID修改事业部关联科室")
	@RequestMapping(value = "/updateRegionDepartmentById", method = RequestMethod.POST)
	public int updateRegionDepartmentById(@RequestBody RegionDepartment regionDepartment){
		return regionDepartmentService.updateRegionDepartmentById(regionDepartment);
	}
	@ApiOperation(value = "根据ID删除事业部关联科室", notes = "根据ID删除事业部关联科室")
	@RequestMapping(value = "/deleteRegionDepartmentById", method = RequestMethod.GET)
	public int deleteRegionDepartmentById( @RequestParam Long regionId  ){
		return regionDepartmentService.deleteRegionDepartmentById(  regionId  );
	}
	@ApiOperation(value = "根据ID获取事业部关联科室", notes = "根据ID获取事业部关联科室")
	@RequestMapping(value = "/getRegionDepartmentById", method = RequestMethod.GET)
	public RegionDepartment getRegionDepartmentById( @RequestParam Long regionId  ){
		return regionDepartmentService.getRegionDepartmentById(  regionId  );
	}
 
	@ApiOperation(value = "根据对象获取事业部关联科室", notes = "根据对象获取事业部关联科室")
	@RequestMapping(value = "/getRegionDepartments", method = RequestMethod.POST)
	public List<RegionDepartment> getRegionDepartments( @RequestBody RegionDepartment regionDepartment){
		return regionDepartmentService.getRegionDepartments(regionDepartment);

 	}

	@ApiOperation(value = "根据对象分页获取事业部关联科室", notes = "根据对象分页获取事业部关联科室")
	@RequestMapping(value = "/getRegionDepartmentsForPage", method = RequestMethod.POST)
	public PageDto<RegionDepartment> getRegionDepartmentsForPage(@RequestBody RegionDepartment regionDepartment,
			@RequestParam(value="page", defaultValue="1")  int page,
			@RequestParam(value="rows", defaultValue="10") int rows,
			@RequestParam(required=false) String sort,
			@RequestParam(required=false) String order){
		Map<String,String> cols = new HashMap<String,String>();
		cols.put("regionId", "region_id");
		cols.put("departmentId", "department_id");
		cols.put("pictures", "pictures");
		Pageable pageable =null;
		if(!StringUtils.isEmpty(order)&&!StringUtils.isEmpty(sort)){
			Sort sortObj =new Sort(Sort.Direction.fromStringOrNull(order), cols.get(sort));
			pageable = new PageRequest(page-1, rows, sortObj);
		}else{
			pageable=new PageRequest(page-1, rows);
		}
		Page<RegionDepartment> regionDepartments= regionDepartmentService.getRegionDepartmentsForPage(regionDepartment,pageable);
	
		PageDto<RegionDepartment> pageDto = new PageDto<RegionDepartment>();
		if (regionDepartments != null) {
			pageDto.setRows( regionDepartments.getContent());
			pageDto.setTotal(regionDepartments.getTotalElements());
		} else {
			pageDto.setRows(new ArrayList<RegionDepartment>());
			pageDto.setTotal(0l);
		}
		 
		return pageDto;
 

 	}
}
