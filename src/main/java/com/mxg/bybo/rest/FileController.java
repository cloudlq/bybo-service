package com.mxg.bybo.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import basic.common.core.exception.BaseException;

import com.mxg.common.utils.GeneralUtils;

/**
 * LoggingController
 *
 * @version : Ver 1.0
 * @date : 2016-8-19
 */
@RestController
@Api(value = "FileController", description = "文件上传相关")
@RequestMapping(value = "/file")
public class FileController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}


	@ApiOperation(value = "上传文件", notes = "上传文件")
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String addLog(@RequestParam("upload") CommonsMultipartFile file,
			HttpServletRequest request,String CKEditorFuncNum) throws Exception {
		 String fileName = saveFile(file, request);
		 String val = "";
		 val += "<script type=\"text/javascript\">" ;
		 val += "window.parent.CKEDITOR.tools.callFunction("+ CKEditorFuncNum + ",'" + fileName + "','')";   
		 val +="</script>";  
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
		String commonBase ="/upload/"+GeneralUtils.date2String(new Date(),"yyyyMMdd")+"/";
		File baseFile = new File(basePath);
		basePath = baseFile.getParent();
		basePath += commonBase;
		File folder = new File(basePath);
		// 如果文件夹不存在则创建
		if (!folder.exists() && !folder.isDirectory()) {
			folder.mkdirs();
		}
		String path = basePath + name;
		String returnPath = commonBase  + name;
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
	

}
