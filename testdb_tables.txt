create database testdb;

create table users (
id serial,
email varchar(70),
password varchar(255),
firstname varchar(100),
lastname varchar(100),
phone varchar(100),
age varchar(10),
gender varchar(10),
course_name varchar(200)
);

create table courses (
id serial,
name varchar(250)
);

insert into courses (name) values ('Programming Principles 1');
insert into courses (name) values ('Programming Principles 2');
insert into courses (name) values ('WEb and Mobile 1');
insert into courses (name) values ('WEb and Mobile 2');
insert into courses (name) values ('Data Science Overview');
insert into courses (name) values ('System analysis and design');
insert into courses (name) values ('IT Project Management');