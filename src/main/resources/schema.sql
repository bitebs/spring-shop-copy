drop table if exists jewelry;
drop table if exists material;

CREATE TABLE jewelry (
                         id int NOT NULL AUTO_INCREMENT,
                         name varchar(100) NOT NULL,
                         color varchar(20) not null,
                         price float NOT NULL,
                         price_new float NOT NULL,
                         image blob,
                         material_id int,
                         PRIMARY KEY (id)
);

CREATE TABLE material
(
    id   int          NOT NULL AUTO_INCREMENT,
    name varchar(100) NOT NULL,
    PRIMARY KEY (id)
);



