    package badblues.timetracker.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
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
        List<Employee> list = employeeRepository.findAll();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(name))
                return list.get(i);
        }
        return null;
    }

    public Employee getEmployee(Long id) {
        Optional <Employee> opt = employeeRepository.findById(id);
        if (opt.isPresent())
            return opt.get(); 
        return null;
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee editEmployee(Employee newEmployee, Long id) {
        Optional <Employee> opt = employeeRepository.findById(id);
        if (opt.isPresent()) {
            Employee oldEmployee = opt.get();
            oldEmployee.setName(newEmployee.getName());
            return employeeRepository.save(oldEmployee);
        }
        return null;
    }

}