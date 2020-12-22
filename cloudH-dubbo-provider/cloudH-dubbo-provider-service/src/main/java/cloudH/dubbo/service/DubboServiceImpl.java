package cloudH.dubbo.service;

import cloudH.dubbo.api.service.DubboApiService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;

@Service
public class DubboServiceImpl implements DubboApiService {


    private String PORT = "0";

    @Override
    public String getPort() {
        return PORT;
    }
}
