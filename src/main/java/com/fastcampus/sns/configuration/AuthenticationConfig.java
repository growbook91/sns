package com.fastcampus.sns.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class AuthenticationConfig extends WebSecurityConfigurerAdapter {
    //여기서 security config
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                // 이 아래 것은 다 허용하는 것.
                .antMatchers("/api/*/users/join", "/api/*/users/login").permitAll()
                // 이건 허용아님.
                .antMatchers("api/**").authenticated()
                .and()
                .sessionManagement()
                // 뭐 이건 세션 관리 안하는 거라는데..?
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ;
                // TODO
//                .exceptionHandling()
//                .authenticationEntryPoint()
    }



}
