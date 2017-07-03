package com.mxg.bybo.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
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
import basic.authority.service.PartyService;
import basic.authority.utils.PartyUtils;
import basic.common.core.dto.PageDto;
import basic.common.core.exception.BaseException;
import basic.common.core.id.IdUtil;

import com.mxg.bybo.model.Article;
import com.mxg.bybo.model.Classify;
import com.mxg.bybo.model.Department;
import com.mxg.bybo.model.Doctor;
import com.mxg.bybo.model.Expert;
import com.mxg.bybo.model.GreenChanne;
import com.mxg.bybo.model.Region;
import com.mxg.bybo.model.RegionDepartment;
import com.mxg.bybo.model.Rollpic;
import com.mxg.bybo.model.Store;
import com.mxg.bybo.model.req.RegisterReq;
import com.mxg.bybo.model.req.ResetPwdReq;
import com.mxg.bybo.model.req.SearchKnowledgeReq;
import com.mxg.bybo.model.resp.DepartmentResp;
import com.mxg.bybo.model.vo.CategoryVo;
import com.mxg.bybo.model.vo.DepartmentVo;
import com.mxg.bybo.model.vo.KnowledgeVo;
import com.mxg.bybo.model.vo.SearchKnowledgeVo;
import com.mxg.bybo.service.ArticleService;
import com.mxg.bybo.service.ClassifyService;
import com.mxg.bybo.service.DepartmentService;
import com.mxg.bybo.service.DoctorService;
import com.mxg.bybo.service.ExpertService;
import com.mxg.bybo.service.GreenChanneService;
import com.mxg.bybo.service.GwService;
import com.mxg.bybo.service.RegionDepartmentService;
import com.mxg.bybo.service.RegionService;
import com.mxg.bybo.service.RollpicService;
import com.mxg.bybo.service.StoreService;
import com.mxg.common.constant.GeneralConstant;
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
@Api(value = "GwController", description = "官网相关数据查询")
@RequestMapping(value = "/gw")
public class GwController {

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
	private DepartmentService departmentService;

	@Autowired
	private GwService gwService;

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private RegionService regionService;

	@Autowired
	private StoreService storeService;

	@Autowired
	private ExpertService expertService;

	@Autowired
	private ClassifyService classifyService;

	@Autowired
	private PartyService partyService;

	@Autowired
	private GreenChanneService greenChanneService;

	@Autowired(required = false)
	private StandardPasswordEncoder passwordEncoder;

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private RegionDepartmentService regionDepartmentService;

	@ApiOperation(value = "查询轮播图", notes = "查询轮播图")
	@RequestMapping(value = "/getRollpics", method = RequestMethod.GET)
	public List<Rollpic> getRollpics(@RequestParam String lang,
			@RequestParam Long regionId) {
		Rollpic rollpic = new Rollpic();
		rollpic.setRegionId(regionId);
		rollpic.setLanguage(lang);
		return rollpicService.getRollpics(rollpic);
	}

	@ApiOperation(value = "根据ID获取科室信息", notes = "根据ID获取科室信息")
	@RequestMapping(value = "/getDepartmentById", method = RequestMethod.GET)
	public DepartmentResp getDepartmentById(@RequestParam Long departmentId,
			@RequestParam Long regionId) {
		DepartmentResp resp = new DepartmentResp();
		Department department = departmentService
				.getDepartmentById(departmentId);
		List<Doctor> doctors = new ArrayList<Doctor>();
		if(regionId == 1){
			doctors = gwService.getAllDoctorByDepartmentId(departmentId);
		}else{
			doctors = gwService.getDoctorByDepartmentId(regionId,
						departmentId);
		}
		
		resp.setDepartment(department);
		resp.setDoctors(doctors);
		return resp;
	}

	@ApiOperation(value = "获取科室医生", notes = "获取科室医生")
	@RequestMapping(value = "/getDepartmentDoctors", method = RequestMethod.GET)
	public List<Doctor> getDepartmentDoctors(@RequestParam long regionId,@RequestParam long departmentId) {
		List<Doctor> doctors = new ArrayList<Doctor>();
		if(regionId == 1){
			doctors = gwService.getAllDoctorByDepartmentId(departmentId);
		}else{
			doctors = gwService.getDoctorByDepartmentId(regionId,
						departmentId);
		}
		return doctors;
	}
	
	
	@ApiOperation(value = "获取科室列表", notes = "获取科室列表")
	@RequestMapping(value = "/getDepartments", method = RequestMethod.GET)
	public List<Department> getDepartments(@RequestParam String lang) {
		Department department = new Department();
		department.setLanguage(lang);
		return departmentService.getDepartments(department);

	}

	@ApiOperation(value = "获取医生", notes = "获取医生")
	@RequestMapping(value = "/getDoctors", method = RequestMethod.GET)
	public List<Doctor> getDoctors(@RequestParam String lang,
			@RequestParam Long regionId) {
		Doctor doctor = new Doctor();
		doctor.setLanguage(lang);
		doctor.setRegionId(regionId);
		return doctorService.getDoctors(doctor);
	}

	@ApiOperation(value = "分页获取医生", notes = "分页获取医生")
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
		List<QueryCondition> conditions = new ArrayList<QueryCondition>();

		if (doctor.getLanguage() == null || doctor.getLanguage().isEmpty()) {
			throw new BaseException("1001", "参数输入不正确", null);
		}

		conditions.add(new QueryCondition("language", doctor.getLanguage()));

		// 根据区域查询
		if (doctor.getRegionId() != null) {
			conditions
					.add(new QueryCondition("region_id", doctor.getRegionId()));
		}
		// 根据科室名称分类查询
		if (doctor.getDepartment() != null && !doctor.getDepartment().isEmpty()) {
			conditions.add(new QueryCondition("department", doctor
					.getDepartment(), QueryCondition.TYPE_LIKE));
		}

		// 根据姓名模糊查询
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

	@ApiOperation(value = "根据ID获取医生", notes = "根据ID获取医生")
	@RequestMapping(value = "/getDoctorById", method = RequestMethod.GET)
	public Doctor getDoctorById(@RequestParam Long id) {
		Doctor d = doctorService.getDoctorById(id);
		if (d == null) {
			d = new Doctor();
		}
		return d;
	}

	@ApiOperation(value = "获取事业部列表", notes = "获取事业部列表")
	@RequestMapping(value = "/getRegions", method = RequestMethod.POST)
	public List<Region> getRegions(@RequestBody Region region) {
		region.setParentRegionId(GeneralConstant.REGION_PARENT);
		return regionService.getRegions(region);
	}

	@ApiOperation(value = "获取门店列表", notes = "获取门店列表")
	@RequestMapping(value = "/getStores", method = RequestMethod.GET)
	public List<Store> getStores(@RequestParam String lang,
			@RequestParam Long regionId) {
		Store store = new Store();
		store.setLanguage(lang);
		store.setRegionId(regionId);
		return storeService.getStores(store);
	}

	@ApiOperation(value = "根据对象获取专家", notes = "根据对象获取专家")
	@RequestMapping(value = "/getExperts", method = RequestMethod.GET)
	public List<Expert> getExperts(@RequestParam String lang) {
		Expert expert = new Expert();
		expert.setLanguage(lang);
		return expertService.getExperts(expert);
	}

	@ApiOperation(value = "分页获取专家", notes = "分页获取专家")
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
		cols.put("department", "department");
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
		if (expert.getLanguage() == null || expert.getLanguage().isEmpty()) {
			throw new BaseException("1001", "参数输入不正确", null);
		}
		conditions.add(new QueryCondition("language", expert.getLanguage()));

		// 根据头衔分类查询
		if (expert.getDepartment() != null && !expert.getDepartment().isEmpty()) {
			conditions.add(new QueryCondition("department", expert
					.getDepartment(), QueryCondition.TYPE_LIKE));
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

	@ApiOperation(value = "根据ID获取专家", notes = "根据ID获取专家")
	@RequestMapping(value = "/getExpertById", method = RequestMethod.GET)
	public Expert getExpertById(@RequestParam Long id) {
		return expertService.getExpertById(id);
	}

	@ApiOperation(value = "获取专家分类", notes = "获取专家分类")
	@RequestMapping(value = "/getClassifys", method = RequestMethod.GET)
	public List<Classify> getClassifys(@RequestParam String lang) {
		Classify classify = new Classify();
		classify.setLanguage(lang);
		return classifyService.getClassifys(classify);
	}

	@ApiOperation(value = "发送短信", notes = "发送短信")
	@RequestMapping(value = "/sendSms", method = RequestMethod.GET)
	public String sendSms(HttpServletRequest req, @RequestParam String phone,
			@RequestParam String code) {
		return gwService.sendSms(req, phone, code);
	}

	@ApiOperation(value = "发送短信", notes = "发送短信")
	@RequestMapping(value = "/sendForgetSms", method = RequestMethod.GET)
	public String sendForgetSms(HttpServletRequest req,
			@RequestParam String phone, @RequestParam String code) {
		return gwService.sendForgetSms(req, phone, code);
	}

	@ApiOperation(value = "忘记密码", notes = "忘记密码")
	@RequestMapping(value = "/forgetPwd", method = RequestMethod.POST)
	public int forgetPwd(HttpServletRequest req,
			@RequestBody ResetPwdReq resetPwdReq) {
		HttpSession session = req.getSession(true);
		String smsCode = (String) session.getAttribute("forgetSmsCode");
		if (resetPwdReq.getSmsCode().isEmpty()
				|| !resetPwdReq.getSmsCode().equals(smsCode)) {
			throw new BaseException("100203", "短信验证码不正确", null);
		}
		Party party = new Party();
		party.setPhone(resetPwdReq.getPhone());
		List<Party> partys = partyService.getPartys(party);

		if (partys.size() == 0) {
			throw new BaseException("100205", "该手机用户不存在", null);
		}

		String partyId = partys.get(0).getPartyId();
		Party updateParty = new Party();
		updateParty.setPartyId(partyId);
		updateParty
				.setPassword(resetPwdReq.getNewPwd());
		partyService.updatePartyById(updateParty);
		session.removeAttribute("forgetSmsCode");
		return 0;
	}

	@ApiOperation(value = "校验短信验证码", notes = "校验短信验证码")
	@RequestMapping(value = "/validSmsCode", method = RequestMethod.GET)
	public String validSmsCode(HttpServletRequest req,
			@RequestParam String smsCode) {
		return gwService.validSmsCode(req, smsCode);
	}

	@ApiOperation(value = "注册用户", notes = "注册用户")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(HttpServletRequest req,
			@RequestBody RegisterReq registerReq) {
		return gwService.register(req, registerReq);
	}

	@ApiOperation(value = "获取图片验证码", notes = "获取图片验证码")
	@RequestMapping(value = "/getVerifyCode", method = RequestMethod.GET)
	public void image(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		// 设置不缓存
		resp.setHeader("pragma", "no-cache");
		resp.setHeader("cache-control", "no-cache");
		resp.setDateHeader("expires", 0);
		// 指定生成的图片的格式
		resp.setContentType("image/jpeg");
		// 验证码的张宽
		int width = 80, height = 30;
		// 图片流
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_BGR);

		Graphics g = image.getGraphics(); // 创建Graphics对象,其作用相当于画笔
		Graphics2D g2d = (Graphics2D) g; // 创建Grapchics2D对象
		Font mfont = new Font("楷体", Font.BOLD, 16); // 定义字体样式
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height); // 绘制背景
		g.setFont(mfont); // 设置字体
		g.setColor(getRandColor(180, 200));

		// 绘制100条颜色和位置全部为随机产生的线条,该线条为2f
		Random random = new Random();
		for (int i = 0; i < 100; i++) {
			int x = random.nextInt(width - 1);
			int y = random.nextInt(height - 1);
			int x1 = random.nextInt(6) + 1;
			int y1 = random.nextInt(12) + 1;
			BasicStroke bs = new BasicStroke(2f, BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_BEVEL);
			Line2D line2d = new Line2D.Double(x, y, x + x1, y + y1);
			g2d.setStroke(bs);
			g2d.draw(line2d);
		}

		// 输出由英文，数字，和中文随机组成的验证文字，具体的组合方式根据生成随机数确定。
		String sRand = "";
		String ctmp = "";
		int itmp = 0;
		// 制定输出的验证码为四位
		for (int i = 0; i < 4; i++) {
			// random.nextInt(2)在0-2中间去随机，不包含2，这样是为了下面不去生成中文验证码，验证出现中文时，不好判断
			switch (random.nextInt(2)) {
			case 1: // 生成A-Z的字母
				itmp = random.nextInt(26) + 65;
				ctmp = String.valueOf((char) itmp);
				break;
			default:
				itmp = random.nextInt(10) + 48;
				ctmp = String.valueOf((char) itmp);
				break;
			}
			sRand += ctmp;
			Color color = new Color(20 + random.nextInt(110),
					20 + random.nextInt(110), random.nextInt(110));
			g.setColor(color);
			// 将生成的随机数进行随机缩放并旋转制定角度 PS.建议不要对文字进行缩放与旋转,因为这样图片可能不正常显示
			/* 将文字旋转制定角度 */
			Graphics2D g2d_word = (Graphics2D) g;
			AffineTransform trans = new AffineTransform();
			trans.rotate((0) * 3.14 / 180, 15 * i + 8, 7);
			/* 缩放文字 */
			float scaleSize = random.nextFloat() + 0.8f;
			if (scaleSize > 1f)
				scaleSize = 1f;
			trans.scale(scaleSize, scaleSize);
			g2d_word.setTransform(trans);
			g.drawString(ctmp, 15 * i + 18, 20);
		}

		// 打开session，并放入验证图片
		HttpSession session = req.getSession(true);
		session.setAttribute("verifyResult", sRand);
		g.dispose(); // 释放g所占用的系统资源
		ImageIO.write(image, "JPEG", resp.getOutputStream()); // 输出图片
	}

	/* 该方法主要作用是获得随机生成的颜色 */
	public Color getRandColor(int s, int e) {
		Random random = new Random();
		if (s > 255)
			s = 255;
		if (e > 255)
			e = 255;
		int r, g, b;
		r = s + random.nextInt(e - s); // 随机生成RGB颜色中的r值
		g = s + random.nextInt(e - s); // 随机生成RGB颜色中的g值
		b = s + random.nextInt(e - s); // 随机生成RGB颜色中的b值
		return new Color(r, g, b);
	}

	@ApiOperation(value = "新增绿色通道", notes = "新增绿色通道")
	@RequestMapping(value = "/insertGreenChanne", method = RequestMethod.POST)
	public int insertGreenChanne(@RequestBody GreenChanne greenChanne) {
		greenChanne.setId(IdUtil.getId());
		greenChanne.setCreateTime(new Date());
		greenChanne.setStatus(0);
		return greenChanneService.insertGreenChanne(greenChanne);
	}
	
	@ApiOperation(value = "上传文件", notes = "上传文件")
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String addLog(@RequestParam("fileUpload") CommonsMultipartFile file,
			HttpServletRequest request) throws Exception {
		return saveFile(file, request);
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

		// 根据类型查询
		if (article.getCategoryId() != null
				&& !article.getCategoryId().isEmpty()) {
			conditions.add(new QueryCondition("category_id", article
					.getCategoryId()));
		}

		// 根据科室查询
		if (article.getDepartment() != null
				&& !article.getDepartment().isEmpty()) {
			conditions.add(new QueryCondition("department", article
					.getDepartment()));
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

	@ApiOperation(value = "根据ID获取案例文章", notes = "根据ID获取案例文章")
	@RequestMapping(value = "/getArticleById", method = RequestMethod.GET)
	public Article getArticleById(@RequestParam Long id) {
		Article article = articleService.getArticleById(id);
		if (article == null) {
			article = new Article();
		}
		return article;
	}
	
	@ApiOperation(value = "获取分类知识库", notes = "获取分类知识库")
	@RequestMapping(value = "/getDepartmentVos", method = RequestMethod.GET)
	public List<CategoryVo> getDepartmentVos(@RequestParam String lang) {
		return gwService.getDepartmentVos(lang);
	}

	@ApiOperation(value = "根据科室分类获取知识库", notes = "根据科室分类获取知识库")
	@RequestMapping(value = "/getDepartmentVoById", method = RequestMethod.GET)
	public CategoryVo getDepartmentVoById(@RequestParam long id) {
		return gwService.getDepartmentVoById(id);
	}
	
	@ApiOperation(value = "根据知识库id获取知识库详情", notes = "根据知识库id获取知识库详情")
	@RequestMapping(value = "/getKnowledgeVoById", method = RequestMethod.GET)
	public KnowledgeVo getKnowledgeVoById(@RequestParam long knowledgeId) {
		return gwService.getKnowledgeVoById(knowledgeId);
	}
	
	@ApiOperation(value = "搜索知识库", notes = "搜索知识库")
	@RequestMapping(value = "/searchKnowledges", method = RequestMethod.POST)
	public SearchKnowledgeVo searchKnowledges(@RequestBody SearchKnowledgeReq searReq) {
		return gwService.searchKnowledges(searReq);
	}
	
	@ApiOperation(value = "根据regionid获取科室列表", notes = "根据regionid获取科室列表")
	@RequestMapping(value = "/getDepartmentsByRegionId", method = RequestMethod.GET)
	public List<Department> getDepartmentsByRegionId(@RequestParam String lang,@RequestParam long regionId) {
		
		List<QueryCondition> conditions =  new ArrayList<QueryCondition>();
		
		RegionDepartment r = regionDepartmentService.getRegionDepartmentById(regionId);
		List<Long> ids = new ArrayList<Long>();
		for(String id:r.getDepartmentId().split(",")){
			ids.add(Long.parseLong(id));
		}
		conditions.add(new QueryCondition("id",ids,QueryCondition.TYPE_IN));
		
		return departmentService.getDepartmentsByCondition(conditions);

	}
	
	@ApiOperation(value = "根据ID获取事业部关联科室", notes = "根据ID获取事业部关联科室")
	@RequestMapping(value = "/getRegionDepartmentById", method = RequestMethod.GET)
	public RegionDepartment getRegionDepartmentById(@RequestParam long regionId ){
		RegionDepartment rd = regionDepartmentService.getRegionDepartmentById(  regionId  );
		if(rd ==null){
			rd = new RegionDepartment();
		}
		return rd;
	}
	
	@ApiOperation(value = "根据对象分页获取门店", notes = "根据对象分页获取门店")
	@RequestMapping(value = "/getStoresForPage", method = RequestMethod.POST)
	public PageDto<Store> getStoresForPage(@RequestBody Store store,
			@RequestParam(value="page", defaultValue="1")  int page,
			@RequestParam(value="rows", defaultValue="10") int rows,
			@RequestParam(required=false) String sort,
			@RequestParam(required=false) String order){
		Map<String,String> cols = new HashMap<String,String>();
		cols.put("id", "id");
		cols.put("name", "name");
		cols.put("address", "address");
		cols.put("phone", "phone");
		cols.put("telphone", "telphone");
		cols.put("userName", "user_name");
		cols.put("regionId", "region_id");
		cols.put("language", "language");
		Pageable pageable =null;
		if(!StringUtils.isEmpty(order)&&!StringUtils.isEmpty(sort)){
			Sort sortObj =new Sort(Sort.Direction.fromStringOrNull(order), cols.get(sort));
			pageable = new PageRequest(page-1, rows, sortObj);
		}else{
			pageable=new PageRequest(page-1, rows);
		}
		List<QueryCondition> conditions = new ArrayList<QueryCondition>();
		//根据事业部查询
		if(store.getRegionId() != null){
			conditions.add(new QueryCondition("region_id", store.getRegionId()) );
		}
		
		//根据国际化来查询
		if(store.getLanguage() != null && !store.getLanguage().isEmpty()){
			conditions.add(new QueryCondition("language", store.getLanguage()) );
		}
		
		//根据名称查询
		if(store.getName() != null && !store.getName().isEmpty()){
			conditions.add(new QueryCondition("name", store.getName(),QueryCondition.TYPE_LIKE));
		}
		
		Page<Store> stores= storeService.getStoresForPage(conditions,pageable);
	
		PageDto<Store> pageDto = new PageDto<Store>();
		if (stores != null) {
			pageDto.setRows( stores.getContent());
			pageDto.setTotal(stores.getTotalElements());
		} else {
			pageDto.setRows(new ArrayList<Store>());
			pageDto.setTotal(0l);
		}
		 
		return pageDto;
 	}
	
	
}
