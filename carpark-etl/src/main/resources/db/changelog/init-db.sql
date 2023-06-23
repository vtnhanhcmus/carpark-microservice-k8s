
CREATE SEQUENCE hibernate_sequence START 1;
CREATE SEQUENCE carpark_seq
  MINVALUE 1
  START WITH 50
  INCREMENT BY 50;

CREATE TABLE car_park (
    id serial PRIMARY KEY,
    car_park_no varchar UNIQUE NOT NULL,
    address varchar NULL,
    coordinate geometry(Geometry, 4326) NOT NULL
);

CREATE TABLE availability (
    id serial PRIMARY KEY,
    car_park_id bigint NOT NULL,
    total_lots int NULL,
    lots_available int NULL,
    last_updated_on timestamp NOT NULL,
    CONSTRAINT car_park_id_unique UNIQUE (car_park_id),
    CONSTRAINT fk_car_park FOREIGN KEY(car_park_id) REFERENCES car_park(id)
);
