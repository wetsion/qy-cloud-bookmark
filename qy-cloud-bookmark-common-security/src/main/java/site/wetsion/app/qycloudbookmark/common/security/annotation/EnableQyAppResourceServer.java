package site.wetsion.app.qycloudbookmark.common.security.annotation;

import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import site.wetsion.app.qycloudbookmark.common.security.config.CommonSecurityConfig;
import site.wetsion.app.qycloudbookmark.common.security.config.CustomerResourceServerConfig;

import java.lang.annotation.*;

/**
 * 自定义封装资源服务器配置，供子服务使用<br/>
 *
 * 同时使用后将会扫描common-security包，注册组件bean
 *
 * @author weixin
 * @version 1.0
 * @date 2020/3/26 3:59 PM
 */
@Documented
@Inherited
@EnableResourceServer
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({CommonSecurityConfig.class, CustomerResourceServerConfig.class})
public @interface EnableQyAppResourceServer {
}
