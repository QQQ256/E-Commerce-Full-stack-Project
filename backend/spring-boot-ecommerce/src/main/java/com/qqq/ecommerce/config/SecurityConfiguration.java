package com.qqq.ecommerce.config;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Created by QQQ on 2022/8/8 15:08
 */
@Configuration
@EnableWebSecurity//detect HttpSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("/api/orders/**")
                .authenticated()
                .and()
                .oauth2ResourceServer()//配置oauth2 resource serve support
                .jwt();//enable JWT-encoded bearer token support

        //add cors filters
        http.cors();

        //为401 Unauthorized 写一个response body

        Okta.configureResourceServer401ResponseBody(http);

        //disable CSRF 因为我们不使用Cookies for session tracking
        http.csrf().disable();
    }


    //    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        //对/api/**进行防护
//        http.authorizeHttpRequests()
//                .antMatchers("/api/orders/**")
//                .authenticated()
//                .and()
//                .oauth2ResourceServer()//配置oauth2 resource serve support
//                .jwt();//enable JWT-encoded bearer token support
//
//        //add cors filters
//        http.cors();
//
//        //为401 Unauthorized 写一个response body
//
////        Okta.configureResourceServer401ResponseBody(http);
//
//        return http.build();
//    }
}
