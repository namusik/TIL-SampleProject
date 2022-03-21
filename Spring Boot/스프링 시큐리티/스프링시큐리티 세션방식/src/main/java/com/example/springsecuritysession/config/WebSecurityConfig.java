package com.example.springsecuritysession.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //스프링시큐리티 앞단 설정 해주는 곳.
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    //스프링시큐리티의 설정을 해주는 곳
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //URL 인증여부 설정.
        http.authorizeRequests()
                .antMatchers("/css/**", "/images/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated();

        //로그인 관련 설정.
        http.formLogin()
                .loginPage("/user/login")
                .loginProcessingUrl("/user/login")
                .defaultSuccessUrl("/")
                .failureUrl("/user/login?error")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/")
                .permitAll();
    }
}
