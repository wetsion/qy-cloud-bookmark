package site.wetsion.app.qycloudbookmark.auth.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import site.wetsion.app.qycloudbookmark.auth.service.AppClientDetailsService;
import site.wetsion.app.qycloudbookmark.common.constant.AuthConstant;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证服务配置
 *
 * @author weixin
 * Created by wetsion on 2020/3/23.
 */
@AllArgsConstructor
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private DataSource dataSource;

    private RedisConnectionFactory redisConnectionFactory;

    private AuthenticationManager authenticationManager;

    private UserDetailsService appUserDetailsService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        AppClientDetailsService appClientDetailsService = new AppClientDetailsService(dataSource);
        appClientDetailsService.setSelectClientDetailsSql(AuthConstant.DEFAULT_SELECT_STATEMENT);
        appClientDetailsService.setFindClientDetailsSql(AuthConstant.DEFAULT_FIND_STATEMENT);
        clients.withClientDetails(appClientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients()
                .checkTokenAccess("isAuthenticated()")
                .tokenKeyAccess("permitAll()");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(appUserDetailsService)
                .tokenStore(tokenStore())
                .reuseRefreshTokens(false)
                .tokenEnhancer(tokenEnhancer())
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    }

    /**
     * 自定义redis方式的token存储器
     *
     * @author weixin
     * @date 11:35 AM 2020/3/24
     * @return org.springframework.security.oauth2.provider.token.TokenStore
     **/
    @Bean
    public TokenStore tokenStore() {
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        tokenStore.setPrefix(AuthConstant.AUTH_PREFIX);
        return tokenStore;
    }


    /**
     * 在token被存储前进行增强
     *
     * @author weixin
     * @date 3:24 PM 2020/3/24
     * @return org.springframework.security.oauth2.provider.token.TokenEnhancer
     **/
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            final Map<String, Object> additionalInfo = new HashMap<>(1);
            additionalInfo.put("license", AuthConstant.LICENSE);
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            return accessToken;
        };
    }
}
