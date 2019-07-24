create database user_items;

create table category (
	id_category serial primary key,
	category varchar(200)
);
create table states (
	id_state serial primary key,
	state_name varchar(100)
);
create table rules (
	id_rule serial primary key,
	rule_name varchar(200),
	rule_description varchar(5000)
);
create table roles (
	id_role serial primary key,
	role_name varchar(100)
);
create table roles_rules (
	id_role int references roles(id_role),
	id_rule int references rules(id_rule)
);
create table users (
	id_user serial primary key,
	user_name varchar(100),
	user_password varchar(50),
	id_role int references roles(id_role)
);
create table items (
	id_item serial primary key,
	item varchar(5000),
	id_user int references users(id_user),
	id_state int references states(id_state),
	id_category int references category(id_category)
);
create table comments (
	id_comment serial primary key,
	comment_text varchar(5000),
	id_item int references items(id_item)
);
create table attaches (
	id_attach serial primary key,
	attach varchar(2000),
	id_item int references items(id_item)
);

insert into roles (role_name) values ('first role');
insert into roles (role_name) values ('second role');
insert into rules (rule_name, rule_description) values ('first rule', 'first rule description');
insert into rules (rule_name, rule_description) values ('second rule', 'second rule description');
insert into states (state_name) values ('first state');
insert into states (state_name) values ('second state');
insert into category (category) values ('first category');
insert into category (category) values ('second category');
insert into roles_rules (id_role, id_rule) values (1, 1);
insert into roles_rules (id_role, id_rule) values (2, 1);
insert into roles_rules (id_role, id_rule) values (1, 2);
insert into users (user_name, user_password, id_role) values ('Ivan', '12345', 2);
insert into users (user_name, user_password, id_role) values ('Anton', '6789', 1);
insert into items (item, id_user, id_state, id_category) values ('first item', 2, 1, 2);
insert into items (item, id_user, id_state, id_category) values ('second item', 1, 2, 1);
insert into comments (comment_text, id_item) values ('first comment', 2);
insert into comments (comment_text, id_item) values ('second comment', 1);
insert into attaches (attach, id_item) values ('first attach', 1);
insert into attaches (attach, id_item) values ('second attach', 2);