drop database if exists inventory_tracker;
create database inventory_tracker;
use inventory_tracker;

create table `user` (
	user_id varchar(255) not null,
	constraint pk_user
        primary key (user_id)

);

create table product (
    product_id int primary key auto_increment,
    product_name varchar(50) not null,
    total_materials_cost decimal (10,2) not null,
    -- time_to_make in hours, used to calculate "hourly wage" 
    time_to_make int,
    user_id varchar(255) not null,
	constraint fk_product_user_id
        foreign key (user_id)
        references user(user_id)
);



create table listed_product (
    listed_product_id int primary key auto_increment,
    listed_price decimal (10,2) not null default 0.00,
    fee_amount decimal (10,2),
    date_listed date not null,
    date_sold date,
    is_sold bit not null default 0,
    listing_name varchar(50) not null,
    product_id int not null,
    constraint fk_product_listed_product_id
        foreign key (product_id)
        references product(product_id)

);

create table material (
	material_id int primary key auto_increment,
	material_name varchar(50) not null,
	price_per_unit decimal (10,2),
    user_id varchar(255) not null,
	constraint fk_material_user_id
        foreign key (user_id)
        references user(user_id)
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
    purchase_price decimal (10,2) not null default 0.00,
    purchase_quantity int not null,
    quantity_units varchar(25),
    purchase_date date,
    purchase_description text,
    material_id int not null,
    constraint fk_material_purchase_material_id
        foreign key (material_id)
        references material(material_id)
);

create table product_material (
	material_quantity_used int not null,
    material_id int not null,
    product_id int not null,
    constraint pk_product_material
        primary key(material_id, product_id),
    constraint fk_product_material_material_id
        foreign key (material_id)
        references material(material_id),
    constraint fk_product_material_product_id
        foreign key (product_id)
        references product(product_id)
);




