package com.kuzin.integration.services;

import com.kuzin.integration.dto.MuscleGroupDto;
import com.kuzin.integration.models.MuscleGroup;
import com.kuzin.integration.persistence.group.MuscleGroupAdapter;
import org.springframework.stereotype.Service;
import java.util.List;

import static com.kuzin.integration.util.ValidationUtil.validateString;

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

    public MuscleGroupDto getMuscleGroup(int groupId) {
        var muscleGroup = groupAdapter.getMuscleGroup(groupId);
        return new MuscleGroupDto(
                muscleGroup.groupId(),
                muscleGroup.groupName()
        );
    }

    public List<MuscleGroupDto> getAllMuscleGroup() {
        return groupAdapter.getAllMuscleGroup().stream()
                .map(muscleGroup -> new MuscleGroupDto(
                        muscleGroup.groupId(),
                        muscleGroup.groupName()
                ))
                .toList();
    }

    public void updateMuscleGroup(String groupName, int groupId) {
        validateString(groupName, MUSCLE_GROUP);

        var group = new MuscleGroup(groupId, groupName);
        groupAdapter.updateMuscleGroup(group);
    }
}
