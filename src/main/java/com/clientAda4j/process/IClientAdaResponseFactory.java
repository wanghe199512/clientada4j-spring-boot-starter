package com.clientAda4j.process;

public interface IClientAdaResponseFactory<T> {
    /**
     * 获取响应参数
     *
     * @param response 响应返回参数
     * @return T
     */
    T process(String response);
}
