package com.clientAda4j;

import org.apache.http.message.BasicHeader;

/**
 * 自定义头部请求适配器
 *
 * @author wanghe
 */
public interface IClientHeaderAdapter {

    BasicHeader[] adapter();

}
