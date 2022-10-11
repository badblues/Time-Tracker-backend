package badblues.timetracker.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import badblues.timetracker.model.Employee;
import badblues.timetracker.model.Task;
import badblues.timetracker.service.EmployeeService;
import badblues.timetracker.service.TaskService;


@RestController
@RequestMapping(value="timetracker/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final TaskService taskService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, TaskService taskService) {
        this.employeeService = employeeService;
        this.taskService = taskService;
    }

    @GetMapping(value="all")
    public List<Employee> getEmployees(@RequestParam(name="name", required=false) String name) {
        if (name != null) {
            Employee emp = employeeService.getEmployee(name);
            return emp != null ? List.of(emp) : null;
        }
        return employeeService.getEmployees();
    }

    @GetMapping(path="{employeeId}")
    public Employee getEmployee(@PathVariable("employeeId") Long id) {
        return employeeService.getEmployee(id); 
    }

    @PostMapping(value="")
    public Employee postEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.createEmployee(employee);
        return createdEmployee; 
    }


    @PostMapping(value="{employeeId}/tasks/{taskId}")
    public Employee postTask(@PathVariable("employeeId") Long employeeId, @PathVariable("taskId") Long taskId) {
        Employee employee = employeeService.getEmployee(employeeId);
        Task task = taskService.getTask(taskId);
        employee.getTasks().add(task);
        task.setEmployee(employee);
        taskService.save(task);
        System.out.println("\n\n\n" + employee.getTasks() + "\n\n\n");
        return employeeService.save(employee);
    }

}