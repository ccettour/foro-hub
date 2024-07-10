CREATE TABLE topicos(
                        id bigint not null AUTO_INCREMENT,
                        titulo varchar(100) not null,
                        mensaje varchar(300) not null,
                        fecha datetime not null,
                        status varchar(100) not null,
                        id_usuario bigint not null,
                        nombre_curso varchar(100) not null,
                        PRIMARY KEY(id)
);