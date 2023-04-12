package com.kuzin.integration.services;

import com.kuzin.integration.dto.ProgramDayDto;
import com.kuzin.integration.dto.ProgramDayRequestDto;
import com.kuzin.integration.dto.ProgramDayFullInfoDto;
import com.kuzin.integration.models.day.ProgramDayRequest;
import com.kuzin.integration.persistence.day.ProgramDayAdapter;
import com.kuzin.integration.persistence.day.ProgramExerciseAdapter;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProgramDayService {
    private final ProgramDayAdapter programDayAdapter;
    private final ProgramExerciseAdapter programExerciseAdapter;

    public ProgramDayService(
            ProgramDayAdapter programDayAdapter,
            ProgramExerciseAdapter programExerciseAdapter
    ) {
        this.programDayAdapter = programDayAdapter;
        this.programExerciseAdapter = programExerciseAdapter;
    }

    public int addProgramDay(ProgramDayRequestDto programDayRequestDto) {
        return programDayAdapter.addProgramDay(
                new ProgramDayRequest(
                        programDayRequestDto.programId(),
                        programDayRequestDto.dayId(),
                        programDayRequestDto.trainingStatus()
                )
        );
    }

    public void updateExercise(
            int programDayId,
            ProgramDayRequestDto programDayRequestDto
    ) {
        programDayAdapter.updateProgramDay(
                programDayId,
                new ProgramDayRequest(
                        programDayRequestDto.programId(),
                        programDayRequestDto.dayId(),
                        programDayRequestDto.trainingStatus()
                )
        );
    }

    public void deleteProgramDay(int programDayId) {
        programDayAdapter.deleteProgramDay(programDayId);
    }

    public ProgramDayFullInfoDto getProgramDay(int programDayId) {
        var responseBuilder = ProgramDayFullInfoDto.newBuilder();
        var programDay = programDayAdapter.getProgramDay(programDayId);

        responseBuilder.setDayName(programDay.dayName());
        responseBuilder.setProgramName(programDay.programName());
        responseBuilder.setProgramDayId(programDay.programDayId());
        responseBuilder.setTrainingStatus(programDay.trainingStatus());

        if (programDay.trainingStatus()) {
            var dayExercises = programExerciseAdapter.getExerciseForDay(programDayId);
            responseBuilder.setExerciseList(dayExercises);
        }

        return responseBuilder.build();
    }

    public List<ProgramDayDto> getALLProgramDays(int programId) {
        return programDayAdapter.getALLProgramDay(programId).stream()
                .map(programDay ->
                        new ProgramDayDto(
                                programDay.programDayId(),
                                programDay.programName(),
                                programDay.dayName(),
                                programDay.trainingStatus()
                        ))
                .toList();
    }
}
