package com.integration.persistence.testcontainers;

import com.integration.models.day.Day;
import com.integration.persistence.day.DayAdapter;
import org.junit.ClassRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.Assert.assertEquals;

@DataJdbcTest
@RunWith(SpringRunner.class)
@EnableAutoConfiguration()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {DayAdapter.class, FlywayConfig.class})
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

        assertEquals(dayList, dayAdapter.getDays());
    }
}
