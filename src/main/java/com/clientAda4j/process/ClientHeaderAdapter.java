package com.clientAda4j.process;

import org.apache.http.message.BasicHeader;

/**
 * 自定义头部请求适配器
 *
 * @author wanghe
 */
public class ClientHeaderAdapter implements IClientHeaderAdapter {
    @Override
    public BasicHeader[] adapter() {
        return new BasicHeader[]{new BasicHeader("Content-Type", "application/json"),
                new BasicHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.6)"), new BasicHeader("Connection", "Keep-Alive")};
    }

}
