CREATE TABLE IF NOT EXISTS muscle_group(
    group_id serial primary key ,
    group_name varchar not null unique
)