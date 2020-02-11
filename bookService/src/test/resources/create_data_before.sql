DELETE
FROM book_service_test.public.book_author;
DELETE
FROM book_service_test.public.author;
DELETE
FROM book_service_test.public.book;


INSERT INTO book_service_test.public.author(id, name, surname, birthday)
VALUES (1, 'Juliana', 'Kuzmina', '1964-06-15');

INSERT INTO book_service_test.public.author(id, name, surname, birthday)
VALUES (2, 'Rob', 'Harrop', '1964-06-15');

INSERT INTO book_service_test.public.author(id, name, surname, birthday)
VALUES (3, 'Chris', 'Sheder', '1964-06-15');

INSERT INTO book_service_test.public.book(id, name, volume, date_of_publishing, is_received_book)
VALUES (4, 'Spring 5 for professionals', '1120', '2019-11-05', false);

INSERT INTO book_service_test.public.book_author(id, book_id, author_id)
VALUES (5, 4, 1),
       (6, 4, 2),
       (7, 4, 3);


INSERT INTO book_service_test.public.author(id, name, surname, birthday)
VALUES (8, 'Tatyana', 'Zhurina', '1996-06-17');

INSERT INTO book_service_test.public.author(id, name, surname, birthday)
VALUES (9, 'Unknown', 'Author', '1999-06-17');

INSERT INTO book_service_test.public.book(id, name, volume, date_of_publishing, is_received_book)
VALUES (10, '55 oral topics in English', '155', '2003-11-05', false);

INSERT INTO book_service_test.public.book_author(id, book_id, author_id)
VALUES (11, 10, 8),
       (12, 10, 9);


INSERT INTO book_service_test.public.author(id, name, surname, birthday)
VALUES (13, 'Herbert', 'Shildt', '1977-06-17');

INSERT INTO book_service_test.public.book(id, name, volume, date_of_publishing, is_received_book)
VALUES (14, 'Java Complete guide', '1486', '2019-10-16', false);

INSERT INTO book_service_test.public.book_author(id, book_id, author_id)
VALUES (15, 14, 13);


INSERT INTO book_service_test.public.author(id, name, surname, birthday)
VALUES (16, 'Unknown', 'Author', '1999-06-17');

INSERT INTO book_service_test.public.book(id, name, volume, date_of_publishing, is_received_book)
VALUES (17, 'Unknown book', '10', '2019-10-16', false);

INSERT INTO book_service_test.public.book_author(id, book_id, author_id)
VALUES (18, 17, 16);
