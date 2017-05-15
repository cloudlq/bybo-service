
package com.mxg.bybo.sms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "sendResult"
})
@XmlRootElement(name = "SendResponse")
public class SendResponse {

    @XmlElement(name = "SendResult")
    protected String sendResult;

    public String getSendResult() {
        return sendResult;
    }

    public void setSendResult(String value) {
        this.sendResult = value;
    }

}
