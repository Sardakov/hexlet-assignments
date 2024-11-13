package exercise.controller;

import exercise.exception.ResourceNotFoundException;
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

import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


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
    public void testShowTaskById() throws Exception {
        Task task = new Task();
        task.setTitle(faker.lorem().sentence());
        task.setDescription(faker.lorem().paragraph());
        taskRepository.save(task);

        var result = mockMvc.perform(get("/tasks/" + task.getId()))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        var returnedTask = om.readValue(body, Task.class);

        assertThat(returnedTask.getId()).isEqualTo(task.getId());
        assertThat(returnedTask.getTitle()).isEqualTo(task.getTitle());
        assertThat(returnedTask.getDescription()).isEqualTo(task.getDescription());
        assertThat(returnedTask.getCreatedAt()).isNotNull();
        assertThat(returnedTask.getUpdatedAt()).isNotNull();


        mockMvc.perform(get("/tasks/" + (task.getId() + 1)))
                .andExpect(status().isNotFound())
                .andExpect(resultException ->
                        assertThat(resultException.getResolvedException())
                                .isInstanceOf(ResourceNotFoundException.class)
                                .hasMessageContaining("Task with id " + (task.getId() + 1) + " not found")
                );
    }

    @Test
    public void testCreate() throws Exception {
        var task = new Task();
        task.setTitle(faker.lorem().sentence());
        task.setDescription(faker.lorem().paragraph());

        var request = mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(task)))
                .andExpect(status().isCreated())
                .andReturn();

        var responseBody = request.getResponse().getContentAsString();
        Task returnedTask = om.readValue(responseBody, Task.class);

        assertThat(returnedTask.getId()).isNotNull();
        assertThat(returnedTask.getTitle()).isEqualTo(task.getTitle());
        assertThat(returnedTask.getDescription()).isEqualTo(task.getDescription());
        assertThat(returnedTask.getCreatedAt()).isNotNull();
        assertThat(returnedTask.getUpdatedAt()).isNotNull();

        assertThat(taskRepository.findById(returnedTask.getId())).isPresent();
    }

    @Test
    public void testUpdate() throws Exception {
        var task = new Task();
        task.setTitle(faker.lorem().sentence());
        task.setDescription(faker.lorem().paragraph());
        taskRepository.save(task);

        var task2 = new Task();
        task2.setTitle("task");
        task2.setDescription("description");

        var request = mockMvc.perform(put("/tasks/{id}", task.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(task2)))
                .andExpect(status().isOk())
                .andReturn();

        var responseBody = request.getResponse().getContentAsString();
        Task returnedTask = om.readValue(responseBody, Task.class);

        assertThat(returnedTask.getId()).isNotNull();
        assertThat(returnedTask.getTitle()).isEqualTo(task2.getTitle());
        assertThat(returnedTask.getDescription()).isEqualTo(task2.getDescription());
        assertThat(returnedTask.getCreatedAt()).isNotNull();
        assertThat(returnedTask.getUpdatedAt()).isNotNull();
    }

    @Test
    public void testDelete() throws Exception {
        var task = new Task();
        task.setTitle(faker.lorem().sentence());
        task.setDescription(faker.lorem().paragraph());
        taskRepository.save(task);

        mockMvc.perform(delete("/tasks/{id}", task.getId()))
                .andExpect(status().isOk());

        assertThat(taskRepository.findById(task.getId())).isNotPresent();
    }
}
