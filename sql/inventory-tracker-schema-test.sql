drop database if exists inventory_tracker_test;
create database inventory_tracker_test;
use inventory_tracker_test;

create table user (
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

create table platform_fee (
    platform_fee_id int primary key auto_increment,
    platform_name varchar(25) not null,
    fee_amount decimal (10,2),
    fee_type varchar(25)
);

create table listed_product (
    listed_product_id int primary key auto_increment,
    listed_price decimal (10,2) not null default 0.00,
    date_listed date not null,
    date_sold date,
    is_sold bit not null default 0,
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

create table material_product (
	quantity int not null,
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








delimiter //
create procedure set_known_good_state()
begin


    insert into user (user_id) values
		('username'),
        ('test');
    
    -- dateTime wont work for time_to_make
    insert into product (product_id, product_name, total_materials_cost, time_to_make, user_id) values
        (1, 'gold earrings with emeralds', 20.05, 1 , 'username'),
        (2, 'silver keychain', 5.00, 3, 'username' ),        
        (3, 'hand knitted hat', 15.00, 720, 'test' );


	insert into platform_fee ( platform_name, fee_amount, fee_type) values
		('Etsy', 10.45, "payment proccessing fee");

	insert into listed_product (listed_price, date_listed, is_sold, date_sold, listing_name, product_id, platform_fee_id) values
		(750.99, '2021-01-14', 0, null, 'gold earrings with real emeralds', 1, 1),
		(15.99, '2021-05-01', 1, '2021-05-10', 'soft and cozy hand knitted hat', 3, 1);
        
        
	insert into material(material_id, material_name, price_per_unit, user_id) values
		(1,'gold earring set', 50.00, 'username'),
        (2,'cut emerald gem', 500.00, 'username'),
        (3,'silver chain', 20.50, 'username'),
        (4,'metal keychain plate', 2.00, 'username'),
        (5,'yarn', 0.50, 'test');
        
	insert into material_product(quantity, material_id, product_id) values 
		(1, 1, 1),
        (2, 2, 1),
        (1, 3, 2),
        (1, 4, 2),
        (30, 5, 3);  

    insert into material_inventory(total_quantity, material_id) values
		(9, 1),
        (0, 2),
        (9, 3),
        (9, 4),
        (470, 5);
        
	insert into material_purchase(purchase_price, purchase_quantity, quantity_units, purchase_date, purchase_description, material_id) values
		(500.00, 10, 'one pair', '04-22-2020', '10 pairs of gold earrings that have room to put a gem or other decoration. Purchased from Kay Jewelers', 1),
		(1000.00, 2, '3 carats', '04-25-2020', 'two 3 carat cut emeralds from Kay Jewelers', 2),
		(205.00, 10, '', '12-26-2020', 'small chain, bought from Michaels', 3),
		(20.00, 10, '', '12-26-2020', 'metal plates that I plan to use for keychains or dog/cat collars, bought from michaels', 4),
		(250.00, 500, 'yards', '08-03-2020', 'yarn of various colors totaling 500 yards', 5);



end //
-- 4. Change the statement terminator back to the original.
delimiter ;


select * from material;
