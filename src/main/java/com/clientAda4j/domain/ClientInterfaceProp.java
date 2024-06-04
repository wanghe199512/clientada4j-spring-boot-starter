package com.clientAda4j.domain;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class ClientInterfaceProp {

    String interfaceName;

    String interfaceId;

    String interfaceUri;

    public ClientInterfaceProp(String interfaceName, String interfaceId, String interfaceUri) {
        this.interfaceName = interfaceName;
        this.interfaceId = interfaceId;
        this.interfaceUri = interfaceUri;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public ClientInterfaceProp setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
        return this;
    }

    public String getInterfaceId() {
        return interfaceId;
    }

    public ClientInterfaceProp setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
        return this;
    }

    public String getInterfaceUri() {
        return interfaceUri;
    }

    public ClientInterfaceProp setInterfaceUri(String interfaceUri) {
        this.interfaceUri = interfaceUri;
        return this;
    }

    @Override
    public String toString() {
        return "接口信息:{" +
                "interfaceName='" + interfaceName + '\'' +
                ", interfaceId='" + interfaceId + '\'' +
                ", interfaceUri='" + interfaceUri + '\'' +
                '}';
    }
}