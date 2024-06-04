package com.clientAda4j.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 配置
 *
 * @author wanghe
 * @email 1280381827@qq.com
 */
@Configuration
@ConfigurationProperties(value = "clientada4j")
public class ClientAdaProperties {
    /**
     * 开启或关闭
     */
    private boolean enabled = false;
    /**
     * 请求连接超时
     */
    private int connectTimeOut = 50000;
    /**
     * 请求响应超时
     */
    private int socketTimeOut = 50000;


    public boolean getEnabled() {
        return enabled;
    }

    public ClientAdaProperties setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public int getConnectTimeOut() {
        return connectTimeOut;
    }

    public ClientAdaProperties setConnectTimeOut(int connectTimeOut) {
        this.connectTimeOut = connectTimeOut;
        return this;
    }

    public int getSocketTimeOut() {
        return socketTimeOut;
    }

    public ClientAdaProperties setSocketTimeOut(int socketTimeOut) {
        this.socketTimeOut = socketTimeOut;
        return this;
    }
}
