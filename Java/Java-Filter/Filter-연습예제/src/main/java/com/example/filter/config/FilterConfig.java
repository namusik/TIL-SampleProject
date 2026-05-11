package com.example.filter.config;

import com.example.filter.filter.FirstFilter;
import com.example.filter.filter.SecondFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<FirstFilter> firstfilter() {
        FilterRegistrationBean<FirstFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new FirstFilter());
        registrationBean.addUrlPatterns("/api/filter");
        registrationBean.setOrder(1);
        registrationBean.setName("firstfilter");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<SecondFilter> secondfilter() {
        FilterRegistrationBean<SecondFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new SecondFilter());
        registrationBean.addUrlPatterns("/api/filter");
        registrationBean.setOrder(2);
        registrationBean.setName("secondfilter");
        return registrationBean;
    }
}
