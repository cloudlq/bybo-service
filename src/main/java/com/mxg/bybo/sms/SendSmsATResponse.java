
package com.mxg.bybo.sms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "sendSmsATResult"
})
@XmlRootElement(name = "SendSmsATResponse")
public class SendSmsATResponse {

    @XmlElement(name = "SendSmsATResult")
    protected String sendSmsATResult;

    public String getSendSmsATResult() {
        return sendSmsATResult;
    }

    public void setSendSmsATResult(String value) {
        this.sendSmsATResult = value;
    }

}
