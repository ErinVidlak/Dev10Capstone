drop database if exists inventory_tracker_test;
create database inventory_tracker_test;
use inventory_tracker_test;


create table user (
	user_id varchar(255) primary key
);


create table product (
    product_id int primary key auto_increment,
    product_name varchar(50) not null,
    total_materials_cost decimal (10,2) not null,
    time_to_make dateTime,

    user_name varchar(50) not null

    user_id varchar(255) not null

);

create table platform_fee (
    platform_fee_id int primary key auto_increment,
    platform_name varchar(25) not null,
    fee_amount decimal (10,2) not null,
    fee_type varchar(25)
);

create table listed_product (
    listed_product_id int primary key auto_increment,
    listed_price decimal (10,2) not null,
    date_listed date not null,
    date_sold date,
    is_sold bit default 0,
    listing_name varchar(50) not null,
    product_id int not null,
    platform_fee_id int,
    constraint fk_product_listed_product_id
        foreign key (product_id)
        references product(product_id),
	constraint fk_platform_fee_listed_product_id
        foreign key (platform_fee_id)
        references platform_fee(platform_fee_id)
);

create table material (
	material_id int primary key auto_increment,
	material_name varchar(50) not null,

	user_name varchar(50) not null,

	user_id varchar(255) not null,
	price_per_unit decimal (10,2)
);

create table material_inventory (
	material_inventory_id int primary key auto_increment,
    total_quantity int not null,
    material_id int not null,
    constraint fk_material_inventory_material_id
        foreign key (material_id)
        references material(material_id)
);

create table material_purchase (
	material_purchase_id int primary key auto_increment,
    purchase_price decimal (10,2) not null,
    purchase_quantity int not null,
    quantity_units varchar(25),
    purchase_date date,
    description text,
    material_id int not null,
    constraint fk_material_purchase_material_id
        foreign key (material_id)
        references material(material_id)
);

create table material_product (
    material_id int not null,
    product_id int not null,
    quantity int not null,
    constraint pk_material_product
        primary key(material_id, product_id),
    constraint fk_material_product_material_id
        foreign key (material_id)
        references material(material_id),
    constraint fk_material_product_product_id
        foreign key (product_id)
        references product(product_id)
);

delimiter //
create procedure set_known_good_state()
begin

	delete from product;
    alter table product auto_increment = 1;
    delete from platform_fee;
    alter table platform_fee auto_increment = 1;
    delete from listed_product;
    alter table listed_product auto_increment = 1;
    delete from material;
    alter table material auto_increment = 1;
    delete from material_inventory;
    alter table material_inventory auto_increment = 1;
    delete from material_purchase;
    alter table material_purchase auto_increment = 1;
    delete from material_product;
    
    insert into product (product_id, product_name, total_materials_cost, time_to_make, user_name) values
    insert into product (product_id, product_name, total_materials_cost, time_to_make, user_id) values
        (1, 'ACME', 'Agency to Classify & Monitor Evildoers'),
        (2, 'MASK', 'Mobile Armored Strike Kommand'),
        (3, 'ODIN', 'Organization of Democratic Intelligence Networks');
    
    insert into product(agency_id, short_name, long_name) values
        (1, 'ACME', 'Agency to Classify & Monitor Evildoers'),
        (2, 'MASK', 'Mobile Armored Strike Kommand'),
        (3, 'ODIN', 'Organization of Democratic Intelligence Networks');
        
	insert into location (location_id, name, address, city, region, country_code, postal_code, agency_id)
		values
	(1, 'HQ', '123 Elm', 'Des Moines', 'IA', 'USA', '55555', 1),
    (2, 'Safe House #1', 'A One Ave.', 'Walla Walla', 'WA', 'USA', '54321-1234', 1),
    (3, 'HQ', '123 Elm', 'Test', 'WI', 'USA', '55555', 2),
	(4, 'Remote', '999 Nine St.', 'Test', 'WI', 'USA', '55555', 2),
	(5, 'HQ', '123 Elm', 'Test', 'WI', 'USA', '55555', 3), -- for delete tests
	(6, 'Remote', '999 Nine St.', 'Test', 'WI', 'USA', '55555', 3);
        
	insert into agent 
		(first_name, middle_name, last_name, dob, height_in_inches) 
	values
		('Hazel','C','Sauven','1954-09-16',76),
		('Claudian','C','O''Lynn','1956-11-09',41),
		('Winn','V','Puckrin','1999-10-21',60),
		('Kiab','U','Whitham','1960-07-29',52),
		('Min','E','Dearle','1967-04-18',44),
		('Urban','H','Carwithen',null,58),
		('Ulises','B','Muhammad','2008-04-01',80),
		('Phylys','Y','Howitt','1979-03-28',68);
        
	insert into agency_agent 
		(agency_id, agent_id, identifier, security_clearance_id, activation_date)
    select
        agency.agency_id,                              -- agency_id
        agent.agent_id,                                -- agent_id
        concat(agency.agency_id, '-', agent.agent_id), -- identifier
        1,                                             -- security_clearance_id
        date_add(agent.dob, interval 10 year)          -- activation_date
    from agency
    inner join agent
    where agent.agent_id not in (6, 8)
    and agency.agency_id != 2;

end //
-- 4. Change the statement terminator back to the original.
delimiter ;
