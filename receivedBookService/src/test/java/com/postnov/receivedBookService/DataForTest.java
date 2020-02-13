package com.postnov.receivedBookService;

class DataForTest {
    static String RECEIVED_BOOK = "receivedBook&{\n" +
            "\t\"book\":\n" +
            "\t\t{\n" +
            "\t\t    \"name\": \"Java. Полное руководство\",\n" +
            "\t\t    \"volume\": \"1486\",\n" +
            "\t\t    \"dateOfPublishing\": \"2019-10-16\",\n" +
            "\t\t    \"authors\": [\n" +
            "\t\t        {\n" +
            "\t\t            \"name\": \"Герберт\",\n" +
            "\t\t            \"surname\": \"Шилдт\",\n" +
            "\t\t            \"birthday\": \"1977-06-17\"\n" +
            "\t\t        }\n" +
            "\t\t    ]\n" +
            "\t\t},\n" +
            "\t\"libraryCard\":\n" +
            "\t\t{\n" +
            "\t\t\"client\":\n" +
            "\t\t\t{\n" +
            "\t\t\t    \"phone\": \"89533576500\",\n" +
            "\t\t\t    \"email\": \"postnov-90@mail.ru\",\n" +
            "\t\t\t    \"passport\":\n" +
            "\t\t\t        {\n" +
            "\t\t\t            \"name\": \"Петя\",\n" +
            "\t\t\t            \"surname\": \"Бубликов\",\n" +
            "\t\t\t            \"birthday\": \"1964-06-15\",\n" +
            "\t\t\t            \"number\": \"4567\",\n" +
            "\t\t\t            \"series\": \"1553445\",\n" +
            "\t\t\t            \"authorityIssuer\": \"Piter\",\n" +
            "\t\t\t            \"dateSigning\": \"1990-05-05\"\n" +
            "\t\t\t        }\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\t\n" +
            "}";

    static String RETURN_BOOKS = "returnBooks&4567&1553445&Java. Полное руководство";

    static String DELETE_LIBRARY_CARD = "deleteLibraryCard&4567&1553445";

    static String DELETE_BOOK = "deletedBook&Java. Полное руководство&1486";

    static String BOOK_DTO = "{\n" +
            "\t\t    \"name\": \"Java. Полное руководство\",\n" +
            "\t\t    \"volume\": \"1486\",\n" +
            "\t\t    \"dateOfPublishing\": \"2019-10-16\",\n" +
            "\t\t    \"authors\": [\n" +
            "\t\t        {\n" +
            "\t\t            \"name\": \"Герберт\",\n" +
            "\t\t            \"surname\": \"Шилдт\",\n" +
            "\t\t            \"birthday\": \"1977-06-17\"\n" +
            "\t\t        }\n" +
            "\t\t    ]\n" +
            "\t\t}";
}
