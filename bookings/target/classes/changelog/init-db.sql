CREATE SEQUENCE hibernate_sequence START 1;
CREATE SEQUENCE booking_seq
  MINVALUE 1
  START WITH 50
  INCREMENT BY 50;
CREATE TABLE booking (
    id bigint PRIMARY KEY,
    account_id bigint NOT NULL,
    car_park_no varchar NOT NULL,
    quantity int NOT NULL,
    created_date timestamp NOT NULL
);

