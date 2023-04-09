package com.integration.rest;

import com.integration.domain.dto.MuscleGroupDto;
import com.integration.domain.services.MuscleGroupService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/muscle-groups")
public class MuscleGroupController {
    private final MuscleGroupService service;

    public MuscleGroupController(MuscleGroupService service) {
        this.service = service;
    }

    @PostMapping
    public void addMuscleGroup(@RequestBody String groupName) {
        service.addMuscleGroup(groupName);
    }

    @DeleteMapping("/{groupId}")
    public void deleteMuscleGroup(@PathVariable("groupId") int groupId) {
        service.deleteMuscleGroup(groupId);
    }

    @GetMapping("/{groupId}")
    public MuscleGroupDto getMuscleGroup(@PathVariable("groupId") int groupId) {
        return service.getMuscleGroup(groupId);
    }

    @GetMapping
    public List<MuscleGroupDto> getAllMuscleGroup() {
        return service.getAllMuscleGroup();
    }

    @PutMapping("/{groupId}")
    public void updateMuscleGroup(
            @PathVariable("groupId") int groupId,
            @RequestBody String groupName
    ) {
        service.updateMuscleGroup(groupName, groupId);
    }
}
