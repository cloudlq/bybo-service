
package com.mxg.bybo.sms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "sendATResult"
})
@XmlRootElement(name = "SendATResponse")
public class SendATResponse {

    @XmlElement(name = "SendATResult")
    protected String sendATResult;

    public String getSendATResult() {
        return sendATResult;
    }

    public void setSendATResult(String value) {
        this.sendATResult = value;
    }

}
