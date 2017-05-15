/*
 * 文 件 名:  GeneralUtils.java
 * 版    权:  Linkage Technology Co., Ltd. Copyright 2010-2011,  All rights reserved
 * 描    述:  <描述>
 * 版    本： <1.0> 
 * 创 建 人:  liuyang
 * 创建时间:  2014年4月23日
 
 */
package com.mxg.common.utils;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.validator.GenericValidator;

import com.mxg.common.constant.GeneralConstant;
import com.mxg.common.exception.UtilException;

/**
 * 通用工具类
 * 
 * @author liuyang
 * @version [1.0, 2014年4月23日]
 * @since [1.0]
 */
public class GeneralUtils {

	private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 判断对象是否为null , 为null返回true,否则返回false
	 * 
	 * @param obj
	 *            被判断的对象
	 * @return boolean
	 */
	public static boolean isNull(Object obj) {
		return (null == obj) ? true : false;
	}

	/**
	 * 判断对象是否为null, 如果不为null则返回true, 否则返回false
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNotNull(Object obj) {
		return (obj != null) ? true : false;
	}

	/**
	 * 判断集合对象是否为null或者0大小 , 为空或0大小返回true ,否则返回false
	 * 
	 * @param c
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean isNullOrZeroSize(Collection<? extends Object> c) {
		return isNull(c) || c.isEmpty();
	}

	/**
	 * 判断集合类型不为空
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isNotNullOrZeroSize(Collection<? extends Object> c) {
		return !isNullOrZeroSize(c);
	}

	public static boolean isNullOrZeroSize(Map<?, ?> map) {
		return isNull(map) || map.isEmpty();
	}

	public static boolean isNotNullOrZeroSize(Map<?, ?> map) {
		return !isNullOrZeroSize(map);
	}

	/**
	 * 讲字符串转换成byte数组
	 * 
	 * @param str
	 *            字符串
	 * @return 转换后的byte数组
	 */
	public static byte[] stringToBytes(String str) {
		try {
			if (GeneralUtils.isNotNull(str)) {
				return str.getBytes(GeneralConstant.CHARACTER_CODING);
			} else {
				return new byte[0];
			}
		} catch (UnsupportedEncodingException e) {
			// 该异常不会发生
			return new byte[0];
		}
	}

	/**
	 * 将字符串时间转换时间格式
	 * 
	 * @param str
	 *            要转换的字符串
	 * @param formatF
	 *            初始时间格式
	 * @param formatT
	 *            目标时间格式
	 * @return 如果转换失败，返回null
	 */
	public static String String2String(String str, String formatF,
			String formatT) {
		return date2String(string2Date(str, formatF), formatT);
	}

	/**
	 * 讲byte[]转换成字符窜
	 * 
	 * @param arr
	 *            byte数组
	 * @return 转换后的字符串
	 */
	public static String bytesToSting(byte[] arr) {
		try {
			return new String(arr, GeneralConstant.CHARACTER_CODING);
		} catch (UnsupportedEncodingException e) {
			// 该异常不会发生
			return "";
		}
	}

	/**
	 * 将字符串时间转换成java.util.Date类型
	 * 
	 * @param str
	 *            要转换的字符串
	 * @param format
	 *            时间格式
	 * @return 如果转换失败，返回null
	 */
	public static Date string2Date(String str, String format) {
		if (GeneralUtils.isNull(str) || GeneralUtils.isNull(format)) {
			return null;
		}

		// 定义日期/时间格式
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date;

		try {
			// 转换日期/时间格式
			date = sdf.parse(str);
			// 判断转换前后时间是否一致

			if (!str.equals(sdf.format(date))) {
				date = null;
			}
		} catch (ParseException e) {
			date = null;
		}

		return date;
	}

	
	
	
	/**
	 * 将java.util.Date类型转化位String类型
	 *
	 * @param date
	 *            要转换的时间
	 * @param format
	 *            时间格式,如果未指定时间格式,那么采用默认的时间格式来转换
	 * 
	 * @return 如果转换成功，返回指定格式字符串，如果转换失败，返回null
	 */
	public static String date2String(Date date, String format) {
		if (GeneralUtils.isNull(date)) {
			return null;
		}

		if (GeneralUtils.isBlank(format)) {
			format = GeneralConstant.DATETIME_14_COMMON;
		}

		SimpleDateFormat formator = new SimpleDateFormat(format);

		return formator.format(date);
	}

	/**
	 * 将日期转换成默认时间字符串yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 *            日期
	 * @return String 时间字符串
	 */
	public static String date2String(Date date) {
		if (GeneralUtils.isNull(date)) {
			return null;
		}

		SimpleDateFormat format = new SimpleDateFormat(
				GeneralConstant.DATETIME_14_COMMON);

		return format.format(date);
	}

	/**
	 * 解码字符串
	 * 
	 * @param s
	 *            待解码字符串
	 * @param enc
	 *            解码方式
	 * @return
	 */
	public static String URLDecoder(String s, String enc) {
		String returnVal = "";
		try {
			returnVal = URLDecoder.decode(s, enc);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return returnVal;
	}

	/**
	 * 解码字符串(按utf-8方式)
	 * 
	 * @param s
	 *            待解码字符串
	 * @return
	 */
	public static String URLDecoder(String s) {
		return URLDecoder(s, "utf-8");
	}

	/**
	 * 编码字符串
	 * 
	 * @param s
	 *            待编码字符串
	 * @param enc
	 *            编码码方式
	 * @return
	 */
	public static String URLEncoder(String s, String enc) throws UtilException {
		String returnVal = "";

		try {
			returnVal = URLEncoder.encode(s, enc);
		} catch (UnsupportedEncodingException e) {
			throw new UtilException("编码出错");
		}

		return returnVal;
	}

	/**
	 * 编码字符串(按utf-8方式)
	 * 
	 * @param s
	 *            待编码字符串
	 * @return
	 */
	public static String URLEncoder(String s) throws UtilException {
		return URLEncoder(s, "utf-8");
	}

	/**
	 * 判断字符串是否为空或者""
	 * 
	 * @param str
	 *            str
	 * @return boolean true-字符串为null或者"", false-其它
	 */
	public static boolean isBlank(String str) {
		return str == null ? true : "".equals(str);
	}

	/**
	 * 判断字符串是否不为空或者""
	 * 
	 * @param str
	 *            str
	 * @return boolean true-字符串不为null或者"", false-其它
	 */
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	/**
	 * 返回当前时间戳
	 * 
	 * @param pattern
	 *            默认为：yyyyMMddHHmmss
	 * @return string 时间字符串
	 */
	public static String getCurrentTimeStamp(String pattern) {
		if (isBlank(pattern)) {
			pattern = GeneralConstant.DATETIME_14;
		}

		Date date = new Date(System.currentTimeMillis());

		return date2String(date, pattern);
	}

	/**
	 * 将时间段转化成用于显示的形式，如3600秒转化成1小时
	 * 
	 * @param time
	 * @return
	 */
	public static String time2View(Long time) {
		String v = "";
		if (time >= 60 * 60 * 24) {
			long t = time / (60 * 60 * 24);
			v += t + "天";
			time = time - (60 * 60 * 24) * t;
		}

		if (time >= 60 * 60) {
			long t = time / (60 * 60);
			v += t + "小时";
			time = time - (60 * 60) * t;
		}

		if (time >= 60) {
			long t = time / 60;
			v += t + "分钟";
			time = time - 60 * t;
		}

		if (time > 0) {
			v += time + "秒";
		}

		return v;
	}

	/**
	 * 把Byte[] 转为响应的十六进制字符串
	 * 
	 * @param bytes
	 *            Byte[]
	 * @return 十六进制字符串
	 * @see [类、类#方法、类#成员]
	 */
	public static String byteArray2HexString(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		String hexStr = "";
		for (byte b : bytes) {
			hexStr = Integer.toHexString(b);
			if (hexStr.length() == 1) {
				hexStr = "0" + hexStr;
			}
			sb.append(hexStr.substring(hexStr.length() - 2));
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * 将byte[]转换成16进制字符串
	 * 
	 * @param b
	 *            byte[]
	 * @return String hexString
	 */
	public static String byteToHexString(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}

	/**
	 * 将字节数组转换成int
	 * 
	 * @param b
	 *            byte array
	 * @return int int value
	 */
	public static int parseByteToInt(byte[] b) {
		int length = b.length;
		int val = 0;
		for (int i = 0; i < length; i++) {
			if (i == length - 1) {
				val |= b[i] & 0xff;
			} else {
				val |= (b[i] & 0xff) << (length - 1 - i) * 8;
			}
		}
		return val;
	}

	/**
	 * 将十六进制字符串转换成byte数组
	 * 
	 * @param src
	 *            十六进制字符串
	 * @return byte[] 字节数组
	 */
	public static byte[] hexStringToBytes(String src) {
		int m = 0, n = 0;
		int l = src.length() / 2;
		byte[] ret = new byte[l];
		for (int i = 0; i < l; i++) {
			m = i * 2 + 1;
			n = m + 1;
			ret[i] = uniteBytes(src.substring(i * 2, m), src.substring(m, n));
		}
		return ret;
	}

	private static byte uniteBytes(String src0, String src1) {
		byte b0 = Byte.decode("0x" + src0).byteValue();
		b0 = (byte) (b0 << 4);
		byte b1 = Byte.decode("0x" + src1).byteValue();
		byte ret = (byte) (b0 | b1);
		return ret;
	}

	/**
	 * BCD字节数组转字符串
	 * 
	 * @param bytes
	 * @return
	 */
	public static String bcd2StrExt(byte[] bytes) {
		StringBuffer temp = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			temp.append((byte) ((bytes[i] & 0xf0) >>> 4));
			temp.append((byte) (bytes[i] & 0x0f));
		}
		return temp.toString();
	}

	/**
	 * 将byte转换成整数字符串
	 * 
	 * @param b
	 * @return
	 */
	public static String byteToIntString(byte b) {
		String hs = String.valueOf(b);

		if (hs.length() == 1) {
			hs = "0" + hs;
		}

		return hs;
	}

	/**
	 * 将int转换成byte数组
	 * 
	 * @param value
	 *            IntValue
	 * @param length
	 *            长度
	 * @return byte[] 字节数组
	 * @throws UtilException
	 *             UtilException
	 */
	public static byte[] createIntToByteArray(int value, int length)
			throws UtilException {
		if (length != 4 && length != 2 && length != 1) {
			throw new UtilException("长度异常!");
		}

		byte[] b = new byte[length];
		int tmp = 0;
		for (int i = 0; i < length; i++) {
			if (i == length - 1) {
				b[i] = (byte) (value & 0xff);
			} else {
				tmp = value >> (8 * (length - 1 - i));
				b[i] = (byte) tmp;
				value -= tmp << (8 * (length - 1 - i));
			}

		}

		return b;
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */

	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The   scale   must   be   a   positive   integer   or   zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 判断字符串是否为null或者0长度，字符串在判断长度时，先去除前后的空格,空或者0长度返回true,否则返回false
	 *
	 * @param str
	 *            被判断的字符串
	 * 
	 * @return boolean
	 */
	public static boolean isNullOrZeroLenght(String str) {
		return GenericValidator.isBlankOrNull(str);
	}

	/**
	 * 判断字符串是否为null或者0长度，字符串在判断长度时，先去除前后的空格,空或者0长度返回false,否则返回true
	 *
	 * @param str
	 *            被判断的字符串
	 * 
	 * @return boolean
	 */
	public static boolean isNotNullOrZeroLenght(String str) {
		return !isNullOrZeroLenght(str);
	}

	/**
	 * 生成EXCEL
	 * 
	 * @param filePath
	 *            文件路径
	 * @param xlContents
	 *            需要生成的内容
	 * @param cellConfig
	 *            配置
	 * @see [类、类#方法、类#成员]
	 */
	public static void createExcel(OutputStream outputStream,
			List<Object> xlContents, int[] cellConfig) {
		int[] widthConfig = new int[cellConfig.length];
		int widthTemp = 0;

		try {
			WritableWorkbook book = Workbook.createWorkbook(outputStream);
			WritableSheet sheet = book.createSheet("sheet", 0);
			// 保护
			sheet.getSettings().setProtected(true);
			// 保护密码
			sheet.getSettings().setPassword("linkage");
			// 冻结标题
			sheet.getSettings().setVerticalFreeze(1);

			for (int i = 0; i < xlContents.size(); i++) {
				Object iContents = xlContents.get(i);

				if (iContents instanceof String[]) {
					for (int j = 0; j < ((String[]) iContents).length; j++) {
						WritableCellFormat wcf = new WritableCellFormat();
						wcf.setAlignment(jxl.format.Alignment.CENTRE);

						Label label = new Label(j, i,
								((String[]) iContents)[j], wcf);
						sheet.addCell(label);

						widthTemp = String.valueOf(((String[]) iContents)[j])
								.getBytes("GBK").length + 1;
						if (widthTemp > widthConfig[j]) {
							widthConfig[j] = widthTemp;
						}
					}
				} else {
					for (int j = 0; j < ((List<?>) iContents).size(); j++) {
						Object obj = ((List<?>) iContents).get(j);

						WritableCellFormat wcf = new WritableCellFormat();
						if (cellConfig[j] == 1) {
							wcf.setLocked(true);
						} else {
							wcf.setLocked(false);
						}

						if (obj instanceof Double) {
							jxl.write.Number number = new jxl.write.Number(j,
									i, (Double) obj, wcf);
							sheet.addCell(number);

							widthTemp = String.valueOf(obj).getBytes("GBK").length + 1;
							if (widthTemp > widthConfig[j]) {
								widthConfig[j] = widthTemp;
							}
						} else if (obj instanceof Integer) {
							jxl.write.Number number = new jxl.write.Number(j,
									i, (Integer) obj, wcf);
							sheet.addCell(number);

							widthTemp = String.valueOf(obj).getBytes("GBK").length + 1;
							if (widthTemp > widthConfig[j]) {
								widthConfig[j] = widthTemp;
							}
						} else if (obj instanceof Long) {
							jxl.write.Number number = new jxl.write.Number(j,
									i, (Long) obj, wcf);
							sheet.addCell(number);

							widthTemp = String.valueOf(obj).getBytes("GBK").length + 1;
							if (widthTemp > widthConfig[j]) {
								widthConfig[j] = widthTemp;
							}
						} else {
							Label label = new Label(j, i, (String) obj, wcf);
							sheet.addCell(label);

							widthTemp = String.valueOf(obj).getBytes("GBK").length + 1;
							if (widthTemp > widthConfig[j]) {
								widthConfig[j] = widthTemp;
							}
						}
					}
				}
			}
			for (int i = 0; i < widthConfig.length; i++) {
				sheet.setColumnView(i, widthConfig[i]);
			}
			book.write();
			book.close();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Double 留两位
	 */
	public static Double DoubleTwo(Double a) {
		BigDecimal b = new BigDecimal(a);
		double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}

	/**
	 * 判断数字类型是否为null或者0，如果是返回true，否则返回false
	 *
	 * @param number
	 *            被判断的数字
	 * @return boolean
	 */
	public static boolean isNullOrZero(Number number) {
		if (GeneralUtils.isNotNull(number)) {
			return (number.doubleValue() != 0) ? false : true;
		}
		return true;
	}

	/**
	 * 判断数字类型是否不为null或者0，如果是返回true，否则返回false
	 *
	 * @param number
	 *            被判断的数字
	 * @return boolean
	 */
	public static boolean isNotNullOrZero(Number number) {
		return !isNullOrZero(number);
	}

	/**
	 * 将用，分割的字符串转化List
	 * 
	 * @param str
	 * @return
	 */
	public static List<String> stringToList(String str) {
		List<String> list = new ArrayList<String>();
		if (isNotBlank(str)) {
			String[] array = str.split(",");
			for (String temp : array) {
				list.add(temp);
			}
		}
		return list;

	}

	/**
	 * 将时间转为标准时间字符串
	 * 
	 * @param time
	 * @return
	 */
	public static String timestamp2Str(Timestamp time) {
		if (null != time && !"".equals(time)) {
			Date date = new Date(time.getTime());
			return date2String(date, DEFAULT_FORMAT);
		}
		return null;
	}

	/**
	 * 将16进制转为字符串
	 * @param s
	 * @return
	 */
	public static String toStringHex(String s) {
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(
						s.substring(i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			s = new String(baKeyword, "GB2312");// UTF-16le:Not
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}

	/**
	 * 获取当前时间 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String currentTime(){
		return GeneralUtils.date2String(new Date(),GeneralConstant.DATETIME_14_COMMON);
	}
	
	/**
	 * 获取过去某一时间与当前时间的间隔
	 * 返回秒
	 * @return
	 */
	public static long get2DateMinus (Date oldDate){
		 long diff = new Date().getTime() - oldDate.getTime();  
		return diff/1000;
	}
	/**
	 * 获取某一天时间的开始时间
	 */
	public static String firstDate(Date date) {
		String day = date2String(date ,"yyyy-MM-dd");
		return day + " 00:00:00";
	}
	
	/**
	 * 获取某一天时间的结束时间
	 */
	public static String lastDate(Date date) {
		String day = date2String(date ,"yyyy-MM-dd");
		return day+" 23:59:59";
	}
	
	
	public static void main(String[] args) {
		System.out.println(firstDate(new Date()));
	}
	
}
