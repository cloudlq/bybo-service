
package com.mxg.bybo.sms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "sendSmsExResult"
})
@XmlRootElement(name = "SendSmsExResponse")
public class SendSmsExResponse {

    @XmlElement(name = "SendSmsExResult")
    protected String sendSmsExResult;

    public String getSendSmsExResult() {
        return sendSmsExResult;
    }

    public void setSendSmsExResult(String value) {
        this.sendSmsExResult = value;
    }

}
