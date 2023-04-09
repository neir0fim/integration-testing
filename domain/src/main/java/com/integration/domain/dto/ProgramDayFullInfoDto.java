package com.integration.domain.dto;

import com.integration.models.exercise.Exercise;
import java.util.List;

public class ProgramDayFullInfoDto {
    private final int programDayId;
    private final String programName;
    private final String dayName;
    private final boolean trainingStatus;
    private final List<Exercise> exerciseList;

    public int getProgramDayId() {
        return programDayId;
    }

    public String getProgramName() {
        return programName;
    }

    public String getDayName() {
        return dayName;
    }

    public boolean isTrainingStatus() {
        return trainingStatus;
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public ProgramDayFullInfoDto(ProgramDayFullInfoBuilder builder) {
        this.programDayId = builder.programDayId;
        this.programName = builder.programName;
        this.dayName = builder.dayName;
        this.trainingStatus = builder.trainingStatus;
        this.exerciseList = builder.exerciseList;
    }

    public static ProgramDayFullInfoBuilder newBuilder() {
        return new ProgramDayFullInfoBuilder();
    }

    public static final class ProgramDayFullInfoBuilder {
        private int programDayId;
        private String programName;
        private String dayName;
        private boolean trainingStatus;
        private List<Exercise> exerciseList;

        private ProgramDayFullInfoBuilder() {
        }

        public ProgramDayFullInfoBuilder setProgramDayId(int programDayId) {
            this.programDayId = programDayId;
            return this;
        }

        public ProgramDayFullInfoBuilder setProgramName(String programName) {
            this.programName = programName;
            return this;
        }

        public ProgramDayFullInfoBuilder setDayName(String dayName) {
            this.dayName = dayName;
            return this;
        }

        public ProgramDayFullInfoBuilder setTrainingStatus(boolean trainingStatus) {
            this.trainingStatus = trainingStatus;
            return this;
        }

        public ProgramDayFullInfoBuilder setExerciseList(List<Exercise> exerciseList) {
            this.exerciseList = exerciseList;
            return this;
        }

        public ProgramDayFullInfoDto build() {
            return new ProgramDayFullInfoDto(this);
        }
    }
}
