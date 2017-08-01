package com.mxg.bybo.rest;

import io.swagger.annotations.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mxg.bybo.model.Article;
import com.mxg.bybo.model.Department;
import com.mxg.bybo.model.Doctor;
import com.mxg.bybo.model.Expert;
import com.mxg.bybo.model.req.MembershipReq;
import com.mxg.bybo.model.resp.BonustransResp;
import com.mxg.bybo.model.resp.MembershipResp;
import com.mxg.bybo.model.resp.TransactionsResp;
import com.mxg.bybo.service.ArticleService;
import com.mxg.bybo.service.DepartmentService;
import com.mxg.bybo.service.DoctorService;
import com.mxg.bybo.service.ExpertService;
import com.mxg.bybo.service.HttpService;
import com.mxg.bybo.service.ToHtmlService;

/**
 * 分布式session测试
 *
 * @author Panda520
 * @version 2016年10月18日 下午7:45:38
 *
 */
@Controller
@Api(value = "TestController", description = "分布式session测试")
@RequestMapping("/test")
public class TestController {

	@Autowired
	private HttpService httpService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private ToHtmlService toHtmlService;
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private ExpertService expertService;
	
	@Autowired
	private ArticleService articleService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	MembershipResp register(@RequestBody MembershipReq req) {
		return httpService.register(req);
	}
	
	@RequestMapping(value = "/getMembershipByid", method = RequestMethod.GET)
	@ResponseBody
	MembershipResp getMembershipByid(@RequestParam String id) {
		return httpService.getMembershipByid(id);
	}
	
	@RequestMapping(value = "/getBonustrans", method = RequestMethod.GET)
	@ResponseBody
	BonustransResp getBonustrans(@RequestParam String id) {
		return httpService.getBonustrans(id);
	}
	
	@RequestMapping(value = "/getTransactions", method = RequestMethod.GET)
	@ResponseBody
	TransactionsResp getTransactions(@RequestParam String id) {
		return httpService.getTransactions(id);
	}

	
	@RequestMapping(value = "/toSubjectHtml", method = RequestMethod.GET)
	@ResponseBody
	int toSubjectHtml(@RequestParam Long id) {
		Department d = departmentService.getDepartmentById(id);
		toHtmlService.toSubjectDetail(d);
		return 1;
	}
	
	@RequestMapping(value = "/toSubjectAllHtml", method = RequestMethod.GET)
	@ResponseBody
	int toSubjectAllHtml() {
		toHtmlService.toSubjectDetailAll();
		return 1;
	}
	

	@RequestMapping(value = "/toDoctorHtml", method = RequestMethod.GET)
	@ResponseBody
	int toDoctorHtml(@RequestParam Long id) {
		Doctor d = doctorService.getDoctorById(id);
		toHtmlService.toDoctorDetail(d);
		return 1;
	}
	
	@RequestMapping(value = "/toDoctorHtmlAll", method = RequestMethod.GET)
	@ResponseBody
	int toDoctorHtmlAll() {
		toHtmlService.toDoctorDetailAll();
		return 1;
	}
	
	@RequestMapping(value = "/toExpertHtml", method = RequestMethod.GET)
	@ResponseBody
	int toExpertHtml(@RequestParam Long id) {
		Expert d = expertService.getExpertById(id);
		toHtmlService.toExpertDetail(d);
		return 1;
	}
	
	@RequestMapping(value = "/toExpertHtmlAll", method = RequestMethod.GET)
	@ResponseBody
	int toExpertHtmlAll() {
		toHtmlService.toExpertDetailAll();
		return 1;
	}
	
	@RequestMapping(value = "/toTopicExchangeDetail", method = RequestMethod.GET)
	@ResponseBody
	int toTopicExchangeDetail(@RequestParam Long id) {
		Article d = articleService.getArticleById(id);
		toHtmlService.toTopicExchangeDetail(d);
		return 1;
	}
	
	@RequestMapping(value = "/toTopicExchangeDetailAll", method = RequestMethod.GET)
	@ResponseBody
	int toTopicExchangeDetailAll() {
		toHtmlService.toTopicExchangeDetailAll();
		return 1;
	}
	
	@RequestMapping(value = "/toAll", method = RequestMethod.GET)
	@ResponseBody
	int toAll() {
		toHtmlService.toTopicExchangeDetailAll();
		toHtmlService.toAcademicExchangeDetailAll();
		toHtmlService.toCooperationDetailAll();
		toHtmlService.toExpertDetailAll();
		toHtmlService.toDoctorDetailAll();
		toHtmlService.toMemberCommunityDetailDetaiAll();
		toHtmlService.toSocialResponsibilityDetaiAll();
		toHtmlService.toKnowledgeDetailAll();
		return 1;
	}
	
	
}
