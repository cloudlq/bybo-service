package com.mxg.bybo.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.mxg.bybo.model.Category;
import java.util.List;

/**
 *  CategoryService
 *
 * @version : Ver 1.0
 * @date	: 2017-3-29 
 */
public interface CategoryService {
	
	int insertCategory(Category category);
	
	int insertCategoryBatch(List<Category> list);
	
	int updateCategoryById(Category category);
	
	int deleteCategoryById(Long id);
	
 	Category getCategoryById(Long id);
 
 	List<Category> getCategorys(Category category);

 	Page<Category> getCategorysForPage(Category category, Pageable pageable);
}
