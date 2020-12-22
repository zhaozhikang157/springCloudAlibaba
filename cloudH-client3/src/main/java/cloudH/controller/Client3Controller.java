package cloudH.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class Client3Controller {

    @Value("${server.port}")
    private String PORT;

    @RequestMapping("/getClientPort")
    public String getClientPort(){
        return PORT;
    }
}
