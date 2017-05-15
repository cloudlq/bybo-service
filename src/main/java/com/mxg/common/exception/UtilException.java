/*
 * 文 件 名:  UtilException.java
 * 版    权:  Linkage Technology Co., Ltd. Copyright 2010-2011,  All rights reserved
 * 描    述:  <描述>
 * 版    本： <1.0> 
 * 创 建 人:  liuyang
 * 创建时间:  2014年4月26日
 
 */
package com.mxg.common.exception;

/**
 * UtilException
 * 
 */
public class UtilException extends Exception
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

    /**
     * <默认构造函数>
     */
    public UtilException()
    {
    }
    
    /**
     * <默认构造函数>
     * @param errorCode 错误码

     */
    public UtilException(String errorCode)
    {
        super(errorCode);
    }
    
    /**
     * <默认构造函数>
     * @param th Throwable 
     */
    public UtilException(Throwable th)
    {
        super(th);
    }
    
    /**
     * <默认构造函数>
     * @param errorCode 错误码

     * @param th Throwable 
     */
    public UtilException(String errorCode, Throwable th)
    {
        super(errorCode, th);
    }
    
}
