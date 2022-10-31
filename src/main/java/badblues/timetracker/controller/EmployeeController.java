package badblues.timetracker.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import badblues.timetracker.model.Employee;
import badblues.timetracker.model.Task;
import badblues.timetracker.service.EmployeeService;
import badblues.timetracker.service.TaskService;
import badblues.timetracker.exception.CustomException;


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
    public List<Employee> getEmployees(@RequestParam(name="name", required=false) String name) throws CustomException {
        if (name != null) {
            Employee emp = employeeService.getEmployee(name);
            return emp != null ? List.of(emp) : null;
        }
        return employeeService.getEmployees();
    }

    @GetMapping(value="{employeeId}")
    public Employee getEmployee(@PathVariable("employeeId") Long id) throws CustomException {
        Employee employee = employeeService.getEmployee(id);
        if (employee == null)
            throw new CustomException("Employee wasn't found.");
        return employee; 
    }

    @PostMapping(value="")
    public Employee postEmployee(@RequestBody Employee employee) throws CustomException {
        for (Employee emp : employeeService.getEmployees())
            if (emp.getId() == employee.getId())
                throw new CustomException("Employee id is already taken.");
        Employee createdEmployee = employeeService.save(employee);
        return createdEmployee; 
    }

    @PutMapping(value="{employeeId}")
    public Employee putEmpoyee(@PathVariable("employeeId") Long id, @RequestBody Employee newEmployee) throws CustomException {
        Employee editedEmployee = employeeService.editEmployee(newEmployee, id);
        if (editedEmployee == null)
            throw new CustomException("Employee wasn't found.");
        return editedEmployee;
    }

    @DeleteMapping(value="{employeeId}")
    public void deleteEmployee(@PathVariable("employeeId") Long id) throws CustomException {
        for (Employee emp : employeeService.getEmployees())
            if (emp.getId() == id) {
                employeeService.deleteEmployee(id);
                return;
            }
        throw new CustomException("Employee wasn't found.");
    }

}