package site.wetsion.app.qycloudbookmark.service.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import site.wetsion.app.qycloudbookmark.common.security.annotation.EnableQyAppResourceServer;

/**
 * 用户服务应用
 *
 * Created by wetsion on 2020/3/16.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableQyAppResourceServer
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
