package com.integration.domain.services;

import com.integration.domain.dto.ProgramDeletingRequestDto;
import com.integration.domain.dto.ProgramSavingRequestDto;
import com.integration.domain.dto.ProgramShortInfoDto;
import com.integration.models.program.ProgramDeletingRequest;
import com.integration.models.program.ProgramSavingRequest;
import com.integration.persistence.program.ProgramAdapter;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.integration.domain.util.ValidationUtil.validateString;

@Service
public class ProgramService {
    private final ProgramAdapter programAdapter;
    private final static String OWNER_EMAIL = "owner email";
    private final static String PROGRAM_NAME = "program name";

    public ProgramService(ProgramAdapter programAdapter) {
        this.programAdapter = programAdapter;
    }

    public int createProgram(ProgramSavingRequestDto request) {
        validateString(request.name(), PROGRAM_NAME);
        validateString(request.ownerEmail(), OWNER_EMAIL);

        var domainRequest = new ProgramSavingRequest(
                request.ownerEmail(),
                request.name()
        );

        return programAdapter.createNewProgram(domainRequest);
    }

    public void deleteUserProgram(ProgramDeletingRequestDto request) {
        validateString(request.ownerEmail(), OWNER_EMAIL);

        var domainRequest = new ProgramDeletingRequest(
                request.programId(),
                request.ownerEmail()
        );

        programAdapter.deleteUserProgram(domainRequest);
    }

    public List<ProgramShortInfoDto> getOwnerPrograms(String ownerEmail) {
        validateString(ownerEmail, PROGRAM_NAME);

        var ownerPrograms = programAdapter.getOwnerProgram(ownerEmail);
        return ownerPrograms.stream()
                .map(programShortInfo -> new ProgramShortInfoDto(
                        programShortInfo.programId(),
                        programShortInfo.programName()
                ))
                .toList();
    }
}
