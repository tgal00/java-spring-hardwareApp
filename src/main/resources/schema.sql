drop table if exists hardware cascade ;
create table hardware(
    id long primary key auto_increment,
    code varchar(255),
    name varchar(255),
    price double,
    stock int,
    type varchar(255)
);


drop table if exists review;
create table review(
    id long primary key auto_increment,
    title varchar(255),
    text varchar(255),
    rating int,
    hardware_id long,
    foreign key (hardware_id) references hardware(id) on delete cascade
);

create table if not exists user (
                                    id identity,
                                    username varchar(100) not null unique,
                                    password varchar(1000) not null
);
create table if not exists authority (
                                         id identity,
                                         authority_name varchar(100) not null unique
);
create table if not exists user_authority (
                                              user_id bigint not null,
                                              authority_id bigint not null,
                                              constraint fk_user foreign key (user_id) references user(id),
                                              constraint fk_authority foreign key (authority_id) references authority(id)
);




