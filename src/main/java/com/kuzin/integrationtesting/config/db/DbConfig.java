package com.kuzin.integrationtesting.config.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class DbConfig {
    @Bean
    public HikariDataSource dataSource(
            @Value("${it.datasource.username}") String username,
            @Value("${it.datasource.password}") String password,
            @Value("${it.datasource.url}") String url,
            @Value("${it.datasource.hikari.maximum-pool-size}") int maxPoolSize,
            @Value("${it.datasource.hikari.idle-timeout}") int idleTimeout,
            @Value("${it.datasource.driverClassName}") String driver
    ) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driver);
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setMaximumPoolSize(maxPoolSize);
        hikariConfig.setMinimumIdle(0);
        hikariConfig.setIdleTimeout(idleTimeout);

        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public NamedParameterJdbcTemplate researchCenterNamedJdbc(HikariDataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean(initMethod = "migrate")
    public Flyway flyway(HikariDataSource dataSource) {
        return Flyway.configure()
                .dataSource(dataSource)
                .outOfOrder(true)
                .locations("classpath:/db/flyway")
                .cleanDisabled(true)
                .baselineOnMigrate(true)
                .load();
    }
}
