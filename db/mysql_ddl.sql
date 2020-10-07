create or replace table districtstats
(
	id int auto_increment
		primary key,
	district varchar(100) null,
	district_id int null,
	t_count int null,
	p_count int null,
	updated_at timestamp null
);

create or replace table hibernate_sequence
(
	next_val bigint null
);

create or replace table spring_session
(
	PRIMARY_ID char(36) not null
		primary key,
	SESSION_ID char(36) not null,
	CREATION_TIME bigint not null,
	LAST_ACCESS_TIME bigint not null,
	MAX_INACTIVE_INTERVAL int not null,
	EXPIRY_TIME bigint not null,
	PRINCIPAL_NAME varchar(100) null,
	constraint SPRING_SESSION_IX1
		unique (SESSION_ID)
);

create or replace index SPRING_SESSION_IX2
	on spring_session (EXPIRY_TIME);

create or replace index SPRING_SESSION_IX3
	on spring_session (PRINCIPAL_NAME);

create or replace table spring_session_attributes
(
	SESSION_PRIMARY_ID char(36) not null,
	ATTRIBUTE_NAME varchar(200) not null,
	ATTRIBUTE_BYTES blob not null,
	primary key (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
	constraint SPRING_SESSION_ATTRIBUTES_FK
		foreign key (SESSION_PRIMARY_ID) references spring_session (PRIMARY_ID)
			on delete cascade
);

create or replace table totalstats
(
	id int auto_increment
		primary key,
	d_last24 int null,
	d_total int null,
	p_last24 int null,
	p_total int null,
	r_last24 int null,
	r_total int null,
	t_last24 int null,
	t_total int null,
	updated_at timestamp null
);

