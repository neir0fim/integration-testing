CREATE TABLE IF NOT EXISTS exercise(
                                       exercise_id serial primary key ,
                                       description varchar(50),
                                       group_tag integer not null
                                       CONSTRAINT fk_group_tag
                                       references muscle_group(group_id)
                                       on delete cascade
                                       on update cascade
)