CREATE SEQUENCE hibernate_sequence START 1;
CREATE SEQUENCE account_seq
  MINVALUE 1
  START WITH 50
  INCREMENT BY 50;
CREATE TABLE accounts (
    id serial PRIMARY KEY,
    name varchar NOT NULL,
    email varchar NOT NULL,
    mobile_number varchar NOT NULL
);

