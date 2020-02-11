DELETE
FROM library_card_test.public.library_card;
DELETE
FROM library_card_test.public.client;
DELETE
FROM library_card_test.public.passport;

INSERT INTO library_card_test.public
    .passport(id, name, surname, birthday, number, series, authority_issuer, date_signing)
VALUES (1, 'Roman', 'Big', '1964-06-15', '4567', '1553445', 'Piter', '1990-05-05');

INSERT INTO library_card_test.public.client(id, phone, email, passport_id)
VALUES (2, '89533576500', 'postnov-90@mail.ru', 1);

INSERT INTO library_card_test.public.library_card(id, client_id)
VALUES (3, 2);

INSERT INTO library_card_test.public
    .passport(id, name, surname, birthday, number, series, authority_issuer, date_signing)
VALUES (4, 'Tolia', 'Smal', '1964-06-20', '4579', '1553456', 'Moskva', '1985-06-08');

INSERT INTO library_card_test.public.client(id, phone, email, passport_id)
VALUES (5, '89533123456', 'qwerty@mail.ru', 4);

INSERT INTO library_card_test.public.library_card(id, client_id)
VALUES (6, 5);