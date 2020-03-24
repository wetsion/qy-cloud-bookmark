package site.wetsion.app.qycloudbookmark.service.system.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * Created by wetsion on 2020/3/25.
 */
@Configuration
@EnableOAuth2Sso
public class AuthConfig extends WebSecurityConfigurerAdapter {
}
