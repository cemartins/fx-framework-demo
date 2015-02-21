create table Contact (
	id int auto_increment primary key,
	person_id int,
	description varchar(255),
	value varchar(255)
) engine=InnoDB default charset utf8;

create table Person (
	id int auto_increment primary key,
	name varchar(255),
	email varchar(255),
	dateOfBirth DATE
) engine=InnoDB default charset utf8;
