package com.clientAda4j.rest;

import com.alibaba.fastjson2.JSON;
import com.clientAda4j.domain.ClientAdaCoreProp;
import com.clientAda4j.domain.ClientInterfaceProp;
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
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
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
        HttpPost httpPost = new HttpPost(domainUrl + clientInterfaceProp.getInterfaceUri());
        httpPost.setConfig(RequestConfig.custom().setSocketTimeout(this.socketTime).setConnectTimeout(this.connectTime).build());
        httpPost.setHeaders(headers);
        logger.info("[三方数据请求] >>> 客户端URL:{} ,客户端请求头:{}", domainUrl, httpPost.getAllHeaders());
        return httpPost;
    }

    /**
     * 请求接口
     *
     * @param clientAdaCoreProp DefaultExternalProp
     * @param serviceId         要请求的接口Id
     * @param requestObj        请求数据
     */
    protected final String executeUri(ClientAdaCoreProp clientAdaCoreProp, String serviceId, HttpEntity requestObj) {
        if (Objects.isNull(clientAdaCoreProp)) {
            throw new RuntimeException("[三方数据请求] >>> 主接口参数对象有误，本次请求进程终止....");
        }
        Optional<ClientInterfaceProp> optional = clientAdaCoreProp.getClientInterface().stream().filter(ser -> ser.getInterfaceId().equals(serviceId)).findFirst();
        if (!optional.isPresent()) {
            throw new RuntimeException(String.format("[三方数据请求] >>> 没有获取到包含[%s]请求的有效Id或者链接！", serviceId));
        }
        logger.info("[三方数据请求] 请求参数详细信息 >>> {}", requestObj.toString());
        return this.executeUri(this.createPost(clientAdaCoreProp.getClientUri(), optional.get()), requestObj);
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
            httpPost.setEntity(new StringEntity(JSON.toJSONString(requestObj), "UTF-8"));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            logger.info(" [三方数据请求] 来自{}的原始响应 >>> {}", httpPost.getURI(), responseString);
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
