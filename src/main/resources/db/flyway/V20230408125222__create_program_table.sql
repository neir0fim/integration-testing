CREATE TABLE IF NOT EXISTS program (
    id serial primary key ,
    owner_email varchar(250) not null unique ,
    program_name varchar(100) not null
)