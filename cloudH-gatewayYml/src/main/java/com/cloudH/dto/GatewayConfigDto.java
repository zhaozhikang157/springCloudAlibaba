package com.cloudH.dto;

import lombok.Data;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;

import java.io.Serializable;
import java.util.List;

@Data
public class GatewayConfigDto implements Serializable {

    /**
     * Path 路由规则
     */
    private List<PredicateDefinition> predicateList;

    /**
     * 路径去前缀 等
     */
    private List<FilterDefinition> filterList;

    /**
     * 注册中心路径
     */
    String fromUriString;

    /**
     * 开关
     */
    private String isOpen;
}
