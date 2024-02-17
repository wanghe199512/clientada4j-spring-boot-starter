package com.clientAda4j.component;

import com.clientAda4j.DefaultExternalAccessAutowiredService;
import com.clientAda4j.adapter.DefaultInterfaceAdaAliasAdapter;
import com.clientAda4j.domain.ExternalProp;
import com.clientAda4j.properties.ClientAdaProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动装配
 *
 * @author wanghe
 * @email 1280381827@qq.com
 */
@Configuration
@ConditionalOnProperty(prefix = "spring.clientada", name = "enabled", havingValue = "true")
public class AutoClientAdaComponent {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ClientAdaProperties clientProperties;

    public AutoClientAdaComponent(ClientAdaProperties clientAdaProperties) {
        this.clientProperties = clientAdaProperties;
    }

    @Bean
    public DefaultExternalAccessAutowiredService<ExternalProp> defaultExternalAccessAutowiredService() {
        logger.info("======================三方客户端请求接口配置开始加载==========================");
        return new DefaultExternalAccessAutowiredService<>()
                .setPropertiesPath(this.clientProperties.getScanProperties())
                .mappingCls(this.clientProperties.getMapCls())
                .addRequestMappingAliasAdapter(new DefaultInterfaceAdaAliasAdapter())
                .build();
    }
}
