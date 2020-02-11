package com.postnov.bookService;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookServiceApplication.class)
@AutoConfigureMockMvc
@Sql(value = {"/create_data_after.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create_data_after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class RabbitMQTest {

    @Autowired
    private MockMvc mockMvc;

    @Value("${host}")
    private String host;

    @Value("${queueName}")
    private String queueName;

    private void send(String json) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(queueName, false, false, false, null);
            channel.basicPublish("", queueName, null, json.getBytes(StandardCharsets.UTF_8));
        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }

    private String createJson() {
        return "[\n" +
                "\t{\n" +
                "\t    \"name\": \"Spring 5 for professionals\",\n" +
                "\t    \"volume\": \"1120\",\n" +
                "\t    \"dateOfPublishing\": \"2019-11-05\",\n" +
                "\t    \"authors\": [\n" +
                "\t        {\n" +
                "\t            \"name\": \"Juliana\",\n" +
                "\t            \"surname\": \"Kuzmina\",\n" +
                "\t            \"birthday\": \"1964-06-15\"\n" +
                "\t        },\n" +
                "\t        {\n" +
                "\t            \"name\": \"Rob\",\n" +
                "\t            \"surname\": \"Harrop\",\n" +
                "\t            \"birthday\": \"1964-06-15\"\n" +
                "\t        },\n" +
                "\t        {\n" +
                "\t            \"name\": \"Chris\",\n" +
                "\t            \"surname\": \"Sheder\",\n" +
                "\t            \"birthday\": \"1964-06-15\"\n" +
                "\t        }\n" +
                "\t    ]\n" +
                "\t}\n" +
                "]";
    }

    @Test
    public void rabbitTest() throws Exception {
        send(createJson());
        Thread.sleep(1000);
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
}