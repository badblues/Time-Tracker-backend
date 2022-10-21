    package badblues.timetracker.service;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import badblues.timetracker.model.Task;
import badblues.timetracker.repository.TaskRepository;
import badblues.timetracker.repository.EmployeeRepository;


@Service
public class TaskService {
    
    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public TaskService(
            TaskRepository taskRepository,
            EmployeeRepository employeeRepository) {
        this.taskRepository = taskRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Task getTask(Long id) {
        Optional <Task> opt = taskRepository.findById(id);
        if (opt.isPresent())
            return opt.get(); 
        return null;
    }

    public Task getTask(String name) {
        List<Task> list = taskRepository.findAll();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(name))
                return list.get(i);
        }
        return null;
    }

    public List<Task> getTask(LocalDateTime st, LocalDateTime end) {
        List<Task> list = taskRepository.findAll();
        List<Task> res = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            if (task.getEndDate() != null && task.getEndDate().isAfter(st) && task.getEndDate().isBefore(end))
                res.add(task);
        }
        return res;
    }


    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public Task editTask(Task newTask, Long id) {
        Optional <Task> opt = taskRepository.findById(id);
        if (opt.isPresent()) {
            Task oldTask = opt.get();
            oldTask.setName(newTask.getName());
            oldTask.setDescription(newTask.getDescription());
            return taskRepository.save(oldTask);
        }
        return null;
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Task startTask(Long id) {
        Optional <Task> opt = taskRepository.findById(id);
        if (opt.isPresent()) {
            Task task = opt.get();
            task.setStartDate(LocalDateTime.now());
            return taskRepository.save(task);
        }
        return null;
    }

    public Task endTask(Long id) {
        Optional <Task> opt = taskRepository.findById(id);
        if (opt.isPresent()) {
            Task task = opt.get();
            task.setEndDate(LocalDateTime.now());
            task.setDuration(dateDifference(task.getStartDate(), task.getEndDate()));
            return taskRepository.save(task);
        }
        return null;
    }

    private LocalTime dateDifference(LocalDateTime start, LocalDateTime end) {

        LocalDateTime temp = LocalDateTime.from(start);

        int hours = (int)temp.until(end, ChronoUnit.HOURS );
        temp = temp.plusHours( hours );

        int minutes = (int)temp.until(end, ChronoUnit.MINUTES );
        temp = temp.plusMinutes( minutes );

        int seconds = (int)temp.until(end, ChronoUnit.SECONDS );

        return LocalTime.of(hours, minutes, seconds);
    }

}