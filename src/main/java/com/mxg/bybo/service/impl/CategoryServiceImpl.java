package com.mxg.bybo.service.impl;  
import com.mxg.bybo.service.CategoryService;
import com.mxg.bybo.dao.CategoryDao;
import com.mxg.bybo.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 *  CategoryServiceImpl
 *
 * @version : Ver 1.0
 * @date	: 2017-3-29 
 */
@Service
public class CategoryServiceImpl  implements CategoryService { 
	
	@Autowired
	private CategoryDao categoryDao;
	
	public int insertCategory(Category category){
		return categoryDao.insertCategory(category);
	}
	public int insertCategoryBatch(List<Category> list){
		return categoryDao.insertCategoryBatch(list);
	}
	public int updateCategoryById(Category category){
		return categoryDao.updateCategoryById(category);
	}
	public int deleteCategoryById(  Long id  ){
		return categoryDao.deleteCategoryById(  id  );
	}
	public Category getCategoryById(  Long id  ){
		return categoryDao.getCategoryById(  id  );
	}
 
 	public List<Category> getCategorys(Category category){
		return categoryDao.getCategorys(category);

 	}

 	public Page<Category> getCategorysForPage(Category category, Pageable pageable){
		return categoryDao.getCategorysForPage(category,pageable);

 	}
}
