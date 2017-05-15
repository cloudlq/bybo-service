
package com.mxg.bybo.sms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "userMoResult"
})
@XmlRootElement(name = "User_MoResponse")
public class UserMoResponse {

    @XmlElement(name = "User_MoResult")
    protected String userMoResult;

    public String getUserMoResult() {
        return userMoResult;
    }

    public void setUserMoResult(String value) {
        this.userMoResult = value;
    }

}
