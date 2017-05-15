package com.mxg.bybo.rest;  
import com.mxg.bybo.service.KnowledgeService;
import com.mxg.bybo.model.Knowledge;
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
 *  KnowledgeController
 *
 * @version : Ver 1.0
 * @date	: 2017-3-29
 */
@RestController
@Api(value = "KnowledgeController", description = "知识库相关")
@RequestMapping(value = "/knowledge")
public class KnowledgeController { 
	
	   @InitBinder   
	    public void initBinder(WebDataBinder binder) {   
	        DateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
	        dateFormat.setLenient(true);   
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   
	    }  
	
	@Autowired
	private KnowledgeService knowledgeService;
	
	@ApiOperation(value = "新增知识库", notes = "新增知识库")
	@RequestMapping(value = "/insertKnowledge", method = RequestMethod.POST)
	public int insertKnowledge(@RequestBody Knowledge knowledge){
		knowledge.setId(IdUtil.getId());
		return knowledgeService.insertKnowledge(knowledge);
	}
	@ApiOperation(value = "批量新增知识库", notes = "批量新增知识库")
	@RequestMapping(value = "/insertKnowledgeBatch", method = RequestMethod.POST)
	public int insertKnowledgeBatch(@RequestBody List<Knowledge> list){
		return knowledgeService.insertKnowledgeBatch(list);
	}
	@ApiOperation(value = "根据ID修改知识库", notes = "根据ID修改知识库")
	@RequestMapping(value = "/updateKnowledgeById", method = RequestMethod.POST)
	public int updateKnowledgeById(@RequestBody Knowledge knowledge){
		return knowledgeService.updateKnowledgeById(knowledge);
	}
	@ApiOperation(value = "根据ID删除知识库", notes = "根据ID删除知识库")
	@RequestMapping(value = "/deleteKnowledgeById", method = RequestMethod.GET)
	public int deleteKnowledgeById( @RequestParam Long id  ){
		return knowledgeService.deleteKnowledgeById(  id  );
	}
	@ApiOperation(value = "根据ID获取知识库", notes = "根据ID获取知识库")
	@RequestMapping(value = "/getKnowledgeById", method = RequestMethod.GET)
	public Knowledge getKnowledgeById( @RequestParam Long id  ){
		return knowledgeService.getKnowledgeById(  id  );
	}
 
	@ApiOperation(value = "根据对象获取知识库", notes = "根据对象获取知识库")
	@RequestMapping(value = "/getKnowledges", method = RequestMethod.POST)
	public List<Knowledge> getKnowledges( @RequestBody Knowledge knowledge){
		return knowledgeService.getKnowledges(knowledge);

 	}

	@ApiOperation(value = "根据对象分页获取知识库", notes = "根据对象分页获取知识库")
	@RequestMapping(value = "/getKnowledgesForPage", method = RequestMethod.POST)
	public PageDto<Knowledge> getKnowledgesForPage(@RequestBody Knowledge knowledge,
			@RequestParam(value="page", defaultValue="1")  int page,
			@RequestParam(value="rows", defaultValue="10") int rows,
			@RequestParam(required=false) String sort,
			@RequestParam(required=false) String order){
		Map<String,String> cols = new HashMap<String,String>();
		cols.put("id", "id");
		cols.put("title", "title");
		cols.put("content", "content");
		cols.put("addTime", "add_time");
		cols.put("updateTime", "update_time");
		cols.put("author", "author");
		cols.put("language", "language");
		Pageable pageable =null;
		if(!StringUtils.isEmpty(order)&&!StringUtils.isEmpty(sort)){
			Sort sortObj =new Sort(Sort.Direction.fromStringOrNull(order), cols.get(sort));
			pageable = new PageRequest(page-1, rows, sortObj);
		}else{
			pageable=new PageRequest(page-1, rows);
		}
		Page<Knowledge> knowledges= knowledgeService.getKnowledgesForPage(knowledge,pageable);
	
		PageDto<Knowledge> pageDto = new PageDto<Knowledge>();
		if (knowledges != null) {
			pageDto.setRows( knowledges.getContent());
			pageDto.setTotal(knowledges.getTotalElements());
		} else {
			pageDto.setRows(new ArrayList<Knowledge>());
			pageDto.setTotal(0l);
		}
		 
		return pageDto;
 	}
}
