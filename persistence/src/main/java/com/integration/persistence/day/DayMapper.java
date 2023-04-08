package com.integration.persistence.day;

import com.integration.models.day.Day;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DayMapper implements RowMapper<Day> {
    @Override
    public Day mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("day_id");
        String code = rs.getString("code");
        String description = rs.getString("description");
        return new Day(id, code, description);
    }
}
