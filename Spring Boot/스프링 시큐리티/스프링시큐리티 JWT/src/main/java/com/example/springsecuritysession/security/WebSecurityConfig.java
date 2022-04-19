package com.example.springsecuritysession.security;

import com.example.springsecuritysession.exception.CustomAuthenticationEntryPoint;
import com.example.springsecuritysession.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

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
        //프론트엔드가 별도로 존재하여 rest Api로 구성한다고 가정
        http.httpBasic().disable();

        //csrf 사용안함
        http.csrf().disable();

        //세선사용 x
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //URL 인증여부 설정.
        http.authorizeRequests()
                .antMatchers( "/user/signup", "/", "/user/login", "/css/**").permitAll()
                .anyRequest().authenticated();


        //비인가자 요청시 보낼 Api URI
        http.exceptionHandling().accessDeniedPage("/forbidden");

        //JwtFilter 추가
        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        //exception handling
        http.exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint());

    }
}
