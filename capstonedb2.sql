drop database if exists capstonedb;
create database capstonedb;

use capstonedb;

create table `role`(
`id` int primary key auto_increment,
`role` varchar(30) not null
);

create table static(
title varchar(20) primary key,
imageURL varchar(500) not null,
post varchar(1000)
);

create table `user`(
`id` int primary key auto_increment,
`username` varchar(30) not null unique,
`password` varchar(100) not null,
roleId int not null,
foreign key fk_user_role(roleId)
references `role`(`id`)
);

create table posts(
postId int primary key auto_increment,
userId int,
Foreign Key fk_posts_user(userId)
references `user`(`id`),
title varchar(20),
imageURL varchar(500),
post varchar(1000) not null,
isPosted boolean not null default true,
postDate datetime
);

insert into `role`(`id`,`role`)
    values(1,"ADMIN"), (2,"TOP_USER"), (3,"USER");

insert into `user`(`id`,`username`,`password`, roleId)
    values(101,"admin", "admin01", 1),
        (102,"topUser","topuser", 2),
        (103,"normalUser","user", 3);

insert into static(title, imageURL, post)
    values('Title1',"url1","post1"), ('Title2',"url2","post2"), ('Title3',"url3","post3");
        
insert into posts(postId, userId, title, imageURL, post, isPosted, postDate)
values
('401','101','#post1','303','post1',true,'2017-09-01'),
('402','101','#post2','302','post2',true,'2018-10-01'),
('403','102','#post3','302','post3',true,'2019-09-01'),
('404','102','#post1','301','post4',false,'2018-12-01'),
('405','102','#post2','301','post5',false,'2020-09-01'),
('406','102','#post3','303','post6',true,'2017-08-01');