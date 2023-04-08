package com.integration.domain.services;

import com.integration.models.program.ProgramDeletingRequest;
import com.integration.models.program.ProgramSavingRequest;
import com.integration.persistence.ProgramAdapter;
import com.integration.util.ValidationException;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class ProgramService {
    private final ProgramAdapter programAdapter;

    public ProgramService(ProgramAdapter programAdapter) {
        this.programAdapter = programAdapter;
    }

    public int createProgram(ProgramSavingRequest request) {
        validateRequest(request);

        return programAdapter.createNewProgram(request);
    }

    public void deleteUserProgram(ProgramDeletingRequest request) {
        validateRequest(request);

        programAdapter.deleteUserProgram(request);
    }

    private void validateRequest(ProgramSavingRequest request) {
        if (isNull(request.ownerEmail()) || request.ownerEmail().isBlank()) {
            throw new ValidationException("owner email is empty.");
        }

        if (isNull(request.name()) || request.name().isBlank()) {
            throw new ValidationException("program name is empty.");
        }
    }

    private void validateRequest(ProgramDeletingRequest request) {
        if (isNull(request.ownerEmail()) || request.ownerEmail().isBlank()) {
            throw new ValidationException("owner email is empty.");
        }
    }
}
