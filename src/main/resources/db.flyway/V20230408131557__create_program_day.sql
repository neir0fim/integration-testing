CREATE TABLE IF NOT EXISTS program_day(
    program_day_id serial primary key ,
    program_id numeric not null ,
    day_id numeric not null
);

Alter table program_day
ADD UNIQUE (program_id, day_id);