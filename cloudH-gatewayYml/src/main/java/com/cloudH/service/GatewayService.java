package com.cloudH.service;

import com.cloudH.dto.GatewayConfigDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.*;

@Service
@Slf4j
public class GatewayService implements ApplicationEventPublisherAware {


    @Autowired
    ApplicationEventPublisher publisher;
    @Autowired
    RouteDefinitionWriter routeDefinitionWriter;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    /**
     * 查询数据库更新route
     * @return
     */
    public void updRoute(){
        List<PredicateDefinition> predicates = new ArrayList<PredicateDefinition>();
        PredicateDefinition predicate = new PredicateDefinition();
        Map<String,String> predicateParams = new HashMap<>();
        predicate.setName("Path");
        //路由转发地址
        predicateParams.put("pattern","/client/**");
        predicate.setArgs(predicateParams);
        predicates.add(predicate);

        PredicateDefinition predicate1 = new PredicateDefinition();
        Map<String,String> predicateParams1 = new HashMap<>();
        predicate1.setName("Query");
        predicateParams1.put("_genkey_0","version");
        predicateParams1.put("_genkey_1","1");
        predicate1.setArgs(predicateParams1);
        predicates.add(predicate1);

        List<FilterDefinition> filterDefinitions = new ArrayList<FilterDefinition>();
        FilterDefinition filterDefinition = new FilterDefinition();
        Map<String,String> filterParams = new HashMap<>();
        //名称是固定的，路径去前缀
        filterDefinition.setName("StripPrefix");
//        filterParams.put("_genkey_0","1");
        filterDefinition.setArgs(filterParams);
        filterDefinitions.add(filterDefinition);


        String isOpen = "0";
        List<GatewayConfigDto> gatewayConfigDtos = new ArrayList<GatewayConfigDto>();
        GatewayConfigDto gatewayConfigDto = new GatewayConfigDto();
        gatewayConfigDto.setFilterList(filterDefinitions);
        gatewayConfigDto.setPredicateList(predicates);
        gatewayConfigDto.setIsOpen(isOpen);

        gatewayConfigDto.setFromUriString("lb://client1/");

        gatewayConfigDtos.add(gatewayConfigDto);

        for(GatewayConfigDto gatewayConfigDto1 : gatewayConfigDtos){
            loadRoute(gatewayConfigDto1);
        }

    }

    /**
     * 更新route
     * @return
     */
    public String loadRoute(GatewayConfigDto gatewayConfigDto){
        RouteDefinition definition = new RouteDefinition();
        URI uri = UriComponentsBuilder.fromUriString(gatewayConfigDto.getFromUriString()).build().toUri();
        definition.setPredicates(gatewayConfigDto.getPredicateList());
        definition.setFilters(gatewayConfigDto.getFilterList());
        definition.setUri(uri);

        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
        return "success";
    }
}
