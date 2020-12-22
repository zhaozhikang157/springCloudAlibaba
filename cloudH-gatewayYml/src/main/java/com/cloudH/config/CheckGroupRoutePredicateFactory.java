package com.cloudH.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

@Component
public class CheckGroupRoutePredicateFactory extends AbstractRoutePredicateFactory<CheckGroupRoutePredicateFactory.Config> {

    public CheckGroupRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {

        Map<String,String> map = new HashMap();
        //先写死，后续可以从配置中心读取，意思是叫client的组有三个，用于后续取模
        map.put("client","3");
        // 写法1
        return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                //获取路径后缀
                String version = serverWebExchange.getRequest().getQueryParams().getFirst("version");
                if(StringUtils.isBlank(version)){
                    return false;
                }

                //传过来的组
                int versionNum = 0;
                //配置的服务当前属于几组
                int configVersionNum = 0;
                //总共几个组
                int GroupNum = 0;
                try{
                    versionNum = Integer.valueOf(version);
                    GroupNum = Integer.valueOf(map.get(config.getGroupName()));
                    configVersionNum = Integer.valueOf(config.getVersion());
                }catch(NumberFormatException e){
                    return false;
                }

                //取模运算
                if(versionNum % GroupNum == configVersionNum){
                    return true;
                }
                return false;
            }
        };
    }

    @Validated
    public static class Config{

        @NotEmpty
        private String version;
        @NotEmpty
        private String groupName;

        public String getVersion(){
            return version;
        }

        public void setVersion(String version){
            this.version=version;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }
    }
}
