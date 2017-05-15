package com.mxg.bybo.rest;  
import com.mxg.bybo.service.KnowledgeCategoryService;
import com.mxg.bybo.model.KnowledgeCategory;
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
 *  KnowledgeCategoryController
 *
 * @version : Ver 1.0
 * @date	: 2017-3-29
 */
@RestController
@Api(value = "KnowledgeCategoryController", description = "相关")
@RequestMapping(value = "/knowledgeCategory")
public class KnowledgeCategoryController { 
	
	   @InitBinder   
	    public void initBinder(WebDataBinder binder) {   
	        DateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
	        dateFormat.setLenient(true);   
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   
	    }  
	
	@Autowired
	private KnowledgeCategoryService knowledgeCategoryService;
	
	@ApiOperation(value = "新增", notes = "新增")
	@RequestMapping(value = "/insertKnowledgeCategory", method = RequestMethod.POST)
	public int insertKnowledgeCategory(@RequestBody KnowledgeCategory knowledgeCategory){
		knowledgeCategory.setKnowledgeId(IdUtil.getId());
		return knowledgeCategoryService.insertKnowledgeCategory(knowledgeCategory);
	}
	@ApiOperation(value = "批量新增", notes = "批量新增")
	@RequestMapping(value = "/insertKnowledgeCategoryBatch", method = RequestMethod.POST)
	public int insertKnowledgeCategoryBatch(@RequestBody List<KnowledgeCategory> list){
		return knowledgeCategoryService.insertKnowledgeCategoryBatch(list);
	}
	@ApiOperation(value = "根据ID修改", notes = "根据ID修改")
	@RequestMapping(value = "/updateKnowledgeCategoryById", method = RequestMethod.POST)
	public int updateKnowledgeCategoryById(@RequestBody KnowledgeCategory knowledgeCategory){
		return knowledgeCategoryService.updateKnowledgeCategoryById(knowledgeCategory);
	}
	@ApiOperation(value = "根据ID删除", notes = "根据ID删除")
	@RequestMapping(value = "/deleteKnowledgeCategoryById", method = RequestMethod.GET)
	public int deleteKnowledgeCategoryById( @RequestParam Long knowledgeId  ){
		return knowledgeCategoryService.deleteKnowledgeCategoryById(  knowledgeId  );
	}
	@ApiOperation(value = "根据ID获取", notes = "根据ID获取")
	@RequestMapping(value = "/getKnowledgeCategoryById", method = RequestMethod.GET)
	public List<KnowledgeCategory> getKnowledgeCategoryById( @RequestParam Long knowledgeId  ){
		return knowledgeCategoryService.getKnowledgeCategoryById(  knowledgeId  );
	}
 
	@ApiOperation(value = "根据对象获取", notes = "根据对象获取")
	@RequestMapping(value = "/getKnowledgeCategorys", method = RequestMethod.POST)
	public List<KnowledgeCategory> getKnowledgeCategorys( @RequestBody KnowledgeCategory knowledgeCategory){
		return knowledgeCategoryService.getKnowledgeCategorys(knowledgeCategory);

 	}

	@ApiOperation(value = "根据对象分页获取", notes = "根据对象分页获取")
	@RequestMapping(value = "/getKnowledgeCategorysForPage", method = RequestMethod.POST)
	public PageDto<KnowledgeCategory> getKnowledgeCategorysForPage(@RequestBody KnowledgeCategory knowledgeCategory,
			@RequestParam(value="page", defaultValue="1")  int page,
			@RequestParam(value="rows", defaultValue="10") int rows,
			@RequestParam(required=false) String sort,
			@RequestParam(required=false) String order){
		Map<String,String> cols = new HashMap<String,String>();
		cols.put("knowledgeId", "knowledge_id");
		cols.put("categoryId", "category_id");
		Pageable pageable =null;
		if(!StringUtils.isEmpty(order)&&!StringUtils.isEmpty(sort)){
			Sort sortObj =new Sort(Sort.Direction.fromStringOrNull(order), cols.get(sort));
			pageable = new PageRequest(page-1, rows, sortObj);
		}else{
			pageable=new PageRequest(page-1, rows);
		}
		Page<KnowledgeCategory> knowledgeCategorys= knowledgeCategoryService.getKnowledgeCategorysForPage(knowledgeCategory,pageable);
	
		PageDto<KnowledgeCategory> pageDto = new PageDto<KnowledgeCategory>();
		if (knowledgeCategorys != null) {
			pageDto.setRows( knowledgeCategorys.getContent());
			pageDto.setTotal(knowledgeCategorys.getTotalElements());
		} else {
			pageDto.setRows(new ArrayList<KnowledgeCategory>());
			pageDto.setTotal(0l);
		}
		 
		return pageDto;
 

 	}
}
