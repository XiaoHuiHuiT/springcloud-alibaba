package cn.ddossec.factory;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * @author 30315
 * @title: LogStatusGatewayFilterFactory
 * @projectName spring-cloud-alibaba-parent
 * @description: TODO
 * @date 2020-04-1022:03
 */
@Component
public class LogStatusGatewayFilterFactory extends AbstractGatewayFilterFactory<LogStatusGatewayFilterFactory.Config> {
    public LogStatusGatewayFilterFactory() {
        super(LogStatusGatewayFilterFactory.Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("cacheStatus", "consoleStatus");
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                if (config.getCacheStatus())
                    System.out.println("缓存状态");
                if (config.getConsoleStatus())
                    System.out.println("控制台状态");
                return chain.filter(exchange);
            }
        };
    }

    @Validated
    public static class Config {

        private Boolean cacheStatus;
        private Boolean consoleStatus;

        public Boolean getCacheStatus() {
            return cacheStatus;
        }

        public void setCacheStatus(Boolean cacheStatus) {
            this.cacheStatus = cacheStatus;
        }

        public Boolean getConsoleStatus() {
            return consoleStatus;
        }

        public void setConsoleStatus(Boolean consoleStatus) {
            this.consoleStatus = consoleStatus;
        }
    }
}
