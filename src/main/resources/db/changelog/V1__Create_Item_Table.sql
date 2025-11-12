--liquibase formatted sql
--changeset newgor:1

CREATE TABLE item
(
    id               UUID PRIMARY KEY,
    create_date      TIMESTAMP NOT NULL,
    last_update_date TIMESTAMP,
    name             VARCHAR(255),
    amount           VARCHAR(255),
    currency         VARCHAR(50),
    description      TEXT,
    link_to_site     VARCHAR(1024),
    priority         VARCHAR(50),
    image            VARCHAR(1024),
    status_code      VARCHAR(50),
    status_name      VARCHAR(100),
    reserved         BOOLEAN   NOT NULL DEFAULT FALSE
);
