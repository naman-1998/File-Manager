create database projectdata;
use projectdata;
create table userdetails(uname varchar(20),email varchar(20),pass varchar(20));
alter table userdetails
modify column email varchar(50);
select * from userdetails;
alter table userdetails
modify column email varchar(50) primary key;
alter table userdetails
add column filename varchar(20);
delete from userdetails where pass='erv';
