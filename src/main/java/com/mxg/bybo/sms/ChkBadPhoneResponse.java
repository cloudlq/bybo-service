
package com.mxg.bybo.sms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "chkBadPhoneResult"
})
@XmlRootElement(name = "ChkBadPhoneResponse")
public class ChkBadPhoneResponse {

    @XmlElement(name = "ChkBadPhoneResult")
    protected String chkBadPhoneResult;
    public String getChkBadPhoneResult() {
        return chkBadPhoneResult;
    }

    public void setChkBadPhoneResult(String value) {
        this.chkBadPhoneResult = value;
    }

}
