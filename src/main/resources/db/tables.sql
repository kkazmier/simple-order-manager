drop table if exists orders;
drop table if exists driver;

create table orders(
                       id int auto_increment primary key,
                       foodtype varchar(100),
                       description varchar(100)
);

create table drivers(
                        id int auto_increment primary key,
                        firstname varchar(100),
                        lastname varchar(100),
                        description varchar(100)
);