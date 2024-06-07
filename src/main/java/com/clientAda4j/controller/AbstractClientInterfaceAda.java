package com.clientAda4j.controller;

import com.clientAda4j.domain.ClientAdaCoreProp;
import com.clientAda4j.domain.ClientInterfaceProp;
import com.clientAda4j.exeption.ClientAdaExecuteException;
import com.google.common.collect.ImmutableMap;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * httpclient封装
 *
 * @author 王贺
 * @email 1280381827@qq.com
 */
public abstract class AbstractClientInterfaceAda implements IClientInterface, Serializable {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 连接超时
     */
    protected int connectionTimeOut;
    /**
     * 请求超时
     */
    protected int socketTimeOut;

    /**
     * 最大连接数
     */
    protected int poolingConnectionMaxTotal = 4;
    /**
     * 每个路由默认最大连接数
     */
    protected int defaultMaxPerRouteTotal = 2;
    /**
     * 请求头
     */
    protected BasicHeader[] headers;
    /**
     * 连接池
     */
    private final PoolingHttpClientConnectionManager manager;

    public AbstractClientInterfaceAda() {
        this.manager = new PoolingHttpClientConnectionManager(
                RegistryBuilder.<ConnectionSocketFactory>create().register("http", PlainConnectionSocketFactory.getSocketFactory())
                        .register("https", SSLConnectionSocketFactory.getSocketFactory()).build());
        this.manager.setMaxTotal(this.poolingConnectionMaxTotal); // 增加最大总连接数
        this.manager.setDefaultMaxPerRoute(this.defaultMaxPerRouteTotal); // 增加每个路由的默认最大连接数
    }

    /**
     * 创建请求对象
     *
     * @param domainUrl           请求主Url
     * @param clientInterfaceProp 请求接口参数
     * @return HttpPost
     */
    protected final HttpPost createPost(String domainUrl, ClientInterfaceProp clientInterfaceProp) {
        HttpPost httpPost = new HttpPost(String.format("%s/%s", domainUrl, clientInterfaceProp.getInterfaceUri()));
        httpPost.setConfig(RequestConfig.custom().setSocketTimeout(this.socketTimeOut).setConnectTimeout(this.connectionTimeOut).build());
        httpPost.setHeaders(this.headers);
        logger.info("[ClientAda SDK] Preparing: >> 请求地址: {} , 请求头: {}", domainUrl, httpPost.getAllHeaders());
        return httpPost;
    }

    /**
     * 请求接口
     *
     * @param clientAdaCoreProp DefaultExternalProp
     * @param requestObj        请求数据
     */
    protected final String executeUri(ClientAdaCoreProp clientAdaCoreProp, HttpEntity requestObj) {
        if (Objects.isNull(clientAdaCoreProp)) {
            throw new ClientAdaExecuteException("未获取到有效参数构建对象");
        }
        ClientInterfaceProp clientInterface = clientAdaCoreProp.getClientInterface();
        if (Objects.isNull(clientInterface)) {
            throw new ClientAdaExecuteException("未获取到有效接口参数对象");
        }
        return this.executeUri(this.createPost(clientAdaCoreProp.getCompleteUrl(), clientInterface), requestObj);
    }

    /**
     * 执行请求
     *
     * @param httpPost   httpPost
     * @param requestObj 请求对象
     * @return String
     */
    protected final String executeUri(HttpPost httpPost, HttpEntity requestObj) {
        try {
            HttpClient httpClient = HttpClientBuilder.create().setConnectionManager(this.manager).build();
            httpPost.setEntity(requestObj);
            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new ClientAdaExecuteException(String.format("[%s]无法请求此接口....", httpPost.getRequestLine().getUri()));
            }
            return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            logger.error("执行远程请求时发生了错误...", e);
        }
        return null;
    }


    /**
     * 过滤value为空的数据
     *
     * @param domain 要过滤的数据
     * @return ImmutableMap<String, Object>
     */
    protected final ImmutableMap<String, Object> removeNull(Map<String, Object> domain) {
        return Objects.isNull(domain) ? ImmutableMap.<String, Object>builder().build()
                : ImmutableMap.copyOf(domain.entrySet().stream().filter((e) -> e.getValue() != null).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }
}
