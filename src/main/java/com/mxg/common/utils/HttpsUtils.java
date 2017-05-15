package com.mxg.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.databind.JavaType;
import com.github.kevinsawicki.http.HttpRequest.HttpRequestException;
import com.mxg.common.exception.HttpsException;
import com.mxg.common.json.Jsons;


/**
 * 简单的Https请求工具
 * 
 * @version 2016年3月28日 下午9:21:56
 * @author panda
 */
public final class HttpsUtils {

	private HttpsURLConnection connection;

	private int connectTimeout = 1000 *5;

	private int readTimeout = 1000 *5;

	private String contentType = "";

	private String acceptCharset = "UTF-8";

	private String acceptType = "application/json";

	private String connectType = "close";

	private String body = "";

	private String bodyCharset = "UTF-8";

	private boolean encode = true;

	private boolean gzip = false;

	private HttpsUtils() {
	}

	/**
	 * 设置连接超时，默认5s
	 * 
	 * @param secs
	 *            秒
	 * @return this
	 */
	public HttpsUtils connectTimeout(int secs) {
		connection.setConnectTimeout(secs * 1000);
		return this;
	}

	/**
	 * 设置读取超时，默认5s
	 * 
	 * @param secs
	 *            秒
	 * @return this
	 */
	public HttpsUtils readTimeout(int secs) {
		readTimeout = secs * 1000;
		return this;
	}

	/**
	 * 设置Content-Type
	 * 
	 * @param contentType
	 *            contentType
	 * @return this
	 */
	public HttpsUtils contentType(String contentType) {
		this.contentType = contentType;
		return this;
	}

	/**
	 * 设置Accept
	 * 
	 * @param acceptType
	 *            accessType，默认text/plain
	 * @return this
	 */
	public HttpsUtils acceptType(String acceptType) {
		this.acceptType = acceptType;
		return this;
	}

	/**
	 * 设置Accept-Charset
	 * 
	 * @param acceptCharset
	 *            默认UTF-8
	 * @return this
	 */
	public HttpsUtils acceptCharset(String acceptCharset) {
		this.acceptCharset = acceptCharset;
		return this;
	}

	/**
	 * 设置Connection
	 * 
	 * @param connectType
	 *            默认close
	 * @return this
	 */
	public HttpsUtils connectType(String connectType) {
		this.connectType = connectType;
		return this;
	}

	/**
	 * 设置UseCaches
	 * 
	 * @param useCache
	 *            use cache or not
	 * @return this
	 */
	public HttpsUtils useCache(boolean useCache) {
		connection.setUseCaches(useCache);
		return this;
	}

	/**
	 * 设置request body
	 * 
	 * @param body
	 *            request body
	 * @return this
	 */
	public HttpsUtils body(String body) {
		this.body = body;
		return this;
	}

	/**
	 * 设置request body字符编码
	 * 
	 * @param charset
	 *            字符编码
	 * @return this
	 */
	public HttpsUtils bodyCharset(String charset) {
		this.setBodyCharset(charset);
		return this;
	}

	/**
	 * 设置SSLSocketFactory
	 * 
	 * @param factory
	 *            SSLSocketFactory
	 * @return this
	 */
	public HttpsUtils ssLSocketFactory(SSLSocketFactory factory) {
		connection.setSSLSocketFactory(factory);
		return this;
	}

	/**
	 * 忽略SSL证书请求
	 *
	 * @return this
	 * 
	 */
	public HttpsUtils ssLNoneSocketFactory() {
		javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
		javax.net.ssl.TrustManager tm = new SSLTrustManager();
		trustAllCerts[0] = tm;
		try {
			javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, null);
			connection.setSSLSocketFactory(sc.getSocketFactory());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	/**
	 * 设置请求header
	 * 
	 * @param name
	 *            名称
	 * @param value
	 *            值
	 * @return this
	 */
	public HttpsUtils header(final String name, final String value) {
		connection.setRequestProperty(name, value);
		return this;
	}

	/**
	 * 获取请求响应内容
	 * 
	 * @return 响应内容
	 */
	public String request() {
		prepareRequest();
		return doRequest();
	}

	public <T> T request(JavaType type) {
		return Jsons.DEFAULT.fromJson(request(), type);
	}

	private void prepareRequest() {

		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setConnectTimeout(connectTimeout);
		connection.setReadTimeout(readTimeout);
		header("Accept-Charset", acceptCharset);
		header("Connection", connectType);

		if (gzip) {
			header("Accept-Encoding", "gzip, deflate");
		}
		if (!StringUtils.isEmpty(contentType)) {
			header("Content-Type", contentType);
		}
		if (!StringUtils.isEmpty(acceptType)) {
			header("Accept", acceptType);
		}

		if (!StringUtils.isEmpty(body)) {
			header("Content-Length", String.valueOf(body.length()));
		}
	}

	private String doRequest() {
		if (!StringUtils.isEmpty(body)) {
			try (OutputStream out = connection.getOutputStream()) {
				out.write(body.getBytes());
			} catch (IOException e) {
				throw new HttpsException(e);
			}
		}

		int respCode;
		try {
			respCode = connection.getResponseCode();
		} catch (IOException e) {
			throw new HttpsException(e);
		}

		try (InputStream in = respCode == HttpURLConnection.HTTP_OK ? connection.getInputStream()
				: connection.getErrorStream(); BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
			StringBuilder content = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				content.append(line);
			}
			return content.toString();
		} catch (IOException e) {
			throw new HttpsException(e);
		}
	}

	public static HttpsUtils get(String url) {
		return get(url, true);
	}

	public static HttpsUtils get(String url, Boolean encode) {
		HttpsUtils httpsUtils = new HttpsUtils();
		httpsUtils.connection = createConnection(url, "GET", encode);
		return httpsUtils;
	}

	public static HttpsUtils post(String url) {
		return post(url, true);
	}

	public static HttpsUtils post(String url, Boolean encode) {
		HttpsUtils httpsUtils = new HttpsUtils();
		httpsUtils.connection = createConnection(url, "POST", encode);
		return httpsUtils;
	}

	private static HttpsURLConnection createConnection(String url, String method, Boolean encode) {
		try {
			URL u = new URL(encode ? encode(url) : url);
			HttpsURLConnection conn = (HttpsURLConnection) u.openConnection();
			conn.setRequestMethod(method);
			return conn;
		} catch (IOException e) {
			throw new HttpsException(e);
		}
	}

	public static String encode(final CharSequence url) throws HttpRequestException {
		URL parsed;
		try {
			parsed = new URL(url.toString());
		} catch (IOException e) {
			throw new HttpRequestException(e);
		}

		String host = parsed.getHost();
		int port = parsed.getPort();
		if (port != -1)
			host = host + ':' + Integer.toString(port);

		try {
			String encoded = new URI(parsed.getProtocol(), host, parsed.getPath(), parsed.getQuery(), null)
					.toASCIIString();
			int paramsStart = encoded.indexOf('?');
			if (paramsStart > 0 && paramsStart + 1 < encoded.length())
				encoded = encoded.substring(0, paramsStart + 1)
						+ encoded.substring(paramsStart + 1).replace("+", "%2B");
			return encoded;
		} catch (URISyntaxException e) {
			IOException io = new IOException("Parsing URI failed");
			io.initCause(e);
			throw new HttpRequestException(io);
		}
	}

	public String getBodyCharset() {
		return bodyCharset;
	}

	public void setBodyCharset(String bodyCharset) {
		this.bodyCharset = bodyCharset;
	}

	public boolean isEncode() {
		return encode;
	}

	public void setEncode(boolean encode) {
		this.encode = encode;
	}

	public class SSLTrustManager
			implements javax.net.ssl.TrustManager, javax.net.ssl.X509TrustManager, HostnameVerifier {
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public boolean isServerTrusted(java.security.cert.X509Certificate[] certs) {
			return true;
		}

		public boolean isClientTrusted(java.security.cert.X509Certificate[] certs) {
			return true;
		}

		public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
				throws java.security.cert.CertificateException {
			return;
		}

		public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
				throws java.security.cert.CertificateException {
			return;
		}

		@Override
		public boolean verify(String urlHostName, SSLSession session) { // 允许所有主机
			return true;
		}
		
	}
	public static void main(String[] args) {
		String s = HttpsUtils.get("http://111.198.110.132:8080/restful/members/1234567890").ssLNoneSocketFactory().acceptType("application/json").request();
    	System.out.println(s);	
	}
}
