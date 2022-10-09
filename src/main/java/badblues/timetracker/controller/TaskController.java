package badblues.timetracker.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import badblues.timetracker.model.Task;
import badblues.timetracker.model.Employee;
import badblues.timetracker.service.TaskService;

@RestController
@RequestMapping(value = "timetracker/task")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController (TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(value="")
    public List<Task> getTasks(@RequestParam(name="name", required=false) String name) {
        if (name != null) {
            Task task = taskService.getTask(name);
            return task != null ? List.of (task) : null;
        }
        return taskService.getTasks();
    }

    @PostMapping(value="")
    public Task postTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);       
        return createdTask; 
    }

}