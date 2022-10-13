package badblues.timetracker.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "task")
public class Task {

    
    @Id
    @SequenceGenerator( 
        name = "task_sequence",
        sequenceName = "task_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "task_sequence"
    )

    @Column(updatable = false)
    private long id;

    private String taskName;
    private String taskDescription;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime creationDate = LocalDateTime.now();

    @ManyToOne
    @JsonIgnore
    @JoinColumn(
        name="employee_id",
        nullable=true,
        foreignKey = @ForeignKey(name="employee_id_fk")
        )
    private Employee employee;

    public Task() {}

    public Task(String taskName, String taskDescription) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
    }

    public long getId() {
        return id;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    } 

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}