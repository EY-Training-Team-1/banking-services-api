create table topics (
	t_id serial primary key,
	name VARCHAR(100)
);

create table flashcards (
	f_id serial primary key,
	question VARCHAR(500) not null,
	answer VARCHAR(500) not null,
	name VARCHAR(100) not null,
	difficulty int,
	topic_id int references topics(t_id) on delete set null
);