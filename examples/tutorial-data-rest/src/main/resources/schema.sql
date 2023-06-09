DROP TABLE Employee IF EXISTS;

-------------------------------------------------------------------------------

create table Employee
(
    ID         bigint generated by default as identity (start with 1),
    FIRST_NAME varchar(100) not null,
    LAST_NAME  varchar(100) not null,
    primary key (id)
);
