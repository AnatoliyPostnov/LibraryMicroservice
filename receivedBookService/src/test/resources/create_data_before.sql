DELETE
FROM received_book_test.public.received_book;

INSERT INTO received_book_test.public.received_book(id, book_id, library_card_id, date_of_book_receiving,
                                                    date_of_book_return)
VALUES (1, 13, 6, '2020-02-10', '2020-02-10'),
       (4, 13, 6, '2020-03-10', '2020-02-10'),
       (19, 13, 6, '2020-04-10', null);
