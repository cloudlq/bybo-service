package com.mxg.bybo.rest;  
import com.mxg.bybo.service.SimilarKnowledgeService;
import com.mxg.bybo.model.SimilarKnowledge;
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
 *  SimilarKnowledgeController
 *
 * @version : Ver 1.0
 * @date	: 2017-3-29
 */
@RestController
@Api(value = "SimilarKnowledgeController", description = "相似文章关联相关")
@RequestMapping(value = "/similarKnowledge")
public class SimilarKnowledgeController { 
	
	   @InitBinder   
	    public void initBinder(WebDataBinder binder) {   
	        DateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
	        dateFormat.setLenient(true);   
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   
	    }  
	
	@Autowired
	private SimilarKnowledgeService similarKnowledgeService;
	
	@ApiOperation(value = "新增相似文章关联", notes = "新增相似文章关联")
	@RequestMapping(value = "/insertSimilarKnowledge", method = RequestMethod.POST)
	public int insertSimilarKnowledge(@RequestBody SimilarKnowledge similarKnowledge){
		similarKnowledge.setKnowledgeId(IdUtil.getId());
		return similarKnowledgeService.insertSimilarKnowledge(similarKnowledge);
	}
	@ApiOperation(value = "批量新增相似文章关联", notes = "批量新增相似文章关联")
	@RequestMapping(value = "/insertSimilarKnowledgeBatch", method = RequestMethod.POST)
	public int insertSimilarKnowledgeBatch(@RequestBody List<SimilarKnowledge> list){
		return similarKnowledgeService.insertSimilarKnowledgeBatch(list);
	}
	@ApiOperation(value = "根据ID修改相似文章关联", notes = "根据ID修改相似文章关联")
	@RequestMapping(value = "/updateSimilarKnowledgeById", method = RequestMethod.POST)
	public int updateSimilarKnowledgeById(@RequestBody SimilarKnowledge similarKnowledge){
		return similarKnowledgeService.updateSimilarKnowledgeById(similarKnowledge);
	}
	@ApiOperation(value = "根据ID删除相似文章关联", notes = "根据ID删除相似文章关联")
	@RequestMapping(value = "/deleteSimilarKnowledgeById", method = RequestMethod.GET)
	public int deleteSimilarKnowledgeById( @RequestParam Long knowledgeId  ){
		return similarKnowledgeService.deleteSimilarKnowledgeById(  knowledgeId  );
	}
	@ApiOperation(value = "根据ID获取相似文章关联", notes = "根据ID获取相似文章关联")
	@RequestMapping(value = "/getSimilarKnowledgeById", method = RequestMethod.GET)
	public List<SimilarKnowledge> getSimilarKnowledgeById( @RequestParam Long knowledgeId  ){
		return similarKnowledgeService.getSimilarKnowledgeById(  knowledgeId  );
	}
 
	@ApiOperation(value = "根据对象获取相似文章关联", notes = "根据对象获取相似文章关联")
	@RequestMapping(value = "/getSimilarKnowledges", method = RequestMethod.POST)
	public List<SimilarKnowledge> getSimilarKnowledges( @RequestBody SimilarKnowledge similarKnowledge){
		return similarKnowledgeService.getSimilarKnowledges(similarKnowledge);

 	}

	@ApiOperation(value = "根据对象分页获取相似文章关联", notes = "根据对象分页获取相似文章关联")
	@RequestMapping(value = "/getSimilarKnowledgesForPage", method = RequestMethod.POST)
	public PageDto<SimilarKnowledge> getSimilarKnowledgesForPage(@RequestBody SimilarKnowledge similarKnowledge,
			@RequestParam(value="page", defaultValue="1")  int page,
			@RequestParam(value="rows", defaultValue="10") int rows,
			@RequestParam(required=false) String sort,
			@RequestParam(required=false) String order){
		Map<String,String> cols = new HashMap<String,String>();
		cols.put("knowledgeId", "knowledge_id");
		cols.put("similarId", "similar_id");
		Pageable pageable =null;
		if(!StringUtils.isEmpty(order)&&!StringUtils.isEmpty(sort)){
			Sort sortObj =new Sort(Sort.Direction.fromStringOrNull(order), cols.get(sort));
			pageable = new PageRequest(page-1, rows, sortObj);
		}else{
			pageable=new PageRequest(page-1, rows);
		}
		Page<SimilarKnowledge> similarKnowledges= similarKnowledgeService.getSimilarKnowledgesForPage(similarKnowledge,pageable);
	
		PageDto<SimilarKnowledge> pageDto = new PageDto<SimilarKnowledge>();
		if (similarKnowledges != null) {
			pageDto.setRows( similarKnowledges.getContent());
			pageDto.setTotal(similarKnowledges.getTotalElements());
		} else {
			pageDto.setRows(new ArrayList<SimilarKnowledge>());
			pageDto.setTotal(0l);
		}
		 
		return pageDto;
 

 	}
}
