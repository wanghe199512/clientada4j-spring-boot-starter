package com.clientAda4j.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "interfaces")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ExternalInterfaceProp {
    /**
     * 接口列表
     */
    @XmlElement(name = "interface", type = BasicExternalInterfaceProp.class)
    protected List<BasicExternalInterfaceProp> propDomains;

    public List<BasicExternalInterfaceProp> getPropDomains() {
        return propDomains;
    }

    public ExternalInterfaceProp setPropDomains(List<BasicExternalInterfaceProp> propDomains) {
        this.propDomains = propDomains;
        return this;
    }
}