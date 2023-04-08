package com.integration.domain.services;

import com.integration.models.MuscleGroup;
import com.integration.persistence.group.MuscleGroupAdapter;
import org.springframework.stereotype.Service;
import java.util.List;

import static com.integration.domain.util.ValidationUtil.validateString;

@Service
public class MuscleGroupService {
    private final MuscleGroupAdapter groupAdapter;
    private static final String MUSCLE_GROUP = "muscle group";
    public MuscleGroupService(MuscleGroupAdapter groupAdapter) {
        this.groupAdapter = groupAdapter;
    }

    public void addMuscleGroup(String groupName) {
        validateString(groupName, MUSCLE_GROUP);

        groupAdapter.addMuscleGroup(groupName);
    }

    public void deleteMuscleGroup(int groupId) {
        groupAdapter.deleteMuscleGroup(groupId);
    }

    public MuscleGroup getMuscleGroup(int groupId) {
        return groupAdapter.getMuscleGroup(groupId);
    }

    public List<MuscleGroup> getAllMuscleGroup() {
        return groupAdapter.getAllMuscleGroup();
    }

    public void updateMuscleGroup(String groupName, int groupId) {
        validateString(groupName, MUSCLE_GROUP);

        var group = new MuscleGroup(groupId, groupName);
        groupAdapter.updateMuscleGroup(group);
    }
}
