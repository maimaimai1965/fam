/*==============================================================*/
/* DBMS name:      PostgreSQL 9.x                               */
/* Created on:     21.04.2020 19:43:26                          */
/*==============================================================*/


drop index I_ARTIFACT3OWNER_ID_2_PERSON_FK;

drop index I_ARTIFACT3TYPE_CODE_FK;

drop index I_ARTIFACT_PK;

drop table artifact;

drop index I_artifact_type_PK;

drop table artifact_type;

drop index I_birth_place_PK;

drop table birth_place;

drop index I_BOX3BOX_TYPE_CODE_FK;

drop index I_box_PK;

drop table box;

drop index I_box_type_PK;

drop table box_type;

drop index I_NOTE3PERSON_ID_2_PERSON_FK;

drop index I_NOTE3ARTIFACT_ID_FK;

drop index I_note_PK;

drop table note;

drop index I_PARENT_CHILD3CHILD_ID_FK;

drop index I_parent_child_PK;

drop table parent_child;

drop index person_PK;

drop table person;

drop index together3together_type_code_FK;

drop index together3person1_id_2_person_FK;

drop index together3person2_id_2_person_FK;

drop index together_PK;

drop table together;

drop index together_type_PK;

drop table together_type;

/*==============================================================*/
/* Table: artifact                                              */
/*==============================================================*/
create table artifact (
   id                   BIGINT               not null,
   version              BIGINT               not null,
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
/* Table: birth_place                                           */
/*==============================================================*/
create table birth_place (
   id                   BIGINT               not null,
   description          VARCHAR(200)         not null,
   constraint PK_BIRTH_PLACE primary key (id)
);

/*==============================================================*/
/* Index: I_birth_place_PK                                      */
/*==============================================================*/
create unique index I_birth_place_PK on birth_place (
id
);

/*==============================================================*/
/* Table: box                                                   */
/*==============================================================*/
create table box (
   id                   BIGINT               not null,
   version              BIGINT               not null,
   box_type_code        VARCHAR(30)          not null,
   description          VARCHAR(100)         null,
   box_char             TEXT                 null,
   box_bin              BYTEA                null,
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
/* Table: note                                                  */
/*==============================================================*/
create table note (
   id                   BIGINT               not null,
   version              BIGINT               not null,
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

/*==============================================================*/
/* Table: person                                                */
/*==============================================================*/
create table person (
   id                   BIGINT               not null,
   version              BIGINT               not null,
   surname              VARCHAR(50)          not null,
   first_name           VARCHAR(25)          null,
   middle_name          VARCHAR(30)          null,
   birth_date           TIMESTAMP            null,
   death_date           TIMESTAMP            null,
   gender               CHAR(1)              not null,
   constraint PK_PERSON primary key (id)
);

/*==============================================================*/
/* Index: person_PK                                             */
/*==============================================================*/
create unique index person_PK on person (
id
);

/*==============================================================*/
/* Table: together                                              */
/*==============================================================*/
create table together (
   id                   BIGINT               not null,
   version              BIGINT               not null,
   together_type_code   VARCHAR(30)          not null,
   person1_id           BIGINT               not null,
   person2_id           BIGINT               not null,
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
/* Index: together3person2_id_2_person_FK                       */
/*==============================================================*/
create  index together3person2_id_2_person_FK on together (
person2_id
);

/*==============================================================*/
/* Index: together3person1_id_2_person_FK                       */
/*==============================================================*/
create  index together3person1_id_2_person_FK on together (
person1_id
);

/*==============================================================*/
/* Index: together3together_type_code_FK                        */
/*==============================================================*/
create  index together3together_type_code_FK on together (
together_type_code
);

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

alter table birth_place
   add constraint FK_BIRTH_PLACE3ID_2_PERSON foreign key (id)
      references person (id)
      on delete restrict on update restrict;

alter table box
   add constraint FK_BOX3BOX_TYPE_CODE foreign key (box_type_code)
      references box_type (code)
      on delete restrict on update restrict;

alter table note
   add constraint FK_NOTEA3ARTIFACT_ID_2_ARTIFACT foreign key (artifact_id)
      references artifact (id)
      on delete restrict on update restrict;

alter table note
   add constraint FK_NOTE3PERSON_ID_2_PERSON foreign key (person_id)
      references person (id)
      on delete restrict on update restrict;

alter table parent_child
   add constraint FK_PARENT_CHILD3CHILD_ID foreign key (child_id)
      references person (id)
      on delete restrict on update restrict;

alter table parent_child
   add constraint FK_PARENT_CHILD3PARENT_ID foreign key (parent_id)
      references person (id)
      on delete restrict on update restrict;

alter table together
   add constraint FK_TOGETHER3MEMBER1 foreign key (person1_id)
      references person (id)
      on delete restrict on update restrict;

alter table together
   add constraint FK_TOGETHER3MEMBER2 foreign key (person2_id)
      references person (id)
      on delete restrict on update restrict;

alter table together
   add constraint FK_TOGETHER3TOGETHER_TYPE_CODE foreign key (together_type_code)
      references together_type (code)
      on delete restrict on update restrict;

