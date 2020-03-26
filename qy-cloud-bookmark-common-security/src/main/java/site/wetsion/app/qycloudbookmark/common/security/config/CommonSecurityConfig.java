package site.wetsion.app.qycloudbookmark.common.security.config;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

/**
 * @author weixin
 * @version 1.0
 * @date 2020/3/26 3:55 PM
 */
@Slf4j
@Configuration
public class CommonSecurityConfig {

    /**
     * 定义一个支持负载均衡的restTemplate，用于remoteTokenService调用
     *
     * @author weixin
     * @date 7:07 PM 2020/3/26
     * @return org.springframework.web.client.RestTemplate
     **/
    @Bean
    @Primary
    @LoadBalanced
    public RestTemplate lbRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            @SneakyThrows
            public void handleError(ClientHttpResponse response) {
                if (response.getRawStatusCode() != HttpStatus.BAD_REQUEST.value()) {
                    log.error("lbRestTemplate error");
                    super.handleError(response);
                }
            }
        });
        return restTemplate;
    }
}
