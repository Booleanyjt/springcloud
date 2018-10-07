package com.tao.springcloud.eureka4.eurekaserver.config;

import com.netflix.discovery.EurekaClientConfig;
import com.netflix.eureka.EurekaServerConfig;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.netflix.eureka.server.EurekaServerAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EurekaServerConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @Author: JiantaoYan
 * @Description:
 * @Date: 10:50 2018/10/7
 */

@Configuration
@AutoConfigureBefore(EurekaServerAutoConfiguration.class)
public class RegionConfig {

    @Bean
    @ConditionalOnMissingBean
    public EurekaServerConfig eurekaServerConfig(EurekaClientConfig clientConfig){
        EurekaServerConfigBean serverConfigBean = new EurekaServerConfigBean();
        if (clientConfig.shouldRegisterWithEureka()){
            serverConfigBean.setRegistrySyncRetries( 5);
        }
        serverConfigBean.setRemoteRegionAppWhitelist(new HashMap<>());
        return serverConfigBean;
    }
}
