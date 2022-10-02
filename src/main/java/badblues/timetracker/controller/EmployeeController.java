package badblues.timetracker.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping
    public List<Employee> hello() {
        return employeeService.getEmployees();
    }

}