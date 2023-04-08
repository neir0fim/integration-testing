package com.integration.domain.models;

import java.util.List;

public record Program(String ownerEmail, int programId, String programName, List<ProgramDay> programDayList) {
}
