create table if not exists cinema_places (
    id serial primary key not null,
    row int not null,
    place_number int not null,
    price int not null
);

create table if not exists cinema_accounts (
    id serial primary key not null,
    name varchar(100) not null,
    phone_number varchar(50) not null,
    id_place int references cinema_places(id),
    CONSTRAINT id_place_unique UNIQUE (id_place)
);