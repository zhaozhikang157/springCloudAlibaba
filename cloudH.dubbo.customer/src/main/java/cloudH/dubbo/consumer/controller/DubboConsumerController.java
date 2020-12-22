package cloudH.dubbo.consumer.controller;

import cloudH.dubbo.api.service.DubboApiService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
public class DubboConsumerController {

    @Reference
    private DubboApiService dubboApiService;

    @GetMapping("/getPort")
    public String getPort() {
        return dubboApiService.getPort();
    }
}
