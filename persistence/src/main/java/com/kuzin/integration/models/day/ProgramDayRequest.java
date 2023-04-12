package com.kuzin.integration.models.day;

public record ProgramDayRequest(int programId, int dayId, boolean trainingStatus) {
}
