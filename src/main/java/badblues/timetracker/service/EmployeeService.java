package badblues.timetracker.service;

import org.springframework.stereotype.Service;
import java.util.List;
import badblues.timetracker.model.Employee;

@Service
public class EmployeeService {
    
    public List<Employee> getEmployees() {
        return List.of(new Employee(1, "Boba"));
    }

}