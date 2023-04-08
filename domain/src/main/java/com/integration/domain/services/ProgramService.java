package com.integration.domain.services;

import com.integration.models.program.ProgramDeletingRequest;
import com.integration.models.program.ProgramSavingRequest;
import com.integration.models.program.ProgramShortInfo;
import com.integration.persistence.program.ProgramAdapter;
import com.integration.util.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class ProgramService {
    private final ProgramAdapter programAdapter;
    private final static String OWNER_EMAIL = "owner email";
    private final static String PROGRAM_NAME = "program name";

    public ProgramService(ProgramAdapter programAdapter) {
        this.programAdapter = programAdapter;
    }

    public int createProgram(ProgramSavingRequest request) {
        validateString(request.name(), PROGRAM_NAME);
        validateString(request.ownerEmail(), OWNER_EMAIL);

        return programAdapter.createNewProgram(request);
    }

    public void deleteUserProgram(ProgramDeletingRequest request) {
        validateString(request.ownerEmail(), OWNER_EMAIL);

        programAdapter.deleteUserProgram(request);
    }

    public List<ProgramShortInfo> getOwnerPrograms(String ownerEmail) {
        validateString(ownerEmail, PROGRAM_NAME);

        return programAdapter.getOwnerProgram(ownerEmail);
    }

    private void validateString(String values, String entity) {
        if (isNull(values) || values.isBlank()) {
            throw new ValidationException(
                    String.format("%s email is empty.", entity)
            );
        }
    }
}
