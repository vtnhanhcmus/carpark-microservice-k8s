CREATE SEQUENCE hibernate_sequence START 1;
CREATE TABLE accounts (
    id serial PRIMARY KEY,
    name varchar NOT NULL,
    email varchar NOT NULL,
    mobile_number varchar NOT NULL
);

