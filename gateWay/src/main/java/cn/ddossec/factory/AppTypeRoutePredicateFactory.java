package cn.ddossec.factory;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
class AppTypeRoutePredicateFactory extends AbstractRoutePredicateFactory<AppTypeRoutePredicateFactory.Config> {

    //构造器
    public AppTypeRoutePredicateFactory() {
        super(AppTypeRoutePredicateFactory.Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("appType");
    }

    @Override
    public Predicate<ServerWebExchange> apply(AppTypeRoutePredicateFactory.Config config) {
        return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                //接收参数
                String app = serverWebExchange.getRequest().getQueryParams().getFirst("app");
                if (StringUtils.isNotEmpty(app)) {
                    if (config.getAppType().equals(app))
                        return true;
                    else
                        return false;
                }
                return false;
            }
        };
    }


    public static class Config {
        private String appType;

        public Config(String appType) {
            this.appType = appType;
        }

        public String getAppType() {
            return appType;
        }

        public void setAppType(String appType) {
            this.appType = appType;
        }
    }

}
