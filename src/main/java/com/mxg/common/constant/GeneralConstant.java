package com.mxg.common.constant;

/**
 * 通用常量类
 * 
 */
public interface GeneralConstant {
	/**
	 * 系统默认编码格式
	 */
	String CHARACTER_CODING = "UTF-8";


	/**
	 * success字符串
	 */
	String SUCCESS = "success";

	String FAILURE = "failure";


	/**
	 * 12位时间格式 yyMMDDHHmmss 应用端时间戳使用
	 */
	String DATETIME_12_A = "yyMMddHHmmss";

	/**
	 * 15位时间格式 yyMMddHHmmssSSS 应用端时间戳使用
	 */
	String DATETIME_15_A = "yyMMddHHmmssSSS";

	/**
	 * 17位时间格式 yyyyMMddHHmmssSSS
	 */
	String DATETIME_17 = "yyyyMMddHHmmssSSS";

	/**
	 * 17位时间格式 yyyy-MM-dd HH:mm:ss:SSS
	 */
	String DATETIME_17_COMMON = "yyyy-MM-dd HH:mm:ss:SSS";

	/**
	 * 14位时间格式 yyyyMMddHHmmss
	 */
	String DATETIME_14 = "yyyyMMddHHmmss";

	/**
	 * 14位时间格式 yyyy-MM-dd HH:mm:ss
	 */
	String DATETIME_14_COMMON = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 10位时间格式 日期部分 yyyy-MM-dd
	 */
	String DATETIME_10 = "yyyy-MM-dd";

	/**
	 * 8位时间格式 日期部分 yyyyMMdd
	 */
	String DATETIME_8 = "yyyyMMdd";

	/**
	 * 6位时间格式 日期部分 yyyymm
	 */
	String DATE_6 = "yyyyMM";

	/**
	 * 6位短日期格式 yymmdd
	 */
	String DATE_SHORT_6 = "yyMMdd";

	/**
	 * 8位日期格式 yyyy年MM月dd日
	 */
	String DATETIME_8_CN = "yyyy年MM月dd日";

	/**
	 * 6位时间格式 时间部分 HHmmss
	 */
	String DATETIME_6 = "HHmmss";


	/**
	 * 中文
	 */
	String LANG_CN = "cn";
	
	/**
	 * 英文
	 */
	String LANG_EN = "en";
	
	/**
	 * 诊疗项目
	 */
	String ARTICLE_ITEM = "00";
	
	/**
	 * 案例中心
	 */
	String ARTICLE_EXAMPLE  = "01";
	
	/**
	 * 校企合作
	 */
	String ARTICLE_SCHOOL  = "02";
	
	
	/**
	 *学术交流
	 */
	String ARTICLE_STUDY  = "03";
	
	/**
	 *社会责任
	 */
	String ARTICLE_SOCIAL  = "04";

	/**
	 *拜博资讯
	 */
	String ARTICLE_INFORMATION  = "05";	
	
	/**
	 * 集团的区域id
	 */
	String REGION_PARENT = "1";
	
	/**
	 * 注册用户类型
	 */
	String REG_USER = "01";
	
	/**
	 * 注册用户角色
	 */
	String REG_USER_ROLE = "USER";
	
}
