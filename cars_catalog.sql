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
('4stage'),
('5stage'),
('3stage');

insert into car (name, body_id, engine_id, gearbox_id) values
('kopeika', (select id from body as b where b.name = '2101'), (select id from engine as e where e.name = '1,2'), (select id from gearbox as g where g.name = '4stage')),
('shesterka', (select id from body as b where b.name = '2106'), (select id from engine as e where e.name = '1,5'), (select id from gearbox as g where g.name = '5stage')),
('devyatka', (select id from body as b where b.name = '2109'), (select id from engine as e where e.name = '1,6'), (select id from gearbox as g where g.name = '5stage')),
('daemon_taz', (select id from body as b where b.name = '210666'), (select id from engine as e where e.name = '6,8'), (select id from gearbox as g where g.name = '3stage'));