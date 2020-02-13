package com.postnov.clientService;

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
@SpringBootTest(classes = ClientServiceApp.class)
@AutoConfigureMockMvc
@Sql(value = {"/create_libraryCard_after.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create_libraryCard_after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
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
                "\t\"client\":\n" +
                "\t\t{\n" +
                "\t\t    \"phone\": \"89533576500\",\n" +
                "\t\t    \"email\": \"postnov-90@mail.ru\",\n" +
                "\t\t    \"passport\":\n" +
                "\t\t        {\n" +
                "\t\t            \"name\": \"Roman\",\n" +
                "\t\t            \"surname\": \"Big\",\n" +
                "\t\t            \"birthday\": \"1964-06-15\",\n" +
                "\t\t            \"number\": \"4535\",\n" +
                "\t\t            \"series\": \"1553445\",\n" +
                "\t\t            \"authorityIssuer\": \"Piter\",\n" +
                "\t\t            \"dateSigning\": \"1990-05-05\"\n" +
                "\t\t        }\n" +
                "\t\t}\n" +
                "\t}\n" +
                "]";
    }

    @Test
    public void rabbitTest() throws Exception {
        send(createJson());
        Thread.sleep(100);
        mockMvc.perform(get(String.format("/libraryCard/filter?passportNumber=%s&passportSeries=%s", 4535, 1553445)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect((MockMvcResultMatchers.content().string(containsString("Roman"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("Big"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("4535"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("1553445"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("Piter"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("1964-06-15"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("89533576500"))))
                .andExpect((MockMvcResultMatchers.content().string(containsString("postnov-90@mail.ru"))));
    }
}
