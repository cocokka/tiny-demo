-- database
create database demo_security;
use demo_security;

-- table
create table user
(
    id       int     not null auto_increment primary key,
    username varchar(50)  default null,
    password varchar(500) default null,
    enabled  boolean not null
);

-- index
create unique index user_username_uidx on user (username);

-- insert data(password is "123"), using default encrypt of spring security.
insert into user (username, password, enabled)
values ('admin', '{bcrypt}$2a$10$2KfPBFQLt2xsNd3/hHWYr.A0GLeriLz7Uujc1zI9FfZME9NzpdBbq', true),
       ('Helen', '{bcrypt}$2a$10$2KfPBFQLt2xsNd3/hHWYr.A0GLeriLz7Uujc1zI9FfZME9NzpdBbq', true),
       ('Tom', '{bcrypt}$2a$10$2KfPBFQLt2xsNd3/hHWYr.A0GLeriLz7Uujc1zI9FfZME9NzpdBbq', true);

commit;

select *
from user;