package com.mxg.bybo.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.experimental.categories.Categories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mxg.bybo.dao.ArticleDao;
import com.mxg.bybo.dao.DepartmentDao;
import com.mxg.bybo.dao.DoctorDao;
import com.mxg.bybo.dao.ExpertDao;
import com.mxg.bybo.dao.KnowledgeDao;
import com.mxg.bybo.model.Article;
import com.mxg.bybo.model.Category;
import com.mxg.bybo.model.Department;
import com.mxg.bybo.model.Doctor;
import com.mxg.bybo.model.Expert;
import com.mxg.bybo.model.Knowledge;
import com.mxg.bybo.model.vo.KnowledgeVo;
import com.mxg.bybo.rest.DoctorController;
import com.mxg.bybo.service.GwService;
import com.mxg.bybo.service.ToHtmlService;
import com.mxg.common.utils.GeneralUtils;

/**
 * ArticleServiceImpl
 *
 * @version : Ver 1.0
 * @author : panda
 * @date : 2017-3-11
 */
@Service
public class ToHtmlServiceImpl implements ToHtmlService {
	@Autowired
	HttpServletRequest request;

	@Autowired
	DepartmentDao departmentDao;

	@Autowired
	DoctorDao doctorDao;

	@Autowired
	ExpertDao expertDao;

	@Autowired
	ArticleDao articleDao;

	@Autowired
	KnowledgeDao knowledgeDao;

	@Autowired
	GwService gwService;

	@Override
	public void toSubjectDetail(Department d) {
		try {
			String basePath = request.getSession().getServletContext()
					.getRealPath("/");
			File baseFile = new File(basePath);
			String tplPath = basePath + "/tpl/subject_detail_tpl.html";
			String rootPath = baseFile.getParent();
			String htmlFilePath = rootPath + "/static-html";
			File folder = new File(htmlFilePath);
			// 如果文件夹不存在则创建
			if (!folder.exists() && !folder.isDirectory()) {
				folder.mkdirs();
			}
			htmlFilePath += "/subject_detail_" + d.getId() + ".html";

			String content = readFile(tplPath);

			content = replaceValue(content, "##name##", d.getName());
			content = replaceValue(content, "##icon##", d.getIcon());
			content = replaceValue(content, "##summary##", d.getSummary());
			content = replaceValue(content, "##service##", d.getService());
			content = replaceValue(content, "##technical##", d.getTechnical());
			content = replaceValue(content, "##keywords##", d.getKeywords());
			content = replaceValue(content, "##description##", d.getDescription());
			writeFile(content, htmlFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 读取文件内容
	 * 
	 * @param filePath
	 * @return
	 */
	public String readFile(String filePath) {
		System.getProperties().put("file.encoding", "UTF-8");
		String str = "";
		try {
			String tempStr = "";
			FileInputStream is = new FileInputStream(filePath);// 读取模块文件
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			while ((tempStr = br.readLine()) != null)
				str = str + tempStr;
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		return str;
	}

	/**
	 * 写入文件内容
	 * 
	 * @param content
	 * @param htmlFile
	 * @return
	 */
	public boolean writeFile(String content, String htmlFilePath) {
		System.getProperties().put("file.encoding", "UTF-8");
		try {
			File f = new File(htmlFilePath);
//			OutputStreamWriter writerStream = new OutputStreamWriter(new FileOutputStream(f),"GBK");
//			BufferedWriter o = new BufferedWriter(writerStream);
			BufferedWriter o = new BufferedWriter(new FileWriter(f));
			o.write(content);
			o.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	@Override
	public void toSubjectDetailAll() {
		List<Department> ds = departmentDao.getDepartments(new Department());
		for (Department d : ds) {
			toSubjectDetail(d);
		}
	}

	@Override
	public void toDoctorDetail(Doctor d) {
		try {
			String basePath = request.getSession().getServletContext()
					.getRealPath("/");
			File baseFile = new File(basePath);
			String tplPath = basePath + "/tpl/doctor_detail_tpl.html";
			String rootPath = baseFile.getParent();
			String htmlFilePath = rootPath + "/static-html";
			File folder = new File(htmlFilePath);
			// 如果文件夹不存在则创建
			if (!folder.exists() && !folder.isDirectory()) {
				folder.mkdirs();
			}
			htmlFilePath += "/doctor_detail_" + d.getId() + ".html";

			String content = readFile(tplPath);

			String cc = d.getContent();
			cc = cc.replaceAll("(\r\n|\r|\n|\n\r)", "<p>");

			content = replaceValue(content, "##name##", d.getName());
			content = replaceValue(content, "##honor##", d.getHonor());
			content = replaceValue(content, "##title##", d.getTitle());
			content = replaceValue(content, "##duty##", d.getDuty());
			content = replaceValue(content, "##specialty##", d.getSpecialty());
			content = replaceValue(content, "##doctorPhoto##", d.getPhoto());
			content = replaceValue(content, "##adept##", d.getAdept());
			content = replaceValue(content, "##content##", cc);
			content = replaceValue(content, "##keywords##", d.getKeywords());
			content = replaceValue(content, "##description##", d.getDescription());
			writeFile(content, htmlFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String replaceValue(String content, String oldV, String newV) throws UnsupportedEncodingException {

		if (newV == null || newV.isEmpty()) {
			newV = "-";
		}
		System.out.println(Charset.defaultCharset());
		System.out.println(System.getProperty("file.encoding"));
		content = content.replaceAll(oldV, newV);

		return content;
	}

	@Override
	public void toDoctorDetailAll() {
		List<Doctor> ds = doctorDao.getDoctors(new Doctor());
		for (Doctor d : ds) {
			toDoctorDetail(d);
		}
	}

	@Override
	public void toExpertDetail(Expert d) {
		try {
			String basePath = request.getSession().getServletContext()
					.getRealPath("/");
			File baseFile = new File(basePath);
			String tplPath = basePath + "/tpl/expert_detail_tpl.html";
			String rootPath = baseFile.getParent();
			String htmlFilePath = rootPath + "/static-html";
			File folder = new File(htmlFilePath);
			// 如果文件夹不存在则创建
			if (!folder.exists() && !folder.isDirectory()) {
				folder.mkdirs();
			}
			htmlFilePath += "/expert_detail_" + d.getId() + ".html";

			String content = readFile(tplPath);

			String cc = d.getContent();
			cc = cc.replaceAll("(\r\n|\r|\n|\n\r)", "<p>");

			content = replaceValue(content, "##name##", d.getName());
			content = replaceValue(content, "##honor##", d.getHonor());
			content = replaceValue(content, "##title##", d.getTitle());
			content = replaceValue(content, "##duty##", d.getDuty());
			content = replaceValue(content, "##specialty##", d.getSpecialty());
			content = replaceValue(content, "##doctorPhoto##", d.getPhoto());
			content = replaceValue(content, "##adept##", d.getAdept());
			content = replaceValue(content, "##content##", cc);
			content = replaceValue(content, "##keywords##", d.getKeywords());
			content = replaceValue(content, "##description##", d.getDescription());
			writeFile(content, htmlFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void toExpertDetailAll() {
		List<Expert> ds = expertDao.getExperts(new Expert());
		for (Expert d : ds) {
			toExpertDetail(d);
		}

	}

	@Override
	public void toTopicExchangeDetail(Article d) {
		toArticelDetail(d, "topic_exchange_detail");
	}

	@Override
	public void toTopicExchangeDetailAll() {
		Article fArticle = new Article();
		fArticle.setCategoryId("06");// 课题交流
		List<Article> as = articleDao.getArticles(fArticle);
		for (Article d : as) {
			toTopicExchangeDetail(d);
		}

	}

	@Override
	public void toCooperationDetail(Article d) {
		toArticelDetail(d, "cooperation_detail");
	}

	public void toArticelDetail(Article d, String path) {
		try {
			String basePath = request.getSession().getServletContext()
					.getRealPath("/");
			File baseFile = new File(basePath);
			String tplPath = basePath + "/tpl/" + path + "_tpl.html";
			String rootPath = baseFile.getParent();
			String htmlFilePath = rootPath + "/static-html";
			File folder = new File(htmlFilePath);
			// 如果文件夹不存在则创建
			if (!folder.exists() && !folder.isDirectory()) {
				folder.mkdirs();
			}
			htmlFilePath += "/" + path + "_" + d.getId() + ".html";

			String content = readFile(tplPath);

			content = replaceValue(content, "##title##", d.getTitle());
			content = replaceValue(content, "##author##", d.getAuthor());
			content = replaceValue(content, "##addTime##",
					GeneralUtils.date2String(d.getAddTime()));
			content = replaceValue(content, "##content##", d.getContent());
			content = replaceValue(content, "##keywords##", d.getKeywords());
			content = replaceValue(content, "##description##", d.getDescription());
			writeFile(content, htmlFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void toCooperationDetailAll() {
		Article fArticle = new Article();
		fArticle.setCategoryId("02");// 校企合作
		List<Article> as = articleDao.getArticles(fArticle);
		for (Article d : as) {
			toCooperationDetail(d);
		}
	}

	@Override
	public void toAcademicExchangeDetail(Article d) {
		toArticelDetail(d, "academic_exchange_detail");
	}

	@Override
	public void toAcademicExchangeDetailAll() {
		Article fArticle = new Article();
		fArticle.setCategoryId("03");// 学术交流
		List<Article> as = articleDao.getArticles(fArticle);
		for (Article d : as) {
			toAcademicExchangeDetail(d);
		}

	}

	@Override
	public void toSocialResponsibilityDetai(Article d) {
		toArticelDetail(d, "social_responsibility_detail");
	}

	@Override
	public void toSocialResponsibilityDetaiAll() {
		Article fArticle = new Article();
		fArticle.setCategoryId("04");// 社会责任
		List<Article> as = articleDao.getArticles(fArticle);
		for (Article d : as) {
			toSocialResponsibilityDetai(d);
		}

	}

	@Override
	public void toMemberCommunityDetailDetai(Article d) {
		toArticelDetail(d, "member_community_detail");
	}

	@Override
	public void toMemberCommunityDetailDetaiAll() {
		Article fArticle = new Article();
		fArticle.setCategoryId("05");// 精彩活动
		List<Article> as = articleDao.getArticles(fArticle);
		for (Article d : as) {
			toMemberCommunityDetailDetai(d);
		}

	}

	@Override
	public void toKnowledgeDetail(KnowledgeVo kv) {
		try {
			String basePath = request.getSession().getServletContext()
					.getRealPath("/");

			List<Category> categories = kv.getCategories();
			Knowledge knowledge = kv.getKnowledge();
			List<Knowledge> knowledges = kv.getKnowledges();

			File baseFile = new File(basePath);
			String tplPath = basePath + "/tpl/oral_knowledge_detail_tpl.html";
			String rootPath = baseFile.getParent();
			String htmlFilePath = rootPath + "/static-html";
			File folder = new File(htmlFilePath);
			// 如果文件夹不存在则创建
			if (!folder.exists() && !folder.isDirectory()) {
				folder.mkdirs();
			}
			htmlFilePath += "/oral_knowledge_detail_" + knowledge.getId()
					+ ".html";

			String content = readFile(tplPath);

			// 标题内容
			content = replaceValue(content, "##title##", knowledge.getTitle());
			content = replaceValue(content, "##content##",
					knowledge.getContent());
			content = replaceValue(content, "##keywords##", knowledge.getKeywords());
			content = replaceValue(content, "##description##", knowledge.getDescription());

			// 父级
			String pHtml = "";
			pHtml += "<p class='father'>父级</p>";
			pHtml += "<p class='father-content father-department' data-id='"
					+ knowledge.getDepartmentId() + "'>"
					+ knowledge.getDepartmentName() + "</p>";
			content = replaceValue(content, "##parentList##", pHtml);

			// 子级
			String cHtml = "";
			cHtml += "<p class='father'>子级</p>";
			for (Category c : categories) {
				cHtml += "<p class='father-content father-category' data-id='"
						+ c.getId() + "'>" + c.getName() + "</p>";
			}
			content = replaceValue(content, "##childList##", cHtml);

			// 推荐
			String silimarHtml = "";
			for (Knowledge k : knowledges) {
				String title = k.getTitle();
				if (title.length() > 12) {
					title = title.substring(0, 12) + "...";
				}
				silimarHtml += "<p class='footer-left-txt fl' data-id='"
						+ k.getId() + "'>" + title + "</p>";
			}
			content = replaceValue(content, "##silimarList##", silimarHtml);

			String showTag = "";
			if (silimarHtml.isEmpty()) {
				showTag = "none";
			} else {
				showTag = "block";
			}
			content = replaceValue(content, "##showsilimar##", showTag);
			writeFile(content, htmlFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void toKnowledgeDetailAll() {

		List<Knowledge> ks = knowledgeDao.getKnowledges(new Knowledge());
		for (Knowledge k : ks) {
			KnowledgeVo v = gwService.getKnowledgeVoById(k.getId());
			toKnowledgeDetail(v);
		}
		;
	}

	public static void main(String[] args) {
		System.out.println(Charset.defaultCharset());
		System.out.println(System.getProperty("file.encoding"));
	}
}
