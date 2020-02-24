package com.postnov.receivedBookService;

import com.postnov.receivedBookService.Repository.ReceivedBookMessageRepository;
import com.postnov.receivedBookService.Service.ConsumerSevice.ConsumeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(value = {"/create_data_after.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create_data_after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class SaveReceivedBookMessageTest {

    private static final Long LIBRARY_CARD_ID = 6L;

    private static final Long BOOK_ID = 13L;

    private static final String PASSPORT_NUMBER = "4567";

    private static final String PASSPORT_SERIES = "1553445";

    private static final String BOOK_NAME = "Java. Полное руководство";

    private static final Integer BOOK_VOLUME = 1486;

    @Autowired
    private ConsumeService consumeService;

    @Autowired
    private ReceivedBookMessageRepository receivedBookMessageRepository;

    @Test
    public void receivedBookTest() {
        consumeService.parseMessage(DataForTest.RECEIVED_BOOK);
        assertEquals(1, receivedBookMessageRepository.findAll().size());
    }

    @Test
    public void returnBookTest() {
        consumeService.parseMessage(DataForTest.RETURN_BOOKS);
        assertEquals(1, receivedBookMessageRepository.findAll().size());
    }

    @Test
    public void deleteBookTest() {
        consumeService.parseMessage(DataForTest.DELETE_BOOK);
        assertEquals(1, receivedBookMessageRepository.findAll().size());
    }

    @Test
    public void deleteLibraryCardTest() {
        consumeService.parseMessage(DataForTest.DELETE_LIBRARY_CARD);
        assertEquals(1, receivedBookMessageRepository.findAll().size());
    }

    @Test
    public void receivedBookReturnBookDeleteBookDeleteLibraryCardTest() throws InterruptedException {
        consumeService.parseMessage(DataForTest.RECEIVED_BOOK);
        consumeService.parseMessage(DataForTest.RETURN_BOOKS);
        consumeService.parseMessage(DataForTest.DELETE_BOOK);
        consumeService.parseMessage(DataForTest.DELETE_LIBRARY_CARD);
        assertEquals(4, receivedBookMessageRepository.findAll().size());
    }

}
