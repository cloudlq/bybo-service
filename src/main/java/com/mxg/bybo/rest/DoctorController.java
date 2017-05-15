package com.mxg.bybo.rest;  
import com.mxg.bybo.service.DoctorService;
import com.mxg.bybo.model.Doctor;
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
 *  DoctorController
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11
 */
@RestController
@Api(value = "DoctorController", description = "医生相关")
@RequestMapping(value = "/doctor")
public class DoctorController { 
	
	   @InitBinder   
	    public void initBinder(WebDataBinder binder) {   
	        DateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
	        dateFormat.setLenient(true);   
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   
	    }  
	
	@Autowired
	private DoctorService doctorService;
	
	@ApiOperation(value = "新增医生", notes = "新增医生")
	@RequestMapping(value = "/insertDoctor", method = RequestMethod.POST)
	public int insertDoctor(@RequestBody Doctor doctor){
		doctor.setId(IdUtil.getId());
		return doctorService.insertDoctor(doctor);
	}
	@ApiOperation(value = "批量新增医生", notes = "批量新增医生")
	@RequestMapping(value = "/insertDoctorBatch", method = RequestMethod.POST)
	public int insertDoctorBatch(@RequestBody List<Doctor> list){
		return doctorService.insertDoctorBatch(list);
	}
	@ApiOperation(value = "根据ID修改医生", notes = "根据ID修改医生")
	@RequestMapping(value = "/updateDoctorById", method = RequestMethod.POST)
	public int updateDoctorById(@RequestBody Doctor doctor){
		return doctorService.updateDoctorById(doctor);
	}
	@ApiOperation(value = "根据ID删除医生", notes = "根据ID删除医生")
	@RequestMapping(value = "/deleteDoctorById", method = RequestMethod.GET)
	public int deleteDoctorById( @RequestParam Long id  ){
		return doctorService.deleteDoctorById(  id  );
	}
	@ApiOperation(value = "根据ID获取医生", notes = "根据ID获取医生")
	@RequestMapping(value = "/getDoctorById", method = RequestMethod.GET)
	public Doctor getDoctorById( @RequestParam Long id  ){
		return doctorService.getDoctorById(  id  );
	}
 
	@ApiOperation(value = "根据对象获取医生", notes = "根据对象获取医生")
	@RequestMapping(value = "/getDoctors", method = RequestMethod.POST)
	public List<Doctor> getDoctors( @RequestBody Doctor doctor){
		return doctorService.getDoctors(doctor);

 	}

	@ApiOperation(value = "根据对象分页获取医生", notes = "根据对象分页获取医生")
	@RequestMapping(value = "/getDoctorsForPage", method = RequestMethod.POST)
	public PageDto<Doctor> getDoctorsForPage(@RequestBody Doctor doctor,
			@RequestParam(value="page", defaultValue="1")  int page,
			@RequestParam(value="rows", defaultValue="10") int rows,
			@RequestParam(required=false) String sort,
			@RequestParam(required=false) String order){
		Map<String,String> cols = new HashMap<String,String>();
		cols.put("id", "id");
		cols.put("name", "name");
		cols.put("specialty", "specialty");
		cols.put("title", "title");
		cols.put("regionId", "region_id");
		cols.put("language", "language");
		cols.put("photo", "photo");
		cols.put("honor", "honor");
		cols.put("adept", "adept");
		cols.put("content", "content");
		Pageable pageable =null;
		if(!StringUtils.isEmpty(order)&&!StringUtils.isEmpty(sort)){
			Sort sortObj =new Sort(Sort.Direction.fromStringOrNull(order), cols.get(sort));
			pageable = new PageRequest(page-1, rows, sortObj);
		}else{
			pageable=new PageRequest(page-1, rows);
		}
		
		List<QueryCondition> conditions = new ArrayList<QueryCondition>();
		Page<Doctor> doctors= doctorService.getDoctorsForPage(conditions,pageable);
	
		PageDto<Doctor> pageDto = new PageDto<Doctor>();
		if (doctors != null) {
			pageDto.setRows( doctors.getContent());
			pageDto.setTotal(doctors.getTotalElements());
		} else {
			pageDto.setRows(new ArrayList<Doctor>());
			pageDto.setTotal(0l);
		}
		 
		return pageDto;
 

 	}
}
