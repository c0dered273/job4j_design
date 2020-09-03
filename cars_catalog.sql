create table body (
	id serial primary key,
	name varchar(256)
);

create table engine (
	id serial primary key,
	name varchar(256)
);

create table gearbox (
	id serial primary key,
	name varchar(256)
);

create table car (
	id serial primary key,
	name varchar(256),
	body_id integer references body(id),
	engine_id integer references engine(id),
	gearbox_id integer references gearbox(id)
);

insert into body(name) values
('2100')
('2101'),
('2106'),
('2109'),
('210666');

insert into engine(name) values
('1,2'),
('1,5'),
('1,6'),
('6,8');

insert into gearbox(name) values
('DSG')
('4stage'),
('5stage'),
('3stage');

insert into car (name, body_id, engine_id, gearbox_id) values
('kopeika', (select id from body as b where b.name = '2101'),
            (select id from engine as e where e.name = '1,2'),
            (select id from gearbox as g where g.name = '4stage')),
('shesterka', (select id from body as b where b.name = '2106'),
              (select id from engine as e where e.name = '1,5'),
              (select id from gearbox as g where g.name = '5stage')),
('devyatka', (select id from body as b where b.name = '2109'),
             (select id from engine as e where e.name = '1,6'),
             (select id from gearbox as g where g.name = '5stage')),
('daemon_taz', (select id from body as b where b.name = '210666'),
               (select id from engine as e where e.name = '6,8'),
               (select id from gearbox as g where g.name = '3stage'));

select c.name, b.name, e.name, g.name from car as c
left join body as b on c.body_id = b.id
left join engine as e on c.engine_id = e.id
left join gearbox as g on c.gearbox_id = g.id

select b.name from body as b left outer join car as c on b.id = c.body_id where c.body_id is null;

select e.name from engine as e left outer join car as c on e.id = c.engine_id where c.engine_id is null;

select g.name from gearbox as g left outer join car as c on g.id = c.gearbox_id where c.gearbox_id is null;