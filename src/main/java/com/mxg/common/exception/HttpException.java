package com.mxg.common.exception;

/**
 * http异常类
 * 
 * @version 2016年3月20日 | 0.0.1
 * @author panda
 */
public class HttpException extends RuntimeException {
	private static final long serialVersionUID = -2395720554259487938L;

	public HttpException() {
		super();
	}

	public HttpException(String message) {
		super(message);
	}

	public HttpException(String message, Throwable cause) {
		super(message, cause);
	}

	public HttpException(Throwable cause) {
		super(cause);
	}

	protected HttpException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
