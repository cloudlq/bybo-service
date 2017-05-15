package com.mxg.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {
	
	public static void sendMessage(String to,String htmlText) {
		try {
			
			// 获取邮件发送服务器及验证用户名和密码
			Properties props = getProp();
			
//			MailSSLSocketFactory sf = new MailSSLSocketFactory();
//			sf.setTrustAllHosts(true);
//			props.put("mail.smtp.ssl.enable", "true");
//			props.put("mail.smtp.ssl.socketFactory", sf);
			
			String from = props.getProperty("from");// 发件人
			String subject =  props.getProperty("subject");
			// 获取Session会话
			Session session = Session.getDefaultInstance(props, null);
			// 创建MIMEMessage
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));
			message.setSubject(subject);
				message.setContent(htmlText, "text/html");
			// 发送邮件
			message.saveChanges();
			Transport transport = session.getTransport("smtp");
			transport.connect(props.getProperty("server"),
					props.getProperty("authUser"),
					props.getProperty("authPasswd"));
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			System.out.println("邮件发送失败--"+e.getMessage());
		}

	}
	
	private static Properties getProp() throws IOException {
		// 获得文件存储路径
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		InputStream inputStream = classLoader
				.getResourceAsStream("messages/mail.properties");
		Properties prop = new Properties();
		prop.load(inputStream);
		return prop;
	}
}
