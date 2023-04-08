package com.integration.persistence.exercise;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ExerciseAdapter {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ExerciseAdapter(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void addExercise(String description) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("description", description);

        String sqlRequest = """
                INSERT INTO exercise (description) values
                (:description)
                """;

        namedParameterJdbcTemplate.update(sqlRequest, map);
    }
}
