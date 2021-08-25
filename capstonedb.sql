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

imageId int,

Foreign Key fk_posts_images(imageId)
references images(`id`),

post varchar(1000) not null
);

insert into `role`(`id`,`role`)
    values(1,"ADMIN"), (2,"TOP_USER"), (3,"USER");

insert into `user`(`id`,`username`,`password`,`enabled`, roleId)
    values(101,"admin", "admin01", true, 1),
        (102,"topUser","topuser",true, 2),
        (103,"normalUser","user",false, 3);

insert into images(`id`, imageURL)
    values(301,"url1"), (302,"url2"), (303,"url3");
        
insert into posts(postId, userId, roleId, imageId, post)
values
('401','101','1','303','post1'),
('402','101','1','302','post2'),
('403','102','2','302','post3'),
('404','102','2','301','post4'),
('405','102','2','301','post5'),
('406','102','2','303','post6');