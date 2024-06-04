package com.clientAda4j.domain;

import java.util.List;

public class ClientAdaCoreProp {
    /**
     * 客户端ID
     */
    String clientId;
    /**
     * 客户端名称
     */
    String clientName;
    /**
     * 客户端URI
     */
    String clientUri;
    /**
     * 客户端请求端口
     */
    String clientPort;
    /**
     * 客户端接口
     */
    List<ClientInterfaceProp> clientInterface;

    /**
     * 客户端请求头
     */
    ClientHeaderProp clientHeaderProp;
    /**
     * 客户端说明
     */
    String clientNote;

    public String getClientId() {
        return clientId;
    }

    public ClientAdaCoreProp setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public String getClientName() {
        return clientName;
    }

    public ClientAdaCoreProp setClientName(String clientName) {
        this.clientName = clientName;
        return this;
    }

    public String getClientUri() {
        return clientUri;
    }

    public ClientAdaCoreProp setClientUri(String clientUri) {
        this.clientUri = clientUri;
        return this;
    }

    public String getClientPort() {
        return clientPort;
    }

    public ClientAdaCoreProp setClientPort(String clientPort) {
        this.clientPort = clientPort;
        return this;
    }

    public List<ClientInterfaceProp> getClientInterface() {
        return clientInterface;
    }

    public ClientAdaCoreProp setClientInterface(List<ClientInterfaceProp> clientInterface) {
        this.clientInterface = clientInterface;
        return this;
    }

    public ClientHeaderProp getClientHeaderProp() {
        return clientHeaderProp;
    }

    public ClientAdaCoreProp setClientHeaderProp(ClientHeaderProp clientHeaderProp) {
        this.clientHeaderProp = clientHeaderProp;
        return this;
    }

    public String getClientNote() {
        return clientNote;
    }

    public ClientAdaCoreProp setClientNote(String clientNote) {
        this.clientNote = clientNote;
        return this;
    }
}
