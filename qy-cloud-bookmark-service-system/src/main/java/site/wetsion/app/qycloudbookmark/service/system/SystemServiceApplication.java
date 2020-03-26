package site.wetsion.app.qycloudbookmark.service.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import site.wetsion.app.qycloudbookmark.common.security.annotation.EnableQyAppResourceServer;

/**
 * 系统服务
 *
 * Created by wetsion on 2020/3/18.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableQyAppResourceServer
public class SystemServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemServiceApplication.class, args);
    }
}
