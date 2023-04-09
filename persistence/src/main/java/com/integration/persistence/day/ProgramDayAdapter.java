package com.integration.persistence.day;

import com.integration.models.day.ProgramDay;
import com.integration.models.day.ProgramDayRequest;
import com.integration.util.ValidationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class ProgramDayAdapter {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ProgramDayAdapter(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public int addProgramDay(ProgramDayRequest programDay) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("program_id", programDay.programId());
        map.addValue("day_id", programDay.dayId());
        map.addValue("training_status", programDay.trainingStatus());

        String sqlRequest = """
                INSERT INTO program_day (program_id, day_id, training_status)
                values (:program_id, :day_id, :training_status)
                RETURNING program_day_id
                """;

        try {
            return Optional.ofNullable(
                    namedParameterJdbcTemplate.queryForObject(sqlRequest, map, Integer.class))
                    .orElseThrow();
        } catch (DuplicateKeyException e) {
            throw new ValidationException("Your program may have only one day of each type");
        }
    }

    public void updateProgramDay(int programDayId, ProgramDayRequest programDay) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("program_id", programDay.programId());
        map.addValue("day_id", programDay.dayId());
        map.addValue("training_status", programDay.trainingStatus());
        map.addValue("program_day_id", programDayId);

        String sqlRequest = """
                UPDATE program_day set program_id = :program_id , day_id = :day_id,
                training_status = :training_status
                WHERE program_day_id = :program_day_id
                """;

        namedParameterJdbcTemplate.update(sqlRequest, map);
    }

    public void deleteProgramDay(int programDayId) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("program_day_id", programDayId);

        String sqlRequest = """
                DELETE FROM program_day WHERE program_day_id = :program_day_id
                """;

        namedParameterJdbcTemplate.update(sqlRequest, map);
    }

    public ProgramDay getProgramDay(int programDayId) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("program_day_id", programDayId);

        String sqlRequest = """
                SELECT program_day_id, program_name, description, training_status
                FROM program_day
                INNER JOIN program pg on program_day.program_id = pg.id
                INNER JOIN day dy on program_day.day_id = dy.day_id
                WHERE program_day_id = :program_day_id
                """;

        return namedParameterJdbcTemplate.queryForObject(sqlRequest, map, new ProgramDayMapper());
    }

    public List<ProgramDay> getALLProgramDay(int programId) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("program_id", programId);

        String sqlRequest = """
                SELECT program_day_id, program_name, description, training_status
                FROM program_day
                INNER JOIN program pg on program_day.program_id = pg.id
                INNER JOIN day dy on program_day.day_id = dy.day_id
                WHERE program_id = :program_id
                """;

        return namedParameterJdbcTemplate.query(sqlRequest, map, new ProgramDayMapper());
    }
}
