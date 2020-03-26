package site.wetsion.app.qycloudbookmark.gateway.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import site.wetsion.app.qycloudbookmark.gateway.handler.HystrixFallbackHandlerFunction;

/**
 * webflux 下定义 route路由，类似Springweb的requestmapping
 *
 * Created by wetsion on 2020/3/27.
 */
@Slf4j
@Configuration
@AllArgsConstructor
public class WebfluxRouteConfig {

    private HystrixFallbackHandlerFunction hystrixFallbackHandlerFunction;

    @Bean
    public RouterFunction routerFunction() {
        return RouterFunctions.route(
                RequestPredicates.path("/fallback")
                        .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                hystrixFallbackHandlerFunction);
    }
}
