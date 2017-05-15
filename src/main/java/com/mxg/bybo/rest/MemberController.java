package com.mxg.bybo.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import basic.authority.model.Party;
import basic.authority.service.PartyService;
import basic.authority.utils.PartyUtils;
import basic.common.core.exception.BaseException;

import com.mxg.bybo.model.req.ResetPwdReq;
import com.mxg.bybo.model.resp.BonustransResp;
import com.mxg.bybo.model.resp.MembershipResp;
import com.mxg.bybo.model.resp.TransactionsResp;
import com.mxg.bybo.service.HttpService;

/**
 *
 * @version 2016年10月18日 下午7:45:38
 *
 */
@Controller
@Api(value = "MemberController", description = "会员查询接口")
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private HttpService httpService;
	
	@Autowired
	private PartyService partyService;
	
	@Autowired
	private StandardPasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "/getMembership", method = RequestMethod.GET)
	@ResponseBody
	public MembershipResp getMembership(){
		String id = PartyUtils.currentParty().getPartySn();
		return httpService.getMembershipByid(id);
	}
	
	@RequestMapping(value = "/getBonustrans", method = RequestMethod.GET)
	@ResponseBody
	public BonustransResp getBonustrans() {
		String id = PartyUtils.currentParty().getPartySn();
		return httpService.getBonustrans(id);
	}
	
	@RequestMapping(value = "/getTransactions", method = RequestMethod.GET)
	@ResponseBody
	public TransactionsResp getTransactions() {
		String id = PartyUtils.currentParty().getPartySn();
		return httpService.getTransactions(id);
	}
	
	@ApiOperation(value = "重置密码", notes = "重置密码")
	@RequestMapping(value = "/restPwd", method = RequestMethod.POST)
	public int insertCustom(HttpServletRequest req, @RequestBody ResetPwdReq resetPwdReq) {
		HttpSession session = req.getSession(true);
		String smsCode = (String) session.getAttribute("smsCode");
		if(resetPwdReq.getSmsCode().isEmpty() || !resetPwdReq.getSmsCode().equals(smsCode) ){
			throw new BaseException("100203", "短信验证码不正确", null);
		}
		
		String partyId = PartyUtils.currentParty().getPartyId();
		Party updateParty = new Party();
		updateParty.setPartyId(partyId);
		updateParty.setPassword(passwordEncoder.encode(resetPwdReq.getNewPwd()));
		partyService.updatePartyById(updateParty);
		return 0;
	}
	
	/**
	 * 获取用户信息
	 * @return
	 */
	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	@ResponseBody
	public Party getUser() {
		String partyId = PartyUtils.currentParty().getPartyId();
		return partyService.getPartyById(partyId);
	}
	
    @ApiOperation(value = "根据ID修改用户", notes = "根据ID修改用户")
    @ResponseBody
    @RequestMapping(value = "/updatePartyById", method = RequestMethod.POST)
    public String updatePartyById(@RequestBody Party party) {
    	String partyId = PartyUtils.currentParty().getPartyId();
        Party updateParty = new Party();
        updateParty.setPartyId(partyId);
        updateParty.setEmail(party.getEmail());
        updateParty.setSex(party.getSex());
        updateParty.setNickName(party.getNickName());
        partyService.updatePartyById(updateParty);
        return "ok";
    }

}
