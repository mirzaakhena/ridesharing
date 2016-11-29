# DC schema

# --- !Ups

CREATE TABLE user (
    id INTEGER PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    password VARCHAR(8) NOT NULL,
    role VARCHAR(10) NOT NULL
);

CREATE TABLE passenger (
    id INTEGER PRIMARY KEY,
    status VARCHAR(25) NOT NULL
);

CREATE TABLE driver (
    id INTEGER PRIMARY KEY,
    status VARCHAR(25) NOT NULL,
    latitude double precision NOT NULL,
    longitude double precision NOT NULL
);

CREATE TABLE trip (
    id INTEGER PRIMARY KEY,
    id_passenger INTEGER NOT NULL DEFAULT 'null',
    id_driver INTEGER,
    status varchar(25) NOT NULL DEFAULT 'REQUESTING',
    latitude_start DOUBLE PRECISION NOT NULL DEFAULT '0.0',
    longitude_start DOUBLE PRECISION NOT NULL DEFAULT '0.0',
    latitude_end DOUBLE PRECISION NOT NULL DEFAULT '0.0',
    longitude_end DOUBLE PRECISION NOT NULL DEFAULT '0.0'
);

ALTER TABLE driver ADD CONSTRAINT driver_fk FOREIGN KEY (id) REFERENCES user (id) ON DELETE CASCADE;
ALTER TABLE passenger ADD CONSTRAINT passenger_fk FOREIGN KEY (id) REFERENCES user (id) ON DELETE CASCADE;

INSERT INTO user(id, name, password, role) VALUES(1, 'joni','1234','DRIVER');
INSERT INTO user(id, name, password, role) VALUES(2, 'budi','1234','PASSENGER');

INSERT INTO driver(id, status, latitude, longitude) VALUES(1,'INACTIVE', 123.2, 345.1);
INSERT INTO passenger(id, status) VALUES(2,'INACTIVE');

# --- !Downs

DROP TABLE user;
DROP TABLE driver;
DROP TABLE passenger;
DROP TABLE trip;