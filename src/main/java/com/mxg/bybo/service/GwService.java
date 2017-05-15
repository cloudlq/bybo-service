package com.mxg.bybo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mxg.bybo.model.Doctor;
import com.mxg.bybo.model.req.RegisterReq;
import com.mxg.bybo.model.req.SearchKnowledgeReq;
import com.mxg.bybo.model.vo.CategoryVo;
import com.mxg.bybo.model.vo.DepartmentVo;
import com.mxg.bybo.model.vo.KnowledgeVo;
import com.mxg.bybo.model.vo.SearchKnowledgeVo;

/**
 * 
 */
public interface GwService {

	List<Doctor> getDoctorByDepartmentId(Long regionId,Long departmentId);

	String sendSms(HttpServletRequest req, String phone, String code);

	String register(HttpServletRequest req, RegisterReq registerReq);

	String sendForgetSms(HttpServletRequest req, String phone, String code);

	String validSmsCode(HttpServletRequest req,String smsCode);

	List<CategoryVo> getDepartmentVos(String lang);

	CategoryVo getDepartmentVoById(long id);

	KnowledgeVo getKnowledgeVoById( long knowledgeId);

	SearchKnowledgeVo searchKnowledges(SearchKnowledgeReq searReq);

	List<Doctor> getAllDoctorByDepartmentId(Long departmentId);
	

}
