package com.kuzin.integration.persistence.day;

import com.kuzin.integration.models.day.ProgramDay;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProgramDayMapper implements RowMapper<ProgramDay> {

    @Override
    public ProgramDay mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("program_day_id");
        String programName = rs.getString("program_name");
        String description = rs.getString("description");
        boolean trainingStatus = rs.getBoolean("training_status");

        return new ProgramDay(id, programName, description, trainingStatus);
    }
}
