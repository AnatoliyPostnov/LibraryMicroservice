package com.postnov.bookService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.postnov.bookService.Dto.AuthorDto;
import com.postnov.bookService.Dto.BookDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookServiceApplication.class)
@AutoConfigureMockMvc
@Sql(value = {"/create_data_before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create_data_after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class CommunicateWithReceivedBookServiceControllerTest {

    private static final Long RECEIVED_BOOK_ID = 4L;

    private static final Long NOT_RECEIVED_BOOK_ID = 14L;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getReceivedBookDtoByIdTest() throws Exception {
        mockMvc.perform(post("/CommunicateWithReceivedBook/received/book")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(convertFromBookDtoToJson(createBookWithTreeAuthors())));

        mockMvc.perform(get(String.format("/CommunicateWithReceivedBook/filter?ReceivedBookId=%s", RECEIVED_BOOK_ID)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect((MockMvcResultMatchers.content().string(containsString("Juliana"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("Kuzmina"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("1964-06-15"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("Rob"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("Harrop"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("1964-06-15"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("Chris"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("Sheder"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("1964-06-15"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("Spring 5 for professionals"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("1120"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("2019-11-05"))));
    }

    @Test
    public void getBookDtoExistingOfTheLibraryByIdTest() throws Exception {
        mockMvc.perform(post("/CommunicateWithReceivedBook/received/book")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(convertFromBookDtoToJson(createBookWithTreeAuthors())));

        mockMvc.perform(get(String.format("/CommunicateWithReceivedBook/BookDtoExistingOfTheLibrary/filter?BookId=%s", RECEIVED_BOOK_ID)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect((MockMvcResultMatchers.content().string(containsString("Juliana"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("Kuzmina"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("1964-06-15"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("Rob"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("Harrop"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("1964-06-15"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("Chris"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("Sheder"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("1964-06-15"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("Spring 5 for professionals"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("1120"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("2019-11-05"))));

        mockMvc.perform(get(String.format("/CommunicateWithReceivedBook/BookDtoExistingOfTheLibrary/filter?BookId=%s", NOT_RECEIVED_BOOK_ID)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect((MockMvcResultMatchers.content().string(containsString("Herbert"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("Shildt"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("1977-06-17"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("Java Complete guide"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("1486"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("2019-10-16"))));
    }

    @Test
    public void getBookIdByBookNameAndBookVolumeTest() throws Exception {
        assertEquals("4", mockMvc.perform(get(String.format("/CommunicateWithReceivedBook/BookId/filter?bookName=%s&bookVolume=%s", "Spring 5 for professionals", 1120)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString());
    }

    @Test
    public void getReceivedBooksIdByBookNameTest() throws Exception {

        mockMvc.perform(post("/CommunicateWithReceivedBook/received/book")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(convertFromBookDtoToJson(createBookWithTreeAuthors())));

        mockMvc.perform(get(String.format("/CommunicateWithReceivedBook/receivedBookId/filter?bookName=%s", "Spring 5 for professionals")))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void returnBookTest() throws Exception {

        mockMvc.perform(post("/CommunicateWithReceivedBook/received/book")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(convertFromBookDtoToJson(createBookWithTreeAuthors())));

        mockMvc.perform(post("/CommunicateWithReceivedBook/return/book")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(RECEIVED_BOOK_ID.toString()));

        mockMvc.perform(get(String.format("/CommunicateWithReceivedBook/filter?ReceivedBookId=%s", RECEIVED_BOOK_ID)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void deleteBookByBookIdTest() throws Exception {
        mockMvc.perform(delete(String.format("/CommunicateWithReceivedBook/delete/filter?bookId=%s", RECEIVED_BOOK_ID)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
        ;
        assertEquals(3,
                convertFromJsonToListBookDto(
                        mockMvc.perform(get(String.format("/books/filter?fromBookId=%s&toBookId=%s", 1, 20)))
                                .andDo(print())
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andReturn())
                        .getBooksDto().size()
        );
    }

    public static BookDto createBookWithTreeAuthors() {
        AuthorDto authorDto1 = new AuthorDto();
        authorDto1.setName("Juliana");
        authorDto1.setSurname("Kuzmina");
        authorDto1.setBirthday(LocalDate.of(1964, 06, 15));

        AuthorDto authorDto2 = new AuthorDto();
        authorDto2.setName("Rob");
        authorDto2.setSurname("Harrop");
        authorDto2.setBirthday(LocalDate.of(1964, 06, 15));

        AuthorDto authorDto3 = new AuthorDto();
        authorDto3.setName("Chris");
        authorDto3.setSurname("Sheder");
        authorDto3.setBirthday(LocalDate.of(1964, 06, 15));

        List<AuthorDto> authorsDto = new ArrayList<>();
        authorsDto.add(authorDto1);
        authorsDto.add(authorDto2);
        authorsDto.add(authorDto3);

        BookDto bookDto = new BookDto();
        bookDto.setName("Spring 5 for professionals");
        bookDto.setVolume(1120);
        bookDto.setDateOfPublishing(LocalDate.of(2019, 05, 11));
        bookDto.setAuthors(authorsDto);
        return bookDto;
    }

    private ListBookDto convertFromJsonToListBookDto(MvcResult mvcResult) throws UnsupportedEncodingException, JsonProcessingException {
        String content = "{\"booksDto\":" + mvcResult.getResponse().getContentAsString() + "}";
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(content, ListBookDto.class);
    }

    private String convertFromBookDtoToJson(BookDto bookDto) throws IOException {
        StringWriter writer = new StringWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(writer, bookDto);
        return writer.toString();
    }

}
