CREATE TABLE company
(
id integer NOT NULL,
name character varying,
CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
id integer NOT NULL,
name character varying,
company_id integer,
CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company (id, name) values (1, 'first company');
insert into company (id, name) values (2, 'second company');
insert into company (id, name) values (3, 'third company');
insert into company (id, name) values (4, 'fourth company');
insert into company (id, name) values (5, 'fifth company');
insert into company (id, name) values (6, 'sixth company');
insert into company (id, name) values (7, 'seventh company');

insert into person (id, name, company_id) values (1, 'Ilya', 2);
insert into person (id, name, company_id) values (2, 'Ivan', 4);
insert into person (id, name, company_id) values (3, 'Olga', 5);
insert into person (id, name, company_id) values (4, 'Irina', 7);
insert into person (id, name, company_id) values (5, 'Max', 1);
insert into person (id, name, company_id) values (6, 'Oleg', 3);
insert into person (id, name, company_id) values (7, 'Julia', 4);
insert into person (id, name, company_id) values (8, 'Anton', 5);
insert into person (id, name, company_id) values (9, 'Yana', 4);

--   1) Retrieve in a single query:
--   - names of all persons that are NOT in the company with id = 5
--   - company name for each person
select p.name, c.name from person as p 
inner join company as c on p.company_id = c.id and p.company_id <> 5;

--   2) Select the name of the company with the maximum number of persons + number of persons in this company
select c.name, count(*) as p_count 
from person as p inner join company as c 
on p.company_id = c.id group by c.name
order by p_count desc limit 1;