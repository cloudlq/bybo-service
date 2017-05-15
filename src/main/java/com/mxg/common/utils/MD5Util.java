package com.mxg.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	
	/**
	 * MD5 16位小写加密
	 * @param sourceStr
	 * @return
	 */
	public static String md5_16(String sourceStr){
		return  md5( sourceStr,16,false) ;
	}
	
	/**
	 * MD5 32位小写加密
	 * @param sourceStr
	 * @return
	 */
	public static String md5_32(String sourceStr){
		return  md5( sourceStr,32,false) ;
	}
	
	/**
	 * MD5 16位大写加密
	 * @param sourceStr
	 * @return
	 */
	public static String MD5_16(String sourceStr){
		return  md5( sourceStr,16,true) ;
	}
	
	/**
	 * MD5 32位大写加密
	 * @param sourceStr
	 * @return
	 */
	public static String MD5_32(String sourceStr){
		return  md5( sourceStr,32,true) ;
	}
	
	/**
	 * MD5加密
	 * @param sourceStr
	 * @param num
	 * @param tag
	 * @return
	 */
	private static String md5(String sourceStr,int num,boolean tag) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            if(num == 16){
            	result = buf.toString().substring(8, 24);
            }else{
            	  result = buf.toString();
            }
            if(tag){
            	result= result.toUpperCase();
            }
          
        } catch (NoSuchAlgorithmException e) {
        }
        return result;
    }
}
