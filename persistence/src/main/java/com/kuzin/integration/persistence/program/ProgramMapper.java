package com.kuzin.integration.persistence.program;

import com.kuzin.integration.models.program.ProgramShortInfo;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProgramMapper implements RowMapper<ProgramShortInfo> {
    @Override
    public ProgramShortInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("id");
        String programName = rs.getString("program_name");

        return new ProgramShortInfo(id, programName);
    }
}
