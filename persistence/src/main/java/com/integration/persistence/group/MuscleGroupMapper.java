package com.integration.persistence.group;

import com.integration.models.MuscleGroup;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MuscleGroupMapper implements RowMapper<MuscleGroup> {
    @Override
    public MuscleGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("group_id");
        String groupName = rs.getString("group_name");

        return new MuscleGroup(id, groupName);
    }
}
