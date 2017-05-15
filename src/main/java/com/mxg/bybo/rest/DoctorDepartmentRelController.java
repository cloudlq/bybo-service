package com.mxg.bybo.rest;  
import com.mxg.bybo.service.DoctorDepartmentRelService;
import com.mxg.bybo.model.DoctorDepartmentRel;
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
 *  DoctorDepartmentRelController
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11
 */
@RestController
@Api(value = "DoctorDepartmentRelController", description = "医生科室关联相关")
@RequestMapping(value = "/doctorDepartmentRel")
public class DoctorDepartmentRelController { 
	
	   @InitBinder   
	    public void initBinder(WebDataBinder binder) {   
	        DateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
	        dateFormat.setLenient(true);   
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   
	    }  
	
	@Autowired
	private DoctorDepartmentRelService doctorDepartmentRelService;
	
	@ApiOperation(value = "新增医生科室关联", notes = "新增医生科室关联")
	@RequestMapping(value = "/insertDoctorDepartmentRel", method = RequestMethod.POST)
	public int insertDoctorDepartmentRel(@RequestBody DoctorDepartmentRel doctorDepartmentRel){
		return doctorDepartmentRelService.insertDoctorDepartmentRel(doctorDepartmentRel);
	}
	@ApiOperation(value = "批量新增医生科室关联", notes = "批量新增医生科室关联")
	@RequestMapping(value = "/insertDoctorDepartmentRelBatch", method = RequestMethod.POST)
	public int insertDoctorDepartmentRelBatch(@RequestBody List<DoctorDepartmentRel> list){
		return doctorDepartmentRelService.insertDoctorDepartmentRelBatch(list);
	}

 
	@ApiOperation(value = "根据对象获取医生科室关联", notes = "根据对象获取医生科室关联")
	@RequestMapping(value = "/getDoctorDepartmentRels", method = RequestMethod.POST)
	public List<DoctorDepartmentRel> getDoctorDepartmentRels( @RequestBody DoctorDepartmentRel doctorDepartmentRel){
		return doctorDepartmentRelService.getDoctorDepartmentRels(doctorDepartmentRel);

 	}

	@ApiOperation(value = "根据对象分页获取医生科室关联", notes = "根据对象分页获取医生科室关联")
	@RequestMapping(value = "/getDoctorDepartmentRelsForPage", method = RequestMethod.POST)
	public PageDto<DoctorDepartmentRel> getDoctorDepartmentRelsForPage(@RequestBody DoctorDepartmentRel doctorDepartmentRel,
			@RequestParam(value="page", defaultValue="1")  int page,
			@RequestParam(value="rows", defaultValue="10") int rows,
			@RequestParam(required=false) String sort,
			@RequestParam(required=false) String order){
		Map<String,String> cols = new HashMap<String,String>();
		cols.put("doctorId", "doctor_id");
		cols.put("departmentId", "department_id");
		Pageable pageable =null;
		if(!StringUtils.isEmpty(order)&&!StringUtils.isEmpty(sort)){
			Sort sortObj =new Sort(Sort.Direction.fromStringOrNull(order), cols.get(sort));
			pageable = new PageRequest(page-1, rows, sortObj);
		}else{
			pageable=new PageRequest(page-1, rows);
		}
		Page<DoctorDepartmentRel> doctorDepartmentRels= doctorDepartmentRelService.getDoctorDepartmentRelsForPage(doctorDepartmentRel,pageable);
	
		PageDto<DoctorDepartmentRel> pageDto = new PageDto<DoctorDepartmentRel>();
		if (doctorDepartmentRels != null) {
			pageDto.setRows( doctorDepartmentRels.getContent());
			pageDto.setTotal(doctorDepartmentRels.getTotalElements());
		} else {
			pageDto.setRows(new ArrayList<DoctorDepartmentRel>());
			pageDto.setTotal(0l);
		}
		 
		return pageDto;
 

 	}
}
