
drop database if exists dev10_users;
create database dev10_users;

use dev10_users;

create table app_user (
    app_user_id char(36) primary key,
    username varchar(255) not null unique,
    password_hash varchar(2048) not null,
    disabled boolean not null default(0)
);

create table app_role (
    app_role_id int primary key auto_increment,
    app_role_name varchar(50) not null unique
);

create table app_user_role (
    app_user_id char(36) not null,
    app_role_id int not null,
    constraint pk_app_user_role
        primary key (app_user_id, app_role_id),
    constraint fk_app_user_role_user_id
        foreign key (app_user_id)
        references app_user(app_user_id),
    constraint fk_app_user_role_role_id
        foreign key (app_role_id)
        references app_role(app_role_id)
);

insert into app_role (app_role_name)
    values
    ('ADMIN'),
    ('USER');

-- passwords are set to "P@ssw0rd!"
insert into app_user (app_user_id, username, password_hash, disabled)
    values
    ('983f1224-af4f-11eb-8368-0242ac110002', 'john@smith.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 0),
    ('9e5d9272-af4f-11eb-8368-0242ac110002', 'sally@jones.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 0);

insert into app_user_role
    values
    ('983f1224-af4f-11eb-8368-0242ac110002', 1),
    ('9e5d9272-af4f-11eb-8368-0242ac110002', 2);
