package jp.co.topu.backend.user.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import jp.co.topu.backend.user.adapter.in.web.in.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Testcontainers
class SignupControllerTest {

    private static final String USERNAME = "topu_backend";
    private static final String PASSWORD = "topu_backend";
    private static final String DATABASE = "mysql_testcontainer";

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @ClassRule
    @Container
    public static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0")
            .withUsername(USERNAME)
            .withPassword(PASSWORD)
            .withDatabaseName(DATABASE);

    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.url", () -> mySQLContainer.getJdbcUrl());
        dynamicPropertyRegistry.add("spring.datasource.username", () -> USERNAME);
        dynamicPropertyRegistry.add("spring.datasource.password", () -> PASSWORD);
        dynamicPropertyRegistry.add("spring.jpa.hibernate.ddl-auto", () -> "create");
    }

    @Test
    void signup() throws Exception {
        // given
        var request = new SignupRequest(
                "Kopher",
                "Backend Engineer",
                3,
                List.of("Spring", "Kotlin", "Java")
        );
        var body = objectMapper.writeValueAsString(request);

        // expect
        mockMvc.perform(MockMvcRequestBuilders.post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(print());
    }
}
