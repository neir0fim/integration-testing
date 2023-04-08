package com.integration.persistence.exercise;

import com.integration.models.exercise.Exercise;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExerciseMapper implements RowMapper<Exercise> {
    @Override
    public Exercise mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("exercise_id");
        String description = rs.getString("description");
        String groupTag = rs.getString("group_name");

        return new Exercise(id, description, groupTag);
    }
}
