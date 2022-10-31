package badblues.timetracker.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import badblues.timetracker.model.Task;
import badblues.timetracker.model.Employee;
import badblues.timetracker.service.TaskService;
import badblues.timetracker.service.EmployeeService;

@RestController
@RequestMapping(value = "timetracker")
public class TaskController {

    private final TaskService taskService;
    private final EmployeeService employeeService;

    @Autowired
    public TaskController (TaskService taskService, EmployeeService employeeService) {
        this.taskService = taskService;
        this.employeeService = employeeService;
    }

    @GetMapping(value="task/all")
    public List<Task> getTasks(
            @RequestParam(name="name", required=false) String name,
            @RequestParam(name="start",required=false) String startDate,
            @RequestParam(name="end",required=false) String endDate) {
        if (name != null) {
            Task task = taskService.getTask(name);
            return task != null ? List.of (task) : null;
        }
        if (startDate != null && endDate != null) {
            LocalDateTime st = LocalDateTime.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            LocalDateTime end = LocalDateTime.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            return taskService.getTask(st, end);
        }
        return taskService.getTasks();
    }

    @GetMapping(path="task/{taskId}")
    public Task getTask(@PathVariable("taskId") Long id) {
        return taskService.getTask(id);
    }

    @PostMapping(value="employee/{employeeId}/task")
    public Employee postTask(@PathVariable("employeeId") Long employeeId, @RequestBody Task task) {
        Employee employee = employeeService.getEmployee(employeeId);
        employee.getTasks().add(task);
        task.setEmployee(employee);
        taskService.save(task);
        return employeeService.save(employee);
    }

    @PutMapping(value="task/{taskId}")
    public Task editTask(@PathVariable("taskId") Long id, @RequestBody Task task) {
        return taskService.editTask(task, id);
    }    

    @DeleteMapping(value="task/{taskId}")
    public void editTask(@PathVariable("taskId") Long id) {
        taskService.deleteTask(id);
    }

    @GetMapping(value="task/{taskId}/start")
    public Task startTask(@PathVariable("taskId") Long id) {
        return taskService.startTask(id);
    }

    @GetMapping(value="task/{taskId}/end")
    public Task endTask(@PathVariable("taskId") Long id) {
        return taskService.endTask(id);
    }
}