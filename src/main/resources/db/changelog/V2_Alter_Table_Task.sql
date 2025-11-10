--liquibase formatted sql
--changeset dpmolchanov:2
ALTER TABLE task
    ADD COLUMN description VARCHAR(255),
    ADD COLUMN application_client VARCHAR(255);