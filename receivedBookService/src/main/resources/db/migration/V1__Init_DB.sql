create table received_book
(
    id                          int8 not null,
    book_id                     int8 not null,
    library_card_id             int8 not null,
    date_of_book_receiving      date,
    date_of_book_return         date,
    primary key (id)
);
create table received_book_message
(
    id      int8 not null,
    message text,
    primary key (id)
);