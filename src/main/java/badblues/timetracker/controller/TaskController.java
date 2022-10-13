package badblues.timetracker.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import badblues.timetracker.model.Task;
import badblues.timetracker.model.Employee;
import badblues.timetracker.service.TaskService;
import badblues.timetracker.service.EmployeeService;

@RestController
@RequestMapping(value = "timetracker/task")
public class TaskController {

    private final TaskService taskService;
    private final EmployeeService employeeService;

    @Autowired
    public TaskController (TaskService taskService, EmployeeService employeeService) {
        this.taskService = taskService;
        this.employeeService = employeeService;
    }

    @GetMapping(value="all")
    public List<Task> getTasks(@RequestParam(name="name", required=false) String name) {
        if (name != null) {
            Task task = taskService.getTask(name);
            return task != null ? List.of (task) : null;
        }
        return taskService.getTasks();
    }

    @GetMapping(path="{taskId}")
    public Task getTask(@PathVariable("taskId") Long id) {
        return taskService.getTask(id);
    }

    @PostMapping(value="{employeeId}/task")
    public Employee postTask(@PathVariable("employeeId") Long employeeId, @RequestBody Task task) {
        Employee employee = employeeService.getEmployee(employeeId);
        employee.getTasks().add(task);
        task.setEmployee(employee);
        taskService.save(task);
        return employeeService.save(employee);
    }

    @PutMapping(value="{taskId}")
    public Task editTask(@PathVariable("taskId") Long id, @RequestBody Task task) {
        return taskService.editTask(task, id);
    }    

    @DeleteMapping(value="{taskId}")
    public void editTask(@PathVariable("taskId") Long id) {
        taskService.deleteTask(id);
    }
}