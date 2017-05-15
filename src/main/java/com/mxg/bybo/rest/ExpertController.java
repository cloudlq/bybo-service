package com.mxg.bybo.rest;  
import com.mxg.bybo.service.ExpertService;
import com.mxg.bybo.model.Expert;
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
 *  ExpertController
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11
 */
@RestController
@Api(value = "ExpertController", description = "专家相关")
@RequestMapping(value = "/expert")
public class ExpertController { 
	
	   @InitBinder   
	    public void initBinder(WebDataBinder binder) {   
	        DateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
	        dateFormat.setLenient(true);   
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   
	    }  
	
	@Autowired
	private ExpertService expertService;
	
	@ApiOperation(value = "新增专家", notes = "新增专家")
	@RequestMapping(value = "/insertExpert", method = RequestMethod.POST)
	public int insertExpert(@RequestBody Expert expert){
		expert.setId(IdUtil.getId());
		return expertService.insertExpert(expert);
	}
	@ApiOperation(value = "批量新增专家", notes = "批量新增专家")
	@RequestMapping(value = "/insertExpertBatch", method = RequestMethod.POST)
	public int insertExpertBatch(@RequestBody List<Expert> list){
		return expertService.insertExpertBatch(list);
	}
	@ApiOperation(value = "根据ID修改专家", notes = "根据ID修改专家")
	@RequestMapping(value = "/updateExpertById", method = RequestMethod.POST)
	public int updateExpertById(@RequestBody Expert expert){
		return expertService.updateExpertById(expert);
	}
	@ApiOperation(value = "根据ID删除专家", notes = "根据ID删除专家")
	@RequestMapping(value = "/deleteExpertById", method = RequestMethod.GET)
	public int deleteExpertById( @RequestParam Long id  ){
		return expertService.deleteExpertById(  id  );
	}
	@ApiOperation(value = "根据ID获取专家", notes = "根据ID获取专家")
	@RequestMapping(value = "/getExpertById", method = RequestMethod.GET)
	public Expert getExpertById( @RequestParam Long id  ){
		return expertService.getExpertById(  id  );
	}
 
	@ApiOperation(value = "根据对象获取专家", notes = "根据对象获取专家")
	@RequestMapping(value = "/getExperts", method = RequestMethod.POST)
	public List<Expert> getExperts( @RequestBody Expert expert){
		return expertService.getExperts(expert);

 	}

	@ApiOperation(value = "根据对象分页获取专家", notes = "根据对象分页获取专家")
	@RequestMapping(value = "/getExpertsForPage", method = RequestMethod.POST)
	public PageDto<Expert> getExpertsForPage(@RequestBody Expert expert,
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
		Page<Expert> experts= expertService.getExpertsForPage(conditions,pageable);
	
		PageDto<Expert> pageDto = new PageDto<Expert>();
		if (experts != null) {
			pageDto.setRows( experts.getContent());
			pageDto.setTotal(experts.getTotalElements());
		} else {
			pageDto.setRows(new ArrayList<Expert>());
			pageDto.setTotal(0l);
		}
		 
		return pageDto;
 	}
	
	
}
