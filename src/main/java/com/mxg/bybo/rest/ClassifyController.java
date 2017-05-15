package com.mxg.bybo.rest;  
import com.mxg.bybo.service.ClassifyService;
import com.mxg.bybo.model.Classify;
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
 *  ClassifyController
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11
 */
@RestController
@Api(value = "ClassifyController", description = "专家分类相关")
@RequestMapping(value = "/classify")
public class ClassifyController { 
	
	   @InitBinder   
	    public void initBinder(WebDataBinder binder) {   
	        DateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
	        dateFormat.setLenient(true);   
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   
	    }  
	
	@Autowired
	private ClassifyService classifyService;
	
	@ApiOperation(value = "新增专家分类", notes = "新增专家分类")
	@RequestMapping(value = "/insertClassify", method = RequestMethod.POST)
	public int insertClassify(@RequestBody Classify classify){
		classify.setId(IdUtil.getId());
		return classifyService.insertClassify(classify);
	}
	@ApiOperation(value = "批量新增专家分类", notes = "批量新增专家分类")
	@RequestMapping(value = "/insertClassifyBatch", method = RequestMethod.POST)
	public int insertClassifyBatch(@RequestBody List<Classify> list){
		return classifyService.insertClassifyBatch(list);
	}
	@ApiOperation(value = "根据ID修改专家分类", notes = "根据ID修改专家分类")
	@RequestMapping(value = "/updateClassifyById", method = RequestMethod.POST)
	public int updateClassifyById(@RequestBody Classify classify){
		return classifyService.updateClassifyById(classify);
	}
	@ApiOperation(value = "根据ID删除专家分类", notes = "根据ID删除专家分类")
	@RequestMapping(value = "/deleteClassifyById", method = RequestMethod.GET)
	public int deleteClassifyById( @RequestParam Long id  ){
		return classifyService.deleteClassifyById(  id  );
	}
	@ApiOperation(value = "根据ID获取专家分类", notes = "根据ID获取专家分类")
	@RequestMapping(value = "/getClassifyById", method = RequestMethod.GET)
	public Classify getClassifyById( @RequestParam Long id  ){
		return classifyService.getClassifyById(  id  );
	}
 
	@ApiOperation(value = "根据对象获取专家分类", notes = "根据对象获取专家分类")
	@RequestMapping(value = "/getClassifys", method = RequestMethod.POST)
	public List<Classify> getClassifys( @RequestBody Classify classify){
		return classifyService.getClassifys(classify);

 	}

	@ApiOperation(value = "根据对象分页获取专家分类", notes = "根据对象分页获取专家分类")
	@RequestMapping(value = "/getClassifysForPage", method = RequestMethod.POST)
	public PageDto<Classify> getClassifysForPage(@RequestBody Classify classify,
			@RequestParam(value="page", defaultValue="1")  int page,
			@RequestParam(value="rows", defaultValue="10") int rows,
			@RequestParam(required=false) String sort,
			@RequestParam(required=false) String order){
		Map<String,String> cols = new HashMap<String,String>();
		cols.put("id", "id");
		cols.put("name", "name");
		cols.put("language", "language");
		Pageable pageable =null;
		if(!StringUtils.isEmpty(order)&&!StringUtils.isEmpty(sort)){
			Sort sortObj =new Sort(Sort.Direction.fromStringOrNull(order), cols.get(sort));
			pageable = new PageRequest(page-1, rows, sortObj);
		}else{
			pageable=new PageRequest(page-1, rows);
		}
		Page<Classify> classifys= classifyService.getClassifysForPage(classify,pageable);
	
		PageDto<Classify> pageDto = new PageDto<Classify>();
		if (classifys != null) {
			pageDto.setRows( classifys.getContent());
			pageDto.setTotal(classifys.getTotalElements());
		} else {
			pageDto.setRows(new ArrayList<Classify>());
			pageDto.setTotal(0l);
		}
		 
		return pageDto;
 

 	}
}
