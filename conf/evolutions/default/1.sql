# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table faccount (
  id                        bigint auto_increment not null,
  username                  varchar(255),
  account_project           varchar(255),
  account_money             float,
  account_date              datetime,
  account_bank              varchar(255),
  create_at                 varchar(255),
  constraint pk_faccount primary key (id))
;

create table fcard (
  id                        bigint auto_increment not null,
  username                  varchar(255),
  bank_name                 varchar(255),
  credit_money              float,
  debt_money                float,
  repay_day                 varchar(255),
  constraint pk_fcard primary key (id))
;

create table fuser (
  id                        bigint auto_increment not null,
  username                  varchar(255),
  password                  varchar(255),
  mobile                    varchar(255),
  email                     varchar(255),
  constraint pk_fuser primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table faccount;

drop table fcard;

drop table fuser;

SET FOREIGN_KEY_CHECKS=1;

