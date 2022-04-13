package com.example.springsecuritysession.security;

import com.example.springsecuritysession.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Bean
    public BCryptPasswordEncoder encoderPassword() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //csrf 사용안함
        http.csrf().disable();

        //URL 인증여부 설정.
        http.authorizeRequests()
                .antMatchers( "/user/signup", "/", "/user/login", "/css/**").permitAll()
                //@Secured("ROLE_ADMIN")으로 대체
//                .antMatchers("/api/admin").hasRole("ADMIN")
                .anyRequest().authenticated();

        //로그인 관련 설정.
        http.formLogin()
                .loginPage("/user/login")
                .loginProcessingUrl("/user/login") //Post 요청
                .defaultSuccessUrl("/")
                .failureUrl("/user/login?error")
                .permitAll();

        //로그아웃 설정
        http.logout()
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/");

        //비인가자 요청시 보낼 Api URI
        http.exceptionHandling().accessDeniedPage("/forbidden");

    }
}
