package com.mxg.bybo.rest;  
import com.mxg.bybo.service.ExpertClassifyRelService;
import com.mxg.bybo.model.ExpertClassifyRel;
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
 *  ExpertClassifyRelController
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11
 */
@RestController
@Api(value = "ExpertClassifyRelController", description = "专家-分类关联相关")
@RequestMapping(value = "/expertClassifyRel")
public class ExpertClassifyRelController { 
	
	   @InitBinder   
	    public void initBinder(WebDataBinder binder) {   
	        DateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
	        dateFormat.setLenient(true);   
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   
	    }  
	
	@Autowired
	private ExpertClassifyRelService expertClassifyRelService;
	
	@ApiOperation(value = "新增专家-分类关联", notes = "新增专家-分类关联")
	@RequestMapping(value = "/insertExpertClassifyRel", method = RequestMethod.POST)
	public int insertExpertClassifyRel(@RequestBody ExpertClassifyRel expertClassifyRel){
		expertClassifyRel.setExpertId(IdUtil.getId());
		return expertClassifyRelService.insertExpertClassifyRel(expertClassifyRel);
	}
	@ApiOperation(value = "批量新增专家-分类关联", notes = "批量新增专家-分类关联")
	@RequestMapping(value = "/insertExpertClassifyRelBatch", method = RequestMethod.POST)
	public int insertExpertClassifyRelBatch(@RequestBody List<ExpertClassifyRel> list){
		return expertClassifyRelService.insertExpertClassifyRelBatch(list);
	}
	@ApiOperation(value = "根据ID修改专家-分类关联", notes = "根据ID修改专家-分类关联")
	@RequestMapping(value = "/updateExpertClassifyRelById", method = RequestMethod.POST)
	public int updateExpertClassifyRelById(@RequestBody ExpertClassifyRel expertClassifyRel){
		return expertClassifyRelService.updateExpertClassifyRelById(expertClassifyRel);
	}
	@ApiOperation(value = "根据ID删除专家-分类关联", notes = "根据ID删除专家-分类关联")
	@RequestMapping(value = "/deleteExpertClassifyRelById", method = RequestMethod.GET)
	public int deleteExpertClassifyRelById( @RequestParam Long expertId  ){
		return expertClassifyRelService.deleteExpertClassifyRelById(  expertId  );
	}
	@ApiOperation(value = "根据ID获取专家-分类关联", notes = "根据ID获取专家-分类关联")
	@RequestMapping(value = "/getExpertClassifyRelById", method = RequestMethod.GET)
	public ExpertClassifyRel getExpertClassifyRelById( @RequestParam Long expertId  ){
		return expertClassifyRelService.getExpertClassifyRelById(  expertId  );
	}
 
	@ApiOperation(value = "根据对象获取专家-分类关联", notes = "根据对象获取专家-分类关联")
	@RequestMapping(value = "/getExpertClassifyRels", method = RequestMethod.POST)
	public List<ExpertClassifyRel> getExpertClassifyRels( @RequestBody ExpertClassifyRel expertClassifyRel){
		return expertClassifyRelService.getExpertClassifyRels(expertClassifyRel);
 	}

	@ApiOperation(value = "根据对象分页获取专家-分类关联", notes = "根据对象分页获取专家-分类关联")
	@RequestMapping(value = "/getExpertClassifyRelsForPage", method = RequestMethod.POST)
	public PageDto<ExpertClassifyRel> getExpertClassifyRelsForPage(@RequestBody ExpertClassifyRel expertClassifyRel,
			@RequestParam(value="page", defaultValue="1")  int page,
			@RequestParam(value="rows", defaultValue="10") int rows,
			@RequestParam(required=false) String sort,
			@RequestParam(required=false) String order){
		Map<String,String> cols = new HashMap<String,String>();
		cols.put("expertId", "expert_id");
		cols.put("classifyId", "classify_id");
		Pageable pageable =null;
		if(!StringUtils.isEmpty(order)&&!StringUtils.isEmpty(sort)){
			Sort sortObj =new Sort(Sort.Direction.fromStringOrNull(order), cols.get(sort));
			pageable = new PageRequest(page-1, rows, sortObj);
		}else{
			pageable=new PageRequest(page-1, rows);
		}
		Page<ExpertClassifyRel> expertClassifyRels= expertClassifyRelService.getExpertClassifyRelsForPage(expertClassifyRel,pageable);
	
		PageDto<ExpertClassifyRel> pageDto = new PageDto<ExpertClassifyRel>();
		if (expertClassifyRels != null) {
			pageDto.setRows( expertClassifyRels.getContent());
			pageDto.setTotal(expertClassifyRels.getTotalElements());
		} else {
			pageDto.setRows(new ArrayList<ExpertClassifyRel>());
			pageDto.setTotal(0l);
		}
		 
		return pageDto;
 

 	}
}
