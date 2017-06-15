package com.mxg.bybo.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mxg.bybo.dao.DepartmentDao;
import com.mxg.bybo.model.Department;
import com.mxg.bybo.service.ToHtmlService;

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

	@Override
	public void toSubjectDetail(Department d) {
		String basePath = request.getSession().getServletContext()
				.getRealPath("/");
		File baseFile = new File(basePath);
		String tplPath = basePath + "/tpl/subject_detail_tpl.html";
		String rootPath = baseFile.getParent();
		String htmlFilePath = rootPath +"/static-html";
		File folder = new File(htmlFilePath);
		// 如果文件夹不存在则创建
		if (!folder.exists() && !folder.isDirectory()) {
			folder.mkdirs();
		}
		htmlFilePath += "/subject_detail_"+d.getId()+".html";
		
		String content = readFile(tplPath);
		
		content = content.replaceAll("##name##",d.getName());
		content = content.replaceAll("##icon##",d.getIcon());
		content = content.replaceAll("##summary##",d.getSummary());
		content = content.replaceAll("##service##",d.getService());
		content = content.replaceAll("##technical##",d.getService());
		
		
		System.out.println(content);
		writeFile(content, htmlFilePath);
		// TODO Auto-generated method stub

	}

	/**
	 * 读取文件内容
	 * @param filePath
	 * @return
	 */
	public String readFile(String filePath) {
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
	 * @param content
	 * @param htmlFile
	 * @return
	 */
	public boolean writeFile(String content, String htmlFilePath) {
		try {
			File f = new File(htmlFilePath);
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
		for(Department d:ds){
			toSubjectDetail(d);
		}
	}
	

}
