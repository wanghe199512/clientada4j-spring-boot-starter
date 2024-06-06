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
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 王贺
 * @email 1280381827@qq.com
 */
public abstract class AbstractClientInterfaceAda implements IClientInterface, Serializable {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 连接超时
     */
    protected int connectTime = 8 * 1000;
    /**
     * 请求超时
     */
    protected int socketTime = 10000;
    /**
     * 请求头
     */
    protected BasicHeader[] headers;

    /**
     * 创建请求对象
     *
     * @param domainUrl           请求主Url
     * @param clientInterfaceProp 请求接口参数
     * @return HttpPost
     */
    protected final HttpPost createPost(String domainUrl, ClientInterfaceProp clientInterfaceProp) {
        HttpPost httpPost = new HttpPost(String.format("%s/%s", domainUrl, clientInterfaceProp.getInterfaceUri()));
        httpPost.setConfig(RequestConfig.custom().setSocketTimeout(this.socketTime).setConnectTimeout(this.connectTime).build());
        httpPost.setHeaders(this.headers);
        logger.info("[ClientAda SDK] >>> url:{} , header:{}", domainUrl, httpPost.getAllHeaders());
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
            throw new ClientAdaExecuteException("[ClientAda SDK] >>> 未获取到有效参数构建对象");
        }
        ClientInterfaceProp clientInterface = clientAdaCoreProp.getClientInterface();
        if (Objects.isNull(clientInterface)) {
            throw new ClientAdaExecuteException("[ClientAda SDK] >>> 未获取到有效接口参数对象");
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
            BasicHttpClientConnectionManager connManager = new BasicHttpClientConnectionManager(RegistryBuilder.<ConnectionSocketFactory>create().register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .register("https", SSLConnectionSocketFactory.getSocketFactory()).build(), null, null, null);
            HttpClient httpClient = HttpClientBuilder.create().setConnectionManager(connManager).build();
            httpPost.setEntity(requestObj);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            logger.debug("[ClientAda SDK] 原始响应 >>> {}", responseString);
            return responseString;
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
