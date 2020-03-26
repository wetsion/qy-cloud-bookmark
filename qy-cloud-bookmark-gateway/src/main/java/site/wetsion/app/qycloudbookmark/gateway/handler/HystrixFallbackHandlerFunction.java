package site.wetsion.app.qycloudbookmark.gateway.handler;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import site.wetsion.app.qycloudbookmark.common.util.R;

import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * 用于处理webflux路由: /fallback 的请求<br/>
 *
 * Created by wetsion on 2020/3/27.
 */
@Slf4j
@Component
public class HystrixFallbackHandlerFunction implements HandlerFunction<ServerResponse> {
    @Override
    public Mono<ServerResponse> handle(ServerRequest request) {
        // 获取请求的URL
        Optional<Object> originUrlOptional =
                request.attribute(ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
        originUrlOptional.ifPresent(url -> {
            log.error("gateway 请求URL: {} 失败, 服务降级", url);
        });
        return ServerResponse
                .status(HttpStatus.SERVICE_UNAVAILABLE.value())
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(R.fail("服务器异常")));
    }
}
