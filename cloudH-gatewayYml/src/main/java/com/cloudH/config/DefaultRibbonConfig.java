package com.cloudH.config;

import com.netflix.loadbalancer.IRule;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
@Configuration
@RibbonClients(
        value = {
                @RibbonClient(name = "gatewayRule", configuration = CustomLoadBalancerClientFilter.class),
        },
        defaultConfiguration = DefaultRibbonConfig.class)
public class DefaultRibbonConfig {

    @Bean
    public IRule ribbonRule() {
        return new CustomLoadBalancerClientFilter();
    }
}*/
