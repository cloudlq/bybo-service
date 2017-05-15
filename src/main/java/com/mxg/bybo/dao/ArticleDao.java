package com.mxg.bybo.dao;  
import com.mxg.bybo.model.Article;
import com.mxg.common.mybatis.QueryCondition;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  ArticleDao 案例文章
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-3-11
 */
@Repository
public interface ArticleDao {
	
	int insertArticle(Article article);
	
	int insertArticleBatch(List<Article> list);
	
	int updateArticleById(Article article);
	
	int deleteArticleById(@Param("id")  Long id  );
	
 	Article getArticleById(@Param("id")  Long id  );

 	List<Article> getArticles(@Param("article")  Article article);

 	Page<Article> getArticlesForPage(@Param("conditions") List<QueryCondition> conditions, Pageable pageable);

}
