create database fabricaweb

use fabricaweb

create table usuario(id int auto_increment,
accountEmail varchar(255), accountPassword varchar(255)); 

create table cliente(id int auto_increment,
nome varchar(255)); 

select * from usuario;
select * from clientecliente;
select * from filme


CREATE TABLE filme(
id int PRIMARY KEY AUTO_INCREMENT,
nome varchar(30) NOT NULL, descricao varchar(240) not null);
