/*==============================================================*/
/* DBMS name:      PostgreSQL 11, H2                            */
/* Created on:     30.03.2020 22:26:22                          */
/*==============================================================*/
DROP SEQUENCE IF EXISTS seq_person;
DROP SEQUENCE IF EXISTS seq_birth_place;
DROP SEQUENCE IF EXISTS seq_artifact;
DROP SEQUENCE IF EXISTS seq_note;
DROP SEQUENCE IF EXISTS seq_together;
DROP SEQUENCE IF EXISTS seq_box;

drop index IF EXISTS together3together_type_code_FK;
drop index IF EXISTS together3member1_2_person_FK;
drop index IF EXISTS together3member2_2_person_FK;
drop index IF EXISTS together_PK;
drop table IF EXISTS together;
drop index IF EXISTS together_type_PK;
drop table IF EXISTS together_type;

drop index IF EXISTS I_NOTE3PERSON_ID_2_PERSON_FK;
drop index IF EXISTS I_NOTE3ARTIFACT_ID_FK;
drop index IF EXISTS I_note_PK;
drop table IF EXISTS note;

drop index IF EXISTS I_ARTIFACT3OWNER_ID_2_PERSON_FK;
drop index IF EXISTS I_ARTIFACT3OWNER_ID_2_PERSON_FK;
drop index IF EXISTS I_ARTIFACT3TYPE_CODE_FK;
drop index IF EXISTS I_ARTIFACT_PK;
drop table IF EXISTS artifact;

drop index IF EXISTS I_artifact_type_PK;
drop table IF EXISTS artifact_type;

drop index IF EXISTS I_birth_place_PK;
drop table IF EXISTS birth_place;

drop index IF EXISTS I_BOX3BOX_TYPE_CODE_FK;
drop index IF EXISTS I_box_PK;
drop table IF EXISTS box;

drop index IF EXISTS I_box_type_PK;
drop table IF EXISTS box_type;

drop index IF EXISTS I_PARENT_CHILD3CHILD_ID_FK;
drop index IF EXISTS I_parent_child_PK;
drop table IF EXISTS parent_child;

drop index IF EXISTS person_PK;
drop table IF EXISTS person;

