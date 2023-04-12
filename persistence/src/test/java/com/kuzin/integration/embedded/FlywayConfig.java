package com.kuzin.integration.embedded;

import org.flywaydb.core.Flyway;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@TestConfiguration
public class FlywayConfig {
    @Bean(initMethod = "migrate")
    public Flyway flyway(DataSource database) {
        return Flyway.configure()
                .dataSource(database)
                .outOfOrder(true)
                .locations("classpath:/embedded/db/flyway")
                .cleanDisabled(true)
                .baselineOnMigrate(true)
                .load();
    }
}
