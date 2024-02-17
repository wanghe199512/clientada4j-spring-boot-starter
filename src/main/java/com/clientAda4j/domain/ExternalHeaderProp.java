package com.clientAda4j.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 接口请求头配置
 *
 * @author wanghe
 */
@XmlRootElement(name = "header")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ExternalHeaderProp {
    /**
     * APPId
     */
    @XmlElement(name = "appId")
    protected String appId;

    /**
     * 客户端Id
     */
    @XmlElement(name = "clientId")
    protected String clientId;
    /**
     *
     */
    @XmlElement(name = "appsecret")
    protected String appSecret;
    /**
     * 编码
     */
    @XmlElement(name = "charset")
    protected String charset;

    public String getAppId() {
        return appId;
    }

    public ExternalHeaderProp setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public ExternalHeaderProp setAppSecret(String appSecret) {
        this.appSecret = appSecret;
        return this;
    }

    public String getClientId() {
        return clientId;
    }

    public ExternalHeaderProp setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public String getCharset() {
        return charset;
    }

    public ExternalHeaderProp setCharset(String charset) {
        this.charset = charset;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                "appId='" + appId + '\'' +
                ", appSecret='" + appSecret + '\'' +
                ", charset='" + charset + '\'' +
                '}';
    }
}