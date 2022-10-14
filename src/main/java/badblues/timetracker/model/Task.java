package badblues.timetracker.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    private String name;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalTime duration;
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

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
 
    public String getDescription() {
        return description;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    } 

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}