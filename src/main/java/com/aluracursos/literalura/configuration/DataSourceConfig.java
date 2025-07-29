package com.aluracursos.literalura.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {
    @Bean
    public HikariDataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/literalura");
        dataSource.setUsername("postgres");
        dataSource.setPassword("123");
        dataSource.setDriverClassName("org.postgresql.Driver");
        return dataSource;
    }
}
