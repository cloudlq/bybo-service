package com.mxg.bybo.service.impl;  
import com.mxg.bybo.service.ArticleService;
import com.mxg.bybo.service.ToHtmlService;
import com.mxg.bybo.dao.ArticleDao;
import com.mxg.bybo.model.Article;
import com.mxg.common.mybatis.QueryCondition;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  ArticleServiceImpl
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11 
 */
@Service
public class ArticleServiceImpl  implements ArticleService { 
	
	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private ToHtmlService toHtmlService;
	
	public int insertArticle(Article article){
		String type = article.getCategoryId();
		if(type.equals("06")){
			toHtmlService.toTopicExchangeDetail(article);
		}else if(type.equals("02")){
			toHtmlService.toCooperationDetail(article);
		}else if(type.equals("03")){
			toHtmlService.toAcademicExchangeDetail(article);
		}else if(type.equals("04")){
			toHtmlService.toSocialResponsibilityDetai(article);
		}else if(type.equals("05")){
			toHtmlService.toMemberCommunityDetailDetai(article);
		}
		return articleDao.insertArticle(article);
	}
	public int insertArticleBatch(List<Article> list){
		return articleDao.insertArticleBatch(list);
	}
	public int updateArticleById(Article article){
		
		
		
		articleDao.updateArticleById(article);
		Article article2 = getArticleById(article.getId());
		String type = article.getCategoryId();
		if(type.equals("06")){
			toHtmlService.toTopicExchangeDetail(article2);
		}else if(type.equals("02")){
			toHtmlService.toCooperationDetail(article2);
		}else if(type.equals("03")){
			toHtmlService.toAcademicExchangeDetail(article2);
		}else if(type.equals("04")){
			toHtmlService.toSocialResponsibilityDetai(article2);
		}else if(type.equals("05")){
			toHtmlService.toMemberCommunityDetailDetai(article2);
		}
		return 1;
	}
	public int deleteArticleById(  Long id  ){
		return articleDao.deleteArticleById(  id  );
	}
	public Article getArticleById(  Long id  ){
		return articleDao.getArticleById(  id  );
	}
 
 	public List<Article> getArticles(Article article){
		return articleDao.getArticles(article);

 	}

 	public Page<Article> getArticlesForPage(List<QueryCondition> conditions , Pageable pageable){
		return articleDao.getArticlesForPage(conditions,pageable);

 	}
}
