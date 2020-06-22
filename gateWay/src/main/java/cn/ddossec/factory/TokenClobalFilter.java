package cn.ddossec.factory;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author 30315
 * @title: TokenClobalFilter
 * @projectName spring-cloud-alibaba-parent
 * @description: TODO
 * @date 2020-04-1022:17
 */
@Component
public class TokenClobalFilter implements GlobalFilter, Ordered {

    /**
     * 过滤器执行的顺序
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }

    /**
     * 执行过滤器逻辑
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        if(!"admin".equals(token)){
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }
}
