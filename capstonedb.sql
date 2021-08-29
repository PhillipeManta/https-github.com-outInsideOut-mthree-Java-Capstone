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
    values('About Me',
    	"https://tse3.mm.bing.net/th/id/OIP.XAuIJMNtJFpkxc--xtHeegHaFj?pid=ImgDet&rs=1",
    	"I'm a person, I do blogs and stuff."), 
    ('Contact Us',
    	"https://tse1.mm.bing.net/th/id/OIP.FodAyCgpHt-Jz3c1_EddewHaHY?pid=ImgDet&rs=1",
    	"Don't contact me, I hate people."), 
    ('Static Page',"https://th.bing.com/th/id/R.ae1956e1ea20c0f8cf9417296c09393f?rik=h8A%2f3mj9JHjoqQ&pid=ImgRaw&r=0",
    	 "This is an example page");
        
insert into posts(postId, userId, title, imageURL, post, isPosted, postDate)
values
('401', '101', 'Pretty', 'https://tse4.mm.bing.net/th/id/OIP.IumWkT1hfQ3DbUZJ47fYiQHaFj?pid=ImgDet&rs=1', 
	'Wow a sunset. The sun, setting, going down, weird colours in the sky... pffft... crazy!',
	true, '2017-09-01'),
('402', '101', 'So nice', 'https://tse3.mm.bing.net/th/id/OIP.0B8FoaGmIdKyEQwRSy26ngHaEK?pid=ImgDet&rs=1', 
	'This was cool. I can\'t remember much about it, but it was LUSH.' , true, '2018-10-01'),
('403', '102', 'Road', 'https://tse4.mm.bing.net/th/id/OIP.jJm2r3ilh6xK93tEMH8kqwHaEK?pid=ImgDet&rs=1', 
	'I drove down this road. I did it in a car. It was AMAZING! But then... A BEAR! It jumped right out infront of me, and crushed the bonnet with it\'s big paws. Luckily, I got his insurance so it should be fine.', true, '2019-09-01'),
('404', '102', 'Butterflies', 
	'https://tse3.mm.bing.net/th/id/OIP.KwWm9QDnmZHB8-NwxiPLzQHaEK?pid=ImgDet&rs=1',
	'These are butterflies. THey used to be caterpillars, that\'s weird right?', 
	false, '18-12-01');