package config;


import com.test.demo.TestContainersDemoApplication;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.lifecycle.Startables;

import java.util.stream.Stream;

@SpringBootTest(classes = TestContainersDemoApplication.class)
@Testcontainers
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public abstract class TestContainersConfig  {

    @Autowired
    public MockMvc mockMvc;

    @Container
    public static final RabbitMQContainer rabbitMQContainer = new RabbitMQContainer("rabbitmq:3.8-management-alpine");

    @Container
    public static PostgreSQLContainer sqlContainer = new PostgreSQLContainer("postgres:latest")
                .withDatabaseName("demo")
                .withUsername("postgres")
                .withPassword("postgres");


    @DynamicPropertySource
    static void registerProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.url",  () -> sqlContainer.getJdbcUrl());
        dynamicPropertyRegistry.add("spring.datasource.username", () -> sqlContainer.getUsername());
        dynamicPropertyRegistry.add("spring.datasource.password", () -> sqlContainer.getPassword());
        dynamicPropertyRegistry.add("spring.rabbitmq.host", rabbitMQContainer::getHost);
        dynamicPropertyRegistry.add("spring.rabbitmq.port", rabbitMQContainer::getAmqpPort);
    }

    static  {
        Startables.deepStart(Stream.of(rabbitMQContainer, sqlContainer)).join();
    }
}

