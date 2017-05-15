package com.mxg.bybo.rest;  
import com.mxg.bybo.service.RollpicService;
import com.mxg.bybo.model.Rollpic;
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
 *  RollpicController
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11
 */
@RestController
@Api(value = "RollpicController", description = "相关")
@RequestMapping(value = "/rollpic")
public class RollpicController { 
	
	   @InitBinder   
	    public void initBinder(WebDataBinder binder) {   
	        DateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
	        dateFormat.setLenient(true);   
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   
	    }  
	
	@Autowired
	private RollpicService rollpicService;
	
	@ApiOperation(value = "新增", notes = "新增")
	@RequestMapping(value = "/insertRollpic", method = RequestMethod.POST)
	public int insertRollpic(@RequestBody Rollpic rollpic){
		rollpic.setId(IdUtil.getId());
		return rollpicService.insertRollpic(rollpic);
	}
	@ApiOperation(value = "批量新增", notes = "批量新增")
	@RequestMapping(value = "/insertRollpicBatch", method = RequestMethod.POST)
	public int insertRollpicBatch(@RequestBody List<Rollpic> list){
		return rollpicService.insertRollpicBatch(list);
	}
	@ApiOperation(value = "根据ID修改", notes = "根据ID修改")
	@RequestMapping(value = "/updateRollpicById", method = RequestMethod.POST)
	public int updateRollpicById(@RequestBody Rollpic rollpic){
		return rollpicService.updateRollpicById(rollpic);
	}
	@ApiOperation(value = "根据ID删除", notes = "根据ID删除")
	@RequestMapping(value = "/deleteRollpicById", method = RequestMethod.GET)
	public int deleteRollpicById( @RequestParam Long id  ){
		return rollpicService.deleteRollpicById(  id  );
	}
	@ApiOperation(value = "根据ID获取", notes = "根据ID获取")
	@RequestMapping(value = "/getRollpicById", method = RequestMethod.GET)
	public Rollpic getRollpicById( @RequestParam Long id  ){
		return rollpicService.getRollpicById(  id  );
	}
 
	@ApiOperation(value = "根据对象获取", notes = "根据对象获取")
	@RequestMapping(value = "/getRollpics", method = RequestMethod.POST)
	public List<Rollpic> getRollpics( @RequestBody Rollpic rollpic){
		return rollpicService.getRollpics(rollpic);

 	}

	@ApiOperation(value = "根据对象分页获取", notes = "根据对象分页获取")
	@RequestMapping(value = "/getRollpicsForPage", method = RequestMethod.POST)
	public PageDto<Rollpic> getRollpicsForPage(@RequestBody Rollpic rollpic,
			@RequestParam(value="page", defaultValue="1")  int page,
			@RequestParam(value="rows", defaultValue="10") int rows,
			@RequestParam(required=false) String sort,
			@RequestParam(required=false) String order){
		Map<String,String> cols = new HashMap<String,String>();
		cols.put("id", "id");
		cols.put("name", "name");
		cols.put("imagPath", "imagPath");
		cols.put("priority", "priority");
		cols.put("status", "status");
		cols.put("addTime", "addTime");
		cols.put("url", "url");
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
		Page<Rollpic> rollpics= rollpicService.getRollpicsForPage(conditions,pageable);
	
		PageDto<Rollpic> pageDto = new PageDto<Rollpic>();
		if (rollpics != null) {
			pageDto.setRows( rollpics.getContent());
			pageDto.setTotal(rollpics.getTotalElements());
		} else {
			pageDto.setRows(new ArrayList<Rollpic>());
			pageDto.setTotal(0l);
		}
		 
		return pageDto;
 	}
}
