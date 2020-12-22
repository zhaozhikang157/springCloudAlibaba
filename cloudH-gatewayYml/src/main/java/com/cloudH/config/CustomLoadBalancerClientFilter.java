//package com.cloudH.config;
//
//import com.netflix.client.config.IClientConfig;
//import com.netflix.loadbalancer.AbstractLoadBalancerRule;
//import com.netflix.loadbalancer.Server;
//import lombok.extern.slf4j.Slf4j;
//
//import java.util.List;
//
//@Slf4j
//public class CustomLoadBalancerClientFilter extends AbstractLoadBalancerRule {
//
//
//    @Override
//    public void initWithNiwsConfig(IClientConfig iClientConfig) {
//
//    }
//
//    @Override
//    public Server choose(Object key) {
//
//
//        List<Server> servers = this.getLoadBalancer().getReachableServers();
//        return servers.get(0);
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
