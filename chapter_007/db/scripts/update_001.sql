create table tracker_items (
    id serial primary key not null,
    name varchar(100),
    description varchar(2000),
    creation_time int8
);

create table item_comments (
    id serial primary key not null,
    comment_text varchar(2000),
    id_item int references tracker_items(id)
);