create table users (
	u_id serial primary key,
	name VARCHAR(500) not null,
	email VARCHAR(500) not null,
	password VARCHAR(100) not null
);