package badblues.timetracker.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import java.util.List;
import badblues.timetracker.model.Employee;
import badblues.timetracker.model.Task;
import badblues.timetracker.repository.EmployeeRepository;
import badblues.timetracker.repository.TaskRepository;


@Configuration
public class TimetrackerConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository employeeRepository, TaskRepository taskRepository) {
        return args -> {

            Employee Boba = new Employee("Boba");
            Employee Biba = new Employee("Biba");
            Task task1 = new Task("task1", "description1");
            task1.setEmployee(Boba);
            Boba.getTasks().add(task1);
            employeeRepository.saveAll(List.of(Boba, Biba));

        }; 
    }

}