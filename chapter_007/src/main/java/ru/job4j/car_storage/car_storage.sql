create table car_body (
	id serial primary key,
	name varchar(50)
);
create table engine (
	id serial primary key,
	name varchar(200)
);
create table transmission (
	id serial primary key,
	name varchar(200)
);
create table car (
	id serial primary key,
	name varchar(100),
	car_body_id int references car_body(id),
	engine_id int references engine(id),
	transmission_id int references transmission(id)
);

insert into car_body (name) 
values ('седан');
insert into car_body (name) 
values ('хэтчбек');
insert into car_body (name) 
values ('кроссовер');
insert into car_body (name) 
values ('внедорожник');
insert into engine (name) 
values ('двигатель внутреннего сгорания');
insert into engine (name) 
values ('электродвигатель');
insert into engine (name) 
values ('гибридный двигатель');
insert into transmission (name) 
values ('механическая коробка передач');
insert into transmission (name) 
values ('автоматизированная коробка передач');
insert into transmission (name) 
values ('роботизированная коробка передач');
insert into car (name, car_body_id, engine_id, transmission_id) 
values ('Volkswagen Golf', 2, 1, 2);
insert into car (name, car_body_id, engine_id, transmission_id) 
values ('Toyota Camry Hybrid', 1, 3, 2);
insert into car (name, car_body_id, engine_id, transmission_id) 
values ('Mitsubishi Pajero', 4, 1, 2);
insert into car (name, car_body_id, engine_id, transmission_id) 
values ('Ford Focus', 1, 1, 1);

select c.name, b.name, e.name, t.name from car as c
inner join car_body as b on b.id = c.car_body_id
inner join engine as e on e.id = c.engine_id
inner join transmission as t on t.id = c.transmission_id;

select b.name from car as c
right outer join car_body as b on b.id = c.car_body_id
where c.id is null;

select e.name from car as c 
right outer join engine as e on e.id = c.engine_id
where c.id is null;

select t.name from car as c
right outer join transmission as t on t.id = c.transmission_id
where c.id is null;