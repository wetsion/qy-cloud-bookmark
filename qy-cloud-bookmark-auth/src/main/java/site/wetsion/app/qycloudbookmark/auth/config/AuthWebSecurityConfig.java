package site.wetsion.app.qycloudbookmark.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // httpBasic 弹窗登录
//        http.httpBasic();
        http.authorizeRequests()
//                .antMatchers("/oauth/**", "/auth/**", "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
        http.formLogin();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
