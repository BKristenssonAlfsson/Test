package message;

import com.test.demo.rabbit.RabbitMQSender;
import config.TestContainersConfig;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
public class MessageControllerTest extends TestContainersConfig {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Autowired
    RabbitMQSender rabbitMQSender = new RabbitMQSender(rabbitTemplate);
    private static final Logger logger = LoggerFactory.getLogger(MessageControllerTest.class);

    @Test
    @Order(1)
    void containersAreShared() {
        logger.info("sqlcontianers are working");
        sqlContainer.isCreated();
        rabbitMQContainer.isRunning();
    }

    @Test
    @Order(2)
    void insertANewMessageInPsql() throws Exception {
        logger.info("InsertNewMessage");
        mockMvc.perform(post("/")
                .headers(getHttpHeaders())
                .content("{\"message\":\"aMessageToTestContainer\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        sleep();
    }

    private void sleep() throws InterruptedException {
        Thread.sleep(60000);
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Content-Type", "application/json");

        return httpHeaders;
    }
}
