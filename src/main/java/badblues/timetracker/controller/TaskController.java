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
import badblues.timetracker.exception.CustomException;

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
    public Task getTask(@PathVariable("taskId") Long id) throws CustomException {
        Task task = taskService.getTask(id);
        if (task == null)
            throw new CustomException("Task wasn't found.");
        return task;
    }

    @PostMapping(value="employee/{employeeId}/task")
    public Employee postTask(@PathVariable("employeeId") Long employeeId, @RequestBody Task task) throws CustomException {
        Employee employee = employeeService.getEmployee(employeeId);
        if (employee == null)
            throw new CustomException("Employee wasn't found.");
        for (Task tsk : taskService.getTasks())
            if (task.getId() == tsk.getId())
                throw new CustomException("Task id is already taken.");
        employee.getTasks().add(task);
        task.setEmployee(employee);
        taskService.save(task);
        return employeeService.save(employee);
    }

    @PutMapping(value="task/{taskId}")
    public Task editTask(@PathVariable("taskId") Long id, @RequestBody Task task) throws CustomException{
        Task editedTask = taskService.editTask(task, id);
        if (editedTask == null)
            throw new CustomException("Task wasn't found");
        return editedTask;
    }    

    @DeleteMapping(value="task/{taskId}")
    public void editTask(@PathVariable("taskId") Long id) throws CustomException {
        for (Task task : taskService.getTasks())
            if (task.getId() == id) {
                taskService.deleteTask(id);
                return;
            }
        throw new CustomException("Task wasn't found");
    }

    @GetMapping(value="task/{taskId}/start")
    public Task startTask(@PathVariable("taskId") Long id) throws CustomException {
        Task startedTask = taskService.startTask(id);
        if (startedTask == null)
            throw new CustomException("Task wasn't found");
        return startedTask;
    }

    @GetMapping(value="task/{taskId}/end")
    public Task endTask(@PathVariable("taskId") Long id) throws CustomException {
        Task endedTask = taskService.endTask(id);
        if (endedTask == null) 
            throw new CustomException("Task wasn't found");
        return endedTask;
    }
}