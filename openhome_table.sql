show tables;

drop table test;
drop table fruits;
drop table manager
drop table board
drop table message
drop table traffic;

--������ ���̺�
create table manager(
manager_num int(20) auto_increment,
manager_id varchar(30) not null,
manager_pwd varchar(30) not null,
manager_name varchar(30) not null,
manager_phone varchar(30) not null,
primary key(manager_num)
);

insert into manager(manager_id, manager_pwd, manager_name, manager_phone) values('WM60000', '!1234', '�����', '010-1234-4567');

select * from manager;

--�Խ��� ���̺�
create table board(
board_num int(20) auto_increment,
board_title varchar(30) not null,
board_reg_date date not null,
board_maker int(20) not null,
board_list_num int(20) not null,
primary key(board_num),
constraint fk_board_marker foreign key(board_maker) references manager(manager_num)
);


insert into board(board_title, board_reg_date, board_maker, board_list_num) values('��������', now(), 1, 1);

select * from board;

select now();

--�Խñ� ���̺�
create table message(
message_num int(20) auto_increment,
board_num int(20) not null,
message_subject varchar(100) not null,
message_content varchar(500) not null,
message_date date not null,
message_writer varchar(30) not null,
message_pwd varchar(30) not null,
primary key(message_num),
constraint fk_board_num foreign key(board_num) references board(board_num)
);

insert into message(board_num, message_subject, message_content, message_date, message_writer, message_pwd) values(1, '�Խ��մϴ�', '�̿� ���� ���������� �����ּ���.', now(), '���ö', '!1111');

select * from message;

--Ʈ���� ���̺�
create table traffic(
traffic_num int(20) auto_increment,
traffic_content_length int(50) not null,
concurrent_connected_user int(30),
traffic_kind varchar(50) not null,
traffic_date date not null,
traffic_ip varchar(50),
primary key(traffic_num)
);

insert into traffic(traffic_content_length, concurrent_connected_user, traffic_kind, traffic_date, traffic_ip) values(250, 2, '�б�', now(), '192.100.023.02');

select * from traffic;

--�Ϲݻ���� ÷������ ���̺�
create table file_upload(
file_num int(20) auto_increment,
file_name varchar(100) not null,
file_size int(20) not null,
message_num int(20) not null,
primary key(file_num),
constraint fk_message_num foreign key(file_num) references message(message_num)
);

insert into file_upload(file_name, file_size, message_num) values('photo.jpg', 50, 1);


select * from file_upload;