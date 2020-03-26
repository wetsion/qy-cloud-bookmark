package site.wetsion.app.qycloudbookmark.common.security.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import site.wetsion.app.qycloudbookmark.common.security.annotation.InnerInvokeApi;
import site.wetsion.app.qycloudbookmark.common.security.bean.AuthUserAuthenticationConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 用于子服务开启资源服务，即api接口资源，保护API接口
 *
 * Created by wetsion on 2020/3/24.
 */
@Slf4j
@AllArgsConstructor
@ComponentScan("site.wetsion.app.qycloudbookmark.common.security")
public class CustomerResourceServerConfig extends ResourceServerConfigurerAdapter implements ApplicationContextAware {

    private AuthenticationEntryPoint appServiceAuthenticationEntryPoint;

    private AccessDeniedHandler appServiceAccessDeniedHandler;

    private RemoteTokenServices remoteTokenServices;

    private RestTemplate lbRestTemplate;

    private ApplicationContext applicationContext;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry =
                http.authorizeRequests();
        getNonAuthenticateUrls().forEach(url -> {
            registry.antMatchers(url).permitAll();
        });
        registry
                .anyRequest().authenticated()
//                .antMatchers("/api/announcement/list").authenticated()
                .and().csrf().disable()
        ;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        DefaultAccessTokenConverter defaultAccessTokenConverter =
                new DefaultAccessTokenConverter();
        AuthUserAuthenticationConverter authUserAuthenticationConverter =
                new AuthUserAuthenticationConverter();
        defaultAccessTokenConverter.setUserTokenConverter(authUserAuthenticationConverter);
        remoteTokenServices.setAccessTokenConverter(defaultAccessTokenConverter);
        remoteTokenServices.setRestTemplate(lbRestTemplate);
        resources
                .authenticationEntryPoint(appServiceAuthenticationEntryPoint)
                .accessDeniedHandler(appServiceAccessDeniedHandler)
                .tokenServices(remoteTokenServices)
        ;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 获取不用鉴权的URL，例如内部服务调用的URL
     *
     * @author weixin
     * @date 8:23 PM 2020/3/26
     * @return java.util.List<java.lang.String>
     **/
    private List<String> getNonAuthenticateUrls() {
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();

        List<String> urls = new ArrayList<>();
        map.keySet().forEach(info -> {
            HandlerMethod handlerMethod = map.get(info);

            // 获取方法上边的注解 替代path variable 为 *
            InnerInvokeApi method = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), InnerInvokeApi.class);
            Optional.ofNullable(method)
                    .ifPresent(inner -> info.getPatternsCondition().getPatterns()
                            .forEach(url -> urls.add(url.replaceAll("\\{(.*?)\\}", "*"))));
        });
        log.info("不用鉴权的URL : {}", urls.toArray());
        return urls;
    }
}
