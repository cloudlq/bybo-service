
package com.mxg.bybo.sms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "changePassResult"
})
@XmlRootElement(name = "ChangePassResponse")
public class ChangePassResponse {

    @XmlElement(name = "ChangePassResult")
    protected String changePassResult;

    public String getChangePassResult() {
        return changePassResult;
    }

    public void setChangePassResult(String value) {
        this.changePassResult = value;
    }

}
