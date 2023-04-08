package com.integration.persistence.exercise;

import com.integration.models.exercise.Exercise;
import com.integration.models.exercise.ExerciseUpdateRequest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExerciseAdapter {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ExerciseAdapter(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void addExercise(ExerciseUpdateRequest updateRequest) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("description", updateRequest.exerciseDescription());
        map.addValue("group_tag", updateRequest.muscleGroupId());

        String sqlRequest = """
                INSERT INTO exercise (description, group_tag) values
                (:description, :group_tag)
                """;

        namedParameterJdbcTemplate.update(sqlRequest, map);
    }

    public void updateExercise(int exerciseId, ExerciseUpdateRequest updateRequest) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("description", updateRequest.exerciseDescription());
        map.addValue("group_tag", updateRequest.muscleGroupId());
        map.addValue("exercise_id", exerciseId);

        String sqlRequest = """
                UPDATE exercise set description = :description , group_tag = :group_tag
                where exercise_id = :exercise_id
                """;

        namedParameterJdbcTemplate.update(sqlRequest, map);
    }

    public void deleteExercise(int exerciseId) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("exercise_id", exerciseId);

        String sqlRequest = """
                DELETE FROM exercise WHERE exercise_id = :exercise_id
                """;

        namedParameterJdbcTemplate.update(sqlRequest, map);
    }

    public Exercise getExercise(int exerciseId) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("exercise_id", exerciseId);

        String sqlRequest = """
                SELECT exercise_id, description, group_name
                FROM exercise
                INNER JOIN muscle_group mg on mg.group_id = exercise.group_tag
                WHERE exercise_id = :exercise_id
                """;

        return namedParameterJdbcTemplate.queryForObject(sqlRequest, map, new ExerciseMapper());
    }

    public List<Exercise> getALLExercise() {
        String sqlRequest = """
                SELECT exercise_id, description, group_name
                FROM exercise
                INNER JOIN muscle_group mg on mg.group_id = exercise.group_tag
                """;

        return namedParameterJdbcTemplate.query(sqlRequest, new ExerciseMapper());
    }
}
