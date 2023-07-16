package main;

import jakarta.validation.Valid;
import main.model.Task;
import main.model.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@Validated
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(value = "/tasks", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> createTask(@Valid @RequestBody Task taskData) {

        Task task = new Task(taskData.getTitle(), taskData.getDescription());
        taskRepository.save(task);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable int id) {
        Optional<Task> taskOptional = taskRepository.findById(id);

        return taskOptional.map(task -> {
            Task taskResponse = new Task(task.getTitle(), task.getDescription());
            taskResponse.setId(task.getId());
            return ResponseEntity.ok(taskResponse);
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getTaskList() {
        List<Task> tasks = taskRepository.findAll(Sort.by(Sort.Direction.ASC, "creationDate"));
        return ResponseEntity.ok(tasks);
    }

    @PatchMapping("/tasks/{id}")
    public ResponseEntity<Void> editTask(@PathVariable int id, @RequestBody Task taskData) {
        Optional<Task> taskOptional = taskRepository.findById(id);

        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();

            // Используем ModelMapper для копирования значений из taskData в task
            modelMapper.map(taskData, task);

            taskRepository.save(task);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> removeTask(@PathVariable int id) {
        // Проверяем, существует ли задача с указанным ID в базе данных
        if (taskRepository.existsById(id)) {
            // Если задача существует, удаляем ее из базы
            taskRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // Возвращаем код 204 (No Content) в случае успешного удаления
        } else {
            // Если задача не найдена, возвращаем код 404 (Not Found)
            return ResponseEntity.notFound().build();
        }
    }

}
