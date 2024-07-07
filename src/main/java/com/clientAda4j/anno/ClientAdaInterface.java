package com.clientAda4j.anno;

import com.clientAda4j.process.IClientAdaResponseFactory;
import com.clientAda4j.process.LinkedHashMapClientAdaResponseFactory;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ClientAdaInterface {
    /**
     * 接口名称
     */
    String interfaceName();

    /**
     * 接口ID
     */
    String interfaceId() default "";

    /**
     * 接口地址
     */
    String interfaceUri();

    /**
     * 返回结果处理器
     */
    Class<? extends IClientAdaResponseFactory<?>> responseFactory() default LinkedHashMapClientAdaResponseFactory.class;

    /**
     * 请求方式
     */
    RequestMethod interfaceMethod() default RequestMethod.GET;
}
