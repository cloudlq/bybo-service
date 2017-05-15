
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
    "password"
})
@XmlRootElement(name = "User_Mo")
public class UserMo {

    @XmlElement(name = "EPID")
    protected String epid;
    @XmlElement(name = "User_Name")
    protected String userName;
    @XmlElement(name = "Password")
    protected String password;

    public String getEPID() {
        return epid;
    }

    public void setEPID(String value) {
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

}
