create table users(
    u_id serial primary key,
    name varchar(100) unique not null,
    email varchar(100) unique not null,
    password varchar(50) not null
);

insert into users values ('22','user1','user1@gmail.com','password');