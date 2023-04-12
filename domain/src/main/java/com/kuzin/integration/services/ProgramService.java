package com.kuzin.integration.services;

import com.kuzin.integration.dto.ProgramDeletingRequestDto;
import com.kuzin.integration.dto.ProgramSavingRequestDto;
import com.kuzin.integration.dto.ProgramShortInfoDto;
import com.kuzin.integration.models.program.ProgramDeletingRequest;
import com.kuzin.integration.models.program.ProgramSavingRequest;
import com.kuzin.integration.persistence.program.ProgramAdapter;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kuzin.integration.util.ValidationUtil.validateString;

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
