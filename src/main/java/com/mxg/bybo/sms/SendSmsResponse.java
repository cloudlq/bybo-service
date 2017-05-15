
package com.mxg.bybo.sms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "sendSmsResult"
})
@XmlRootElement(name = "SendSmsResponse")
public class SendSmsResponse {

    @XmlElement(name = "SendSmsResult")
    protected String sendSmsResult;
    public String getSendSmsResult() {
        return sendSmsResult;
    }

    public void setSendSmsResult(String value) {
        this.sendSmsResult = value;
    }

}
