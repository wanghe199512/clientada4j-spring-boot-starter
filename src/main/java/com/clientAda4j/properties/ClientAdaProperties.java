package com.clientAda4j.properties;

import com.clientAda4j.domain.ExternalProp;
import com.google.common.collect.ImmutableMap;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置
 *
 * @author wanghe
 * @email 1280381827@qq.com
 */
@ConfigurationProperties("spring.clientada")
public class ClientAdaProperties {
    /**
     * 开启或关闭
     */
    private boolean enabled = false;
    /**
     * 扫描配置文件(默认扫描resources/clientAda目录下所有文件)
     */
    private String scanProperties = "classpath:/clientAda/*";

    /**
     * 文件与关系实体映射
     */
    private ImmutableMap<String, Class<? extends ExternalProp>> mapCls;

    public String getScanProperties() {
        return scanProperties;
    }

    public ClientAdaProperties setScanProperties(String scanProperties) {
        this.scanProperties = scanProperties;
        return this;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public ClientAdaProperties setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public ImmutableMap<String, Class<? extends ExternalProp>> getMapCls() {
        return mapCls;
    }

    public ClientAdaProperties setMapCls(ImmutableMap<String, Class<? extends ExternalProp>> mapCls) {
        this.mapCls = mapCls;
        return this;
    }
}
