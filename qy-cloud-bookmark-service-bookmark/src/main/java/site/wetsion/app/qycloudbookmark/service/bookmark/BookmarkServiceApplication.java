package site.wetsion.app.qycloudbookmark.service.bookmark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import site.wetsion.app.qycloudbookmark.common.security.annotation.EnableQyAppResourceServer;

/**
 * 书签服务
 *
 * @author weixin
 * @version 1.0
 * @date 2020/3/30 3:28 PM
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableQyAppResourceServer
@EnableElasticsearchRepositories("site.wetsion.app.qycloudbookmark.common.bigdata.dao")
public class BookmarkServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookmarkServiceApplication.class, args);
    }
}
