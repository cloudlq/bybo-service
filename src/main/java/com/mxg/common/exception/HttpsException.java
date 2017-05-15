package com.mxg.common.exception;

/**
 * http异常类
 * 
 * @version 2016年3月20日 | 0.0.1
 * @author panda
 */
public class HttpsException extends RuntimeException {
	private static final long serialVersionUID = -2395720554259487938L;

	public HttpsException() {
		super();
	}

	public HttpsException(String message) {
		super(message);
	}

	public HttpsException(String message, Throwable cause) {
		super(message, cause);
	}

	public HttpsException(Throwable cause) {
		super(cause);
	}

	protected HttpsException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
