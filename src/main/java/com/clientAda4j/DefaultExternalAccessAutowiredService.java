package com.clientAda4j;

import cn.hutool.core.bean.BeanUtil;
import com.clientAda4j.domain.ExternalProp;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;
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
        return ImmutableMap.copyOf(this.mappingProps);
    }

    /**
     * 使用Id获取配置
     *
     * @return ImmutableList<DefaultExternalProp>
     */
    @Override
    public Map<String, Object> getInterfaceProp(String externalId) {
        Optional<Map.Entry<String, HashMap<String, Object>>> optional = this.mappingProps.entrySet().stream().filter(item -> item.getValue().get("externalId").equals(externalId)).findFirst();
        return optional.<Map<String, Object>>map(Map.Entry::getValue).orElse(null);
    }

    /**
     * 使用Id获取配置并转换为实际的类接收
     *
     * @return ImmutableList<DefaultExternalProp>
     */
    @Override
    public <T extends ExternalProp> T getInterfaceProp(String externalId, Class<T> cls) {
        return BeanUtil.mapToBean(this.getInterfaceProp(externalId), cls, true);
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
