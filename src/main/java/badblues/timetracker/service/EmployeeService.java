    package badblues.timetracker.service;

import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import badblues.timetracker.model.Employee;
import badblues.timetracker.repository.EmployeeRepository;

@Service
public class EmployeeService {
    
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployee(String name) {
        System.out.println("OKAYYYYYYYYYYYYYYY " + name);
        List<Employee> list = employeeRepository.findAll();
        for (int i = 0; i < list.size(); i++) {
            System.out.println("testin " + list.get(i).getName());
            if (list.get(i).getName().equals(name))
                return list.get(i);
        }
        return null;
    }

}