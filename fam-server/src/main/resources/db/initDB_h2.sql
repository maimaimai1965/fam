DROP TABLE IF EXISTS person;
DROP SEQUENCE IF EXISTS seq_person;

CREATE SEQUENCE seq_person START WITH 100000;
CREATE TABLE person
(
    id          BIGINT NOT NULL DEFAULT nextval('seq_person') PRIMARY KEY,
    surname     VARCHAR  NOT NULL,
    first_name  VARCHAR,
    middle_name VARCHAR,
    birth_date  TIMESTAMP,
    death_date  TIMESTAMP,
    gender      VARCHAR(1),
    CONSTRAINT cntr_person_gender CHECK (gender IN ('M','F'))
);
