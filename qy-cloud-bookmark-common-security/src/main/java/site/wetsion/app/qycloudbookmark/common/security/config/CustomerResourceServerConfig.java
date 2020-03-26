package site.wetsion.app.qycloudbookmark.common.security.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.client.RestTemplate;
import site.wetsion.app.qycloudbookmark.common.security.bean.AuthUserAuthenticationConverter;

/**
 * 用于子服务开启资源服务，即api接口资源，保护API接口
 *
 * Created by wetsion on 2020/3/24.
 */
@AllArgsConstructor
@ComponentScan("site.wetsion.app.qycloudbookmark.common.security")
public class CustomerResourceServerConfig extends ResourceServerConfigurerAdapter {

    private AuthenticationEntryPoint appServiceAuthenticationEntryPoint;

    private AccessDeniedHandler appServiceAccessDeniedHandler;

    private RemoteTokenServices remoteTokenServices;

    private RestTemplate lbRestTemplate;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
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
}
