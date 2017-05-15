package com.mxg.bybo.rest;  
import com.mxg.bybo.service.DepartmentService;
import com.mxg.bybo.model.Department;
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
 *  DepartmentController
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11
 */
@RestController
@Api(value = "DepartmentController", description = "科室相关")
@RequestMapping(value = "/department")
public class DepartmentController { 
	
	   @InitBinder   
	    public void initBinder(WebDataBinder binder) {   
	        DateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
	        dateFormat.setLenient(true);   
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   
	    }  
	
	@Autowired
	private DepartmentService departmentService;
	
	@ApiOperation(value = "新增科室", notes = "新增科室")
	@RequestMapping(value = "/insertDepartment", method = RequestMethod.POST)
	public int insertDepartment(@RequestBody Department department){
		department.setId(IdUtil.getId());
		return departmentService.insertDepartment(department);
	}
	@ApiOperation(value = "批量新增科室", notes = "批量新增科室")
	@RequestMapping(value = "/insertDepartmentBatch", method = RequestMethod.POST)
	public int insertDepartmentBatch(@RequestBody List<Department> list){
		return departmentService.insertDepartmentBatch(list);
	}
	@ApiOperation(value = "根据ID修改科室", notes = "根据ID修改科室")
	@RequestMapping(value = "/updateDepartmentById", method = RequestMethod.POST)
	public int updateDepartmentById(@RequestBody Department department){
		return departmentService.updateDepartmentById(department);
	}
	@ApiOperation(value = "根据ID删除科室", notes = "根据ID删除科室")
	@RequestMapping(value = "/deleteDepartmentById", method = RequestMethod.GET)
	public int deleteDepartmentById( @RequestParam Long id  ){
		return departmentService.deleteDepartmentById(  id  );
	}
	@ApiOperation(value = "根据ID获取科室", notes = "根据ID获取科室")
	@RequestMapping(value = "/getDepartmentById", method = RequestMethod.GET)
	public Department getDepartmentById( @RequestParam Long id  ){
		return departmentService.getDepartmentById(  id  );
	}
 
	@ApiOperation(value = "根据对象获取科室", notes = "根据对象获取科室")
	@RequestMapping(value = "/getDepartments", method = RequestMethod.POST)
	public List<Department> getDepartments( @RequestBody Department department){
		return departmentService.getDepartments(department);

 	}

	@ApiOperation(value = "根据对象分页获取科室", notes = "根据对象分页获取科室")
	@RequestMapping(value = "/getDepartmentsForPage", method = RequestMethod.POST)
	public PageDto<Department> getDepartmentsForPage(@RequestBody Department department,
			@RequestParam(value="page", defaultValue="1")  int page,
			@RequestParam(value="rows", defaultValue="10") int rows,
			@RequestParam(required=false) String sort,
			@RequestParam(required=false) String order){
		Map<String,String> cols = new HashMap<String,String>();
		cols.put("id", "id");
		cols.put("name", "name");
		cols.put("summary", "summary");
		cols.put("service", "service");
		cols.put("technical", "technical");
		cols.put("picture", "picture");
		cols.put("language", "language");
		Pageable pageable =null;
		if(!StringUtils.isEmpty(order)&&!StringUtils.isEmpty(sort)){
			Sort sortObj =new Sort(Sort.Direction.fromStringOrNull(order), cols.get(sort));
			pageable = new PageRequest(page-1, rows, sortObj);
		}else{
			pageable=new PageRequest(page-1, rows);
		}
		List<QueryCondition> conditions = new ArrayList<QueryCondition>();
		Page<Department> departments= departmentService.getDepartmentsForPage(conditions,pageable);
	
		PageDto<Department> pageDto = new PageDto<Department>();
		if (departments != null) {
			pageDto.setRows( departments.getContent());
			pageDto.setTotal(departments.getTotalElements());
		} else {
			pageDto.setRows(new ArrayList<Department>());
			pageDto.setTotal(0l);
		}
		 
		return pageDto;
 

 	}
}
