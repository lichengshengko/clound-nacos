package com.cloud.demo.filter;

import com.fasterxml.jackson.core.filter.TokenFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthFilter implements GlobalFilter, Ordered {

    Logger logger = LoggerFactory.getLogger(TokenFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst("token");
        if (token == null || token.isEmpty()) {
            logger.info("token is empty...");
            //exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            //不合法(响应未登录的异常)
            ServerHttpResponse response = exchange.getResponse();
            //设置headers
            HttpHeaders httpHeaders = response.getHeaders();
            httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
            httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");

            //设置body
            String warningStr = "未授权的请求，请登录";
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("code", 20000);
//            jsonObject.put("msg", "未授权的请求，请登录");
            DataBuffer bodyDataBuffer = response.bufferFactory().wrap(warningStr.getBytes());
            return response.writeWith(Mono.just(bodyDataBuffer));
            //return exchange.getResponse().setComplete();
        }
        String[] str = new String[2];
        str[0] = "1";
        str[1] = "2";
        ServerHttpRequest request = exchange.getRequest().mutate()
                .header("userId", str)
                .build();
        return chain.filter(exchange.mutate().request(request).build());
    }

    @Override
    public int getOrder() {
        return -100;
    }
}
