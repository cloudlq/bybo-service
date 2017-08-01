package com.mxg.bybo.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import basic.authority.model.Party;
import basic.authority.model.PartyRoleRel;
import basic.authority.service.PartyRoleRelService;
import basic.authority.service.PartyService;
import basic.authority.utils.PartyUtils;
import basic.common.core.dto.PageDto;
import basic.common.core.exception.BaseException;
import basic.common.core.id.IdUtil;

import com.mxg.bybo.model.Article;
import com.mxg.bybo.model.Category;
import com.mxg.bybo.model.Classify;
import com.mxg.bybo.model.Department;
import com.mxg.bybo.model.Doctor;
import com.mxg.bybo.model.DoctorDepartmentRel;
import com.mxg.bybo.model.Expert;
import com.mxg.bybo.model.ExpertClassifyRel;
import com.mxg.bybo.model.GreenChanne;
import com.mxg.bybo.model.Knowledge;
import com.mxg.bybo.model.MenuVo;
import com.mxg.bybo.model.Region;
import com.mxg.bybo.model.RegionDepartment;
import com.mxg.bybo.model.Rollpic;
import com.mxg.bybo.model.SimilarKnowledge;
import com.mxg.bybo.model.Store;
import com.mxg.bybo.model.req.SimilarKnowledgeReq;
import com.mxg.bybo.model.vo.KnowledgeVo;
import com.mxg.bybo.service.ArticleService;
import com.mxg.bybo.service.CategoryService;
import com.mxg.bybo.service.ClassifyService;
import com.mxg.bybo.service.DepartmentService;
import com.mxg.bybo.service.DoctorDepartmentRelService;
import com.mxg.bybo.service.DoctorService;
import com.mxg.bybo.service.ExpertClassifyRelService;
import com.mxg.bybo.service.ExpertService;
import com.mxg.bybo.service.GreenChanneService;
import com.mxg.bybo.service.GwService;
import com.mxg.bybo.service.KnowledgeCategoryService;
import com.mxg.bybo.service.KnowledgeService;
import com.mxg.bybo.service.RegionDepartmentService;
import com.mxg.bybo.service.RegionService;
import com.mxg.bybo.service.RollpicService;
import com.mxg.bybo.service.SimilarKnowledgeService;
import com.mxg.bybo.service.StoreService;
import com.mxg.bybo.service.ToHtmlService;
import com.mxg.common.mybatis.QueryCondition;
import com.mxg.common.utils.GeneralUtils;

/**
 * ArticleController
 *
 * @version : Ver 1.0
 * @author : panda
 * @date : 2017-3-11
 */
@RestController
@Api(value = "AdminController", description = "后台管理系统")
@RequestMapping(value = "/admin")
public class AdminController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	@Autowired
	private RollpicService rollpicService;

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private DoctorDepartmentRelService doctorDepartmentRelService;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private ExpertService expertService;

	@Autowired
	private ClassifyService classifyService;

	@Autowired
	private ExpertClassifyRelService expertClassifyRelService;

	@Autowired
	private RegionService regionService;

	@Autowired
	private StoreService storeService;

	@Autowired
	private PartyService partyService;

	@Autowired
	KnowledgeCategoryService knowledgeCategoryService;

	@Autowired
	private PartyRoleRelService partyRoleRelService;

	@Autowired
	private SimilarKnowledgeService similarKnowledgeService;
	
	@Autowired
	private GreenChanneService greenChanneService;

	@Autowired
	private  RegionDepartmentService regionDepartmentService;
	
	@Autowired
	private ToHtmlService toHtmlService;
	
	@Autowired
	private GwService gwService;
	
	@ApiOperation(value = "上传文件", notes = "上传文件")
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String addLog(@RequestParam("fileUpload") CommonsMultipartFile file,
			HttpServletRequest request) throws Exception {
		return saveFile(file, request);
	}

	@ApiOperation(value = "富文本上传文件", notes = "上传文件")
	@RequestMapping(value = "/webUpload", method = RequestMethod.POST)
	public String addLog(@RequestParam("upload") CommonsMultipartFile file,
			HttpServletRequest request, String CKEditorFuncNum)
			throws Exception {
		String fileName = saveFile(file, request);
		String val = "";
		val += "<script type=\"text/javascript\">";
		val += "window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum
				+ ",'" + fileName + "','')";
		val += "</script>";
		return val;
	}

	public String saveFile(CommonsMultipartFile file, HttpServletRequest request)
			throws Exception {
		List<String> types = new ArrayList<String>();

		types.add("image/bmp");
		types.add("image/jpg");
		types.add("image/jpeg");
		types.add("image/png");
		// 获取文件类型
		String type = file.getContentType();
		if (!types.contains(type)) {
			throw new BaseException("009098", "文件格式不正确", null);
		}

		// 获取文件名称
		String name = file.getOriginalFilename();
		String md5Name = md5(file);
		String[] strs = name.split("\\.");
		name = md5Name + "." + strs[strs.length - 1];
		String basePath = request.getSession().getServletContext()
				.getRealPath("/");
		String commonBase = "/byboUpload/"
				+ GeneralUtils.date2String(new Date(), "yyyyMMdd") + "/";
		File baseFile = new File(basePath);
		basePath = baseFile.getParent();
		basePath += commonBase;
		File folder = new File(basePath);
		// 如果文件夹不存在则创建
		if (!folder.exists() && !folder.isDirectory()) {
			folder.mkdirs();
		}
		String path = basePath + name;
		String returnPath = commonBase + name;
		File localFile = new File(path);
		try {
			if (!localFile.exists()) {
				file.transferTo(localFile);
			}

			return returnPath;

		} catch (Exception e) {
			throw new BaseException("009099", "文件上传失败", null);
		}
	}

	public String md5(CommonsMultipartFile upload) throws Exception {
		byte[] uploadBytes = upload.getBytes();
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] digest = md5.digest(uploadBytes);
		String hashString = new BigInteger(1, digest).toString(16);
		return hashString.toUpperCase();
	}

	@ApiOperation(value = "批量上传文件", notes = "批量上传文件")
	@RequestMapping(value = "/uploadAll", method = RequestMethod.POST)
	public List<String> filesUpload(
			@RequestParam("fileUpload") CommonsMultipartFile[] files,
			HttpServletRequest request) throws Exception {
		List<String> paths = new ArrayList<String>();
		// 判断file数组不能为空并且长度大于0
		if (files != null && files.length > 0) {
			// 循环获取file数组中得文件
			for (int i = 0; i < files.length; i++) {
				CommonsMultipartFile file = files[i];
				// 保存文件
				String path = saveFile(file, request);
				paths.add(path);
			}
		}
		// 重定向
		return paths;
	}

	@ApiOperation(value = "新增", notes = "新增")
	@RequestMapping(value = "/insertRollpic", method = RequestMethod.POST)
	public int insertRollpic(@RequestBody Rollpic rollpic) {
		rollpic.setId(IdUtil.getId());
		rollpic.setAddTime(GeneralUtils.currentTime());
		if (rollpic.getRegionId() == null) {
			long regionId = Long.parseLong(PartyUtils.currentParty()
					.getCommonRegionId());
			rollpic.setRegionId(regionId);
		}
		return rollpicService.insertRollpic(rollpic);
	}

	@ApiOperation(value = "根据ID修改", notes = "根据ID修改")
	@RequestMapping(value = "/updateRollpicById", method = RequestMethod.POST)
	public int updateRollpicById(@RequestBody Rollpic rollpic) {
		return rollpicService.updateRollpicById(rollpic);
	}

	@ApiOperation(value = "根据ID删除", notes = "根据ID删除")
	@RequestMapping(value = "/deleteRollpicById", method = RequestMethod.GET)
	public int deleteRollpicById(@RequestParam Long id) {
		return rollpicService.deleteRollpicById(id);
	}

	@ApiOperation(value = "根据ID获取", notes = "根据ID获取")
	@RequestMapping(value = "/getRollpicById", method = RequestMethod.GET)
	public Rollpic getRollpicById(@RequestParam Long id) {
		return rollpicService.getRollpicById(id);
	}

	@ApiOperation(value = "根据对象获取", notes = "根据对象获取")
	@RequestMapping(value = "/getRollpics", method = RequestMethod.POST)
	public List<Rollpic> getRollpics(@RequestBody Rollpic rollpic) {
		return rollpicService.getRollpics(rollpic);

	}

	@ApiOperation(value = "根据对象分页获取", notes = "根据对象分页获取")
	@RequestMapping(value = "/getRollpicsForPage", method = RequestMethod.POST)
	public PageDto<Rollpic> getRollpicsForPage(@RequestBody Rollpic rollpic,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,
			@RequestParam(required = false) String sort,
			@RequestParam(required = false) String order) {
		Map<String, String> cols = new HashMap<String, String>();
		cols.put("id", "id");
		cols.put("name", "name");
		cols.put("imagPath", "imagPath");
		cols.put("priority", "priority");
		cols.put("status", "status");
		cols.put("addTime", "addTime");
		cols.put("url", "url");
		cols.put("regionId", "region_id");
		cols.put("language", "language");
		Pageable pageable = null;
		if (!StringUtils.isEmpty(order) && !StringUtils.isEmpty(sort)) {
			Sort sortObj = new Sort(Sort.Direction.fromStringOrNull(order),
					cols.get(sort));
			pageable = new PageRequest(page - 1, rows, sortObj);
		} else {
			pageable = new PageRequest(page - 1, rows);
		}
		Party party = PartyUtils.currentParty();
		List<QueryCondition> conditions = new ArrayList<QueryCondition>();

		if (!party.getCommonRegionId().equals("1")) {
			conditions.add(new QueryCondition("region_id", party
					.getCommonRegionId()));
		}

		if (rollpic.getRegionId() != null) {
			conditions.add(new QueryCondition("region_id", rollpic
					.getRegionId()));
		}

		// 根据语言查询
		if (rollpic.getLanguage() != null && !rollpic.getLanguage().isEmpty()) {
			conditions
					.add(new QueryCondition("language", rollpic.getLanguage()));
		}

		Page<Rollpic> rollpics = rollpicService.getRollpicsForPage(conditions,
				pageable);

		PageDto<Rollpic> pageDto = new PageDto<Rollpic>();
		if (rollpics != null) {
			pageDto.setRows(rollpics.getContent());
			pageDto.setTotal(rollpics.getTotalElements());
		} else {
			pageDto.setRows(new ArrayList<Rollpic>());
			pageDto.setTotal(0l);
		}
		return pageDto;
	}

	@ApiOperation(value = "新增医生", notes = "新增医生")
	@RequestMapping(value = "/insertDoctor", method = RequestMethod.POST)
	public int insertDoctor(@RequestBody Doctor doctor) {
		long doctorId = IdUtil.getId();
		doctor.setId(doctorId);
		List<Long> ids = doctor.getDepartmentIds();
		for (long id : ids) {
			DoctorDepartmentRel rel = new DoctorDepartmentRel();
			rel.setDepartmentId(id);
			rel.setDoctorId(doctorId);
			doctorDepartmentRelService.insertDoctorDepartmentRel(rel);
		}

		if (doctor.getRegionId() == null) {
			long regionId = Long.parseLong(PartyUtils.currentParty()
					.getCommonRegionId());
			doctor.setRegionId(regionId);
		}
		if(doctor.getSpecialty() == null || doctor.getSpecialty().isEmpty()){
			doctor.setSpecialty("0");
		}
		return doctorService.insertDoctor(doctor);
	}

	@ApiOperation(value = "根据ID修改医生", notes = "根据ID修改医生")
	@RequestMapping(value = "/updateDoctorById", method = RequestMethod.POST)
	public int updateDoctorById(@RequestBody Doctor doctor) {
		long doctorId = doctor.getId();
		// 删除关联关系
		doctorDepartmentRelService.deleteRel(doctorId);
		List<Long> ids = doctor.getDepartmentIds();
		// 添加关联关系
		for (long id : ids) {
			DoctorDepartmentRel rel = new DoctorDepartmentRel();
			rel.setDepartmentId(id);
			rel.setDoctorId(doctorId);
			doctorDepartmentRelService.insertDoctorDepartmentRel(rel);
		}

		return doctorService.updateDoctorById(doctor);
	}

	@ApiOperation(value = "根据ID删除医生", notes = "根据ID删除医生")
	@RequestMapping(value = "/deleteDoctorById", method = RequestMethod.GET)
	public int deleteDoctorById(@RequestParam Long id) {
		doctorDepartmentRelService.deleteRel(id);
		return doctorService.deleteDoctorById(id);
	}

	@ApiOperation(value = "根据ID获取医生", notes = "根据ID获取医生")
	@RequestMapping(value = "/getDoctorById", method = RequestMethod.GET)
	public Doctor getDoctorById(@RequestParam Long id) {
		Doctor doctor = doctorService.getDoctorById(id);
		List<Long> ids = doctorDepartmentRelService.getDepartmentIds(id);
		doctor.setDepartmentIds(ids);
		return doctor;
	}

	@ApiOperation(value = "根据对象获取医生", notes = "根据对象获取医生")
	@RequestMapping(value = "/getDoctors", method = RequestMethod.POST)
	public List<Doctor> getDoctors(@RequestBody Doctor doctor) {
		return doctorService.getDoctors(doctor);

	}

	@ApiOperation(value = "根据对象分页获取医生", notes = "根据对象分页获取医生")
	@RequestMapping(value = "/getDoctorsForPage", method = RequestMethod.POST)
	public PageDto<Doctor> getDoctorsForPage(@RequestBody Doctor doctor,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,
			@RequestParam(required = false) String sort,
			@RequestParam(required = false) String order) {
		Map<String, String> cols = new HashMap<String, String>();
		cols.put("id", "id");
		cols.put("name", "name");
		cols.put("specialty", "specialty");
		cols.put("title", "title");
		cols.put("regionId", "region_id");
		cols.put("language", "language");
		cols.put("photo", "photo");
		cols.put("honor", "honor");
		cols.put("adept", "adept");
		cols.put("content", "content");
		cols.put("department", "department");
		Pageable pageable = null;
		if (!StringUtils.isEmpty(order) && !StringUtils.isEmpty(sort)) {
			Sort sortObj = new Sort(Sort.Direction.fromStringOrNull(order),
					cols.get(sort));
			pageable = new PageRequest(page - 1, rows, sortObj);
		} else {
			pageable = new PageRequest(page - 1, rows);
		}
		Party party = PartyUtils.currentParty();
		List<QueryCondition> conditions = new ArrayList<QueryCondition>();
		conditions.add(new QueryCondition("region_id", party
				.getCommonRegionId()));
		// 根据语言查询
		if (doctor.getLanguage() != null && !doctor.getLanguage().isEmpty()) {
			conditions
					.add(new QueryCondition("language", doctor.getLanguage()));
		}

		// 根据医生姓名查询
		if (doctor.getName() != null && !doctor.getName().isEmpty()) {
			conditions.add(new QueryCondition("name", doctor.getName(),
					QueryCondition.TYPE_LIKE));
		}

		Page<Doctor> doctors = doctorService.getDoctorsForPage(conditions,
				pageable);

		PageDto<Doctor> pageDto = new PageDto<Doctor>();
		if (doctors != null) {
			pageDto.setRows(doctors.getContent());
			pageDto.setTotal(doctors.getTotalElements());
		} else {
			pageDto.setRows(new ArrayList<Doctor>());
			pageDto.setTotal(0l);
		}

		return pageDto;

	}

	@ApiOperation(value = "新增科室", notes = "新增科室")
	@RequestMapping(value = "/insertDepartment", method = RequestMethod.POST)
	public int insertDepartment(@RequestBody Department department) {
		department.setId(IdUtil.getId());
		return departmentService.insertDepartment(department);
	}

	@ApiOperation(value = "批量新增科室", notes = "批量新增科室")
	@RequestMapping(value = "/insertDepartmentBatch", method = RequestMethod.POST)
	public int insertDepartmentBatch(@RequestBody List<Department> list) {
		return departmentService.insertDepartmentBatch(list);
	}

	@ApiOperation(value = "根据ID修改科室", notes = "根据ID修改科室")
	@RequestMapping(value = "/updateDepartmentById", method = RequestMethod.POST)
	public int updateDepartmentById(@RequestBody Department department) {
		return departmentService.updateDepartmentById(department);
	}

	@ApiOperation(value = "根据ID删除科室", notes = "根据ID删除科室")
	@RequestMapping(value = "/deleteDepartmentById", method = RequestMethod.GET)
	public int deleteDepartmentById(@RequestParam Long id) {
		return departmentService.deleteDepartmentById(id);
	}

	@ApiOperation(value = "根据ID获取科室", notes = "根据ID获取科室")
	@RequestMapping(value = "/getDepartmentById", method = RequestMethod.GET)
	public Department getDepartmentById(@RequestParam Long id) {
		return departmentService.getDepartmentById(id);
	}

	@ApiOperation(value = "根据对象获取科室", notes = "根据对象获取科室")
	@RequestMapping(value = "/getDepartments", method = RequestMethod.POST)
	public List<Department> getDepartments(@RequestBody Department department) {
		return departmentService.getDepartments(department);

	}

	@ApiOperation(value = "根据对象分页获取科室", notes = "根据对象分页获取科室")
	@RequestMapping(value = "/getDepartmentsForPage", method = RequestMethod.POST)
	public PageDto<Department> getDepartmentsForPage(
			@RequestBody Department department,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,
			@RequestParam(required = false) String sort,
			@RequestParam(required = false) String order) {
		Map<String, String> cols = new HashMap<String, String>();
		cols.put("id", "id");
		cols.put("name", "name");
		cols.put("summary", "summary");
		cols.put("service", "service");
		cols.put("technical", "technical");
		cols.put("picture", "picture");
		cols.put("language", "language");
		Pageable pageable = null;
		if (!StringUtils.isEmpty(order) && !StringUtils.isEmpty(sort)) {
			Sort sortObj = new Sort(Sort.Direction.fromStringOrNull(order),
					cols.get(sort));
			pageable = new PageRequest(page - 1, rows, sortObj);
		} else {
			pageable = new PageRequest(page - 1, rows);
		}
		List<QueryCondition> conditions = new ArrayList<QueryCondition>();
		// 根据语言查询
		if (department.getLanguage() != null
				&& !department.getLanguage().isEmpty()) {
			conditions.add(new QueryCondition("language", department
					.getLanguage()));
		}

		Page<Department> departments = departmentService.getDepartmentsForPage(
				conditions, pageable);

		PageDto<Department> pageDto = new PageDto<Department>();
		if (departments != null) {
			pageDto.setRows(departments.getContent());
			pageDto.setTotal(departments.getTotalElements());
		} else {
			pageDto.setRows(new ArrayList<Department>());
			pageDto.setTotal(0l);
		}

		return pageDto;
	}

	@ApiOperation(value = "新增案例文章", notes = "新增案例文章")
	@RequestMapping(value = "/insertArticle", method = RequestMethod.POST)
	public int insertArticle(@RequestBody Article article) {
		article.setId(IdUtil.getId());
		article.setAddTime(new Date());
		return articleService.insertArticle(article);
	}

	@ApiOperation(value = "根据ID修改案例文章", notes = "根据ID修改案例文章")
	@RequestMapping(value = "/updateArticleById", method = RequestMethod.POST)
	public int updateArticleById(@RequestBody Article article) {
		article.setUpdateTime(new Date());
		return articleService.updateArticleById(article);
	}

	@ApiOperation(value = "根据ID删除案例文章", notes = "根据ID删除案例文章")
	@RequestMapping(value = "/deleteArticleById", method = RequestMethod.GET)
	public int deleteArticleById(@RequestParam Long id) {
		return articleService.deleteArticleById(id);
	}

	@ApiOperation(value = "根据ID获取案例文章", notes = "根据ID获取案例文章")
	@RequestMapping(value = "/getArticleById", method = RequestMethod.GET)
	public Article getArticleById(@RequestParam Long id) {
		return articleService.getArticleById(id);
	}

	@ApiOperation(value = "根据对象获取案例文章", notes = "根据对象获取案例文章")
	@RequestMapping(value = "/getArticles", method = RequestMethod.POST)
	public List<Article> getArticles(@RequestBody Article article) {
		return articleService.getArticles(article);

	}

	@ApiOperation(value = "根据对象分页获取案例文章", notes = "根据对象分页获取案例文章")
	@RequestMapping(value = "/getArticlesForPage", method = RequestMethod.POST)
	public PageDto<Article> getArticlesForPage(@RequestBody Article article,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,
			@RequestParam(required = false) String sort,
			@RequestParam(required = false) String order) {
		Map<String, String> cols = new HashMap<String, String>();
		cols.put("id", "id");
		cols.put("title", "title");
		cols.put("content", "content");
		cols.put("addTime", "add_time");
		cols.put("updateTime", "update_time");
		cols.put("categoryId", "category_id");
		cols.put("categoryName", "category_name");
		cols.put("regionId", "region_id");
		cols.put("imageUrl", "image_url");
		cols.put("language", "language");
		cols.put("author", "author");
		Pageable pageable = null;
		if (!StringUtils.isEmpty(order) && !StringUtils.isEmpty(sort)) {
			Sort sortObj = new Sort(Sort.Direction.fromStringOrNull(order),
					cols.get(sort));
			pageable = new PageRequest(page - 1, rows, sortObj);
		} else {
			pageable = new PageRequest(page - 1, rows);
		}
		List<QueryCondition> conditions = new ArrayList<QueryCondition>();

		// 根据语言查询
		if (article.getLanguage() != null && !article.getLanguage().isEmpty()) {
			conditions
					.add(new QueryCondition("language", article.getLanguage()));
		}

		// 根据分类查询
		if (article.getCategoryId() != null
				&& !article.getCategoryId().isEmpty()) {
			conditions.add(new QueryCondition("category_id", article
					.getCategoryId()));
		}
		
		// 根据标题查询
		if (article.getTitle() != null&& !article.getTitle().isEmpty()) {
			conditions.add(new QueryCondition("title", article.getTitle(),QueryCondition.TYPE_LIKE));
		}
				
		Page<Article> articles = articleService.getArticlesForPage(conditions,
				pageable);

		PageDto<Article> pageDto = new PageDto<Article>();
		if (articles != null) {
			pageDto.setRows(articles.getContent());
			pageDto.setTotal(articles.getTotalElements());
		} else {
			pageDto.setRows(new ArrayList<Article>());
			pageDto.setTotal(0l);
		}

		return pageDto;
	}

	@ApiOperation(value = "新增专家", notes = "新增专家")
	@RequestMapping(value = "/insertExpert", method = RequestMethod.POST)
	public int insertExpert(@RequestBody Expert expert) {
		long regionId = Long.parseLong(PartyUtils.currentParty()
				.getCommonRegionId());
		long expertId = IdUtil.getId();
		expert.setId(expertId);
		List<Long> ids = expert.getDepartmentIds();
		for (long id : ids) {
			ExpertClassifyRel rel = new ExpertClassifyRel();
			rel.setExpertId(expertId);
			rel.setClassifyId(id);
			expertClassifyRelService.insertExpertClassifyRel(rel);
		}
		expert.setRegionId(regionId);
		if(expert.getSpecialty() == null || expert.getSpecialty().isEmpty()){
			expert.setSpecialty("0");
		}
		return expertService.insertExpert(expert);
	}

	@ApiOperation(value = "批量新增专家", notes = "批量新增专家")
	@RequestMapping(value = "/insertExpertBatch", method = RequestMethod.POST)
	public int insertExpertBatch(@RequestBody List<Expert> list) {
		return expertService.insertExpertBatch(list);
	}

	@ApiOperation(value = "根据ID修改专家", notes = "根据ID修改专家")
	@RequestMapping(value = "/updateExpertById", method = RequestMethod.POST)
	public int updateExpertById(@RequestBody Expert expert) {
		long expertId = expert.getId();
		// 删除关联关系
		expertClassifyRelService.deleteRel(expertId);
		List<Long> ids = expert.getDepartmentIds();
		// 添加关联关系
		for (long id : ids) {
			ExpertClassifyRel rel = new ExpertClassifyRel();
			rel.setExpertId(expertId);
			rel.setClassifyId(id);
			expertClassifyRelService.insertExpertClassifyRel(rel);
		}
		return expertService.updateExpertById(expert);
	}

	@ApiOperation(value = "根据ID删除专家", notes = "根据ID删除专家")
	@RequestMapping(value = "/deleteExpertById", method = RequestMethod.GET)
	public int deleteExpertById(@RequestParam Long id) {
		expertClassifyRelService.deleteRel(id);
		return expertService.deleteExpertById(id);
	}

	@ApiOperation(value = "根据ID获取专家", notes = "根据ID获取专家")
	@RequestMapping(value = "/getExpertById", method = RequestMethod.GET)
	public Expert getExpertById(@RequestParam Long id) {
		Expert export = expertService.getExpertById(id);
		List<Long> ids = expertClassifyRelService.getDepartmentIds(id);
		export.setDepartmentIds(ids);
		return export;
	}

	@ApiOperation(value = "根据对象获取专家", notes = "根据对象获取专家")
	@RequestMapping(value = "/getExperts", method = RequestMethod.POST)
	public List<Expert> getExperts(@RequestBody Expert expert) {
		return expertService.getExperts(expert);
	}

	@ApiOperation(value = "根据对象分页获取专家", notes = "根据对象分页获取专家")
	@RequestMapping(value = "/getExpertsForPage", method = RequestMethod.POST)
	public PageDto<Expert> getExpertsForPage(@RequestBody Expert expert,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,
			@RequestParam(required = false) String sort,
			@RequestParam(required = false) String order) {
		Map<String, String> cols = new HashMap<String, String>();
		cols.put("id", "id");
		cols.put("name", "name");
		cols.put("specialty", "specialty");
		cols.put("title", "title");
		cols.put("regionId", "region_id");
		cols.put("language", "language");
		cols.put("photo", "photo");
		cols.put("honor", "honor");
		cols.put("adept", "adept");
		cols.put("content", "content");
		Pageable pageable = null;
		if (!StringUtils.isEmpty(order) && !StringUtils.isEmpty(sort)) {
			Sort sortObj = new Sort(Sort.Direction.fromStringOrNull(order),
					cols.get(sort));
			pageable = new PageRequest(page - 1, rows, sortObj);
		} else {
			pageable = new PageRequest(page - 1, rows);
		}
		List<QueryCondition> conditions = new ArrayList<QueryCondition>();
		// 根据语言查询
		if (expert.getLanguage() != null && !expert.getLanguage().isEmpty()) {
			conditions
					.add(new QueryCondition("language", expert.getLanguage()));
		}

		// 根据医生姓名查询
		if (expert.getName() != null && !expert.getName().isEmpty()) {
			conditions.add(new QueryCondition("name", expert.getName(),
					QueryCondition.TYPE_LIKE));
		}

		Page<Expert> experts = expertService.getExpertsForPage(conditions,
				pageable);

		PageDto<Expert> pageDto = new PageDto<Expert>();
		if (experts != null) {
			pageDto.setRows(experts.getContent());
			pageDto.setTotal(experts.getTotalElements());
		} else {
			pageDto.setRows(new ArrayList<Expert>());
			pageDto.setTotal(0l);
		}

		return pageDto;
	}

	@ApiOperation(value = "新增专家分类", notes = "新增专家分类")
	@RequestMapping(value = "/insertClassify", method = RequestMethod.POST)
	public int insertClassify(@RequestBody Classify classify) {
		classify.setId(IdUtil.getId());
		return classifyService.insertClassify(classify);
	}

	@ApiOperation(value = "批量新增专家分类", notes = "批量新增专家分类")
	@RequestMapping(value = "/insertClassifyBatch", method = RequestMethod.POST)
	public int insertClassifyBatch(@RequestBody List<Classify> list) {
		return classifyService.insertClassifyBatch(list);
	}

	@ApiOperation(value = "根据ID修改专家分类", notes = "根据ID修改专家分类")
	@RequestMapping(value = "/updateClassifyById", method = RequestMethod.POST)
	public int updateClassifyById(@RequestBody Classify classify) {
		return classifyService.updateClassifyById(classify);
	}

	@ApiOperation(value = "根据ID删除专家分类", notes = "根据ID删除专家分类")
	@RequestMapping(value = "/deleteClassifyById", method = RequestMethod.GET)
	public int deleteClassifyById(@RequestParam Long id) {
		return classifyService.deleteClassifyById(id);
	}

	@ApiOperation(value = "根据ID获取专家分类", notes = "根据ID获取专家分类")
	@RequestMapping(value = "/getClassifyById", method = RequestMethod.GET)
	public Classify getClassifyById(@RequestParam Long id) {
		return classifyService.getClassifyById(id);
	}

	@ApiOperation(value = "根据对象获取专家分类", notes = "根据对象获取专家分类")
	@RequestMapping(value = "/getClassifys", method = RequestMethod.POST)
	public List<Classify> getClassifys(@RequestBody Classify classify) {
		return classifyService.getClassifys(classify);

	}

	@ApiOperation(value = "根据对象分页获取专家分类", notes = "根据对象分页获取专家分类")
	@RequestMapping(value = "/getClassifysForPage", method = RequestMethod.POST)
	public PageDto<Classify> getClassifysForPage(
			@RequestBody Classify classify,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,
			@RequestParam(required = false) String sort,
			@RequestParam(required = false) String order) {
		Map<String, String> cols = new HashMap<String, String>();
		cols.put("id", "id");
		cols.put("name", "name");
		cols.put("language", "language");
		Pageable pageable = null;
		if (!StringUtils.isEmpty(order) && !StringUtils.isEmpty(sort)) {
			Sort sortObj = new Sort(Sort.Direction.fromStringOrNull(order),
					cols.get(sort));
			pageable = new PageRequest(page - 1, rows, sortObj);
		} else {
			pageable = new PageRequest(page - 1, rows);
		}
		Page<Classify> classifys = classifyService.getClassifysForPage(
				classify, pageable);

		PageDto<Classify> pageDto = new PageDto<Classify>();
		if (classifys != null) {
			pageDto.setRows(classifys.getContent());
			pageDto.setTotal(classifys.getTotalElements());
		} else {
			pageDto.setRows(new ArrayList<Classify>());
			pageDto.setTotal(0l);
		}

		return pageDto;
	}

	@ApiOperation(value = "新增区域", notes = "新增区域")
	@RequestMapping(value = "/insertRegion", method = RequestMethod.POST)
	public int insertRegion(@RequestBody Region region) {
		region.setRegionId(IdUtil.getId());
		region.setParentRegionId(PartyUtils.currentParty().getCommonRegionId());
		return regionService.insertRegion(region);
	}

	@ApiOperation(value = "根据ID修改区域", notes = "根据ID修改区域")
	@RequestMapping(value = "/updateRegionById", method = RequestMethod.POST)
	public int updateRegionById(@RequestBody Region region) {
		return regionService.updateRegionById(region);
	}

	@ApiOperation(value = "根据ID删除区域", notes = "根据ID删除区域")
	@RequestMapping(value = "/deleteRegionById", method = RequestMethod.GET)
	public int deleteRegionById(@RequestParam Long regionId) {
		return regionService.deleteRegionById(regionId);
	}

	@ApiOperation(value = "根据ID获取区域", notes = "根据ID获取区域")
	@RequestMapping(value = "/getRegionById", method = RequestMethod.GET)
	public Region getRegionById(@RequestParam Long regionId) {
		return regionService.getRegionById(regionId);
	}

	@ApiOperation(value = "根据对象获取区域", notes = "根据对象获取区域")
	@RequestMapping(value = "/getRegions", method = RequestMethod.POST)
	public List<Region> getRegions(@RequestBody Region region) {
		region.setParentRegionId(PartyUtils.currentParty().getCommonRegionId());
		return regionService.getRegions(region);
	}

	@ApiOperation(value = "根据对象分页获取区域", notes = "根据对象分页获取区域")
	@RequestMapping(value = "/getRegionsForPage", method = RequestMethod.POST)
	public PageDto<Region> getRegionsForPage(@RequestBody Region region,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,
			@RequestParam(required = false) String sort,
			@RequestParam(required = false) String order) {
		Map<String, String> cols = new HashMap<String, String>();
		cols.put("regionId", "region_id");
		cols.put("cnName", "cn_name");
		cols.put("parentRegionId", "parent_region_id");
		cols.put("cnAddress", "cn_address");
		cols.put("phone", "phone");
		cols.put("email", "email");
		cols.put("logoImageUrl", "logo_image_url");
		cols.put("url", "url");
		cols.put("enName", "en_name");
		cols.put("enAddress", "en_address");
		Pageable pageable = null;
		if (!StringUtils.isEmpty(order) && !StringUtils.isEmpty(sort)) {
			Sort sortObj = new Sort(Sort.Direction.fromStringOrNull(order),
					cols.get(sort));
			pageable = new PageRequest(page - 1, rows, sortObj);
		} else {
			pageable = new PageRequest(page - 1, rows);
		}

		List<QueryCondition> conditions = new ArrayList<QueryCondition>();
		conditions.add(new QueryCondition("parent_region_id", PartyUtils
				.currentParty().getCommonRegionId()));

		Page<Region> regions = regionService.getRegionsForPage(conditions,
				pageable);

		PageDto<Region> pageDto = new PageDto<Region>();
		if (regions != null) {
			pageDto.setRows(regions.getContent());
			pageDto.setTotal(regions.getTotalElements());
		} else {
			pageDto.setRows(new ArrayList<Region>());
			pageDto.setTotal(0l);
		}

		return pageDto;
	}

	@ApiOperation(value = "新增门店", notes = "新增门店")
	@RequestMapping(value = "/insertStore", method = RequestMethod.POST)
	public int insertStore(@RequestBody Store store) {
		store.setId(IdUtil.getId());
		if (store.getRegionId() == null || store.getRegionName() == null) {
			Party party = PartyUtils.currentParty();
			store.setRegionId(Long.parseLong(party.getCommonRegionId()));
			store.setRegionName(party.getRemark());
		}
		return storeService.insertStore(store);
	}

	@ApiOperation(value = "批量新增门店", notes = "批量新增门店")
	@RequestMapping(value = "/insertStoreBatch", method = RequestMethod.POST)
	public int insertStoreBatch(@RequestBody List<Store> list) {
		return storeService.insertStoreBatch(list);
	}

	@ApiOperation(value = "根据ID修改门店", notes = "根据ID修改门店")
	@RequestMapping(value = "/updateStoreById", method = RequestMethod.POST)
	public int updateStoreById(@RequestBody Store store) {
		return storeService.updateStoreById(store);
	}

	@ApiOperation(value = "根据ID删除门店", notes = "根据ID删除门店")
	@RequestMapping(value = "/deleteStoreById", method = RequestMethod.GET)
	public int deleteStoreById(@RequestParam Long id) {
		return storeService.deleteStoreById(id);
	}

	@ApiOperation(value = "根据ID获取门店", notes = "根据ID获取门店")
	@RequestMapping(value = "/getStoreById", method = RequestMethod.GET)
	public Store getStoreById(@RequestParam Long id) {
		return storeService.getStoreById(id);
	}

	@ApiOperation(value = "根据对象获取门店", notes = "根据对象获取门店")
	@RequestMapping(value = "/getStores", method = RequestMethod.POST)
	public List<Store> getStores(@RequestBody Store store) {
		store.setRegionId(Long.parseLong(PartyUtils.currentParty()
				.getCommonRegionId()));
		return storeService.getStores(store);

	}

	@ApiOperation(value = "根据对象分页获取门店", notes = "根据对象分页获取门店")
	@RequestMapping(value = "/getStoresForPage", method = RequestMethod.POST)
	public PageDto<Store> getStoresForPage(@RequestBody Store store,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,
			@RequestParam(required = false) String sort,
			@RequestParam(required = false) String order) {
		Map<String, String> cols = new HashMap<String, String>();
		cols.put("id", "id");
		cols.put("name", "name");
		cols.put("address", "address");
		cols.put("phone", "phone");
		cols.put("telphone", "telphone");
		cols.put("userName", "user_name");
		cols.put("regionId", "region_id");
		cols.put("language", "language");
		Pageable pageable = null;
		if (!StringUtils.isEmpty(order) && !StringUtils.isEmpty(sort)) {
			Sort sortObj = new Sort(Sort.Direction.fromStringOrNull(order),
					cols.get(sort));
			pageable = new PageRequest(page - 1, rows, sortObj);
		} else {
			pageable = new PageRequest(page - 1, rows);
		}
		List<QueryCondition> conditions = new ArrayList<QueryCondition>();
		if (store.getLanguage() != null && !store.getLanguage().isEmpty()) {
			conditions.add(new QueryCondition("language", store.getLanguage()));
		}

		if (store.getRegionId() != null) {
			conditions
					.add(new QueryCondition("region_id", store.getRegionId()));
		}

		Page<Store> stores = storeService
				.getStoresForPage(conditions, pageable);

		PageDto<Store> pageDto = new PageDto<Store>();
		if (stores != null) {
			pageDto.setRows(stores.getContent());
			pageDto.setTotal(stores.getTotalElements());
		} else {
			pageDto.setRows(new ArrayList<Store>());
			pageDto.setTotal(0l);
		}

		return pageDto;
	}

	@ApiOperation(value = "根据对象分页获取用户", notes = "根据对象分页获取用户")
	@RequestMapping(value = "/getPartysForPage", method = RequestMethod.POST)
	public PageDto<Party> getPartysForPage(
			@RequestBody(required = false) Party party,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,
			@RequestParam(required = false) String sort,
			@RequestParam(required = false) String order) {
		if (party == null)
			party = new Party();
		Map<String, String> cols = new HashMap<String, String>();
		cols.put("partyId", "PARTY_ID");
		cols.put("partySn", "PARTY_SN");
		cols.put("statusCode", "STATUS_CODE");
		cols.put("userName", "USER_NAME");
		cols.put("password", "PASSWORD");
		cols.put("remark", "REMARK");
		cols.put("lastLoginTime", "LAST_LOGIN_TIME");
		cols.put("lastLoginIp", "LAST_LOGIN_IP");
		cols.put("isLocked", "IS_LOCKED");
		cols.put("lockedDate", "LOCKED_DATE");
		cols.put("createTime", "CREATE_TIME");
		cols.put("loginFailureCount", "LOGIN_FAILURE_COUNT");
		cols.put("registerIp", "REGISTER_IP");
		cols.put("email", "EMAIL");
		cols.put("countryCode", "COUNTRY_CODE");
		cols.put("phone", "PHONE");
		cols.put("nickName", "NICK_NAME");
		Pageable pageable = null;
		if (!StringUtils.isEmpty(order) && !StringUtils.isEmpty(sort)) {
			Sort sortObj = new Sort(Sort.Direction.fromStringOrNull(order),
					cols.get(sort));
			pageable = new PageRequest(page - 1, rows, sortObj);
		} else {
			pageable = new PageRequest(page - 1, rows);
		}
		List<QueryCondition> conditions = new ArrayList<QueryCondition>();
		
		conditions.add(new QueryCondition("USER_NAME", "admin",
				QueryCondition.TYPE_NOT_EQUAL));
		if (party.getCommonRegionId() != null
				&& !party.getCommonRegionId().isEmpty()) {
			conditions.add(new QueryCondition("COMMON_REGION_ID", party
					.getCommonRegionId()));
		}
		
		//根据类型查询
		if(party.getPartyType() != null
				&& !party.getPartyType().isEmpty()){
			conditions.add(new QueryCondition("PARTY_TYPE",party.getPartyType()));
		}
		Page<Party> partys = regionService.getPartysForPage(conditions,
				pageable);
		PageDto<Party> pageDto = new PageDto<Party>();
		if (partys != null) {
			pageDto.setRows(partys.getContent());
			pageDto.setTotal(partys.getTotalElements());
		} else {
			pageDto.setRows(new ArrayList<Party>());
			pageDto.setTotal(0l);
		}
		return pageDto;

	}

	@ApiOperation(value = "新增用户", notes = "新增用户")
	@RequestMapping(value = "/insertParty", method = RequestMethod.POST)
	public int insertParty(@RequestBody Party party) {
		Party user = partyService.getPartyByUserName(party.getUserName(),
				party.getPartyType());
		if (user != null) {
			throw new BaseException("010014");
		}
		Party insertParty = new Party();
		String partyId = IdUtil.getId() + "";

		insertParty.setUserName(party.getUserName());
		insertParty.setPartyType(party.getPartyType());
		insertParty.setStatusCode("00");
		insertParty.setPassword("123456");
		insertParty.setIsLocked(false);
		insertParty.setEmail(party.getEmail());
		insertParty.setPhone(party.getPhone());
		insertParty.setRemark(party.getRemark());
		insertParty.setPartyId(partyId);
		insertParty.setCommonRegionId(party.getCommonRegionId());
		insertParty.setLoginFailureCount(0);
		PartyRoleRel rel = new PartyRoleRel();
		rel.setPartyId(partyId);
		rel.setRoleId("ADMIN");
		partyRoleRelService.insertPartyRoleRel(rel);
		return partyService.insertParty(insertParty);
	}

	@ApiOperation(value = "根据ID删除用户", notes = "根据ID删除用户")
	@RequestMapping(value = "/deletePartyById", method = RequestMethod.GET)
	public int deletePartyById(@RequestParam java.lang.String partyId) {
		return partyService.deletePartyById(partyId);
	}

	@ApiOperation(value = "重置密码", notes = "重置密码")
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public int changePassword(@RequestParam String partyId) {
		return partyService.resetPassword(partyService.getPartyById(partyId));
	}

	@ApiOperation(value = "获取菜单", notes = "获取菜单")
	@RequestMapping(value = "/getMenus", method = RequestMethod.GET)
	public List<MenuVo> getMenus() {
		List<MenuVo> list = null;
		String regionId = PartyUtils.currentParty().getCommonRegionId();
		if (regionId.equals("1")) {
			list = getAdminMenus();
		} else {
			list = getRegionMenus();
		}

		return list;
	}

	@Autowired
	private KnowledgeService knowledgeService;

	@ApiOperation(value = "新增知识库", notes = "新增知识库")
	@RequestMapping(value = "/insertKnowledge", method = RequestMethod.POST)
	public int insertKnowledge(@RequestBody Knowledge knowledge) {
		Knowledge k = new Knowledge();
		k.setTitle(knowledge.getTitle());
		List<Knowledge> ks= knowledgeService.getKnowledges(k);
		if(ks != null && ks.size() > 0){
			throw new BaseException("k0001", "该标题已存在，请勿重复添加", null);
		}
		
		knowledge.setId(IdUtil.getId());
		knowledge.setAddTime(new Date());
		knowledgeService.insertKnowledge(knowledge);
		
		List<Long> similarIds =  knowledge.getSimilarIds();
		for (long similarId : similarIds) {
			SimilarKnowledge sk = new SimilarKnowledge();
			sk.setKnowledgeId(knowledge.getId());
			sk.setSimilarId(similarId);
			similarKnowledgeService.insertSimilarKnowledge(sk);
		}
		
		KnowledgeVo kv = gwService.getKnowledgeVoById(knowledge.getId());
		toHtmlService.toKnowledgeDetail(kv);

		return 1;
	}

	@ApiOperation(value = "批量新增知识库", notes = "批量新增知识库")
	@RequestMapping(value = "/insertKnowledgeBatch", method = RequestMethod.POST)
	public int insertKnowledgeBatch(@RequestBody List<Knowledge> list) {
		return knowledgeService.insertKnowledgeBatch(list);
	}

	@ApiOperation(value = "根据ID修改知识库", notes = "根据ID修改知识库")
	@RequestMapping(value = "/updateKnowledgeById", method = RequestMethod.POST)
	public int updateKnowledgeById(@RequestBody Knowledge knowledge) {
		knowledge.setUpdateTime(new Date());
		knowledgeService.updateKnowledgeById(knowledge);
		similarKnowledgeService.deleteSimilarKnowledgeById(knowledge.getId());
		List<Long> similarIds =  knowledge.getSimilarIds();
		for (long similarId : similarIds) {
			SimilarKnowledge sk = new SimilarKnowledge();
			sk.setKnowledgeId(knowledge.getId());
			sk.setSimilarId(similarId);
			similarKnowledgeService.insertSimilarKnowledge(sk);
		}
		
		KnowledgeVo kv = gwService.getKnowledgeVoById(knowledge.getId());
		toHtmlService.toKnowledgeDetail(kv);
		
		return 1;
	}

	@ApiOperation(value = "根据ID删除知识库", notes = "根据ID删除知识库")
	@RequestMapping(value = "/deleteKnowledgeById", method = RequestMethod.GET)
	public int deleteKnowledgeById(@RequestParam Long id) {
		knowledgeService.deleteKnowledgeById(id);
		knowledgeCategoryService.deleteKnowledgeCategoryById(id);
		return 1;
	}

	@ApiOperation(value = "根据ID获取知识库", notes = "根据ID获取知识库")
	@RequestMapping(value = "/getKnowledgeById", method = RequestMethod.GET)
	public Knowledge getKnowledgeById(@RequestParam Long id) {
		Knowledge knowledge = knowledgeService.getKnowledgeById(id);
		List<SimilarKnowledge> similarKnowledges = similarKnowledgeService
				.getSimilarKnowledgeById(id);
		List<Long> similarIds = new ArrayList<Long>();
		for (SimilarKnowledge sk : similarKnowledges) {
			similarIds.add(sk.getSimilarId());
		}
		knowledge.setSimilarIds(similarIds);
		return knowledge;
	}

	@ApiOperation(value = "根据对象获取知识库", notes = "根据对象获取知识库")
	@RequestMapping(value = "/getKnowledges", method = RequestMethod.POST)
	public List<Knowledge> getKnowledges(@RequestBody Knowledge knowledge) {
		return knowledgeService.getKnowledges(knowledge);
	}

	@ApiOperation(value = "关联文章", notes = "关联文章")
	@RequestMapping(value = "/addSimilarKnowledge", method = RequestMethod.POST)
	public int addSimilarKnowledge(
			@RequestBody SimilarKnowledgeReq similarKnowledgeReq) {
		long knowledgeId = similarKnowledgeReq.getKnowledgeId();
		List<Long> similarIds = similarKnowledgeReq.getSimilarIds();
		for (long similarId : similarIds) {
			SimilarKnowledge sk = new SimilarKnowledge();
			sk.setKnowledgeId(knowledgeId);
			sk.setSimilarId(similarId);
			similarKnowledgeService.insertSimilarKnowledge(sk);
		}
		return 1;
	}

	@ApiOperation(value = "查询关联文章id", notes = "查询关联文章id")
	@RequestMapping(value = "/getSimilarIds", method = RequestMethod.GET)
	public List<Long> getSimilarIds(@RequestParam long knowledgeId) {
		List<SimilarKnowledge> similarKnowledges = similarKnowledgeService
				.getSimilarKnowledgeById(knowledgeId);
		List<Long> similarIds = new ArrayList<Long>();
		for (SimilarKnowledge sk : similarKnowledges) {
			similarIds.add(sk.getSimilarId());
		}
		return similarIds;
	}

	@ApiOperation(value = "根据对象分页获取知识库", notes = "根据对象分页获取知识库")
	@RequestMapping(value = "/getKnowledgesForPage", method = RequestMethod.POST)
	public PageDto<Knowledge> getKnowledgesForPage(
			@RequestBody Knowledge knowledge,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,
			@RequestParam(required = false) String sort,
			@RequestParam(required = false) String order) {
		Map<String, String> cols = new HashMap<String, String>();
		cols.put("id", "id");
		cols.put("title", "title");
		cols.put("content", "content");
		cols.put("addTime", "add_time");
		cols.put("updateTime", "update_time");
		cols.put("author", "author");
		cols.put("language", "language");
		Pageable pageable = null;
		if (!StringUtils.isEmpty(order) && !StringUtils.isEmpty(sort)) {
			Sort sortObj = new Sort(Sort.Direction.fromStringOrNull(order),
					cols.get(sort));
			pageable = new PageRequest(page - 1, rows, sortObj);
		} else {
			pageable = new PageRequest(page - 1, rows);
		}
		
		List<QueryCondition> conditions = new ArrayList<QueryCondition>();
		
		//标题查询
		if(knowledge.getTitle() !=null && !knowledge.getTitle().isEmpty()){
			conditions.add(new QueryCondition("title",knowledge.getTitle(),QueryCondition.TYPE_LIKE));
		}
		
		//根据语言查询
		if(knowledge.getLanguage() !=null && !knowledge.getLanguage().isEmpty()){
			conditions.add(new QueryCondition("language",knowledge.getLanguage()));
		}
		
		Page<Knowledge> knowledges = knowledgeService.getKnowledgesByConditions(conditions, pageable);
		
		

		PageDto<Knowledge> pageDto = new PageDto<Knowledge>();
		if (knowledges != null) {
			pageDto.setRows(knowledges.getContent());
			pageDto.setTotal(knowledges.getTotalElements());
		} else {
			pageDto.setRows(new ArrayList<Knowledge>());
			pageDto.setTotal(0l);
		}
		return pageDto;
	}

	@Autowired
	private CategoryService categoryService;

	@ApiOperation(value = "新增文章标签", notes = "新增文章标签")
	@RequestMapping(value = "/insertCategory", method = RequestMethod.POST)
	public int insertCategory(@RequestBody Category category) {
		category.setId(IdUtil.getId());
		return categoryService.insertCategory(category);
	}

	@ApiOperation(value = "批量新增文章标签", notes = "批量新增文章标签")
	@RequestMapping(value = "/insertCategoryBatch", method = RequestMethod.POST)
	public int insertCategoryBatch(@RequestBody List<Category> list) {
		return categoryService.insertCategoryBatch(list);
	}

	@ApiOperation(value = "根据ID修改文章标签", notes = "根据ID修改文章标签")
	@RequestMapping(value = "/updateCategoryById", method = RequestMethod.POST)
	public int updateCategoryById(@RequestBody Category category) {
		return categoryService.updateCategoryById(category);
	}

	@ApiOperation(value = "根据ID删除文章标签", notes = "根据ID删除文章标签")
	@RequestMapping(value = "/deleteCategoryById", method = RequestMethod.GET)
	public int deleteCategoryById(@RequestParam Long id) {
		return categoryService.deleteCategoryById(id);
	}

	@ApiOperation(value = "根据ID获取文章标签", notes = "根据ID获取文章标签")
	@RequestMapping(value = "/getCategoryById", method = RequestMethod.GET)
	public Category getCategoryById(@RequestParam Long id) {
		return categoryService.getCategoryById(id);
	}

	@ApiOperation(value = "根据对象获取文章标签", notes = "根据对象获取文章标签")
	@RequestMapping(value = "/getCategorys", method = RequestMethod.POST)
	public List<Category> getCategorys(@RequestBody Category category) {
		return categoryService.getCategorys(category);
	}

	@ApiOperation(value = "根据对象分页获取文章标签", notes = "根据对象分页获取文章标签")
	@RequestMapping(value = "/getCategorysForPage", method = RequestMethod.POST)
	public PageDto<Category> getCategorysForPage(
			@RequestBody Category category,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,
			@RequestParam(required = false) String sort,
			@RequestParam(required = false) String order) {
		Map<String, String> cols = new HashMap<String, String>();
		cols.put("id", "id");
		cols.put("name", "name");
		cols.put("departmentId", "department_id");
		Pageable pageable = null;
		if (!StringUtils.isEmpty(order) && !StringUtils.isEmpty(sort)) {
			Sort sortObj = new Sort(Sort.Direction.fromStringOrNull(order),
					cols.get(sort));
			pageable = new PageRequest(page - 1, rows, sortObj);
		} else {
			pageable = new PageRequest(page - 1, rows);
		}
		Page<Category> categorys = categoryService.getCategorysForPage(
				category, pageable);

		PageDto<Category> pageDto = new PageDto<Category>();
		if (categorys != null) {
			pageDto.setRows(categorys.getContent());
			pageDto.setTotal(categorys.getTotalElements());
		} else {
			pageDto.setRows(new ArrayList<Category>());
			pageDto.setTotal(0l);
		}
		return pageDto;
	}
	
	@ApiOperation(value = "根据对象分页获取绿色通道", notes = "根据对象分页获取绿色通道")
	@RequestMapping(value = "/getGreenChannesForPage", method = RequestMethod.POST)
	public PageDto<GreenChanne> getGreenChannesForPage(@RequestBody GreenChanne greenChanne,
			@RequestParam(value="page", defaultValue="1")  int page,
			@RequestParam(value="rows", defaultValue="10") int rows,
			@RequestParam(required=false) String sort,
			@RequestParam(required=false) String order){
		Map<String,String> cols = new HashMap<String,String>();
		cols.put("id", "id");
		cols.put("name", "name");
		cols.put("sex", "sex");
		cols.put("phone", "phone");
		cols.put("address", "address");
		cols.put("describe", "describe");
		cols.put("picture", "picture");
		cols.put("regionId", "region_id");
		Pageable pageable =null;
		if(!StringUtils.isEmpty(order)&&!StringUtils.isEmpty(sort)){
			Sort sortObj =new Sort(Sort.Direction.fromStringOrNull(order), cols.get(sort));
			pageable = new PageRequest(page-1, rows, sortObj);
		}else{
			pageable=new PageRequest(page-1, rows);
		}
		Page<GreenChanne> greenChannes= greenChanneService.getGreenChannesForPage(greenChanne,pageable);
	
		PageDto<GreenChanne> pageDto = new PageDto<GreenChanne>();
		if (greenChannes != null) {
			pageDto.setRows( greenChannes.getContent());
			pageDto.setTotal(greenChannes.getTotalElements());
		} else {
			pageDto.setRows(new ArrayList<GreenChanne>());
			pageDto.setTotal(0l);
		}
		 
		return pageDto;
 	}
	
	@ApiOperation(value = "根据ID获取绿色通道", notes = "根据ID获取绿色通道")
	@RequestMapping(value = "/getGreenChanneById", method = RequestMethod.GET)
	public GreenChanne getGreenChanneById( @RequestParam Long id  ){
		return greenChanneService.getGreenChanneById(  id  );
	}
	
	@ApiOperation(value = "设置首页展现科室", notes = "设置首页展现科室")
	@RequestMapping(value = "/setHomeDepartment", method = RequestMethod.POST)
	public int setHomeDepartment( @RequestBody RegionDepartment req  ){
		
		long regionId = Long.parseLong(PartyUtils.currentParty().getCommonRegionId());
		
		RegionDepartment addRd = new RegionDepartment();
		addRd.setRegionId(regionId);
		addRd.setDepartmentId(req.getDepartmentId());
		addRd.setPictures(req.getPictures());
		addRd.setContent(req.getContent());
		regionDepartmentService.deleteRegionDepartmentById(regionId);
		regionDepartmentService.insertRegionDepartment(addRd);
		
		return 1;
	}
	
	@ApiOperation(value = "根据ID获取事业部关联科室", notes = "根据ID获取事业部关联科室")
	@RequestMapping(value = "/getRegionDepartmentById", method = RequestMethod.GET)
	public RegionDepartment getRegionDepartmentById(   ){
		long id =Long.parseLong(PartyUtils.currentParty().getCommonRegionId());
		RegionDepartment rd = regionDepartmentService.getRegionDepartmentById(  id  );
		if(rd ==null){
			rd = new RegionDepartment();
		}
		return rd;
	}
	

	/**
	 * 事业部菜单
	 * 
	 * @return
	 */
	private List<MenuVo> getRegionMenus() {
		List<MenuVo> list = new ArrayList<>();
		// ---------第1个----------//
		MenuVo pVo1 = new MenuVo();
		pVo1.setName("首页");
		List<MenuVo> cList1 = new ArrayList<>();
		MenuVo c1Vo1 = new MenuVo();
		c1Vo1.setLink("#rollpic");
		c1Vo1.setName("轮播图管理");
		cList1.add(c1Vo1);
		
		MenuVo c1Vo2 = new MenuVo();
		c1Vo2.setLink("#home");
		c1Vo2.setName("首页管理");
		cList1.add(c1Vo2);
		

		pVo1.setMenuVos(cList1);
		list.add(pVo1);

		// ---------第2个----------//
		MenuVo pVo2 = new MenuVo();
		pVo2.setName("就诊指引");
		List<MenuVo> cList2 = new ArrayList<>();
		MenuVo c2Vo1 = new MenuVo();
		c2Vo1.setLink("#doctor");
		c2Vo1.setName("医生团队管理");
		cList2.add(c2Vo1);

		pVo2.setMenuVos(cList2);
		list.add(pVo2);

		// ---------第3个----------//
		MenuVo pVo3 = new MenuVo();
		pVo3.setName("系统管理");
		List<MenuVo> cList3 = new ArrayList<>();

		MenuVo c3Vo2 = new MenuVo();
		c3Vo2.setLink("#store");
		c3Vo2.setName("门店管理");
		cList3.add(c3Vo2);

		pVo3.setMenuVos(cList3);
		list.add(pVo3);

		return list;
	}

	/**
	 * 集团菜单
	 * 
	 * @return
	 */
	public List<MenuVo> getAdminMenus() {

		List<MenuVo> list = new ArrayList<>();

		// ---------第1个----------//
		MenuVo pVo1 = new MenuVo();
		pVo1.setName("首页");
		List<MenuVo> cList1 = new ArrayList<>();
		MenuVo c1Vo1 = new MenuVo();
		c1Vo1.setLink("#rollpicAll");
		c1Vo1.setName("轮播图管理");
		cList1.add(c1Vo1);

		MenuVo c1Vo2 = new MenuVo();
		c1Vo2.setLink("#home");
		c1Vo2.setName("首页管理");
		cList1.add(c1Vo2);
		
		pVo1.setMenuVos(cList1);
		list.add(pVo1);

		// ---------第2个----------//
		MenuVo pVo2 = new MenuVo();
		pVo2.setName("就诊指引");
		List<MenuVo> cList2 = new ArrayList<>();
		MenuVo c2Vo1 = new MenuVo();
		c2Vo1.setLink("#department");
		c2Vo1.setName("诊疗科室");
		cList2.add(c2Vo1);

		MenuVo c2Vo2 = new MenuVo();
		c2Vo2.setLink("#article/00");
		c2Vo2.setName("诊疗项目");
		cList2.add(c2Vo2);

		MenuVo c2Vo3 = new MenuVo();
		c2Vo3.setLink("#doctor");
		c2Vo3.setName("医生团队");
		cList2.add(c2Vo3);

//		MenuVo c2Vo4 = new MenuVo();
//		c2Vo4.setLink("#doctor");
//		c2Vo4.setName("医疗网络");
//		cList2.add(c2Vo4);

		pVo2.setMenuVos(cList2);
		list.add(pVo2);

		// ---------第3个----------//
		MenuVo pVo3 = new MenuVo();
		pVo3.setName("知识库管理");
		List<MenuVo> cList3 = new ArrayList<>();
		MenuVo c3Vo1 = new MenuVo();
		c3Vo1.setLink("#knowledge");
		c3Vo1.setName("知识库管理");
		cList3.add(c3Vo1);

		MenuVo c3Vo2 = new MenuVo();
		c3Vo2.setLink("#category");// TODO
		c3Vo2.setName("知识库标签管理");
		cList3.add(c3Vo2);

		pVo3.setMenuVos(cList3);
		list.add(pVo3);

		// ---------第4个----------//
		MenuVo pVo4 = new MenuVo();
		pVo4.setName("IDDC管理");
		List<MenuVo> cList4 = new ArrayList<>();
		MenuVo c4Vo1 = new MenuVo();
		c4Vo1.setLink("#classify");
		c4Vo1.setName("专家分类");
		cList4.add(c4Vo1);

		MenuVo c4Vo2 = new MenuVo();
		c4Vo2.setLink("#expert");
		c4Vo2.setName("专家管理");
		cList4.add(c4Vo2);

		MenuVo c4Vo3 = new MenuVo();
		c4Vo3.setLink("#article/06");
		c4Vo3.setName("课题交流");
		cList4.add(c4Vo3);

		MenuVo c4Vo4 = new MenuVo();
		c4Vo4.setLink("#caseCenter");
		c4Vo4.setName("案例中心");
		cList4.add(c4Vo4);

		pVo4.setMenuVos(cList4);
		list.add(pVo4);

		// ---------第5个----------//
		MenuVo pVo5 = new MenuVo();
		pVo5.setName("学术合作");
		List<MenuVo> cList5 = new ArrayList<>();
		MenuVo c5Vo1 = new MenuVo();
		c5Vo1.setLink("#article/02");
		c5Vo1.setName("校企合作");
		cList5.add(c5Vo1);

		MenuVo c5Vo2 = new MenuVo();
		c5Vo2.setLink("#article/03");
		c5Vo2.setName("学术交流");
		cList5.add(c5Vo2);

		pVo5.setMenuVos(cList5);
		list.add(pVo5);

		// ---------第6个----------//
		MenuVo pVo6 = new MenuVo();
		pVo6.setName("关于拜博");
		List<MenuVo> cList6 = new ArrayList<>();
		MenuVo c6Vo1 = new MenuVo();
		c6Vo1.setLink("#article/04");
		c6Vo1.setName("社会责任");
		cList6.add(c6Vo1);

		MenuVo c6Vo2 = new MenuVo();
		c6Vo2.setLink("#article/05");
		c6Vo2.setName("精彩活动");
		cList6.add(c6Vo2);

		pVo6.setMenuVos(cList6);
		list.add(pVo6);

		// ---------第7个----------//
		MenuVo pVo7 = new MenuVo();
		pVo7.setName("系统管理");
		List<MenuVo> cList7 = new ArrayList<>();
		MenuVo c7Vo1 = new MenuVo();
		c7Vo1.setLink("#region");
		c7Vo1.setName("事业部管理");
		cList7.add(c7Vo1);

		MenuVo c7Vo2 = new MenuVo();
		c7Vo2.setLink("#adminStore");
		c7Vo2.setName("门店管理");
		cList7.add(c7Vo2);

		MenuVo c7Vo3 = new MenuVo();
		c7Vo3.setLink("#party");
		c7Vo3.setName("账号管理");
		cList7.add(c7Vo3);
		
		MenuVo c7Vo4 = new MenuVo();
		c7Vo4.setLink("#user");
		c7Vo4.setName("用户管理");
		cList7.add(c7Vo4);
		
		MenuVo c7Vo5 = new MenuVo();
		c7Vo5.setLink("#greenChanne");
		c7Vo5.setName("绿色通道");
		cList7.add(c7Vo5);

		pVo7.setMenuVos(cList7);
		list.add(pVo7);

		return list;
	}
	
	
    public static String dateTransformBetweenTimeZone(Date sourceDate, DateFormat formatter,  
            TimeZone sourceTimeZone, TimeZone targetTimeZone) {  
            Long targetTime = sourceDate.getTime() - sourceTimeZone.getRawOffset() + targetTimeZone.getRawOffset();  
            return getTime(new Date(targetTime), formatter);  
        }  
             
        public static String getTime(Date date, DateFormat formatter){  
           return formatter.format(date);  
        }  
         
	 public static void main(String[] args){  
	        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	        Date date = Calendar.getInstance().getTime();  
	        TimeZone srcTimeZone = TimeZone.getTimeZone("GMT+8");  
	        TimeZone destTimeZone = TimeZone.getTimeZone("GMT+7");  
	        System.out.println(dateTransformBetweenTimeZone(date, formatter, srcTimeZone, destTimeZone));  
	    } 

}
