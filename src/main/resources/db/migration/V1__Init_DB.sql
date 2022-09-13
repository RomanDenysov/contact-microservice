DROP TABLE IF EXISTS contacts CASCADE;

CREATE TABLE IF NOT EXISTS contacts
(
    id       int8 generated by default as identity,
    email    varchar(255) null,
    phone    varchar(255) null,
    telegram varchar(255) null,
    user_id int8 unique not null,
    PRIMARY KEY (id)
);