package site.wetsion.app.qycloudbookmark.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * web security
 *
 * Created by wetsion on 2020/3/24.
 */
@Configuration
@EnableWebSecurity
public class AuthWebSecurityConfig extends WebSecurityConfigurerAdapter {
}
