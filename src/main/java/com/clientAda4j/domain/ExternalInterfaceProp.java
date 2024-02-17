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
    @XmlElement(name = "interface", type = ExternalInterfacePropDomain.class)
    protected List<ExternalInterfacePropDomain> propDomains;

    public List<ExternalInterfacePropDomain> getPropDomains() {
        return propDomains;
    }

    public ExternalInterfaceProp setPropDomains(List<ExternalInterfacePropDomain> propDomains) {
        this.propDomains = propDomains;
        return this;
    }
}