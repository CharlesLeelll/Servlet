drop table if exists user;
create table user(
id int primary key auto_increment,
name varchar(20) not null,
password varchar(30) not null,
sex char(2) default '男',
age int,
address varchar(50)
);