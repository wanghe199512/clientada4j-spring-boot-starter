package com.clientAda4j.rest;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.clientAda4j.DefaultExternalAccessAutowiredService;
import com.clientAda4j.IExternalAccessAutowired;
import com.clientAda4j.adapter.InterfaceAdaAliasAdapter;
import com.clientAda4j.domain.*;
import com.google.common.collect.ImmutableMap;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Optional;

/**
 * 默认接口请求
 *
 * @param <T>
 * @author wanghe
 */
@Component
@ConditionalOnProperty(prefix = "spring.clientada", name = "enabled", havingValue = "true")
public class DefaultInterfaceClientAda<T extends ExternalProp> extends AbstractExternalInterfaceClientAda<T> {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 连接超时
     */
    private int connectTimeout = 8 * 1000;
    /**
     * 请求超时
     */
    private int socketTimeout = 10000;
    /**
     * 请求头
     */
    private Header[] headers = {new BasicHeader("Content-Type", "application/json"),
            new BasicHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.6)"), new BasicHeader("Connection", "Keep-Alive")};

    @Resource
    private DefaultExternalAccessAutowiredService<T> externalAccessAutowired;

    /**
     * 标准参数接口请求
     *
     * @param domainUrl 主URL
     * @param params    请求参数
     */
    @Override
    public ExternalResponseProp<ImmutableMap<String, Object>> request(String domainUrl, ImmutableMap<String, Object> params) {
        ExternalResponseProp<ImmutableMap<String, Object>> responseProp = new ExternalResponseProp<>();
        try {
            ImmutableMap<String, Object> resultBean = JSON.parseObject(this.execute(this.createPost(domainUrl, new ExternalInterfacePropDomain("/", "000000")), params), ImmutableMap.class);
            responseProp.setResponse(resultBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseProp;
    }

    /**
     * 请求接口(请求结果返回默认DefaultExternalResponseProp类型实体)
     *
     * @param externalId 接口ID
     * @param serviceCd  请求子服务code
     * @param params     请求参数
     */
    @Override
    public ExternalResponseProp<DefaultExternalResponseProp> request(String externalId, String serviceCd, ImmutableMap<String, Object> params) {
        return this.request(this.externalAccessAutowired.getInterfaceProp(externalId), serviceCd, params);
    }

    /**
     * 请求接口(请求结果返回指定类型实体)
     *
     * @param externalId 接口ID
     * @param serviceCd  请求子服务code
     * @param params     请求参数
     */
    public <E> ExternalResponseProp<E> request(String externalId, String serviceCd, ImmutableMap<String, Object> params, Class<ExternalResponseProp<E>> cls) {
        return this.request(this.externalAccessAutowired.getInterfaceProp(externalId), serviceCd, params, cls);
    }


    /**
     * 请求接口
     *
     * @param prop      接口参数
     * @param serviceCd 请求子服务code
     * @param params    请求参数
     */
    @Override
    public ExternalResponseProp<DefaultExternalResponseProp> request(T prop, String serviceCd, ImmutableMap<String, Object> params) {
        ExternalResponseProp<DefaultExternalResponseProp> responseProp = new ExternalResponseProp<>();
        try {
            DefaultExternalResponseProp resultBean = JSON.parseObject(
                    this.request(prop, serviceCd, this.removeNull(BeanUtil.beanToMap(prop.getHeader())), params), DefaultExternalResponseProp.class);
            responseProp.setResponse(resultBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.logger.info("[三方数据请求] 请求响应详细信息 >>> {}", responseProp.toString());
        return responseProp;
    }

    /**
     * 请求接口
     *
     * @param prop      接口参数
     * @param serviceCd 请求子服务code
     * @param params    请求参数
     * @param cls       响应参数转换为实际对象
     * @param <E>       实际参数对象
     */
    public <E> ExternalResponseProp<E> request(T prop, String serviceCd, ImmutableMap<String, Object> params, Class<ExternalResponseProp<E>> cls) {
        return BeanUtil.toBean(this.request(prop, serviceCd, params), cls);
    }

    /**
     * 请求接口
     *
     * @param prop      接口参数
     * @param serviceCd 请求子服务code
     * @param header    请求头
     * @param params    请求参数
     */
    @Override
    public String request(T prop, String serviceCd, ImmutableMap<String, Object> header, ImmutableMap<String, Object> params) {
        InterfaceAdaAliasAdapter aliasAdapter = this.externalAccessAutowired.getRequestMappingAdaAliasAdapter();
        boolean useAlias = prop.useAliasAsRequest();
        ImmutableMap<String, Object> mergedParams = ImmutableMap.<String, Object>builder()
                .putAll(useAlias ? aliasAdapter.headerAlias(header) : header)
                .putAll(useAlias ? aliasAdapter.paramsAlias(params) : params)
                .build();
        return this.execute(prop, serviceCd, mergedParams);
    }

    /**
     * 执行接口请求
     *
     * @param httpPost httpPost
     * @param params   dataMap
     * @return String
     */
    private String execute(HttpPost httpPost, ImmutableMap<String, Object> params) {
        try {
            BasicHttpClientConnectionManager connManager = new BasicHttpClientConnectionManager(RegistryBuilder.<ConnectionSocketFactory>create().register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .register("https", SSLConnectionSocketFactory.getSocketFactory()).build(), null, null, null);
            HttpClient httpClient = HttpClientBuilder.create().setConnectionManager(connManager).build();
            httpPost.setEntity(new StringEntity(JSON.toJSONString(params), "UTF-8"));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            logger.info(" [三方数据请求] 来自{}的原始响应 >>> {}", httpPost.getURI(), responseString);
            return responseString;
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return null;
    }

    /**
     * 添加自定义请求头
     * <p>
     * 自定义请求头会覆盖所有默认的请求头配置
     *
     * @param headers Header
     * @return this
     */
    @Override
    public <H extends Header> DefaultInterfaceClientAda<T> addClientHeaders(H[] headers) {
        if (Objects.isNull(headers)) {
            throw new RuntimeException("[三方数据请求] >>> header对象不能为null, 请检查....");
        }
        this.headers = headers;
        return this;
    }

    /**
     * 创建请求对象
     *
     * @param domainUrl             请求主Url
     * @param externalInterfaceProp 请求接口参数
     * @return HttpPost
     */
    private HttpPost createPost(String domainUrl, ExternalInterfacePropDomain externalInterfaceProp) {
        HttpPost httpPost = new HttpPost(domainUrl + externalInterfaceProp.getServiceCd());
        httpPost.setConfig(RequestConfig.custom().setSocketTimeout(this.socketTimeout).setConnectTimeout(this.connectTimeout).build());
        httpPost.setHeaders(this.headers);
        logger.info("[三方数据请求] >>> 客户端URL:{} ,客户端请求头:{}", domainUrl, httpPost.getAllHeaders());
        return httpPost;
    }

    /**
     * 请求接口
     *
     * @param prop      DefaultExternalProp
     * @param serviceCd 要请求的接口Id
     * @param params    请求数据
     */
    private <E extends ExternalProp> String execute(E prop, String serviceCd, ImmutableMap<String, Object> params) {
        if (Objects.isNull(prop)) {
            throw new RuntimeException("[三方数据请求] >>> 主接口参数对象有误，本次请求进程终止....");
        }
        Optional<ExternalInterfacePropDomain> optional = prop.getInterfaceProps().getPropDomains().stream().filter(ser -> ser.getServiceCd().equals(serviceCd)).findFirst();
        if (!optional.isPresent()) {
            throw new RuntimeException(String.format("[三方数据请求] >>> 没有获取到包含[%s]请求的有效Id或者链接！", serviceCd));
        }
        logger.info("[三方数据请求] 请求参数详细信息 >>> {}", params.toString());
        return this.execute(this.createPost(prop.getUrl(), optional.get()), params);
    }

    /**
     * 添加映射器
     *
     * @param adapter adapter
     * @return DefaultExternalInterfaceAda<T>
     */
    public DefaultInterfaceClientAda<T> addRequestMappingAliasAdapter(InterfaceAdaAliasAdapter adapter) {
        this.externalAccessAutowired.addRequestMappingAliasAdapter(adapter);
        return this;
    }

    /**
     * 设置请求等待时间
     *
     * @param connectTimeout httpConnectTimeout
     * @return this
     */
    public DefaultInterfaceClientAda<T> setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    public DefaultInterfaceClientAda<T> setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
        return this;
    }

    public IExternalAccessAutowired<T> getExternalAccessAutowired() {
        return externalAccessAutowired;
    }
}
