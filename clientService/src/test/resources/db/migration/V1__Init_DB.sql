create table passport
(
    id                   int8 not null,
    name                 varchar(255),
    surname              varchar(255),
    birthday             date,
    number               varchar(255),
    series               varchar(255),
    authority_issuer     varchar(255),
    date_signing         date,
    primary key (id)
);

create table client
(
    id                   int8 not null,
    phone                varchar(255),
    email                varchar(255),
    passport_id          int8 not null,
    primary key (id),
    FOREIGN KEY (passport_id) REFERENCES passport (id)
);

create table library_card
(
    id                  int8 not null,
    client_id           int8 not null,
    primary key (id),
    FOREIGN KEY (client_id) REFERENCES client (id)
);