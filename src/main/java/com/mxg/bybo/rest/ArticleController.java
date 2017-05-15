package com.mxg.bybo.rest;  
import com.mxg.bybo.service.ArticleService;
import com.mxg.bybo.model.Article;
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
 *  ArticleController
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11
 */
@RestController
@Api(value = "ArticleController", description = "案例文章相关")
@RequestMapping(value = "/article")
public class ArticleController { 
	
	   @InitBinder   
	    public void initBinder(WebDataBinder binder) {   
	        DateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
	        dateFormat.setLenient(true);   
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   
	    }  
	
	@Autowired
	private ArticleService articleService;
	
	@ApiOperation(value = "新增案例文章", notes = "新增案例文章")
	@RequestMapping(value = "/insertArticle", method = RequestMethod.POST)
	public int insertArticle(@RequestBody Article article){
		article.setId(IdUtil.getId());
		return articleService.insertArticle(article);
	}
	@ApiOperation(value = "批量新增案例文章", notes = "批量新增案例文章")
	@RequestMapping(value = "/insertArticleBatch", method = RequestMethod.POST)
	public int insertArticleBatch(@RequestBody List<Article> list){
		return articleService.insertArticleBatch(list);
	}
	@ApiOperation(value = "根据ID修改案例文章", notes = "根据ID修改案例文章")
	@RequestMapping(value = "/updateArticleById", method = RequestMethod.POST)
	public int updateArticleById(@RequestBody Article article){
		return articleService.updateArticleById(article);
	}
	@ApiOperation(value = "根据ID删除案例文章", notes = "根据ID删除案例文章")
	@RequestMapping(value = "/deleteArticleById", method = RequestMethod.GET)
	public int deleteArticleById( @RequestParam Long id  ){
		return articleService.deleteArticleById(  id  );
	}
	@ApiOperation(value = "根据ID获取案例文章", notes = "根据ID获取案例文章")
	@RequestMapping(value = "/getArticleById", method = RequestMethod.GET)
	public Article getArticleById( @RequestParam Long id  ){
		return articleService.getArticleById(  id  );
	}
 
	@ApiOperation(value = "根据对象获取案例文章", notes = "根据对象获取案例文章")
	@RequestMapping(value = "/getArticles", method = RequestMethod.POST)
	public List<Article> getArticles( @RequestBody Article article){
		return articleService.getArticles(article);

 	}

	@ApiOperation(value = "根据对象分页获取案例文章", notes = "根据对象分页获取案例文章")
	@RequestMapping(value = "/getArticlesForPage", method = RequestMethod.POST)
	public PageDto<Article> getArticlesForPage(@RequestBody Article article,
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
		cols.put("categoryId", "category_id");
		cols.put("categoryName", "category_name");
		cols.put("regionId", "region_id");
		cols.put("imageUrl", "image_url");
		cols.put("language", "language");
		cols.put("author", "author");
		Pageable pageable =null;
		if(!StringUtils.isEmpty(order)&&!StringUtils.isEmpty(sort)){
			Sort sortObj =new Sort(Sort.Direction.fromStringOrNull(order), cols.get(sort));
			pageable = new PageRequest(page-1, rows, sortObj);
		}else{
			pageable=new PageRequest(page-1, rows);
		}
		List<QueryCondition> conditions = new ArrayList<QueryCondition>();
		
		
		Page<Article> articles= articleService.getArticlesForPage(conditions,pageable);
	
		PageDto<Article> pageDto = new PageDto<Article>();
		if (articles != null) {
			pageDto.setRows( articles.getContent());
			pageDto.setTotal(articles.getTotalElements());
		} else {
			pageDto.setRows(new ArrayList<Article>());
			pageDto.setTotal(0l);
		}
		 
		return pageDto;
 

 	}
}
