package com.mxg.common.utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class MessageUtil {

    private static Properties _prop;

    /**
     * 读取配置文件
     * @param fileName
     */
    public static void readProperties(String fileName){
        try {
            InputStream in = MessageUtil.class.getResourceAsStream("/"+fileName);
            BufferedReader bf = new BufferedReader(new InputStreamReader(in));
            _prop.load(bf);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 根据key读取对应的value
     * @param key
     * @return
     */
    public static String getProperty(String key){
    	if(_prop == null){
    		_prop = new Properties();
    		readProperties("conf/message.properties");
    	}
        return _prop.getProperty(key);
    }
    
}
