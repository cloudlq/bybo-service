
package com.mxg.bybo.sms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "userOverageResult"
})
@XmlRootElement(name = "User_OverageResponse")
public class UserOverageResponse {

    @XmlElement(name = "User_OverageResult")
    protected String userOverageResult;

    public String getUserOverageResult() {
        return userOverageResult;
    }

    public void setUserOverageResult(String value) {
        this.userOverageResult = value;
    }

}
