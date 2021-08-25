drop database if exists capstonedb;
create database capstonedb;

use capstonedb;

create table `role`(
`id` int primary key auto_increment,
`role` varchar(30) not null
);

create table images(
`id` int primary key auto_increment,
imageURL varchar(1000) not null
);

create table `user`(
`id` int primary key auto_increment,
`username` varchar(30) not null unique,
`password` varchar(100) not null,
`enabled` boolean not null default true,
roleId int not null,
foreign key fk_user_role(roleId)
references `role`(`id`)
);

create table posts(
postId int primary key auto_increment,
userId int not null,

Foreign Key fk_posts_user(userId)
references `user`(`id`),

roleId int not null,

Foreign Key fk_posts_role(roleId)
references `role`(`id`),

imageId int not null,

Foreign Key fk_posts_images(imageId)
references images(`id`),

post varchar(1000) not null
);