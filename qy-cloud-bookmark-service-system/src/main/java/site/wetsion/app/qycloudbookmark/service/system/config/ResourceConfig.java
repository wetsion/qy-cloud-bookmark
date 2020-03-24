package site.wetsion.app.qycloudbookmark.service.system.config;

import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 开启资源服务，api接口也是资源
 *
 * Created by wetsion on 2020/3/24.
 */
//@Configuration
//@EnableResourceServer
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/announcement/list").authenticated();
    }

}
