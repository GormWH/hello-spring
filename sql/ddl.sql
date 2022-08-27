drop table if exists member CASCADE;

create table member
(
    name varchar(255),
    id bigint not null auto_increment,
    primary key (id)
);

select * from member;

delete from member;

insert into member (name) values ('suhong');
insert into member (name) values ('woohee');