create database employee;
use employee;
create table users (
  employeeId bigint primary key unique auto_increment,
    name varchar(30) not null ,
  email varchar(50) unique not null ,
  dob long not null ,
  role enum('MANAGER','EMPLOYEE'),
  status enum ('ACTIVE','INACTIVE') default 'INACTIVE'
);

drop table users;
create table userLogin(
    employeeId bigint primary key ,
    email varchar(30) not null ,
    password varchar(20) not null ,
    foreign key (employeeId) references users (employeeId)
);

drop table userLogin;
select * from users;

select *from userLogin;