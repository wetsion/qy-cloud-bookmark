package site.wetsion.app.qycloudbookmark.service.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * 系统服务
 *
 * Created by wetsion on 2020/3/18.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
//@EnableWebSecurity
//@EnableOAuth2Sso
public class SystemServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemServiceApplication.class, args);
    }
}
