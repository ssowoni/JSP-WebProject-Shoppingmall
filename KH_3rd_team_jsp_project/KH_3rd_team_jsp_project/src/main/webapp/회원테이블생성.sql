drop table users;
create table users (
	id varchar2(20) not null,
	pass varchar2(20) not null,
	name varchar2(20) not null,
	nickname varchar2(10) not null,
	zipCode varchar2(10) default '미입력',
	address varchar2(100) default '미입력',
	email varchar2(20) not null,
	phoneNum varchar2(13) not null,
	admin number default 0 not null,
	primary key(id),
	unique (nickname)
);