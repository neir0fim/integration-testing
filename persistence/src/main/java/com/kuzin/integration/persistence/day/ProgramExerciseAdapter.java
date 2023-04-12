package com.kuzin.integration.persistence.day;

import com.kuzin.integration.models.exercise.Exercise;
import com.kuzin.integration.persistence.exercise.ExerciseProgramMapper;
import com.kuzin.integration.models.exercise.ProgramExerciseRequest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class ProgramExerciseAdapter {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ProgramExerciseAdapter(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public int addNewExerciseForProgram(ProgramExerciseRequest request) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("program_day_id", request.programDayId());
        map.addValue("exercise_id", request.exerciseId());

        String sqlRequest = """
                INSERT INTO program_exercise (program_day_id, exercise_id)
                values (:program_day_id, :exercise_id)
                RETURNING program_exercise_id
                """;

        return Optional.ofNullable(
                namedParameterJdbcTemplate.queryForObject(sqlRequest, map, Integer.class))
                .orElseThrow();
    }

    public void deleteExerciseForProgram(int programExerciseId) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("program_exercise_id", programExerciseId);

        String sqlRequest = """
                DELETE FROM program_exercise
                WHERE program_exercise_id = :program_exercise_id
                """;

        namedParameterJdbcTemplate.update(sqlRequest, map);
    }

    public List<Exercise> getExerciseForDay(int programDay) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("program_day_id", programDay);

        String sqlRequest = """
                SELECT program_exercise_id, description, group_tag FROM program_exercise
                INNER JOIN exercise e on program_exercise.exercise_id = e.exercise_id
                WHERE program_day_id = :program_day_id
                """;

        return namedParameterJdbcTemplate.query(sqlRequest, map, new ExerciseProgramMapper());
    }
}