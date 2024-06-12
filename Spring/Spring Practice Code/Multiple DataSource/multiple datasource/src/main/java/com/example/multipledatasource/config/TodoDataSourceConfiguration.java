package com.example.multipledatasource.config;

import com.example.multipledatasource.model.Todo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
        basePackageClasses = Todo.class,
        entityManagerFactoryRef = "todosEntityManagerFactory",
        transactionManagerRef = "todosTransactionManager")
public class TodoDataSourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.todos")
    public DataSourceProperties todosDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource todosDataSource() {
        return todosDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean todosEntityManagerFactory(@Qualifier("todosDataSource") DataSource dataSource, EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages(Todo.class)
                .build();
    }

    @Bean
    public PlatformTransactionManager todosTransactionManager(@Qualifier("todosEntityManagerFactory") LocalContainerEntityManagerFactoryBean todosEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(todosEntityManagerFactory.getObject()));
    }
}
