
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
