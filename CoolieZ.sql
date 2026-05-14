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

create table tasks(
    taskId bigint primary key auto_increment,
    taskName varchar(30),
    assignedBy bigint not null ,
    assignedTo bigint not null ,
    createdAt bigint not null ,
    dueDate bigint not null ,
    status  enum('COMPLETED','INPROGRESS','CREATED') default 'CREATED',
    description varchar(200),
    foreign key (assignedBy)  references users (employeeId),
    foreign key (assignedTo)  references users (employeeId)
);

select * from tasks;

drop table tasks;

truncate tasks;