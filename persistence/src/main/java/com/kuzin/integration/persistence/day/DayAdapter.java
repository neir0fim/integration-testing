package com.kuzin.integration.persistence.day;

import com.kuzin.integration.models.day.Day;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class DayAdapter {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DayAdapter(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Day> getDays() {
        String sqlRequest = """
                SELECT * FROM day
                """;

        return namedParameterJdbcTemplate.query(sqlRequest, new DayMapper());
    }
}
