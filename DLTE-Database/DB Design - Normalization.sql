create table banking(username varchar(255) not null,
upi VARCHAR(255) not null,
mobile_number number(10) not null,
eamil varchar(255) not null,
wallet_type varchar(255) not null,
recharged_date date not null,
recharged_provider varchar(255) not null,
recharged_to VARCHAR(255) not null,
recharged_amount NUMBER(10) not null);



create table user1(username VARCHAR(255) not null,
mobile_number number(10) Primary key,
eamil varchar(255) not null);

create table usser1_wallet(
upi VARCHAR(255) primary key,
wallet_type varchar(255) not null,
mobile_number number(10) not null)

create table recharge_wallet(
upi VARCHAR(255) primary key,
recharged_date date not null,
recharged_provider varchar(255) not null,
recharged_to VARCHAR(255) not null,
recharged_amount NUMBER(10) not null)

alter table usser1_wallet add foreign key(mobile_number) references user1(mobile_number);

alter table usser1_wallet add foreign key(upi) references recharge_wallet(upi);