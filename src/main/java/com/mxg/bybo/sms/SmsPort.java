package com.mxg.bybo.sms;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

@WebServiceClient(name = "SmsPort", 
                  wsdlLocation = "http://61.191.26.181:8888/SmsPort.asmx?WSDL",
                  targetNamespace = "http://support.microsoft.com/") 
public class SmsPort extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://support.microsoft.com/", "SmsPort");
    public final static QName SmsPortSoap = new QName("http://support.microsoft.com/", "SmsPortSoap");
    public final static QName SmsPortSoap12 = new QName("http://support.microsoft.com/", "SmsPortSoap12");
    public final static QName SmsPortHttpPost = new QName("http://support.microsoft.com/", "SmsPortHttpPost");
    public final static QName SmsPortHttpGet = new QName("http://support.microsoft.com/", "SmsPortHttpGet");
    static {
        URL url = null;
        try {
            url = new URL("http://61.191.26.181:8888/SmsPort.asmx?WSDL");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(SmsPort.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://61.191.26.181:8888/SmsPort.asmx?WSDL");
        }
        WSDL_LOCATION = url;
    }

    public SmsPort(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SmsPort(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SmsPort() {
        super(WSDL_LOCATION, SERVICE);
    }
    




    /**
     *
     * @return
     *     returns SmsPortSoap
     */
    @WebEndpoint(name = "SmsPortSoap")
    public SmsPortSoap getSmsPortSoap() {
        return super.getPort(SmsPortSoap, SmsPortSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SmsPortSoap
     */
    @WebEndpoint(name = "SmsPortSoap")
    public SmsPortSoap getSmsPortSoap(WebServiceFeature... features) {
        return super.getPort(SmsPortSoap, SmsPortSoap.class, features);
    }


    /**
     *
     * @return
     *     returns SmsPortSoap
     */
    @WebEndpoint(name = "SmsPortSoap12")
    public SmsPortSoap getSmsPortSoap12() {
        return super.getPort(SmsPortSoap12, SmsPortSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SmsPortSoap
     */
    @WebEndpoint(name = "SmsPortSoap12")
    public SmsPortSoap getSmsPortSoap12(WebServiceFeature... features) {
        return super.getPort(SmsPortSoap12, SmsPortSoap.class, features);
    }


    /**
     *
     * @return
     *     returns SmsPortHttpPost
     */
    @WebEndpoint(name = "SmsPortHttpPost")
    public SmsPortHttpPost getSmsPortHttpPost() {
        return super.getPort(SmsPortHttpPost, SmsPortHttpPost.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SmsPortHttpPost
     */
    @WebEndpoint(name = "SmsPortHttpPost")
    public SmsPortHttpPost getSmsPortHttpPost(WebServiceFeature... features) {
        return super.getPort(SmsPortHttpPost, SmsPortHttpPost.class, features);
    }


    /**
     *
     * @return
     *     returns SmsPortHttpGet
     */
    @WebEndpoint(name = "SmsPortHttpGet")
    public SmsPortHttpGet getSmsPortHttpGet() {
        return super.getPort(SmsPortHttpGet, SmsPortHttpGet.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SmsPortHttpGet
     */
    @WebEndpoint(name = "SmsPortHttpGet")
    public SmsPortHttpGet getSmsPortHttpGet(WebServiceFeature... features) {
        return super.getPort(SmsPortHttpGet, SmsPortHttpGet.class, features);
    }

}
