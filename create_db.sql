CREATE ROLE job4j_user WITH LOGIN PASSWORD 'job4j_user';
CREATE DATABASE job4j_design WITH OWNER job4j_user;

create table Rules
(
    id   serial primary key,
    name character varying(256)
);

create table Role
(
    id   serial primary key,
    name character varying(256)
);

create table RulesRoles
(
    id       serial primary key,
    role_id  integer,
    rules_id integer
);

create table Users
(
    id      serial primary key,
    name    character varying(256),
    role_id integer references Role (id)
);

create table Category
(
    id   serial primary key,
    name character varying(256)
);

create table State
(
    id   serial primary key,
    name character varying(256)
);

create table Item
(
    id          serial primary key,
    header  character varying(256),
    description text,
    user_id     integer references Users (id),
    category_id integer references Category (id),
    state_id    integer references State (id)
);

create table Comments
(
    id          serial primary key,
    description text,
    item_id     integer references Item (id)
);

create table Attaches
(
    id      serial primary key,
    blob    bytea,
    item_id integer references Item (id)
);

-- Add data
insert into Rules (name) VALUES
('write_db'),
('read_db'),
('send_messages'),
('read_test_db'),
('write_test_db'),
('run_application');

insert into Role (name) VALUES
    ('administrator'),
    ('developer'),
    ('user');


insert into RulesRoles (role_id, rules_id)
select Role.id, Rules.id
from Role
         inner join Rules on Role.name = 'administrator' and Rules.name = 'write_db';

insert into RulesRoles (role_id, rules_id)
select Role.id, Rules.id
from Role
         inner join Rules on Role.name = 'administrator' and Rules.name = 'read_db';

insert into RulesRoles (role_id, rules_id)
select Role.id, Rules.id
from Role
         inner join Rules on Role.name = 'administrator' and Rules.name = 'send_messages';

insert into RulesRoles (role_id, rules_id)
select Role.id, Rules.id
from Role
         inner join Rules on Role.name = 'administrator' and Rules.name = 'read_test_db';

insert into RulesRoles (role_id, rules_id)
select Role.id, Rules.id
from Role
         inner join Rules on Role.name = 'administrator' and Rules.name = 'write_test_db';

insert into RulesRoles (role_id, rules_id)
select Role.id, Rules.id
from Role
         inner join Rules on Role.name = 'administrator' and Rules.name = 'run_application';

insert into RulesRoles (role_id, rules_id)
select Role.id, Rules.id
from Role
         inner join Rules on Role.name = 'developer' and Rules.name = 'read_test_db';

insert into RulesRoles (role_id, rules_id)
select Role.id, Rules.id
from Role
         inner join Rules on Role.name = 'developer' and Rules.name = 'write_test_db';

insert into RulesRoles (role_id, rules_id)
select Role.id, Rules.id
from Role
         inner join Rules on Role.name = 'developer' and Rules.name = 'run_application';

insert into RulesRoles (role_id, rules_id)
select Role.id, Rules.id
from Role
         inner join Rules on Role.name = 'user' and Rules.name = 'run_application';


insert into Users (name, role_id) values
('Vasya Admin', (select id from role where name = 'administrator')),
('Petya Admin', (select id from role where name = 'administrator')),
('Vladimir Ivanovich', (select id from role where name = 'developer')),
('Guest User', (select id from role where name = 'user'));

insert into Category (name) values
('Tech support'),
('Bugs'),
('Feature request');

insert into State (name) values
('Waiting'),
('Accepted'),
('Finished');

insert into Item (header, description, user_id, category_id, state_id) values
('Bug#452907', 'some long description', (select id from Users where name = 'Vladimir Ivanovich'),
                                        (select id from Category where name = 'Bugs'),
                                        (select id from State where name = 'Waiting')
);
insert into Item (header, description, user_id, category_id, state_id) values
('Bug#452883', 'some long description', (select id from Users where name = 'Vladimir Ivanovich'),
                                        (select id from Category where name = 'Bugs'),
                                        (select id from State where name = 'Accepted')
);

insert into Item (header, description, user_id, category_id, state_id) values
('Ticket#124745', 'some long description', (select id from Users where name = 'Vasya Admin'),
                                        (select id from Category where name = 'Tech support'),
                                        (select id from State where name = 'Finished')
);

insert into Comments (description, item_id) values
('some comment', (select id from Item where header = 'Bug#452883'));

insert into Attaches (blob, item_id) values
('some blob', (select id from Item where header = 'Bug#452883'));