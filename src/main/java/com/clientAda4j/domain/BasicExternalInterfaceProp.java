package com.clientAda4j.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "interface")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class BasicExternalInterfaceProp {
    /**
     * 编码
     */
    @XmlElement(name = "serviceCode")
    private String code;
    /**
     * 名称
     */
    @XmlElement(name = "serviceName")
    private String name;
    /**
     * 服务ID
     */
    @XmlElement(name = "serviceCd")
    private String serviceCd;

    /**
     * 服务描述信息
     */
    @XmlElement(name = "desc")
    private String desc = "";

    /**
     * 服务动态信息
     */
    @XmlElement(name = "dynamic")
    private String dynamic = "";

    public BasicExternalInterfaceProp() {
    }

    public BasicExternalInterfaceProp(String serviceCd, String code) {
        this.code = code;
        this.serviceCd = serviceCd;
    }

    public String getCode() {
        return code;
    }

    public BasicExternalInterfaceProp setCode(String code) {
        this.code = code;
        return this;
    }

    public String getName() {
        return name;
    }

    public BasicExternalInterfaceProp setName(String name) {
        this.name = name;
        return this;
    }

    public String getServiceCd() {
        return serviceCd;
    }

    public BasicExternalInterfaceProp setServiceCd(String serviceCd) {
        this.serviceCd = serviceCd;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public BasicExternalInterfaceProp setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public String getDynamic() {
        return dynamic;
    }

    public BasicExternalInterfaceProp setDynamic(String dynamic) {
        this.dynamic = dynamic;
        return this;
    }


    @Override
    public String toString() {
        return "{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", serviceCd='" + serviceCd + '\'' +
                ", desc='" + desc + '\'' +
                ", dynamic='" + dynamic + '\'' +
                '}';
    }
}