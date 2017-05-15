
package com.mxg.bybo.sms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "epid",
    "userName",
    "password",
    "phone",
    "content"
})
@XmlRootElement(name = "SendSms")
public class SendSms {

    @XmlElement(name = "Epid")
    protected String epid;
    @XmlElement(name = "User_Name")
    protected String userName;
    protected String password;
    protected String phone;
    protected String content;

    public String getEpid() {
        return epid;
    }

    public void setEpid(String value) {
        this.epid = value;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String value) {
        this.userName = value;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String value) {
        this.password = value;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String value) {
        this.phone = value;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String value) {
        this.content = value;
    }

}
