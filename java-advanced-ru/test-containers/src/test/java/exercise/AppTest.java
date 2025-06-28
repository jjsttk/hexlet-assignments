package exercise;

import exercise.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import org.springframework.http.MediaType;

import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.junit.jupiter.Container;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc

// BEGIN
@Testcontainers
@Transactional
// END
public class AppTest {

    @Autowired
    private MockMvc mockMvc;

    // BEGIN
    @Container
    private static final PostgreSQLContainer<?> db = new PostgreSQLContainer<>("postgres")
            .withDatabaseName("test")
            .withUsername("sa")
            .withPassword("sa")
            .withInitScript("init.sql");
    @Autowired
    private PersonRepository personRepository;

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        // Устанавливаем URL базы данных
        registry.add("spring.datasource.url", db::getJdbcUrl);
        // Имя пользователя и пароль для подключения
        registry.add("spring.datasource.username", db::getUsername);
        registry.add("spring.datasource.password", db::getPassword);
        // Эти значения приложение будет использовать при подключении к базе данных
    }

    @Test
    void testShowAll() throws Exception {
        var response = mockMvc.perform(get("/people"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentAsString()).contains(("John"));
        assertThat(response.getContentAsString()).contains(("Jack"));
    }

    @Test
    void testDelete() throws Exception {
        var id = personRepository.findById(1).getId();
        var response = mockMvc.perform(delete("/people/{id}", id)).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(personRepository.findById(1)).isEqualTo(null);
    }

    @Test
    void testUpdate() throws Exception {
        var person = personRepository.findById(1);
        var oldName = person.getFirstName();
        var newName = "Kris";
        person.setFirstName(newName);

        var resp = mockMvc.perform(patch("/people/{id}", person.getId())
                .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(person)))
                .andReturn().getResponse();
        assertThat(resp.getStatus()).isEqualTo(200);
    }

    // END

    @Test
    void testCreatePerson() throws Exception {
        MockHttpServletResponse responsePost = mockMvc
            .perform(
                post("/people")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"firstName\": \"Jackson\", \"lastName\": \"Bind\"}")
            )
            .andReturn()
            .getResponse();

        assertThat(responsePost.getStatus()).isEqualTo(200);

        MockHttpServletResponse response = mockMvc
            .perform(get("/people"))
            .andReturn()
            .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(response.getContentAsString()).contains("Jackson", "Bind");
    }
}
