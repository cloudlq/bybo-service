package com.mxg.bybo.model.resp;

import java.util.List;

import com.mxg.bybo.model.Department;
import com.mxg.bybo.model.Doctor;

public class DepartmentResp {
	
	private Department department;
	
	private List<Doctor> doctors;

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}

}
