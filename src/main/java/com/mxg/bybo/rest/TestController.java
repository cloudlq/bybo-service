package com.mxg.bybo.rest;

import io.swagger.annotations.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mxg.bybo.model.Department;
import com.mxg.bybo.model.req.MembershipReq;
import com.mxg.bybo.model.resp.BonustransResp;
import com.mxg.bybo.model.resp.MembershipResp;
import com.mxg.bybo.model.resp.TransactionsResp;
import com.mxg.bybo.service.DepartmentService;
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

	
	@RequestMapping(value = "/toHtml", method = RequestMethod.GET)
	@ResponseBody
	int toHtml(@RequestParam Long id) {
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
}
