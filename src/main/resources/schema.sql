DROP table if exists employees;
DROP table if exists departments;


create table departments
(
    id   integer primary key AUTO_INCREMENT,
    name varchar(100) not null
);

create table employees
(
    id            integer primary key AUTO_INCREMENT,
    name          varchar(100) not null,
    email         varchar(100) not null,
    department_id integer      not null,
    foreign key (department_id) references departments (id)
);

insert into departments(name) values ('Lightweight');
insert into departments(name) values ('Welterweight');
insert into departments(name) values ('Heavyweight');

insert into employees(name, email, department_id) values ('Khalib', 'khalib@gmail.com',1);
insert into employees(name, email, department_id) values ('Amed', 'Amed@gmail.com',1);
insert into employees(name, email, department_id) values ('mustafar', 'mustafar@gmail.com',1);
insert into employees(name, email, department_id) values ('ali', 'ali@gmail.com',2);
insert into employees(name, email, department_id) values ('amir', 'amir@gmail.com',2);
insert into employees(name, email, department_id) values ('dafme', 'dafme@gmail.com',3);