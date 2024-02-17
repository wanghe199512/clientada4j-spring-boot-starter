package com.clientAda4j.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "external")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ExternalProp implements Serializable {
    /**
     * 接口ID
     */
    @XmlElement(name = "externalId")
    protected String externalId;
    /**
     * 名称
     */
    @XmlElement(name = "name")
    protected String name;
    /**
     * 地址
     */
    @XmlElement(name = "url")
    protected String url;
    /**
     * 运行检查
     */
    @XmlElement(name = "healthCheck")
    private boolean healthCheck = false;

    /**
     * 是否使用参数别名映射请求，true: 直接在url后拼接header元素内参数请求 false：使用别名嵌套请求
     */
    @XmlElement(name = "useAliasAsRequest")
    private boolean useAliasAsRequest = false;
    /**
     * 头部信息
     */
    @XmlElement(name = "headers", type = ExternalHeaderProp.class)
    protected ExternalHeaderProp header;

    /**
     * 访问接口数组
     */
    @XmlElement(name = "interfaces", type = ExternalInterfaceProp.class)
    protected ExternalInterfaceProp interfaceProps;

    public String getExternalId() {
        return externalId;
    }

    public ExternalProp setExternalId(String externalId) {
        this.externalId = externalId;
        return this;
    }

    public String getName() {
        return name;
    }

    public ExternalProp setName(String name) {
        this.name = name;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public ExternalProp setUrl(String url) {
        this.url = url;
        return this;
    }

    public ExternalHeaderProp getHeader() {
        return header;
    }

    public ExternalProp setHeader(ExternalHeaderProp header) {
        this.header = header;
        return this;
    }

    public ExternalInterfaceProp getInterfaceProps() {
        return interfaceProps;
    }

    public ExternalProp setInterfaceProps(ExternalInterfaceProp interfaceProps) {
        this.interfaceProps = interfaceProps;
        return this;
    }

    public boolean isHealthCheck() {
        return healthCheck;
    }

    public ExternalProp setHealthCheck(boolean healthCheck) {
        this.healthCheck = healthCheck;
        return this;
    }

    public boolean useAliasAsRequest() {
        return useAliasAsRequest;
    }

    public ExternalProp setUseAliasAsRequest(boolean useAliasAsRequest) {
        this.useAliasAsRequest = useAliasAsRequest;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                "服务ID='" + externalId + '\'' +
                ", 服务名称='" + name + '\'' +
                ", 服务Url='" + url + '\'' +
                ", 服务检查=" + (healthCheck ? "是" : "否") +
                ", 请求参数别名映射=" + (useAliasAsRequest ? "是" : "否") +
                ", 服务请求头=" + header +
                ", 服务接口列表=" + interfaceProps.getPropDomains() +
                '}';
    }
}
