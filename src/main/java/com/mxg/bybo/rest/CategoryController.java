package com.mxg.bybo.rest;  
import com.mxg.bybo.service.CategoryService;
import com.mxg.bybo.model.Category;
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
 *  CategoryController
 *
 * @version : Ver 1.0
 * @date	: 2017-3-29
 */
@RestController
@Api(value = "CategoryController", description = "文章标签相关")
@RequestMapping(value = "/category")
public class CategoryController { 
	
	   @InitBinder   
	    public void initBinder(WebDataBinder binder) {   
	        DateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
	        dateFormat.setLenient(true);   
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   
	    }  
	
	@Autowired
	private CategoryService categoryService;
	
	@ApiOperation(value = "新增文章标签", notes = "新增文章标签")
	@RequestMapping(value = "/insertCategory", method = RequestMethod.POST)
	public int insertCategory(@RequestBody Category category){
		category.setId(IdUtil.getId());
		return categoryService.insertCategory(category);
	}
	@ApiOperation(value = "批量新增文章标签", notes = "批量新增文章标签")
	@RequestMapping(value = "/insertCategoryBatch", method = RequestMethod.POST)
	public int insertCategoryBatch(@RequestBody List<Category> list){
		return categoryService.insertCategoryBatch(list);
	}
	@ApiOperation(value = "根据ID修改文章标签", notes = "根据ID修改文章标签")
	@RequestMapping(value = "/updateCategoryById", method = RequestMethod.POST)
	public int updateCategoryById(@RequestBody Category category){
		return categoryService.updateCategoryById(category);
	}
	@ApiOperation(value = "根据ID删除文章标签", notes = "根据ID删除文章标签")
	@RequestMapping(value = "/deleteCategoryById", method = RequestMethod.GET)
	public int deleteCategoryById( @RequestParam Long id  ){
		return categoryService.deleteCategoryById(  id  );
	}
	@ApiOperation(value = "根据ID获取文章标签", notes = "根据ID获取文章标签")
	@RequestMapping(value = "/getCategoryById", method = RequestMethod.GET)
	public Category getCategoryById( @RequestParam Long id  ){
		return categoryService.getCategoryById(  id  );
	}
 
	@ApiOperation(value = "根据对象获取文章标签", notes = "根据对象获取文章标签")
	@RequestMapping(value = "/getCategorys", method = RequestMethod.POST)
	public List<Category> getCategorys( @RequestBody Category category){
		return categoryService.getCategorys(category);

 	}

	@ApiOperation(value = "根据对象分页获取文章标签", notes = "根据对象分页获取文章标签")
	@RequestMapping(value = "/getCategorysForPage", method = RequestMethod.POST)
	public PageDto<Category> getCategorysForPage(@RequestBody Category category,
			@RequestParam(value="page", defaultValue="1")  int page,
			@RequestParam(value="rows", defaultValue="10") int rows,
			@RequestParam(required=false) String sort,
			@RequestParam(required=false) String order){
		Map<String,String> cols = new HashMap<String,String>();
		cols.put("id", "id");
		cols.put("name", "name");
		cols.put("departmentId", "department_id");
		Pageable pageable =null;
		if(!StringUtils.isEmpty(order)&&!StringUtils.isEmpty(sort)){
			Sort sortObj =new Sort(Sort.Direction.fromStringOrNull(order), cols.get(sort));
			pageable = new PageRequest(page-1, rows, sortObj);
		}else{
			pageable=new PageRequest(page-1, rows);
		}
		Page<Category> categorys= categoryService.getCategorysForPage(category,pageable);
	
		PageDto<Category> pageDto = new PageDto<Category>();
		if (categorys != null) {
			pageDto.setRows( categorys.getContent());
			pageDto.setTotal(categorys.getTotalElements());
		} else {
			pageDto.setRows(new ArrayList<Category>());
			pageDto.setTotal(0l);
		}
		 
		return pageDto;
 

 	}
}
