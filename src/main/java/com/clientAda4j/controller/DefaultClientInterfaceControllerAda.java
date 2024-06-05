package com.clientAda4j.controller;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.clientAda4j.ClientHeaderAdapter;
import com.clientAda4j.DefaultClientAdaResponseFactory;
import com.clientAda4j.IClientAdaResponseFactory;
import com.clientAda4j.domain.*;
import com.clientAda4j.exeption.ClientAdaExecuteException;
import com.google.common.collect.ImmutableMap;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Objects;

/**
 * 默认接口请求
 *
 * @author wanghe
 */
@Component
@ConditionalOnProperty(prefix = "clientada4j", name = "enabled", havingValue = "true")
public final class DefaultClientInterfaceControllerAda extends AbstractClientInterfaceAda {

    public DefaultClientInterfaceControllerAda(int connectTime, int socketTime) {
        this.connectTime = connectTime;
        this.socketTime = socketTime;
    }

    /**
     * 标准参数接口请求
     *
     * @param clientUrl 主URL
     * @param params    请求参数
     */
    @Override
    public ClientResponseProp<LinkedHashMap<String, Object>> get(String clientUrl, String interfaceUri, ImmutableMap<String, Object> params) {
        ClientResponseProp<LinkedHashMap<String, Object>> clientResponseProp = new ClientResponseProp<>();
        try {
            LinkedHashMap<String, Object> resultBean = JSON.parseObject(this.executeUri(this.createPost(clientUrl,
                    new ClientInterfaceProp("---", "000000", interfaceUri)), new StringEntity(JSON.toJSONString(params))), new TypeReference<LinkedHashMap<String, Object>>() {
            });
            clientResponseProp.setResponse(resultBean);
        } catch (Exception e) {
            logger.error("clientUrl:{} interfaceUri:{} 格式化返回参数错误...", clientUrl, interfaceUri, e);
        }
        return clientResponseProp;
    }


    /**
     * 请求接口
     *
     * @param clientAdaCoreProp 接口参数
     * @param params            请求参数
     */
    @Override
    public ClientResponseProp<DefaultClientResponseProp> request(ClientAdaCoreProp clientAdaCoreProp, ImmutableMap<String, Object> params) {
        try {
            ClientResponseProp<DefaultClientResponseProp> clientResponseProp = this.request(clientAdaCoreProp, new StringEntity(JSON.toJSONString(params)), new DefaultClientAdaResponseFactory());
            this.logger.info("[三方数据请求] 请求响应详细信息 >>> {}", clientResponseProp.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 请求接口
     *
     * @param clientAdaCoreProp 接口参数
     * @param requestObj        请求对象
     * @param cls               响应参数转换为实际对象
     * @param <E>               实际参数对象
     */
    @Override
    public <E> ClientResponseProp<E> request(ClientAdaCoreProp clientAdaCoreProp, HttpEntity requestObj, Class<E> cls) {
        return new ClientResponseProp<E>(BeanUtil.toBean(this.executeUri(clientAdaCoreProp, requestObj), cls));
    }

    /**
     * 请求接口
     *
     * @param clientAdaCoreProp 接口参数
     * @param requestObj        请求对象
     * @param factory   IClientAdaResponseFactory
     * @param <E>               实际参数对象
     */
    @Override
    public <E> ClientResponseProp<E> request(ClientAdaCoreProp clientAdaCoreProp, HttpEntity requestObj, IClientAdaResponseFactory<E> factory) {
        return new ClientResponseProp<E>(factory.process(this.executeUri(clientAdaCoreProp, requestObj)));
    }

    /**
     * 添加请求头
     * <p>
     * 自定义请求头会覆盖所有默认的请求头配置
     *
     * @param clientHeaderProp Header
     * @return this
     */
    @Override
    public DefaultClientInterfaceControllerAda addClientHeaders(ClientHeaderProp clientHeaderProp) {
        if (Objects.isNull(clientHeaderProp) || clientHeaderProp.getHeaders().length == 0) {
            throw new ClientAdaExecuteException("[三方数据请求] >>> header对象不能为null, 请检查....");
        }
        this.headers = clientHeaderProp.getHeaders();
        return this;
    }

    /**
     * 添加自定义请求头
     * <p>
     * 自定义请求头会覆盖所有默认的请求头配置
     *
     * @param e ClientAdaHeaderAdapter
     * @return this
     */
    @Override
    public <E extends ClientHeaderAdapter> DefaultClientInterfaceControllerAda addClientHeadersAdapter(E e) {
        return this.addClientHeaders(new ClientHeaderProp(e.handler()));
    }
}
