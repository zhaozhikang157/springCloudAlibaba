package com.cloudH.controller;

import com.cloudH.service.GatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gatewayCon")
public class GatewayController {

    @Autowired
    GatewayService gatewayService;

    @RequestMapping("add")
    public String addRoute(){
        return gatewayService.loadRoute(null);
    }
}
