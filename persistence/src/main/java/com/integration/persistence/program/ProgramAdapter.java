package com.integration.persistence.program;

import com.integration.models.program.ProgramDeletingRequest;
import com.integration.models.program.ProgramSavingRequest;
import com.integration.models.program.ProgramShortInfo;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProgramAdapter {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ProgramAdapter(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public int createNewProgram(ProgramSavingRequest request) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("owner_email", request.ownerEmail());
        map.addValue("program_name", request.name());

        String sqlRequest = """
                INSERT INTO program (owner_email, program_name) values
                (:owner_email, :program_name) returning id
                """;

        return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(sqlRequest, map, Integer.class))
                .orElseThrow();
    }

    public void deleteUserProgram(ProgramDeletingRequest deletingRequest) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("owner_email", deletingRequest.ownerEmail());
        map.addValue("id", deletingRequest.programId());

        String sqlRequest = """
                DELETE FROM program WHERE id = :id and owner_email = :owner_email
                """;

        namedParameterJdbcTemplate.update(sqlRequest, map);
    }

    public List<ProgramShortInfo> getOwnerProgram(String ownerEmail) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("owner_email",ownerEmail);

        String sqlRequest = """
                SELECT * FROM program WHERE owner_email = :owner_email
                """;

        return namedParameterJdbcTemplate.query(sqlRequest, map, new ProgramMapper());
    }
}
