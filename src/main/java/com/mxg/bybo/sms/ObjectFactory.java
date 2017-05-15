
package com.mxg.bybo.sms;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


@XmlRegistry
public class ObjectFactory {

    private final static QName _String_QNAME = new QName("http://support.microsoft.com/", "string");

    public ObjectFactory() {
    }

    public SendSms createSendSms() {
        return new SendSms();
    }

    public SendSmsResponse createSendSmsResponse() {
        return new SendSmsResponse();
    }

    public SendSmsEx createSendSmsEx() {
        return new SendSmsEx();
    }

    public SendSmsExResponse createSendSmsExResponse() {
        return new SendSmsExResponse();
    }

    public ChkBadWord createChkBadWord() {
        return new ChkBadWord();
    }

    public ChkBadWordResponse createChkBadWordResponse() {
        return new ChkBadWordResponse();
    }

    public SendSmsAT createSendSmsAT() {
        return new SendSmsAT();
    }

    public SendSmsATResponse createSendSmsATResponse() {
        return new SendSmsATResponse();
    }

    public ChkBadPhone createChkBadPhone() {
        return new ChkBadPhone();
    }

    public ChkBadPhoneResponse createChkBadPhoneResponse() {
        return new ChkBadPhoneResponse();
    }

    public ChangePass createChangePass() {
        return new ChangePass();
    }

    public ChangePassResponse createChangePassResponse() {
        return new ChangePassResponse();
    }

    public UserOverage createUserOverage() {
        return new UserOverage();
    }

    public UserOverageResponse createUserOverageResponse() {
        return new UserOverageResponse();
    }

    public UserMo createUserMo() {
        return new UserMo();
    }

    public UserMoResponse createUserMoResponse() {
        return new UserMoResponse();
    }

    public Send createSend() {
        return new Send();
    }

    public SendResponse createSendResponse() {
        return new SendResponse();
    }

    public SendAT createSendAT() {
        return new SendAT();
    }

    public SendATResponse createSendATResponse() {
        return new SendATResponse();
    }

    @XmlElementDecl(namespace = "http://support.microsoft.com/", name = "string")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<String>(_String_QNAME, String.class, null, value);
    }

}
