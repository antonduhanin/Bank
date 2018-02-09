CREATE TABLE "accounts" (
  "id"          INTEGER NOT NULL,
  "id_customer" INTEGER NOT NULL,
  "balance"     REAL    NOT NULL,
  "state"       VARCHAR NOT NULL,
  PRIMARY KEY ("id")
);
INSERT INTO public.accounts (id, id_customer, balance, state) VALUES (2, 2, 100, 'UNLOCK');
INSERT INTO public.accounts (id, id_customer, balance, state) VALUES (1, 2, 122.1, 'UNLOCK');
INSERT INTO public.accounts (id, id_customer, balance, state) VALUES (3, 8, 100, 'UNLOCK');

CREATE SEQUENCE table_id_seq_accounts
START WITH 10;
ALTER TABLE accounts
  ALTER COLUMN id
  SET DEFAULT NEXTVAL('table_id_seq_accounts');

CREATE TABLE "cards" (
  "id"         INTEGER NOT NULL,
  "id_account" INTEGER NOT NULL,
  "balance"    REAL    NOT NULL DEFAULT E'0',
  "state"      VARCHAR NOT NULL DEFAULT E'0',
  PRIMARY KEY ("id")
);

INSERT INTO public.cards (id, id_account, balance, state) VALUES (3, 2, 25, 'UNLOCK');
INSERT INTO public.cards (id, id_account, balance, state) VALUES (2, 1, 18.8, 'UNLOCK');
INSERT INTO public.cards (id, id_account, balance, state) VALUES (4, 3, 27.2, 'UNLOCK');

CREATE SEQUENCE table_id_seq_cards
START WITH 10;
ALTER TABLE cards
  ALTER COLUMN id
  SET DEFAULT NEXTVAL('table_id_seq_cards');

CREATE TABLE "news" (
  "id"        INTEGER        NOT NULL,
  "title"     VARCHAR        NOT NULL,
  "content"   VARCHAR(10000) NOT NULL,
  "recipient" INTEGER,
  PRIMARY KEY ("id")
);

INSERT INTO public.news (id, title, content, recipient) VALUES (1, 'first news', ' we are open', NULL);
INSERT INTO public.news (id, title, content, recipient) VALUES (2, 'second news', 'special', 2);
INSERT INTO public.news (id, title, content, recipient) VALUES (3, 'news', 'test', NULL);


CREATE SEQUENCE table_id_seq_news
START WITH 4;
ALTER TABLE news
  ALTER COLUMN id
  SET DEFAULT NEXTVAL('table_id_seq_news');

CREATE TABLE "roles" (
  "id"    INTEGER NOT NULL,
  "title" VARCHAR NULL     DEFAULT NULL,
  PRIMARY KEY ("id")
);

INSERT INTO public.roles (id, title) VALUES (1, 'Administrator');
INSERT INTO public.roles (id, title) VALUES (2, 'Customer');

CREATE SEQUENCE table_id_seq_roles
START WITH 3;
ALTER TABLE roles
  ALTER COLUMN id
  SET DEFAULT NEXTVAL('table_id_seq_roles');

CREATE TABLE "transactions" (
  "id"        INTEGER NOT NULL,
  "date"      DATE    NOT NULL,
  "title"     VARCHAR NOT NULL,
  "recipient" INTEGER NOT NULL,
  "id_card"   INTEGER NOT NULL,
  "summa"     REAL    NOT NULL DEFAULT E'0',
  PRIMARY KEY ("id")
);


INSERT INTO public.transactions (id, date, title, recipient, id_card, summa)
VALUES (1, '2017-11-08', 'transfer to card', 2, 4, 12.3);


CREATE SEQUENCE table_id_seq_transactions
START WITH 2;
ALTER TABLE transactions
  ALTER COLUMN id
  SET DEFAULT NEXTVAL('table_id_seq_transactions');


CREATE TABLE "users" (
  "id"      INTEGER NOT NULL,
  "name"    VARCHAR NOT NULL,
  "id_role" INTEGER NOT NULL,
  PRIMARY KEY ("id")
);

INSERT INTO public.users (id, name, id_role) VALUES (2, 'Jack', 2);
INSERT INTO public.users (id, name, id_role) VALUES (8, 'kane', 2);
INSERT INTO public.users (id, name, id_role) VALUES (9, 'john', 1);

CREATE SEQUENCE table_id_seq_users
START WITH 10;
ALTER TABLE users
  ALTER COLUMN id
  SET DEFAULT NEXTVAL('table_id_seq_users');
