package com.example.multipledatasource.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.write")
    public DataSource writeDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.read")
    public DataSource readOnlyDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public DataSource routingDataSource(@Qualifier("writeDataSource") DataSource writeDataSource, @Qualifier("readOnlyDataSource") DataSource readOnlyDataSource) {
        MultiRoutingDataSource multiRoutingDataSource = new MultiRoutingDataSource();
        HashMap<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put("writeDataSource", writeDataSource);
        dataSourceMap.put("readOnlyDataSource", readOnlyDataSource);
        multiRoutingDataSource.setDefaultTargetDataSource(writeDataSource);
        multiRoutingDataSource.setTargetDataSources(dataSourceMap);
        return multiRoutingDataSource;
    }
}
