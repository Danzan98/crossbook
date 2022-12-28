--liquibase formatted sql

--changeset crossbook:1
CREATE TABLE IF NOT EXISTS book_record
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    author VARCHAR NOT NULL,
    capture_date TIMESTAMP,
    reader_id BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS reader
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    birthday TIMESTAMP,
    amount BIGINT NOT NULL
);