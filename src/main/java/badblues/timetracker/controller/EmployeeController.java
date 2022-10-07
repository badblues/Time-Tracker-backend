package badblues.timetracker.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import badblues.timetracker.model.Employee;
import badblues.timetracker.service.EmployeeService;


@RestController
@RequestMapping(value="timetracker/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
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

}