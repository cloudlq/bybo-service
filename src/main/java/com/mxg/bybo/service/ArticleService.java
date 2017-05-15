package com.mxg.bybo.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mxg.bybo.model.Article;
import com.mxg.common.mybatis.QueryCondition;

import java.util.List;

/**
 *  ArticleService
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11 
 */
public interface ArticleService {
	
	int insertArticle(Article article);
	
	int insertArticleBatch(List<Article> list);
	
	int updateArticleById(Article article);
	
	int deleteArticleById(Long id);
	
 	Article getArticleById(Long id);
 
 	List<Article> getArticles(Article article);

 	Page<Article> getArticlesForPage(List<QueryCondition> conditions , Pageable pageable);
}
