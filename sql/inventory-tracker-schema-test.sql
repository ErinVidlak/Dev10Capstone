drop database if exists inventory_tracker_test;
create database inventory_tracker_test;
use inventory_tracker_test;

create table product (
    product_id int primary key auto_increment,
    product_name varchar(50) not null,
    total_materials_cost decimal (10,2) not null,
    time_to_make dateTime not null,
    user_name varchar(50) not null
);

create table platform_fee (
    platform_fee_id int primary key auto_increment,
    platform_name varchar(25),
    fee_amount decimal (10,2),
    fee_type varchar(25)
);

create table listed_product (
    listed_product_id int primary key auto_increment,
    listed_price decimal (10,2) not null,
    date_listed date not null,
    date_sold date,
    has_sold bit,
    listing_name varchar(50),
    product_id int,
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
	material_name varchar(50),
	user_name varchar(50),
	price_per_unit decimal (10,2)
);

create table material_inventory (
	material_inventory_id int primary key auto_increment,
    total_quantity int,
    material_id int,
    constraint fk_material_inventory_material_id
        foreign key (material_id)
        references material(material_id)
);

create table material_purchase (
	material_purchase_id int primary key auto_increment,
    purchase_price decimal (10,2),
    purchase_quantity int,
    quantity_units varchar(25),
    purchase_date date,
    description text,
    material_id int,
    constraint fk_material_purchase_material_id
        foreign key (material_id)
        references material(material_id)
);

create table material_product (
    material_id int not null,
    product_id int not null,
    constraint pk_material_product
        primary key(material_id, product_id),
    constraint fk_material_product_material_id
        foreign key (material_id)
        references material(material_id),
    constraint fk_material_product_product_id
        foreign key (product_id)
        references product(product_id)
);

