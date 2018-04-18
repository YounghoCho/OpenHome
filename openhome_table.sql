show tables;

drop table test;
drop table fruits;
drop table manager
drop table board
drop table traffic;

--관리자 테이블
create table manager(
managerNum int(20) auto_increment,
managerId varchar(30) not null,
managerPwd varchar(30) not null,
managerName varchar(30) not null,
managerPhone varchar(30) not null,
primary key(managerNum)
);

--게시판 테이블
create table board(
boardNum int(20) auto_increment,
boardTitle varchar(30) not null,
boardDate date not null,
boardMaker int(20) not null,
boardSequenceNum int(20) not null, //원래 boardlistnum
primary key(boardNum),
constraint fk_boardMarker foreign key(boardMaker) references manager(managerNum)
);

--게시글 테이블
create table article(
articleNum int(20) auto_increment,
boardNum int(20) not null,
articleSubject varchar(200),
articleTextContent varchar(1000),
articleContent varchar(1000),
articleDate date,
articleWriter varchar(50),
articleAccessPwd varchar(30),
aritcleStatus varchar(10) default 'N',
primary key(articleNum),
constraint fk_boardNum foreign key(boardNum) references board(boardNum)
);

alter table article drop column aritcleStatus
alter table article add articleStatus varchar(10) default 'N'

select * from article;
drop table article;
drop table attachmentfile;


--트래픽테이블
create table traffic(
trafficNum int(20) auto_increment,
trafficContentLength int(50) not null,
trafficKind varchar(50) not null,
trafficDate date not null,
trafficIp varchar(50),
primary key(trafficNum)
);

--첨부파일테이블
create table attachmentfile(
fileNum int(20) auto_increment,
articleNum int(20) not null,
originalFileName varchar(100) not null,
storedFileName varchar(150) not null,
fileSize int(20) not null,
fileAttacher varchar(50) not null,
fileDate date not null,
primary key(fileNum),
constraint fk_articleNum foreign key(articleNum) references article(articleNum)
);

drop table attachmentfile;
select * from attachmentfile;

--에디터 사진 데이터베이스
create table attachmentfile(
fileNum int(20) auto_increment,
articleNum int(20) not null,
originalFileName varchar(100) not null,
storedFileName varchar(150) not null,
fileSize int(20) not null,
fileAttacher varchar(50) not null, //filecreater
fileDate date not null, //storedDate
primary key(fileNum),
constraint fk_articleNum foreign key(fileNum) references article(articleNum)
);