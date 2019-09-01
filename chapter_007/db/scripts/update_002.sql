create table if not exists vacancies_java (
    id serial primary key not null,
    name varchar(300),
    text varchar(8000),
    link varchar(300),
    creation_time int8,
    CONSTRAINT name_unique UNIQUE (name)
);