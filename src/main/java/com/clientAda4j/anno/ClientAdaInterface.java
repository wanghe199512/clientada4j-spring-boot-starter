package com.clientAda4j.anno;

import com.clientAda4j.DefaultClientResponseFactory;
import com.clientAda4j.domain.DefaultClientResponseProp;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ClientAdaInterface {
    /**
     * 接口名称
     */
    String interfaceName() default "";

    /**
     * 接口ID
     */
    String interfaceId();

    /**
     * 返回类型
     */
    Class<? extends DefaultClientResponseProp> responseCls() default DefaultClientResponseProp.class;

    /**
     * 返回结果处理器
     */
    Class<? extends DefaultClientResponseFactory> responseFactory() default DefaultClientResponseFactory.class;
}
