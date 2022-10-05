package badblues.timetracker.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import badblues.timetracker.model.Employee;
import badblues.timetracker.service.EmployeeService;


@RestController
@RequestMapping("timetracker")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("employee")
    public List<Employee> getEmployees(@RequestParam(name="name", required=false) String name) {
        if (name != null) {
            Employee emp = employeeService.getEmployee(name);
            return emp != null ? List.of(emp) : null;
        }
        return employeeService.getEmployees();
    }

}