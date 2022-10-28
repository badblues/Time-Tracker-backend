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

            Employee emp1 = new Employee("Andrew");
            Employee emp2 = new Employee("Vladimir");
            Task task1 = new Task("task_1", "task_1_description");
            task1.setEmployee(emp1);
            emp1.getTasks().add(task1);
            employeeRepository.saveAll(List.of(emp1, emp2));

        }; 
    }

}