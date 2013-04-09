# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table faccount (
  id                        bigint not null,
  username                  varchar(255),
  account_project           varchar(255),
  account_money             float,
  account_date              timestamp,
  account_bank              varchar(255),
  create_at                 varchar(255),
  constraint pk_faccount primary key (id))
;

create table fcard (
  id                        bigint not null,
  username                  varchar(255),
  bank_name                 varchar(255),
  credit_money              float,
  debt_money                float,
  repay_day                 varchar(255),
  constraint pk_fcard primary key (id))
;

create table fuser (
  id                        bigint not null,
  username                  varchar(255),
  password                  varchar(255),
  mobile                    varchar(255),
  email                     varchar(255),
  constraint pk_fuser primary key (id))
;

create sequence faccount_seq;

create sequence fcard_seq;

create sequence fuser_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists faccount;

drop table if exists fcard;

drop table if exists fuser;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists faccount_seq;

drop sequence if exists fcard_seq;

drop sequence if exists fuser_seq;

