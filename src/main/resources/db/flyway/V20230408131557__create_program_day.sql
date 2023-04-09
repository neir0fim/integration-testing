CREATE TABLE IF NOT EXISTS program_day(
    program_day_id serial primary key ,
    program_id integer not null ,
    day_id integer not null,
    training_status boolean default false,
    CONSTRAINT fk_program_id
                                        foreign key (program_id)
                                        references program(id)
                                        on delete cascade
                                        on update cascade ,
    CONSTRAINT fl_day_id
                                        foreign key (day_id)
                                        references day(day_id)
                                        on  delete cascade
                                        on update cascade
);

Alter table program_day
ADD UNIQUE (program_id, day_id);