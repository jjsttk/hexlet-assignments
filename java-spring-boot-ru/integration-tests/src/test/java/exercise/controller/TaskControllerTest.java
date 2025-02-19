package exercise.controller;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// BEGIN
@Log4j2
@AutoConfigureMockMvc
@SpringBootTest
// END
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private Faker getFaker;


    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    // BEGIN
    @Test
    public void testGetCurrentTask() throws Exception {
        var task = generateTask();;
        taskRepository.save(task);

        var result = mockMvc.perform(get("/tasks/" + task.getId()))
                .andExpect(status().isOk())
                .andReturn();
        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isEqualTo(om.writeValueAsString(task));
    }

    @Test
    public void addTaskTest() throws Exception {
        var task = generateTask();
        var result = mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(task)))
                .andExpect(status().isCreated()).andReturn();
        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isEqualTo(om.writeValueAsString(
                taskRepository.findByTitle(task.getTitle())
                .get()));
    }

    @Test
    public void updateTaskTest() throws Exception {
        var task = generateTask();
        var id = taskRepository.save(task).getId();
        task.setTitle("PutTest");
        task.setDescription("Putted description for test");

        var resultBeforePut = mockMvc.perform(get("/tasks/" + id))
                .andExpect(status().isOk())
                .andReturn();
        var bodyBeforePut = resultBeforePut.getResponse().getContentAsString();

        var resultAfterPut = mockMvc.perform(put("/tasks/" +id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(task)))
                .andExpect(status().isOk())
                .andReturn();
        var bodyAfterPut = resultAfterPut.getResponse().getContentAsString();
        assertThatJson(bodyAfterPut).isNotEqualTo(bodyBeforePut);
        assertThat(bodyBeforePut).doesNotContain("PutTest");
        assertThat(bodyAfterPut).contains("PutTest");
        var model = taskRepository.findById(id).get();
        var modelFromJson = om.readValue(bodyAfterPut, Task.class);
        assertThat(model.getTitle()).isEqualTo(modelFromJson.getTitle());
        assertThat(model.getDescription()).isEqualTo(modelFromJson.getDescription());
    }

    @Test
    public void deleteTaskTest() throws Exception {
        var taskForDelete = taskRepository.save(generateTask());
        var id = taskForDelete.getId();

        assertThat(taskRepository.findById(id)).isNotEmpty();
        var resBeforeDelete = mockMvc.perform(get("/tasks/" + id))
                .andExpect(status().isOk())
                .andReturn();

        var resAfterDelete = mockMvc.perform(delete("/tasks/" + id))
                .andExpect(status().isOk())
                .andReturn();

        var noContentTest = mockMvc.perform(get("/tasks/" + id))
                .andExpect(status().isNotFound())
                .andReturn();

        assertThat(taskRepository.findById(id)).isEmpty();

    }

    @AfterEach
    public void removeTestData() {
        var mbTask = taskRepository.findByTitle(generateTask().getTitle());
        mbTask.ifPresent(task -> taskRepository.delete(task));
    }

    private Task generateTask() {
        return Instancio.of(Task.class)
                .ignore(Select.field("id"))
                .supply(Select.field("title"), () -> faker.lorem().word())
                .supply(Select.field("description"), () -> faker.lorem().sentence())
                .create();
    }
    // END
}
