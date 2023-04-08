package com.integration.models;

import java.time.DayOfWeek;
import java.util.List;

public class ProgramDay {
    private final int id;
    private final DayOfWeek dayOfWeek;
    private final List<Exercise> exerciseList;
    private final boolean isTrainingDay;

    public ProgramDay(ProgramDayBuilder builder) {
        this.id = builder.id;
        this.dayOfWeek = builder.dayOfWeek;
        this.exerciseList = builder.exerciseList;
        this.isTrainingDay = builder.isTrainingDay;
    }

    public int getId() {
        return id;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public boolean isTrainingDay() {
        return isTrainingDay;
    }

    public static ProgramDayBuilder newBuilder() {
        return new ProgramDayBuilder();
    }

    public static final class ProgramDayBuilder {
        private int id;
        private DayOfWeek dayOfWeek;
        private List<Exercise> exerciseList;
        private boolean isTrainingDay;

        private ProgramDayBuilder() {
        }

        public ProgramDayBuilder getId(int id) {
            this.id = id;
            return this;
        }

        public ProgramDayBuilder getDayOfWeek(DayOfWeek dayOfWeek) {
            this.dayOfWeek = dayOfWeek;
            return this;
        }

        public ProgramDayBuilder getExerciseList(List<Exercise> exerciseList) {
            this.exerciseList = exerciseList;
            return this;
        }

        public ProgramDayBuilder getIsTrainingDat(boolean isTrainingDat) {
            this.isTrainingDay = isTrainingDat;
            return this;
        }

        public ProgramDay build() {
            return new ProgramDay(this);
        }
    }
}
