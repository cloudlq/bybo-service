package com.mxg.bybo.service;

import com.mxg.bybo.model.req.MembershipReq;
import com.mxg.bybo.model.resp.BonustransResp;
import com.mxg.bybo.model.resp.MembershipResp;
import com.mxg.bybo.model.resp.TransactionsResp;

/**
 * @date	: 2017-3-11 
 */
public interface HttpService {
	
	MembershipResp register(MembershipReq req);
	
	MembershipResp getMembershipByid(String id);
	
	BonustransResp getBonustrans(String id);

	TransactionsResp getTransactions(String id);
}
