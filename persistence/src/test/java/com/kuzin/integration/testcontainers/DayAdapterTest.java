package com.kuzin.integration.testcontainers;

import com.kuzin.integration.TestApplication;
import com.kuzin.integration.models.day.Day;
import com.kuzin.integration.persistence.day.DayAdapter;
import org.junit.ClassRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.util.List;

@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {DbTestConfig.class, TestApplication.class})
@Testcontainers
public class DayAdapterTest {
    @ClassRule
    @Container
    public static PostgreSQLContainer<DbContainer> postgresSQLContainer = DbContainer.getInstance();

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUp() {
        dayAdapter = new DayAdapter(jdbcTemplate);
    }

    private DayAdapter dayAdapter;

    @Test
    public void shouldReturnDays() {

        List<Day> dayList = List.of(new Day(1, "MN", "Monday"));

        Assertions.assertEquals(dayList, dayAdapter.getDays());
    }
}
