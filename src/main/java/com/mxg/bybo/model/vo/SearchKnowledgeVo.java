package com.mxg.bybo.model.vo;

import java.util.List;

import com.mxg.bybo.model.Category;
import com.mxg.bybo.model.Department;
import com.mxg.bybo.model.Knowledge;

public class SearchKnowledgeVo {
	private List<Knowledge> knowledges;
	private List<Category> categories;
	private List<Department> departments;

	public List<Knowledge> getKnowledges() {
		return knowledges;
	}

	public void setKnowledges(List<Knowledge> knowledges) {
		this.knowledges = knowledges;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

}
