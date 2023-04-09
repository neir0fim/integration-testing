CREATE TABLE IF NOT EXISTS program_exercise(
    program_exercise_id serial primary key ,
    program_day_id integer not null
        CONSTRAINT fk_program_day_id
            references program_day(program_day_id)
            on delete cascade
            on update cascade,
    exercise_id integer not null
    CONSTRAINT  fk_exercise_id
        references exercise(exercise_id)
            on delete cascade
            on update cascade
);

Alter table program_exercise
    ADD UNIQUE (program_day_id, exercise_id);