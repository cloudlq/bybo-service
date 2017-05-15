package com.mxg.bybo.service.impl;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mxg.bybo.model.req.MembershipReq;
import com.mxg.bybo.model.resp.BonustransResp;
import com.mxg.bybo.model.resp.MembershipResp;
import com.mxg.bybo.model.resp.TransactionsResp;
import com.mxg.bybo.service.HttpService;
import com.mxg.common.utils.HttpUtils;
import com.mxg.common.utils.HttpsUtils;
import com.mxg.common.utils.MessageUtil;

/**
 * @date : 2017-3-11
 */
@Service
public class HttpServiceImpl implements HttpService {

	@Override
	public MembershipResp register(MembershipReq req) {
		MembershipResp resp = null;
		try {

		String path = "/restful/members/register";
		String host = MessageUtil.getProperty("host");
		String url = host + path;
		
		//json 请求
		ObjectMapper mapper = new ObjectMapper();
		String jsonReq = mapper.writeValueAsString(req);
		
		//json返回
		String jsonRespStr = HttpUtils.post(url).body(jsonReq)
				.contentType("application/json").request();
//		String jsonRespStr =	HttpsUtils.post(url).body(jsonReq)
//		.contentType("application/json").connectTimeout(10).ssLNoneSocketFactory().request();
//		
		 resp = mapper.readValue(jsonRespStr, MembershipResp.class);  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}

	@Override
	public MembershipResp getMembershipByid(String id) {
		MembershipResp resp = null;
		try {
		String path = "/restful/members/"+id;
		String host = MessageUtil.getProperty("host");
		String url = host + path;

		//json返回
		String jsonRespStr = HttpUtils.get(url).contentType("application/json").request();
		ObjectMapper mapper = new ObjectMapper();
		 resp = mapper.readValue(jsonRespStr, MembershipResp.class);  
		} catch (Exception e) {
		}
		return resp;
	}

	@Override
	public BonustransResp getBonustrans(String id) {
		BonustransResp resp = null;
		try {
		String path = "/restful/members/"+id+"/bonustrans";
		String host = MessageUtil.getProperty("host");
		String url = host + path;

		//json返回
		String jsonRespStr = HttpUtils.get(url).contentType("application/json").request();
		ObjectMapper mapper = new ObjectMapper();
		 resp = mapper.readValue(jsonRespStr, BonustransResp.class);  
		} catch (Exception e) {
		}
		return resp;
	}

	@Override
	public TransactionsResp getTransactions(String id) {
		TransactionsResp resp = new TransactionsResp();
		try {
		String path = "/restful/members/"+id+"/transactions";
		String host = MessageUtil.getProperty("host");
		String url = host + path;

		//json返回
		String jsonRespStr = HttpUtils.get(url).contentType("application/json").request();
		ObjectMapper mapper = new ObjectMapper();
		 resp = mapper.readValue(jsonRespStr, TransactionsResp.class);  
		} catch (Exception e) {
		}
		return resp;
	}


}
