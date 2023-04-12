package com.kuzin.integration.persistence.exercise;

import com.kuzin.integration.models.exercise.Exercise;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExerciseProgramMapper implements RowMapper<Exercise> {
    @Override
    public Exercise mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("program_exercise_id");
        String description = rs.getString("description");
        String groupTag = rs.getString("group_tag");

        return new Exercise(id, description, groupTag);
    }
}
