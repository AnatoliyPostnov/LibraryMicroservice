create table author
(
    id       int8 not null,
    name     varchar(255),
    surname  varchar(255),
    birthday date,
    primary key (id)
);

create table book
(
    id     int8 not null,
    name   varchar(255),
    volume int4,
    date_of_publishing date,
    is_received_book boolean,
    primary key (id)
);

create table book_author
(
    id        int8 not null,
    author_id int8,
    book_id   int8,
    primary key (id),
    FOREIGN KEY (author_id) REFERENCES author (id),
    FOREIGN KEY (book_id) REFERENCES book (id)
);