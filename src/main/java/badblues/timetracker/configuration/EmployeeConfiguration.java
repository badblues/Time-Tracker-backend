package badblues.timetracker.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import java.util.List;
import badblues.timetracker.model.Employee;
import badblues.timetracker.repository.EmployeeRepository;


@Configuration
public class EmployeeConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository repository) {
        return args -> {

            Employee Boba = new Employee("Boba");
            Employee Biba = new Employee("Biba");

            repository.saveAll(List.of(Boba, Biba));

        }; 
    }

}