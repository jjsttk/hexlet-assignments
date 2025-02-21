package exercise.controller;

import java.util.List;

import exercise.dto.TaskCreateDTO;
import exercise.dto.TaskDTO;
import exercise.dto.TaskUpdateDTO;
import exercise.mapper.TaskMapper;
import exercise.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.TaskRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    private static final Logger log = LoggerFactory.getLogger(TasksController.class);
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserRepository userRepository;

    public TasksController(TaskRepository taskRepository,
                           TaskMapper taskMapper,
                           UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.userRepository = userRepository;
    }

    // BEGIN

    @GetMapping(path = "")
    public List<TaskDTO> showAll() {
        return taskRepository.findAll().stream()
                .map(taskMapper::map)
                .toList();
    }

    @GetMapping(path = "{id}")
    public TaskDTO show(@PathVariable Long id) {
        return taskMapper.map(taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found")));
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO create(@RequestBody @Valid TaskCreateDTO createDTO) {
        var view = taskMapper.map(createDTO);
        return taskMapper.map(taskRepository.save(view));
    }

    @PutMapping(path = "{id}")
    public TaskDTO update(@PathVariable Long id,
                          @RequestBody @Valid TaskUpdateDTO updateDTO) {
        var model = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));
        log.info("Task исполнитель: {}, update исполнитель: {}", model.getAssignee().getId(), updateDTO.getAssigneeId());
        taskMapper.update(updateDTO, model);
        
        var assignee = userRepository.findById(updateDTO.getAssigneeId())
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + updateDTO.getAssigneeId() + " not found"));
        model.setAssignee(assignee);

        taskRepository.save(model);
        return taskMapper.map(model);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable Long id) {
        var model = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));
        taskRepository.delete(model);
    }
    // END
}
