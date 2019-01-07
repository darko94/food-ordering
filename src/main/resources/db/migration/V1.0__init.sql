create TABLE if not exists authorities(
	id binary(16) primary key not NULL,
	authority varchar(20) not NULL
);

create TABLE if not exists users(
	id binary(16) primary key not NULL,
	email varchar(100) not NULL,
	password varchar(100) not NULL,
	authorities_id binary(16),
	foreign key(authorities_id) references authorities(id)
);

create table if not exists restaurants(
	id binary(16) primary key not null,
	email varchar(100) not null,
	name varchar(50) not null,
	address varchar(100) not null,
	phone_number varchar(20) not null,
	menu_url varchar(4000) not null,
	delivery_time varchar(50),
	additional_charges varchar(500)
);

create table if not exists group_orders(
	id binary(16) primary key not null,
	restaurant_id binary(16),
	timeout int not null,
	created datetime not null,
	creator varchar(100) not null,
	foreign key(restaurant_id) references restaurants(id)
);

create table if not exists orders(
	id binary(16) primary key not null,
	employee_name varchar(100) not null,
	item_name varchar(500) not null,
	price double not null,
	group_order_id binary(16),
	foreign key(group_order_id) references group_orders(id)
);