package com.mxg.bybo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.formula.functions.Now;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import basic.authority.dao.PartyDao;
import basic.authority.dao.PartyRoleRelDao;
import basic.authority.enums.PartyType;
import basic.authority.model.Party;
import basic.authority.model.PartyRoleRel;
import basic.authority.service.PartyService;
import basic.common.core.exception.BaseException;
import basic.common.core.id.IdUtil;

import com.mxg.bybo.dao.CategoryDao;
import com.mxg.bybo.dao.DepartmentDao;
import com.mxg.bybo.dao.DoctorDao;
import com.mxg.bybo.dao.DoctorDepartmentRelDao;
import com.mxg.bybo.dao.KnowledgeCategoryDao;
import com.mxg.bybo.dao.KnowledgeDao;
import com.mxg.bybo.dao.SimilarKnowledgeDao;
import com.mxg.bybo.model.Category;
import com.mxg.bybo.model.Department;
import com.mxg.bybo.model.Doctor;
import com.mxg.bybo.model.Knowledge;
import com.mxg.bybo.model.SimilarKnowledge;
import com.mxg.bybo.model.req.MembershipReq;
import com.mxg.bybo.model.req.RegisterReq;
import com.mxg.bybo.model.req.SearchKnowledgeReq;
import com.mxg.bybo.model.resp.MembershipResp;
import com.mxg.bybo.model.vo.CategoryVo;
import com.mxg.bybo.model.vo.DepartmentVo;
import com.mxg.bybo.model.vo.KnowledgeVo;
import com.mxg.bybo.model.vo.SearchKnowledgeVo;
import com.mxg.bybo.service.GwService;
import com.mxg.bybo.service.HttpService;
import com.mxg.common.constant.GeneralConstant;
import com.mxg.common.mybatis.QueryCondition;
import com.mxg.common.utils.SmsSendUtil;

/**
 * GwServiceImpl
 * @version : Ver 1.0
 * @date : 2017-3-11
 */
@Service
public class GwServiceImpl implements GwService {

	@Autowired
	private DepartmentDao departmentDao;

	@Autowired
	private DoctorDepartmentRelDao doctorDepartmentRelDao;

	@Autowired
	private DoctorDao doctorDao;
	
	@Autowired
	private PartyDao partyDao;
	
	@Autowired
	private HttpService httpService;
	
	@Autowired
	private PartyService partyService;
	
	@Autowired
	private PartyRoleRelDao partyRoleRelDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	KnowledgeDao knowledgeDao;
	
	@Autowired
	SimilarKnowledgeDao similarKnowledgeDao;

	@Override
	public List<Doctor> getDoctorByDepartmentId(Long regionId, Long departmentId) {
		return doctorDao.getDoctorsByDepartmentId(regionId, departmentId);
	}

	@Override
	public String sendSms(HttpServletRequest req, String phone, String code) {
		HttpSession session = req.getSession(true);
		String validateCode = (String) session.getAttribute("verifyResult");
		if (code.toUpperCase().equals(validateCode.toUpperCase())) {
			
			Party party1 = partyDao.getPartyByUserName(phone, GeneralConstant.REG_USER);
			if(party1 != null){
				throw new BaseException("100204", "该手机号已被注册", null);
			}
			
			session.removeAttribute("verifyResult");
			int number = (int) ((Math.random() * 9 + 1) * 100000);
			session.setAttribute("smsCode", number + "");
			boolean tag = SmsSendUtil.sendSms(phone, number + "");
			if (!tag) {
				throw new BaseException("100202", "短信发送失败，请稍后再试", null);
			}
		} else {
			throw new BaseException("100201", "图形验证码不正确", null);
		}
		return "OK";
	}

	@Override
	public String register(HttpServletRequest req, RegisterReq registerReq) {
		HttpSession session = req.getSession(true);
		String smsCode = (String) session.getAttribute("smsCode");
		if(registerReq.getSmsCode().isEmpty() || !registerReq.getSmsCode().equals(smsCode) ){
			throw new BaseException("100203", "短信验证码不正确", null);
		}
		
		//邮箱
		String email = registerReq.getEmail();
		
		Party party1 = partyDao.getPartyByUserName(registerReq.getPhone(), GeneralConstant.REG_USER);
		if(party1 != null){
			throw new BaseException("100204", "该手机号已被注册", null);
		}
//		if(email != null && !email.isEmpty()){
//			Party party2 = partyDao.getPartyByUserName(registerReq.getEmail(), GeneralConstant.REG_USER);
//			if(party2 !=null){
//				throw new BaseException("100205", "该邮箱已被注册", null);
//			}
//		}
		
		MembershipReq membershipReq = new MembershipReq();
		membershipReq.setMobile(registerReq.getPhone());
		membershipReq.setName(registerReq.getNickName());
		MembershipResp membershipResp = httpService.register(membershipReq);
		if(membershipResp == null){
			throw new BaseException("100206", "网络异常，请稍后重新注册", null);
		}
		
		//添加注册用户
		Party addParty =new Party();
		String partyId = IdUtil.getId() + "";
//		if(email != null && !email.isEmpty()){
//			addParty.setUserName(email);
//			addParty.setEmail(email);
//		}else{
//			addParty.setUserName(registerReq.getPhone());
//		}
		addParty.setUserName(registerReq.getPhone());
		addParty.setPartyId( partyId );
		addParty.setEmail(email);
		addParty.setNickName( registerReq.getNickName() );
		addParty.setPhone( registerReq.getPhone() );
		addParty.setPartyType( GeneralConstant.REG_USER );
		addParty.setPassword( registerReq.getPassword() );
		addParty.setIsLocked( false );
		addParty.setLoginFailureCount( 0 );
		addParty.setStatusCode( PartyType.OPEN.getCode() );
		addParty.setSessionId( req.getSession().getId() );
		addParty.setSex(registerReq.getSex());
		addParty.setCreateTime(new Date());
		addParty.setPartySn(membershipResp.getId());//保存会员id
		
		//注册用户
		partyService.insertParty(addParty);
		
		//关联权限
		  PartyRoleRel partyRoleRel=new PartyRoleRel();
		  partyRoleRel.setPartyId(partyId);
	      partyRoleRel.setRoleId(GeneralConstant.REG_USER_ROLE);
	      partyRoleRelDao.insertPartyRoleRel(partyRoleRel);
		
		return "OK";
	}

	@Override
	public String sendForgetSms(HttpServletRequest req, String phone,
			String code) {
		
		HttpSession session = req.getSession(true);
		String validateCode = (String) session.getAttribute("verifyResult");
		if (code.toUpperCase().equals(validateCode.toUpperCase())) {
			Party party = new Party();
			party.setPhone(phone);
			List<Party> partys = partyService.getPartys(party);
			
			if(partys.size() == 0){
				throw new BaseException("100205", "该手机号用户不存在", null);
			}
			
			session.removeAttribute("verifyResult");
			int number = (int) ((Math.random() * 9 + 1) * 100000);
			session.setAttribute("forgetSmsCode", number + "");
			boolean tag = SmsSendUtil.sendSms(phone, number + "");
			if (!tag) {
				throw new BaseException("100202", "短信发送失败，请稍后再试", null);
			}
		} else {
			throw new BaseException("100201", "图形验证码不正确", null);
		}
		return "OK";
	}

	@Override
	public String validSmsCode(HttpServletRequest req,String validSmsCode) {
		HttpSession session = req.getSession(true);
		String smsCode = (String) session.getAttribute("forgetSmsCode");
		if(validSmsCode.isEmpty() || !validSmsCode.equals(smsCode) ){
			throw new BaseException("100203", "短信验证码不正确", null);
		}
		return "OK";
	}

	@Override
	public List<CategoryVo> getDepartmentVos(String lang) {
		
		List<CategoryVo> categoryVos = new ArrayList<CategoryVo>();
		List<Category> categories = categoryDao.getShowCategorys();
		for(Category c :categories){
			CategoryVo vo = new CategoryVo();
			vo.setCategory(c);
			
			List<QueryCondition> conditions = new ArrayList<QueryCondition>();
			conditions.add(new QueryCondition("category_ids", c.getId(),QueryCondition.TYPE_LIKE));
			 List<Knowledge> ks = knowledgeDao.getKnowledgesByConditions(conditions);
			 vo.setKnowledges(ks);
			 categoryVos.add(vo);
		}
		
		return categoryVos;
	}

	@Override
	public CategoryVo getDepartmentVoById(long id) {
		CategoryVo vo =new CategoryVo();
		Category c = categoryDao.getCategoryById(id);
		vo.setCategory(c);
		
		List<QueryCondition> conditions = new ArrayList<QueryCondition>();
		conditions.add(new QueryCondition("category_ids", c.getId(),QueryCondition.TYPE_LIKE));
		 List<Knowledge> ks = knowledgeDao.getKnowledgesByConditions(conditions);
		 vo.setKnowledges(ks);

		return vo;
	}

	@Override
	public KnowledgeVo getKnowledgeVoById(long knowledgeId) {
		KnowledgeVo vo = new KnowledgeVo();
		Knowledge knowledge = knowledgeDao.getKnowledgeById(knowledgeId);
		
		//相似文章
		List<SimilarKnowledge> sks = similarKnowledgeDao.getSimilarKnowledgeById(knowledgeId);
		List<Knowledge> knowledges = new ArrayList<Knowledge>();
		for(SimilarKnowledge sk :sks){
			knowledges.add(knowledgeDao.getKnowledgeById(sk.getSimilarId()));
		}
		vo.setKnowledges(knowledges);
		vo.setKnowledge(knowledge);
		
		List<Category> cs = new ArrayList<Category>();
		String [] categoryIds =	knowledge.getCategoryIds().split(",");
		String [] categoryNames= knowledge.getCategoryNames().split(",");
		for(int i = 0;i<categoryIds.length;i++ ){
			try {
				Category c = new Category();
				c.setId(Long.parseLong(categoryIds[i]));
				c.setName(categoryNames[i]);
				cs.add(c);
			} catch (Exception e) {
			}

		}
		vo.setCategories(cs);
		
		return vo;
	}



	@Override
	public SearchKnowledgeVo searchKnowledges(SearchKnowledgeReq searReq) {
		List<QueryCondition> conditions = new ArrayList<QueryCondition>();
		//语言
		String lang = searReq.getLang();
		conditions.add(new QueryCondition("language", lang));
		
		//搜索关键字
		if(searReq.getKeyName() != null && !searReq.getKeyName().isEmpty()){
			conditions.add(new QueryCondition("title", searReq.getKeyName(), QueryCondition.TYPE_LIKE));
		}
		
		//根据父级搜索
		if(searReq.getDepartmentId() != null ){
			conditions.add(new QueryCondition("department_id", searReq.getDepartmentId()));
		}
		
		//根据子级搜索
		if(searReq.getCategoryId() != null ){
			conditions.add(new QueryCondition("category_ids", searReq.getCategoryId(), QueryCondition.TYPE_LIKE));
		}
		
		List<Knowledge> knowledges = knowledgeDao.getKnowledgesByConditions(conditions);
		List<Long> departmentIds = new ArrayList<Long>();
		List<Department> departments = new ArrayList<Department>();
		List<Long> categoryIds = new ArrayList<Long>();
		List<Category> categories = new ArrayList<Category>();
		for(Knowledge k:knowledges){
			if(!departmentIds.contains(k.getDepartmentId())){
				departmentIds.add(k.getDepartmentId());
				Department department = new Department();
				department.setId(k.getDepartmentId());
				department.setName(k.getDepartmentName());
				departments.add(department);
			}
			String cIds[] = k.getCategoryIds().split(",");
			String cNames[]=k.getCategoryNames().split(",");
			for(int i = 0;i<cIds.length;i++){
				try {
					long cId = Long.parseLong(cIds[i]);
					if(!categoryIds.contains(cId)){
						Category c = new Category();
						c.setId(cId);
						c.setName(cNames[i]);
						categoryIds.add(cId);
						categories.add(c);
					}
				} catch (Exception e) {}
			}
			
		}
		SearchKnowledgeVo vo =new SearchKnowledgeVo();
		vo.setCategories(categories);
		vo.setDepartments(departments);
		vo.setKnowledges(knowledges);
		return vo;
	}

	@Override
	public List<Doctor> getAllDoctorByDepartmentId(Long departmentId) {
		return doctorDao.getAllDoctorsByDepartmentId(departmentId);
	}

}
