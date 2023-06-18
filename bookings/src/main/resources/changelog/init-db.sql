CREATE TABLE booking (
    id serial PRIMARY KEY,
    account_id varchar NOT NULL,
    slot int NOT NULL,
    created_date timestamp NOT NULL
);

