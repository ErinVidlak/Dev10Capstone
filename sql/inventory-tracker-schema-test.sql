drop database if exists inventory_tracker_test;
create database inventory_tracker_test;
use inventory_tracker_test;

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
    time_to_make int null,
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




delimiter //
create procedure set_known_good_state()
begin

	delete from material_inventory;
    alter table material_inventory auto_increment = 1;
    delete from material_purchase;
    alter table material_purchase auto_increment = 1;
    delete from listed_product;
    alter table listed_product auto_increment = 1;
    delete from product_material;
    alter table product_material auto_increment = 1;
    delete from product;
    alter table product auto_increment = 1;
    delete from material;
    alter table material auto_increment = 1;
    delete from `user`;


    insert into `user` (user_id) values
		('username'),
        ('test');

    insert into product (product_id, product_name, total_materials_cost, time_to_make, user_id) values
        (1, 'gold earrings with emeralds', 1050.00, 1 , 'username'),
        (2, 'silver keychain', 22.50, 3, 'username' ),
        (3, 'hand knitted hat', 15.00, 720, 'test' );

	insert into listed_product (listed_price,fee_amount, date_listed, is_sold, date_sold, listing_name, product_id) values
		(750.99, 5.99, '2021-01-14', 0, null, 'gold earrings with real emeralds', 1),
		(15.99, 10.00, '2021-05-01', 1, '2021-05-10', 'soft and cozy hand knitted hat', 3);


	insert into material (material_id, material_name, price_per_unit, user_id) values
		(1,'gold earring set', 50.00, 'username'),
        (2,'cut emerald gem', 500.00, 'username'),
        (3,'silver chain', 20.50, 'username'),
        (4,'metal keychain plate', 2.00, 'username'),
        (5,'yarn', 0.50, 'test');

    insert into material_inventory(total_quantity, material_id) values
		(9, 1),
        (0, 2),
        (9, 3),
        (9, 4),
        (470, 5);

	insert into material_purchase(purchase_price, purchase_quantity, quantity_units, purchase_date, purchase_description, material_id) values
		(500.00, 10, 'one pair', '2020-03-12', '10 pairs of gold earrings that have room to put a gem or other decoration. Purchased from Kay Jewelers', 1),
		(1000.00, 2, '3 carats', '2020-05-12', 'two 3 carat cut emeralds from Kay Jewelers', 2),
		(205.00, 10, null, '2020-11-25', 'small chain, bought from Michaels', 3),
		(20.00, 10, null, '2020-10-10', 'metal plates that I plan to use for keychains or dog/cat collars, bought from michaels', 4),
		(250.00, 500, 'yards', '2020-09-15', 'yarn of various colors totaling 500 yards', 5);

	insert into product_material(material_quantity_used, material_id, product_id) values
		(1, 1, 1),
        (2, 2, 1),
        (1, 3, 2),
        (1, 4, 2),
        (30, 5, 3);


end //
-- 4. Change the statement terminator back to the original.
delimiter ;
