create table usuarios(
                         id bigint not null auto_increment,
                         email varchar(100) not null unique,
                         contrasena varchar(100) not null unique,
                         primary key(id)
);