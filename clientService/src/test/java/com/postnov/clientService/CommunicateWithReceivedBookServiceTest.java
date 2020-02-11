package com.postnov.clientService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.postnov.clientService.Dto.LibraryCardsDto;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientServiceApp.class)
@AutoConfigureMockMvc
@Sql(value = {"/create_libraryCard_before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create_libraryCard_after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class CommunicateWithReceivedBookServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getLibraryCardDtoByIdTest() throws Exception {
        mockMvc.perform(get(String.format("/CommunicateWithReceivedBook/filter?Id=%s", 3)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect((MockMvcResultMatchers.content().string(containsString("Roman"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("1964-06-15"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("4567"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("1553445"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("Piter"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("1990-05-05"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("89533576500"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("postnov-90@mail.ru"))));
    }

    @Test
    public void getLibraryCardIdByPassportNumberAndSeriesTest() throws Exception {
        mockMvc.perform(get(String.format("/CommunicateWithReceivedBook/get/Id?passportNumber=%s&passportSeries=%s", 4567, 1553445)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect((MockMvcResultMatchers.content().string(containsString("3"))));
    }

    @Test
    public void deleteLibraryCardTest() throws Exception {
        mockMvc.perform(delete(String.format("/CommunicateWithReceivedBook/delete/filter?passportNumber=%s&passportSeries=%s", 4567, 1553445)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
        assertEquals(1,
                countContents(
                        mockMvc.perform(get(String.format("/libraryCards/filter?fromLibraryCardsId=%s&toLibraryCardsId=%s", 1, 10)))
                                .andDo(print())
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andReturn())
                        .getLibraryCardsDto().size()
        );
    }

    private LibraryCardsDto countContents(MvcResult mvcResult) throws UnsupportedEncodingException, JsonProcessingException {
        String content = "{\"libraryCardsDto\":" + mvcResult.getResponse().getContentAsString() + "}";
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(content, LibraryCardsDto.class);
    }
}
