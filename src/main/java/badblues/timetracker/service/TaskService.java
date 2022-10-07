    package badblues.timetracker.service;

import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import badblues.timetracker.model.Task;
import badblues.timetracker.repository.TaskRepository;
import badblues.timetracker.repository.EmployeeRepository;


@Service
public class TaskService {
    
    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository,
                       EmployeeRepository employeeRepository) {
        this.taskRepository = taskRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task getTask(String name) {
        List<Task> list = taskRepository.findAll();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getTaskName().equals(name))
                return list.get(i);
        }
        return null;
    }

}