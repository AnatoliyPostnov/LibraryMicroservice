package com.postnov.bookService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.UnsupportedEncodingException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookServiceApplication.class)
@AutoConfigureMockMvc
@Sql(value = {"/create_data_before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create_data_after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getBookByBookNameAndBookVolumeTest() throws Exception {
        mockMvc.perform(get(String.format("/book/filter?bookName=%s&bookVolume=%s", "Spring 5 for professionals", 1120)))
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
    public void getBooksByAuthorNameAndAuthorSurnameTest() throws Exception {
        mockMvc.perform(get(String.format("/books/author/filter?authorName=%s&authorSurname=%s", "Juliana", "Kuzmina")))
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
    public void getBooksFromBookIdToBookIdTest() throws Exception {
        assertEquals(4,
                countContents(
                        mockMvc.perform(get(String.format("/books/filter?fromBookId=%s&toBookId=%s", 1, 20)))
                                .andDo(print())
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andReturn())
                        .getBooksDto().size()
        );
    }

    private ListBookDto countContents(MvcResult mvcResult) throws UnsupportedEncodingException, JsonProcessingException {
        String content = "{\"booksDto\":" + mvcResult.getResponse().getContentAsString() + "}";
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(content, ListBookDto.class);
    }

}
