/*==============================================================*/
/* DBMS name:      PostgreSQL 11, H2                            */
/* Created on:     30.03.2020 22:26:22                          */
/*==============================================================*/
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

/*==============================================================*/
/* Table: person                                                */
/*==============================================================*/
DROP SEQUENCE IF EXISTS seq_person;
CREATE SEQUENCE seq_person START WITH 100000;
create table person (
  id                   BIGINT               DEFAULT nextval('seq_person'),
  surname              VARCHAR(50)          not null,
  first_name           VARCHAR(25)          null,
  middle_name          VARCHAR(30)          null,
  birth_date           TIMESTAMP            null,
  death_date           TIMESTAMP            null,
  gender               CHAR(1)              null,
  constraint PK_PERSON primary key (id),
  CONSTRAINT cnstr_person_gender CHECK (gender IN ('M','F'))
);

/*==============================================================*/
/* Index: person_PK                                             */
/*==============================================================*/
create unique index person_PK on person (
  id
);


/*==============================================================*/
/* Table: parent_child                                          */
/*==============================================================*/
create table parent_child (
  parent_id            BIGINT               not null,
  child_id             BIGINT               not null,
  constraint PK_PARENT_CHILD primary key (parent_id, child_id)
);
/*==============================================================*/
/* Index: I_parent_child_PK                                     */
/*==============================================================*/
create unique index I_parent_child_PK on parent_child (
  parent_id,
  child_id
);
/*==============================================================*/
/* Index: I_PARENT_CHILD3CHILD_ID_FK                            */
/*==============================================================*/
create  index I_PARENT_CHILD3CHILD_ID_FK on parent_child (
     child_id
    );

alter table parent_child
    add constraint FK_PARENT_CHILD3CHILD_ID foreign key (child_id)
        references person (id)
        on delete restrict on update restrict;

alter table parent_child
    add constraint FK_PARENT_CHILD3PARENT_ID foreign key (parent_id)
        references person (id)
        on delete restrict on update restrict;


/*==============================================================*/
/* Table: box_type                                              */
/*==============================================================*/
create table box_type (
  code                 VARCHAR(30)          not null,
  description          VARCHAR(100)         not null,
  is_bin               CHAR(1)              not null,
  constraint PK_BOX_TYPE primary key (code)
);
/*==============================================================*/
/* Index: I_box_type_PK                                         */
/*==============================================================*/
create unique index I_box_type_PK on box_type (
  code
);


/*==============================================================*/
/* Table: box                                                   */
/*==============================================================*/
DROP SEQUENCE IF EXISTS seq_box;
CREATE SEQUENCE seq_box START WITH 100000;

create table box (
  id                   BIGINT               DEFAULT nextval('seq_box'),
  description          VARCHAR(100)         null,
  box_type_code        VARCHAR(30)          not null,
  box_char             VARCHAR(1)           null,
  box_bin              CHAR                 null,
  constraint PK_BOX primary key (id)
);
/*==============================================================*/
/* Index: I_box_PK                                              */
/*==============================================================*/
create unique index I_box_PK on box (
  id
);
/*==============================================================*/
/* Index: I_BOX3BOX_TYPE_CODE_FK                                */
/*==============================================================*/
create  index I_BOX3BOX_TYPE_CODE_FK on box (
  box_type_code
);

alter table box
    add constraint FK_BOX3BOX_TYPE_CODE foreign key (box_type_code)
        references box_type (code)
        on delete restrict on update restrict;



/*==============================================================*/
/* Table: birth_place                                           */
/*==============================================================*/
DROP SEQUENCE IF EXISTS seq_birth_place;
CREATE SEQUENCE seq_birth_place START WITH 100000;

create table birth_place (
  id                   BIGINT               DEFAULT nextval('seq_birth_place'),
  description          VARCHAR(200)         not null,
  constraint PK_BIRTH_PLACE primary key (id)
);
/*==============================================================*/
/* Index: I_birth_place_PK                                      */
/*==============================================================*/
create unique index I_birth_place_PK on birth_place (
  id
);

alter table birth_place
    add constraint FK_BIRTH_PLACE3ID_2_PERSON foreign key (id)
        references person (id)
        on delete restrict on update restrict;


/*==============================================================*/
/* Table: artifact_type                                         */
/*==============================================================*/
create table artifact_type (
  code                 VARCHAR(30)          not null,
  name                 VARCHAR(100)         not null,
  constraint PK_ARTIFACT_TYPE primary key (code)
);
/*==============================================================*/
/* Index: I_artifact_type_PK                                    */
/*==============================================================*/
create unique index I_artifact_type_PK on artifact_type (
  code
);

/*==============================================================*/
/* Table: artifact                                              */
/*==============================================================*/
DROP SEQUENCE IF EXISTS seq_artifact;
CREATE SEQUENCE seq_artifact START WITH 100000;

create table artifact (
  id                   BIGINT               DEFAULT nextval('seq_artifact'),
  artifact_type_code   VARCHAR(30)          not null,
  owner_id             BIGINT               not null,
  name                 VARCHAR(100)         null,
  description          VARCHAR(500)         null,
  link                 VARCHAR(200)         null,
  box_id               BIGINT               null,
  constraint PK_ARTIFACT primary key (id)
);
/*==============================================================*/
/* Index: I_ARTIFACT_PK                                         */
/*==============================================================*/
create unique index I_ARTIFACT_PK on artifact (
  id
);
/*==============================================================*/
/* Index: I_ARTIFACT3TYPE_CODE_FK                               */
/*==============================================================*/
create  index I_ARTIFACT3TYPE_CODE_FK on artifact (
  artifact_type_code
);
/*==============================================================*/
/* Index: I_ARTIFACT3OWNER_ID_2_PERSON_FK                       */
/*==============================================================*/
create  index I_ARTIFACT3OWNER_ID_2_PERSON_FK on artifact (
  owner_id
);

alter table artifact
   add constraint FK_ARTIFACT3ARTIFACT_TYPE_CODE foreign key (artifact_type_code)
      references artifact_type (code)
      on delete restrict on update restrict;

alter table artifact
   add constraint FK_ARTIFACT3BOX_ID foreign key (box_id)
      references box (id)
      on delete restrict on update restrict;

alter table artifact
   add constraint FK_ARTIFACT3OWNER_ID_2_PERSON foreign key (owner_id)
      references person (id)
      on delete restrict on update restrict;

/*==============================================================*/
/* Table: note                                                  */
/*==============================================================*/
DROP SEQUENCE IF EXISTS seq_note;
CREATE SEQUENCE seq_note START WITH 100000;

create table note (
                      id                   BIGINT               DEFAULT nextval('seq_note'),
                      person_id            BIGINT               not null,
                      artifact_id          BIGINT               not null,
                      description          VARCHAR(500)         not null,
                      constraint PK_NOTE primary key (id)
);
/*==============================================================*/
/* Index: I_note_PK                                             */
/*==============================================================*/
create unique index I_note_PK on note (
                                       id
    );
/*==============================================================*/
/* Index: I_NOTE3ARTIFACT_ID_FK                                 */
/*==============================================================*/
create  index I_NOTE3ARTIFACT_ID_FK on note (
                                             artifact_id
    );
/*==============================================================*/
/* Index: I_NOTE3PERSON_ID_2_PERSON_FK                          */
/*==============================================================*/
create  index I_NOTE3PERSON_ID_2_PERSON_FK on note (
                                                    person_id
    );

alter table note
    add constraint FK_NOTEA3ARTIFACT_ID_2_ARTIFACT foreign key (artifact_id)
        references artifact (id)
        on delete restrict on update restrict;

alter table note
    add constraint FK_NOTE3PERSON_ID_2_PERSON foreign key (person_id)
        references person (id)
        on delete restrict on update restrict;


/*==============================================================*/
/* Table: together_type                                         */
/*==============================================================*/
create table together_type (
   code                 VARCHAR(30)          not null,
   name                 VARCHAR(100)         not null,
   constraint PK_TOGETHER_TYPE primary key (code)
);
/*==============================================================*/
/* Index: together_type_PK                                      */
/*==============================================================*/
create unique index together_type_PK on together_type (
code
);
		

/*==============================================================*/
/* Table: together                                              */
/*==============================================================*/
DROP SEQUENCE IF EXISTS seq_together;
CREATE SEQUENCE seq_together START WITH 100000;

create table together (
   id                   BIGINT               DEFAULT nextval('seq_together'),
   together_type_code   VARCHAR(30)          not null,
   member1              BIGINT               not null,
   member2              BIGINT               not null,
   start_date           DATE                 null,
   finish_date          DATE                 null,
   description          VARCHAR(500)         null,
   constraint PK_TOGETHER primary key (id)
);
/*==============================================================*/
/* Index: together_PK                                           */
/*==============================================================*/
create unique index together_PK on together (
id
);
/*==============================================================*/
/* Index: together3member1_2_person_FK                          */
/*==============================================================*/
create  index together3member1_2_person_FK on together (
member1
);
/*==============================================================*/
/* Index: together3member2_2_person_FK                          */
/*==============================================================*/
create  index together3member2_2_person_FK on together (
member2
);
/*==============================================================*/
/* Index: together3together_type_code_FK                        */
/*==============================================================*/
create  index together3together_type_code_FK on together (
together_type_code
);

alter table together
   add constraint FK_TOGETHER3MEMBER1 foreign key (member1)
      references person (id)
      on delete restrict on update restrict;

alter table together
   add constraint FK_TOGETHER3MEMBER2 foreign key (member2)
      references person (id)
      on delete restrict on update restrict;

alter table together
   add constraint FK_TOGETHER3TOGETHER_TYPE_CODE foreign key (together_type_code)
      references together_type (code)
      on delete restrict on update restrict;
		
		