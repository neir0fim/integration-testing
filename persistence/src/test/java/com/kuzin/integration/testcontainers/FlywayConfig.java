package com.kuzin.integration.testcontainers;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@TestConfiguration
public class FlywayConfig {
    @Bean
    public HikariDataSource dataSource(
            @Value("${DB_USERNAME}") String username,
            @Value("${DB_PASSWORD}") String password,
            @Value("${DB_URL}") String url
    ) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setMinimumIdle(0);

        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public NamedParameterJdbcTemplate researchCenterNamedJdbc(HikariDataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean(initMethod = "migrate")
    public Flyway flyway(DataSource database) {
        return Flyway.configure()
                .dataSource(database)
                .outOfOrder(true)
                .locations("classpath:/testcontainers/db/flyway")
                .cleanDisabled(true)
                .baselineOnMigrate(true)
                .load();
    }
}
