DROP TABLE IF EXISTS person;
DROP SEQUENCE IF EXISTS seq_person;

CREATE SEQUENCE seq_person START WITH 100000;
CREATE TABLE person
(
    id          BIGINT PRIMARY KEY DEFAULT nextval('seq_person'),
    surname     VARCHAR  NOT NULL,
    first_name  VARCHAR,
    middle_name VARCHAR,
    birth_date  TIMESTAMP,
    death_date  TIMESTAMP,
    gender      VARCHAR(1),
    CONSTRAINT cnstr_person_gender CHECK (gender IN ('M','F'))
);
