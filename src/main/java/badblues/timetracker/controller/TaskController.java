package badblues.timetracker.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import badblues.timetracker.model.Task;
import badblues.timetracker.model.Employee;
import badblues.timetracker.service.TaskService;

@RestController
@RequestMapping(value = "timetracker")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController (TaskService taskService) {
        this.taskService = taskService;
    }

    // @GetMapping(value="{id}/task")
    // public List<Employee> getEmployees(@RequestParam(name="name", required=false) String name) {
    //     if (name != null) {
    //         Employee emp = taskService.getEmployee(name);
    //         return emp != null ? List.of(emp) : null;
    //     }
    //     return taskService.getTasks();
    // }

    @PostMapping(value="task")
    public Task postTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);       
        return createdTask; 
    }

}