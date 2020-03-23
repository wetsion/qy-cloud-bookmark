package site.wetsion.app.qycloudbookmark.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * auth 认证服务
 *
 * Created by wetsion on 2020/3/23.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("site.wetsion.app.qycloudbookmark.api")
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
