select * from product as p inner join type as t 
on t.id = p.type_id and t.name = 'СЫР';

select * from product as p 
where p.name like '%мороженое%';

select * from product as p 
where extract(month from p.expired_date) = extract(month from now()) + 1;

select * from product as p 
where p.price = (select max(p.price) from product as p);

select count(*) from product as p inner join type as t 
on t.id = p.type_id and t.name = 'СЫР';

select * from product as p 
inner join type as t on t.id = p.type_id and (t.name = 'СЫР' or t.name = 'МОЛОКО');

select t.name from type as t inner join product as p 
on t.id = p.type_id 
and p.type_id in (select p.type_id from product as p group by p.type_id having count(p.type_id) < 10) 
group by t.name;

select * from product as p inner join type as t 
on t.id = p.type_id;