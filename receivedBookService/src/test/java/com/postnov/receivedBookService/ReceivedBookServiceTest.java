package com.postnov.receivedBookService;

import com.postnov.receivedBookService.Service.EntityService.BookService;
import com.postnov.receivedBookService.Service.EntityService.LibraryCardService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql(value = {"/create_data_before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create_data_after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ReceivedBookServiceTest {

    private static final Long LIBRARY_CARD_ID = 6L;

    private static final String PASSPORT_NUMBER = "4567";

    private static final String PASSPORT_SERIES = "1553445";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibraryCardService libraryCardService;

    @MockBean
    private BookService bookService;

    @Before
    public void init() {
        when(libraryCardService.getLibraryCardIdByPassportNumberAndSeries(PASSPORT_NUMBER, PASSPORT_SERIES)).thenReturn(LIBRARY_CARD_ID);
        when(bookService.getReceivedBookDtoById(13L)).thenReturn(null);
        when(bookService.getBookDtoById(13L)).thenReturn(null);
        when(libraryCardService.getLibraryCardDtoById(6L)).thenReturn(null);
    }

    @Test
    public void getReceivedBooksByPassportSNumberAndSeriesTest1() throws Exception {
        mockMvc.perform(get(String.format("/received/books/filter?passportNumber=%s&passportSeries=%s", PASSPORT_NUMBER, PASSPORT_SERIES)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect((MockMvcResultMatchers.content().string(containsString("2020-04-10"))));
    }

    @Test(expected = AssertionError.class)
    public void getReceivedBooksByPassportSNumberAndSeriesTest2() throws Exception {
        mockMvc.perform(get(String.format("/received/books/filter?passportNumber=%s&passportSeries=%s", PASSPORT_NUMBER, PASSPORT_SERIES)))
                .andDo(print())
                .andExpect((MockMvcResultMatchers.content().string(containsString("2020-02-10"))));
    }

    @Test
    public void getHistoryReceivedBooksByPassportNumberAndSeriesTest() throws Exception {
        mockMvc.perform(get(String.format("/history/received/books/filter?passportNumber=%s&passportSeries=%s", PASSPORT_NUMBER, PASSPORT_SERIES)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect((MockMvcResultMatchers.content().string(containsString("2020-02-10"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("2020-03-10"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("2020-04-10"))));
    }

    @Test
    public void getAllReceivedBooksTest1() throws Exception {
        mockMvc.perform(get(String.format("/all/received/books/filter?fromReceivedBookId=%s&toReceivedBookId=%s", 1L, 20L)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect((MockMvcResultMatchers.content().string(containsString("2020-04-10"))));
    }

    @Test(expected = AssertionError.class)
    public void getAllReceivedBooksTest2() throws Exception {
        mockMvc.perform(get(String.format("/all/received/books/filter?fromReceivedBookId=%s&toReceivedBookId=%s", 1L, 20L)))
                .andDo(print())
                .andExpect((MockMvcResultMatchers.content().string(containsString("2020-02-10"))));
    }
}
