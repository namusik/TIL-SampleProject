package com.example.multipledatasource.config;

import com.example.multipledatasource.model.Todo;
import com.example.multipledatasource.model.Topic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackageClasses = Topic.class,
        entityManagerFactoryRef = "topicEntityManagerFactory",
        transactionManagerRef = "topicTransactionManager")
public class TopicDataSourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.topics")
    public DataSourceProperties topicDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource topicDataSource() {
        return topicDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean topicEntityManagerFactory (@Qualifier("topicDataSource") DataSource dataSource, EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages(Topic.class)
                .build();
    }

    @Bean
    public PlatformTransactionManager topicTransactionManager(@Qualifier("topicEntityManagerFactory") LocalContainerEntityManagerFactoryBean topicEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(topicEntityManagerFactory.getObject()));
    }
}
