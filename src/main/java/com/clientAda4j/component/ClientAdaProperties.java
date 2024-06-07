package com.clientAda4j.component;

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
     * 连接超时
     */
    private int connectTimeOut = 50000;
    /**
     * 响应超时
     */
    private int socketTimeOut = 5000000;
    /**
     * 最大连接数
     */
    private int poolingConnectionMaxTotal = 500;
    /**
     * 每个路由默认最大连接数
     */
    private int defaultMaxPerRouteTotal = 50;

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

    public int getPoolingConnectionMaxTotal() {
        return poolingConnectionMaxTotal;
    }

    public ClientAdaProperties setPoolingConnectionMaxTotal(int poolingConnectionMaxTotal) {
        this.poolingConnectionMaxTotal = poolingConnectionMaxTotal;
        return this;
    }

    public int getDefaultMaxPerRouteTotal() {
        return defaultMaxPerRouteTotal;
    }

    public ClientAdaProperties setDefaultMaxPerRouteTotal(int defaultMaxPerRouteTotal) {
        this.defaultMaxPerRouteTotal = defaultMaxPerRouteTotal;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " 连接超时=" + connectTimeOut +
                ", 响应超时=" + socketTimeOut +
                ", 最大连接数=" + poolingConnectionMaxTotal +
                ", 每个路由默认最大连接数=" + defaultMaxPerRouteTotal +
                '}';
    }
}
