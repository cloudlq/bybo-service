package com.mxg.bybo.model.vo;

import java.util.List;

import com.mxg.bybo.model.Category;
import com.mxg.bybo.model.Department;
import com.mxg.bybo.model.Knowledge;

public class DepartmentVo {
	private Department department;
	private List<Knowledge> knowledges;
	private List<Category> categories;

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

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

}
