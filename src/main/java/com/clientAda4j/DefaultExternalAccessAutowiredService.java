package com.clientAda4j;

import com.clientAda4j.domain.ExternalProp;
import com.google.common.collect.ImmutableMap;

import java.util.Optional;

/**
 * 默认的三方接口请求服务
 *
 * @author 王贺
 * @email 1280381827@qq.com
 */
public final class DefaultExternalAccessAutowiredService extends AbstractExternalAccessAutowired {

    /**
     * 获取所有配置
     *
     * @return ImmutableList<DefaultExternalProp>
     */
    @Override
    public ImmutableMap<String, Object> getInterfaceProp() {
        return null;
    }

    /**
     * 使用Id获取配置并转换为实际的类接收
     *
     * @return ImmutableList<DefaultExternalProp>
     */
    @Override
    public <T extends ExternalProp> T getInterfaceProp(String externalId, Class<T> cls) {
        try {
            Optional<T> optional = (Optional<T>) this.requestProps.parallelStream().filter(item -> item.getExternalId().equals(externalId)).findFirst();
            return optional.orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("执行错误，请检查参数[cls]是否继承自ExternalProp");
        }
    }

    /**
     * 构建
     *
     * @return this
     */
    @Override
    public DefaultExternalAccessAutowiredService build() {
        this.loaderFile();
        return this;
    }
}
