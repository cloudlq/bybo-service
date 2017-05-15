package com.mxg.common.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.databind.JavaType;
import com.github.kevinsawicki.http.HttpRequest;
import com.mxg.common.exception.HttpException;
import com.mxg.common.json.Jsons;

/**
 * HTTP操作类
 * 
 * @version 2016年3月21日 | 0.0.1
 * @author panda
 */
public class HttpUtils {

	private String url;

	private HttpMethod method = HttpMethod.GET;

	private Map<String, String> headers = Collections.emptyMap();

	private Map<String, ?> params = Collections.emptyMap();

	/**
	 * 请求body
	 */
	private String body;

	private Boolean ssl = Boolean.FALSE;

	private Integer connectTimeout = 1000 * 5;

	private Integer readTimeout = 1000 * 5;

	/**
	 * 是否编码
	 */
	private Boolean encode = Boolean.TRUE;

	/**
	 * 请求内容类型
	 */
	private String contentType = "application/json";

	/**
	 * 编码字符集
	 */
	private String charset = "UTF-8";

	/**
	 * 接收类型
	 */
	private String accept = "";

	private HttpUtils(String url) {
		this.url = url;
	}

	private HttpUtils method(HttpMethod method) {
		this.method = method;
		return this;
	}

	public HttpUtils ssl() {
		this.ssl = Boolean.TRUE;
		return this;
	}

	public HttpUtils headers(Map<String, String> headers) {
		this.headers = headers;
		return this;
	}

	public HttpUtils params(Map<String, ?> params) {
		this.params = params;
		return this;
	}

	/**
	 * 请求body
	 * 
	 * @param body
	 *            request body
	 * @return this
	 */
	public HttpUtils body(String body) {
		this.body = body;
		return this;
	}

	public HttpUtils encode(Boolean encode) {
		this.encode = encode;
		return this;
	}

	public HttpUtils contentType(String contentType) {
		this.contentType = contentType;
		return this;
	}

	public HttpUtils charset(String charset) {
		this.charset = charset;
		return this;
	}

	public HttpUtils accept(String accept) {
		this.accept = accept;
		return this;
	}

	/**
	 * set connect timeout
	 * 
	 * @param connectTimeout
	 *            (s)
	 * @return this
	 */
	public HttpUtils connTimeout(Integer connectTimeout) {
		this.connectTimeout = connectTimeout * 1000;
		return this;
	}

	/**
	 * set read timeout
	 * 
	 * @param readTimeout
	 *            (s)
	 * @return this
	 */
	public HttpUtils readTimeout(Integer readTimeout) {
		this.readTimeout = readTimeout * 1000;
		return this;
	}

	public String request() {
		switch (method) {
		case GET:
			return doGet();
		case POST:
			return doPost();
		default:
			break;
		}
		return null;
	}

	public <T> T request(JavaType type) {
		return Jsons.DEFAULT.fromJson(request(), type);
	}

	private String doPost() {
		HttpRequest post = HttpRequest.post(url, params, encode).headers(headers).connectTimeout(connectTimeout)
				.readTimeout(readTimeout).acceptGzipEncoding().uncompress(true);
		setOptionalHeaders(post);
		if (ssl) {
			trustHttps(post);
		}
		if (!StringUtils.isEmpty(body)) {
			post.send(body);
		}

		return post.body();
	}

	private String doGet() {
		HttpRequest get = HttpRequest.get(url, params, encode).headers(headers).connectTimeout(connectTimeout)
				.readTimeout(readTimeout).acceptGzipEncoding().uncompress(true);
		if (ssl) {
			trustHttps(get);
		}
		setOptionalHeaders(get);
		return get.body();
	}

	private void setOptionalHeaders(HttpRequest request) {
		if (!StringUtils.isEmpty(contentType)) {
			request.contentType(contentType, charset);
		}
		if (!StringUtils.isEmpty(accept)) {
			request.accept(accept);
		}
	}

	
	
	private void trustHttps(HttpRequest request) {
		request.trustAllCerts().trustAllHosts();
	}

	public static HttpUtils get(String url) {
		return new HttpUtils(url);
	}

	public static HttpUtils post(String url) {
		return new HttpUtils(url).method(HttpMethod.POST);
	}

	public static HttpUtils put(String url) {
		return new HttpUtils(url).method(HttpMethod.PUT);
	}

	public static HttpUtils delete(String url) {
		return new HttpUtils(url).method(HttpMethod.DELETE);
	}

	/**
	 * 上传文件
	 * 
	 * @param url
	 *            请求地址
	 * @param fieldName
	 *            字段名称
	 * @param fileName
	 *            文件名称
	 * @param data
	 *            上传数据
	 * @param params
	 *            请求参数
	 * @return 返回
	 */
	public static String upload(String url, String fieldName, String fileName, byte[] data,
			Map<String, String> params) {
		return upload(url, fieldName, fileName, new ByteArrayInputStream(data), params);
	}

	/**
	 * 上传文件
	 * 
	 * @param url
	 *            请求地址
	 * @param fieldName
	 *            字段名称
	 * @param fileName
	 *            文件名称
	 * @param in
	 *            上传流
	 * @param params
	 *            请求参数
	 * @return 返回
	 */
	public static String upload(String url, String fieldName, String fileName, InputStream in,
			Map<String, String> params) {
		try {
			HttpRequest request = HttpRequest.post(url);
			if (!params.isEmpty()) {
				for (Map.Entry<String, String> param : params.entrySet()) {
					request.part(param.getKey(), param.getValue());
				}
			}
			request.part(fieldName, fileName, null, in);
			return request.body();
		} catch (Exception e) {
			throw new HttpException(e);
		}
	}

	/**
	 * 下载文件
	 * 
	 * @param url
	 *            请求地址
	 * @param into
	 *            下载文件对象
	 */
	public static void download(String url, File into) {
		try {
			download(url, new FileOutputStream(into));
		} catch (FileNotFoundException e) {
			throw new HttpException(e);
		}
	}

	/**
	 * 下载文件
	 * 
	 * @param url
	 *            请求地址
	 * @param output
	 *            输出流
	 */
	public static void download(String url, OutputStream output) {
		try {
			HttpRequest request = HttpRequest.get(url);
			if (request.ok()) {
				request.receive(output);
			} else {
				throw new HttpException("request isn't ok: " + request.body());
			}
		} catch (Exception e) {
			throw new HttpException(e);
		}
	}

	private static enum HttpMethod {
		GET, POST, PUT, DELETE
	}

	public static void main(String[] args) {
		String str = HttpUtils.get("http://111.198.110.132:8080/restful/members/1234567890").request();
		System.out.println(str);
	}
}
