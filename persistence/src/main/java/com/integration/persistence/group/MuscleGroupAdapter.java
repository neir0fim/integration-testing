package com.integration.persistence.group;

import com.integration.models.MuscleGroup;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MuscleGroupAdapter {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public MuscleGroupAdapter(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void addMuscleGroup(String groupName) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("group_name", groupName);

        String sqlRequest = """
                INSERT INTO muscle_group(group_name) values (:group_name)
                """;

        namedParameterJdbcTemplate.update(sqlRequest, map);
    }

    public void deleteMuscleGroup(int groupId) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("group_id", groupId);

        String sqlRequest = """
                DELETE FROM muscle_group WHERE group_id = :group_id
                """;

        namedParameterJdbcTemplate.update(sqlRequest, map);
    }

    public MuscleGroup getMuscleGroup(int groupId) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("group_id", groupId);

        String sqlRequest = """
                SELECT * FROM muscle_group WHERE group_id = :group_id;
                """;

        return namedParameterJdbcTemplate.queryForObject(sqlRequest, map, new MuscleGroupMapper());
    }

    public List<MuscleGroup> getAllMuscleGroup() {
        String sqlRequest = """
                SELECT * from muscle_group
                """;

        return namedParameterJdbcTemplate.query(sqlRequest, new MuscleGroupMapper());
    }

    public void updateMuscleGroup(MuscleGroup group) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("group_name", group.groupName());
        map.addValue("group_id", group.groupId());

        String sqlRequest = """
                UPDATE muscle_group set group_name = :group_name WHERE group_id = :group_id
                """;

        namedParameterJdbcTemplate.update(sqlRequest, map);
    }
}
