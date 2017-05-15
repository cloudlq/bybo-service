package com.mxg.bybo.dao;  
import com.mxg.bybo.model.Category;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 *  CategoryDao 文章标签
 *
 * @version : Ver 1.0
 * @date	: 2017-3-29
 */
@Repository
public interface CategoryDao {
	
	int insertCategory(Category category);
	
	int insertCategoryBatch(List<Category> list);
	
	int updateCategoryById(Category category);
	
	int deleteCategoryById(@Param("id")  Long id  );
	
 	Category getCategoryById(@Param("id")  Long id  );

 	List<Category> getCategorys(@Param("category")  Category category);
 	
 	List<Category> getShowCategorys();
 	
 	Page<Category> getCategorysForPage(@Param("category")  Category category, Pageable pageable);

 	
}
