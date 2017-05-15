
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
    "oldPass",
    "newPass",
    "confirmPassword"
})
@XmlRootElement(name = "ChangePass")
public class ChangePass {

    @XmlElement(name = "EPID")
    protected String epid;
    @XmlElement(name = "User_Name")
    protected String userName;
    @XmlElement(name = "OldPass")
    protected String oldPass;
    @XmlElement(name = "NewPass")
    protected String newPass;
    @XmlElement(name = "Confirm_Password")
    protected String confirmPassword;

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
    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String value) {
        this.oldPass = value;
    }
    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String value) {
        this.newPass = value;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String value) {
        this.confirmPassword = value;
    }

}
