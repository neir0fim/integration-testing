package com.integration.rest;

import com.integration.domain.services.ProgramService;
import com.integration.models.program.ProgramDeletingRequest;
import com.integration.models.program.ProgramSavingRequest;
import com.integration.rest.util.EmailResolver;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/programs")
public class ProgramController {
    private final EmailResolver resolver;
    private final ProgramService service;

    public ProgramController(
            EmailResolver resolver,
            ProgramService service
    ) {
        this.resolver = resolver;
        this.service = service;
    }

    @PostMapping
    public int createNewProgram(
            @RequestBody String programName,
            org.springframework.security.core.Authentication principal
    ) {
        var userEmail = resolver.getUserEmail(principal);

        var saveRequest = new ProgramSavingRequest(userEmail, programName);

        return service.createProgram(saveRequest);
    }

    @DeleteMapping("/{programId}")
    public void deleteUserGroup(
            @PathVariable("programId") int programId,
            org.springframework.security.core.Authentication principal
    ) {
        var userEmail = resolver.getUserEmail(principal);

        var deleteRequest = new ProgramDeletingRequest(programId, userEmail);

        service.deleteUserProgram(deleteRequest);
    }
}
