package com.mxg.common.utils;

import java.net.URL;

import javax.xml.namespace.QName;

import com.mxg.bybo.sms.SmsPort;
import com.mxg.bybo.sms.SmsPortSoap;

public class SmsSendUtil {
	private static final QName SERVICE_NAME = new QName(
			"http://support.microsoft.com/", "SmsPort");

	public static boolean sendSms(String phone, String number) {
		URL wsdlURL = SmsPort.WSDL_LOCATION;
		SmsPort ss = new SmsPort(wsdlURL, SERVICE_NAME);
		SmsPortSoap port = ss.getSmsPortSoap();

		java.lang.String _sendSms_epid = MessageUtil.getProperty("epid");
		java.lang.String _sendSms_userName = MessageUtil
				.getProperty("userName");
		String pwd =  MessageUtil.getProperty("password");
		java.lang.String _sendSms_password = MD5Util.md5_16(pwd);
		java.lang.String _sendSms_phone = phone;
		java.lang.String _sendSms_content = "您的验证码是："+number+"，此验证码只用于您登录拜博口腔官网或找回密码，请勿提供给他人。【拜博医疗集团】";
		java.lang.String _sendSms__return = port.sendSms(_sendSms_epid,
				_sendSms_userName, _sendSms_password, _sendSms_phone,
				_sendSms_content);
		boolean tag = false;
		if(_sendSms__return.equals("00")){
			tag =  true;
		}
		return tag;
	}

	public static void main(String[] args) {
		System.out.println(sendSms("13023403001","5201314"));		
	}

}
