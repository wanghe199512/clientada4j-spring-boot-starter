package com.clientAda4j;

import com.clientAda4j.domain.ExternalProp;
import com.google.common.collect.ImmutableList;

import java.util.Optional;

/**
 * 默认的三方接口请求服务
 * @email 1280381827@qq.com
 *
 * @author 王贺
 */
public class DefaultExternalAccessAutowiredService<T extends ExternalProp> extends AbstractExternalAccessAutowired<T> {

    /**
     * 获取所有配置
     *
     * @return ImmutableList<DefaultExternalProp>
     */
    @Override
    public ImmutableList<T> getInterfaceProp() {
        return ImmutableList.copyOf(this.props);
    }

    /**
     * 使用Id获取配置
     *
     * @return ImmutableList<DefaultExternalProp>
     */
    @Override
    public T getInterfaceProp(String externalId) {
        Optional<T> optional = this.props.stream().filter(i -> i.getExternalId().equals(externalId)).findFirst();
        return optional.orElse(null);
    }

    /**
     * 使用Id获取配置并转换为实际的类接收
     *
     * @return ImmutableList<DefaultExternalProp>
     */
    @Override
    public T getInterfaceProp(String externalId, Class<? extends ExternalProp> cls) {
        return null;
    }

    /**
     * 构建
     *
     * @return this
     */
    @Override
    public DefaultExternalAccessAutowiredService<T> build() {
        this.loaderFile();
        return this;
    }
}
