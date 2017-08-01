package com.mxg.bybo.service;

import com.mxg.bybo.model.Article;
import com.mxg.bybo.model.Department;
import com.mxg.bybo.model.Doctor;
import com.mxg.bybo.model.Expert;
import com.mxg.bybo.model.vo.KnowledgeVo;

public interface ToHtmlService {
	void toSubjectDetail(Department department);
	void toSubjectDetailAll();
	void toDoctorDetail(Doctor doctor);
	void toDoctorDetailAll();
	void toExpertDetail(Expert expert);
	void toExpertDetailAll();
	void toTopicExchangeDetail(Article article);
	void toTopicExchangeDetailAll();
	void toCooperationDetail(Article article);
	void toCooperationDetailAll();
	void toAcademicExchangeDetail(Article article);
	void toAcademicExchangeDetailAll();
	void toSocialResponsibilityDetai(Article article);
	void toSocialResponsibilityDetaiAll();
	void toMemberCommunityDetailDetai(Article article);
	void toMemberCommunityDetailDetaiAll();
	void toKnowledgeDetail(KnowledgeVo kv);
	void toKnowledgeDetailAll();
}
